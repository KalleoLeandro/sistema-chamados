import { CustomError } from "../exceptions/CustomError";

import dotenv from 'dotenv';
import { logger } from '../utils/Utils';
import { Produto } from "../models/Produto";

dotenv.config();

const log = logger;

export const gravarProduto = async (body: Produto) => {
    try {
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        };
        log.info("Executando a /api/produtos/gravar-produto");
        let response: any = await fetch(`${process.env.HOST_MS}/produtos/gravar-produto`, options);
        return response.status;
    }catch(error:any){
        log.error(`Erro: ${error}`);
        throw new CustomError(error.message, 500);
    }
}

export const listarProdutos = async () => {
    try {
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        log.info("Executando a /api/produtos/listar-produtos");
        const response: any = await fetch(`${process.env.HOST_MS}/produtos/listar-produtos`, options).then(res => res.json());
        return {
            status: 200,
            body: response
        }
    } catch (error: any) {
        log.error(`Erro: ${error}`);
        throw new CustomError(error.message, 500);
    }
}

export const buscarProdutoPorId = async (id: string) => {
    try {
        const options = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        log.info(`Executando a /api/produtos/buscar-produto-por-id`);
        const response: any = await fetch(`${process.env.HOST_MS}/produtos/buscar-produto-por-id/${id}`, options).then(res => res.json());
        return response;
    } catch (error: any) {
        log.error(`Erro: ${error}`);
        throw new CustomError(error.message, 500);
    }  
}

export const excluirProdutoPorId = async (id: string) => {
    try {
        const options = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        log.info("Executando a /api/produtos/excluir-produto-por-id");
        await fetch(`${process.env.HOST_MS}/produtos/excluir-produto-por-id/${id}`, options);               
    } catch (error: any) {
        log.error(`Erro: ${error}`);
        throw new CustomError(error.message, 500);
    }
}
