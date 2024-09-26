import { CustomError } from "../exceptions/CustomError";
import { Usuario } from "../models/UsuarioResponse";
import { UsuarioRequest } from "../models/UsuarioRequest";
import { logger } from "../utils/Utils";
import dotenv from 'dotenv';

dotenv.config();

const log = logger;

export const gravarUsuario = async (body: UsuarioRequest) => {
    try {
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        };

        log.info("Executando a /api/usuarios/gravar-usuario");
        let response: any = await fetch(`${process.env.HOST_MS}/usuarios/gravar-usuario`, options);
        return response.status;
    } catch (error: any) {
        log.error(`Erro: ${error}`);
        throw new CustomError(error.message, 500);
    }
}

export const listaUsuarios = async () => {
    try {
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        log.info("Executando a /api/usuarios/listar-usuarios");
        const response: any = await fetch(`${process.env.HOST_MS}/usuarios/listar-usuarios`, options).then(res => res.json());
        return {
            status: 200,
            body: response
        }
    } catch (error: any) {
        log.error(`Erro: ${error}`);
        throw new CustomError(error.message, 500);
    }
}
export const buscarUsuarioPorId = async(id: string) => {
    try {
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        log.info("Executando a /api/usuarios/buscar-por-id");
        const response: any = await fetch(`${process.env.HOST_MS}/usuarios/buscar-por-id/${id}`, options).then(res => res.json());
        return response;        
    } catch (error: any) {
        log.error(`Erro: ${error}`);
        throw new CustomError(error.message, 500);
    }
}

export const excluirUsuarioPorId = async (id: string) => {
    try {
        const options = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        log.info("Executando a /api/usuarios/excluir-usuario-por-id");
        await fetch(`${process.env.HOST_MS}/usuarios/excluir-usuario-por-id/${id}`, options).then(res => res.json());               
    } catch (error: any) {
        log.error(`Erro: ${error}`);
        throw new CustomError(error.message, 500);
    }
}

