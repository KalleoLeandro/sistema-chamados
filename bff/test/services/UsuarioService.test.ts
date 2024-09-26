
import { CustomError } from '../../src/exceptions/CustomError';
import fetch from 'node-fetch';
import fetchMock from 'jest-fetch-mock';
import { logger } from '../../src/utils/Utils';
import { buscarUsuarioPorId, excluirUsuarioPorId, gravarUsuario, listaUsuarios } from '../../src/services/UsuarioService';
import { UsuarioRequest } from '../../src/models/UsuarioRequest';
import { response } from 'express';
import { Body } from 'node-fetch';
import dotent from 'dotenv';

dotent.config();

jest.mock('node-fetch', () => ({
    __esModule: true,
    default: jest.fn()
}));

jest.mock('../../src/utils/Utils');


fetchMock.enableMocks();

let log: any;

describe('Teste do serviço gravarUsuario', () => {

    let usuario: UsuarioRequest = {
        nome: "Teste",
        cpf: "222.333.444-05",
        dataNascimento: "1990-01-01",
        sexo: "m",
        cep: "80000-000",
        rua: "Teste",
        numero: 100,
        bairro: "Teste",
        cidade: "Teste",
        uf: "Teste",
        telefone: "(11) 1111-1111",
        celular: "(11) 11111-1111",
        email: "teste@teste.com",
        login: "teste",
        senha: "123",
        perfil: "adm"
    }


    beforeEach(() => {
        log = logger;
        (log.info as jest.Mock).mockClear();
        (log.error as jest.Mock).mockClear();
    });

    it('Deve gravar um usuário e retornar 200', async () => {
        const mockResponse = new Response(
            JSON.stringify({}),
            { status: 200 }
        );

        fetchMock.mockResolvedValue(mockResponse);

        const result = await gravarUsuario(usuario);
        expect(result).toBe(200);
    });

    it('Deve lançar CustomError com status 500', async () => {
        const mockError = new Error('Erro ao gravar usuário');

        fetchMock.mockRejectedValue(mockError);

        await expect(gravarUsuario(usuario)).rejects.toThrow(CustomError);

        expect(log.error).toHaveBeenCalledWith(`Erro: ${mockError}`);
    });
});

describe('Teste do serviço listarUsuarios', () => {
    let mockUsuarios:any = [];
    beforeEach(() => {
        mockUsuarios = [{
            id: 1,
            nome: "Teste",
            cpf: "222.333.444-05",
            dataNascimento: "1990-01-01",
            sexo: "m",
            cep: "80000-000",
            rua: "Teste",
            numero: 100,
            bairro: "Teste",
            cidade: "Teste",
            uf: "Teste",
            telefone: "(11) 1111-1111",
            celular: "(11) 11111-1111",
            email: "teste@teste.com",
            login: "teste",
            senha: "123",
            perfil: "adm"
        },
        {
            id: 2,
            nome: "Teste",
            cpf: "222.333.444-05",
            dataNascimento: "1990-01-01",
            sexo: "m",
            cep: "80000-000",
            rua: "Teste",
            numero: 100,
            bairro: "Teste",
            cidade: "Teste",
            uf: "Teste",
            telefone: "(11) 1111-1111",
            celular: "(11) 11111-1111",
            email: "teste@teste.com",
            login: "teste",
            senha: "123",
            perfil: "adm"
        }];
        log = logger;
        (log.info as jest.Mock).mockClear();
        (log.error as jest.Mock).mockClear();
    });

    it('Deve retornar uma lista de usuários', async () => {
        const mockResponse = new Response(
            JSON.stringify(
                mockUsuarios
            )            
        );

        fetchMock.mockResolvedValue(mockResponse);

        const result = await listaUsuarios();

        expect(result.status).toBe(200);
        expect(result.body).toEqual(mockUsuarios);
    });

    it('Deve retornar status 500', async () => {
        fetchMock.mockRejectedValue(new Error('Erro interno'));

        await expect(listaUsuarios()).rejects.toThrow(CustomError);
    });
});

describe('Teste do serviço listarUsuarios', () => {
    let mockUsuario:any;

    beforeEach(() => {
        mockUsuario = {
            id: 2,
            nome: "Teste",
            cpf: "222.333.444-05",
            dataNascimento: "1990-01-01",
            sexo: "m",
            cep: "80000-000",
            rua: "Teste",
            numero: 100,
            bairro: "Teste",
            cidade: "Teste",
            uf: "Teste",
            telefone: "(11) 1111-1111",
            celular: "(11) 11111-1111",
            email: "teste@teste.com",
            login: "teste",
            senha: "123",
            perfil: "adm"
        };

        log = logger;
        (log.info as jest.Mock).mockClear();
        (log.error as jest.Mock).mockClear();
    });

    it('Deve retornar uma usuário de igual id fornecido', async () => {
        const mockResponse = new Response(
            JSON.stringify(
                mockUsuario
            )            
        );

        fetchMock.mockResolvedValue(mockResponse);

        const result = await buscarUsuarioPorId('2');        
        expect(result).toEqual(mockUsuario);
    });

    it('Deve retornar status 500', async () => {
        fetchMock.mockRejectedValue(new Error('Erro interno'));

        await expect(buscarUsuarioPorId('2')).rejects.toThrow(CustomError);
    });
});

describe('Teste do serviço excluirUsuarioPorId', () => {
    
    beforeEach(()=>{
        log = logger;
        (log.info as jest.Mock).mockClear();
        (log.error as jest.Mock).mockClear();
    })

    it('Deve excluir um usuário e não lançar exceção', async () => {
        const id = '123'; 
        fetchMock.mockResolvedValue(new Response(JSON.stringify({}), { status: 200 }));

        await excluirUsuarioPorId(id);
       
        expect(fetchMock).toHaveBeenCalledWith(
            `${process.env.HOST_MS}/usuarios/excluir-usuario-por-id/${id}`,
            {
                method: 'DELETE',
                headers: { 'Content-Type': 'application/json' }
            }
        );
        
        expect(log.info).toHaveBeenCalledWith("Executando a /api/usuarios/excluir-usuario-por-id");
    });

    it('Deve lançar CustomError quando houver erro', async () => {
        const id = '123';
        const mockError = new Error('Erro ao excluir usuário');
        
        fetchMock.mockRejectedValue(mockError); 

        
        await expect(excluirUsuarioPorId(id)).rejects.toThrow(CustomError);
        
        expect(log.error).toHaveBeenCalled();
    });

});

