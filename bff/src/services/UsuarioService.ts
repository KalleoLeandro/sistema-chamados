import { CustomError } from "../exceptions/CustomError";
import { Usuario } from "../models/Usuario";
import { UsuarioRequest } from "../models/UsuarioRequest";
import { logger } from "../utils/Utils";
import dotenv from 'dotenv';

dotenv.config();

const log = logger;

export const gravarUsuario = async (body:UsuarioRequest) =>{
    try {
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        };

        log.info("Executando a /api/usuarios/gravar-usuario");
        let response:any = await fetch(`${process.env.HOST_MS}/usuarios/gravar-usuario`, options);        
        return response.status;
    } catch (error: any) {
        log.error(`Erro: ${error}`);
        throw new CustomError(error.message, 500);
    }
}