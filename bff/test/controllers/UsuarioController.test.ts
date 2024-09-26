import { Request, Response } from "express";
import * as UsuarioService from '../../src/services/UsuarioService';
import { buscarUsuarioPorId, excluirUsuarioPorId, gravarUsuario, listaUsuarios } from "../../src/controllers/UsuarioController";
import { UsuarioRequest } from "../../src/models/UsuarioRequest";

// Mock dos módulos
jest.mock('../../src/services/UsuarioService');
jest.mock('../../src/utils/Utils');

let req: Request;
let res: Response;
describe('Testes da função gravarUsuario', () => {

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
        req = {
            headers: {
                'authorization': 'token'
            }
        } as Request;
        res = {
            status: jest.fn().mockReturnThis(),
            json: jest.fn()
        } as unknown as Response;
    });

    it('Deve retornar status 200 e gravar usuario', async () => {
        (UsuarioService.gravarUsuario as jest.Mock).mockResolvedValue(200);

        req.body = { ...usuario };

        await gravarUsuario(req, res);

        expect(res.status).toHaveBeenCalledWith(200);
    });

    it('Deve retornar status 500', async () => {
        (UsuarioService.gravarUsuario as jest.Mock).mockRejectedValue(500);

        req.body = { ...usuario };

        await gravarUsuario(req, res);

        expect(res.status).toHaveBeenCalledWith(500);
    });
});

describe('Teste da função listaUsuarios', () => {

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
        req = {
            headers: {
                'authorization': 'token'
            }
        } as Request;
        res = {
            status: jest.fn().mockReturnThis(),
            json: jest.fn()
        } as unknown as Response;
    });

    it('Deve retornar status 200 e listaUsuarios', async () => {
        (UsuarioService.listaUsuarios as jest.Mock).mockResolvedValue({ status: 200, body: [] });

        req.body = { ...usuario };

        await listaUsuarios(req, res);

        expect(res.status).toHaveBeenCalledWith(200);
        expect(res.json).toHaveBeenCalledWith({ status: 200, body: [] });
    });

    it('Deve retornar status 401', async () => {
        (UsuarioService.listaUsuarios as jest.Mock).mockResolvedValue({ status: 401 });

        req.body = { ...usuario };

        await listaUsuarios(req, res);

        expect(res.status).toHaveBeenCalledWith(401);
    });

    it('Deve retornar status 500', async () => {
        (UsuarioService.listaUsuarios as jest.Mock).mockRejectedValue(500);

        req.body = { ...usuario };

        await listaUsuarios(req, res);

        expect(res.status).toHaveBeenCalledWith(500);
    });
});


describe('Teste da função buscarUsuarioPorId', () => {
    

    let usuario: UsuarioRequest = {
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
    }

    beforeEach(() => {
        req = {
            params: {
                id: '1'
            },
            headers: {
                'authorization': 'token'
            }
        } as unknown as Request;

        res = {
            status: jest.fn().mockReturnThis(),
            json: jest.fn()
        } as unknown as Response;
    });


    it('Deve retornar status 200 e usuario', async () => {
        (UsuarioService.buscarUsuarioPorId as jest.Mock).mockResolvedValue(usuario);

        await buscarUsuarioPorId(req, res);

        expect(res.status).toHaveBeenCalledWith(200);
        expect(res.json).toHaveBeenCalledWith(usuario);
    });

    it('Deve retornar status 500', async () => {
        (UsuarioService.buscarUsuarioPorId as jest.Mock).mockRejectedValue(500);

        await buscarUsuarioPorId(req, res);

        expect(res.status).toHaveBeenCalledWith(500);        
    });
});

describe('Teste da função excluirUsuarioPorId', () => {

    beforeEach(() => {
        req = {
            params: {
                id: '1'
            },
            headers: {
                'authorization': 'token'
            }
        } as unknown as Request;

        res = {
            status: jest.fn().mockReturnThis(),
            json: jest.fn()
        } as unknown as Response;
    });

    it('Deve retornar status 200', async () => {
        (UsuarioService.excluirUsuarioPorId as jest.Mock).mockResolvedValue(204);

        await excluirUsuarioPorId(req, res);

        expect(res.status).toHaveBeenCalledWith(200);        
    });

    it('Deve retornar status 500', async () => {
        (UsuarioService.excluirUsuarioPorId as jest.Mock).mockRejectedValue(500);

        await excluirUsuarioPorId(req, res);

        expect(res.status).toHaveBeenCalledWith(500);        
    });
});


