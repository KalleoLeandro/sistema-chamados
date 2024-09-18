import dotenv from 'dotenv';
import { LoginRequest } from '../models/LoginRequest';
import { CustomError } from '../exceptions/CustomError';
import { LoginResponse } from '../models/LoginResponse';
import { response } from 'express';

dotenv.config();

export const validarLogin = async (body: LoginRequest) => {
    try {
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        };

        let response:LoginResponse = await fetch(`${process.env.HOST_MS}/login/validar-login`, options).then((res) => res.json());
        const statusCode = response.status;
        let retorno: LoginResponse;
    
        if (statusCode === 200) {
            //const getTokenResponse = await fetch(`${process.env.HOST_MS}/login/gerar-token`, options).then((res) => res.json());
            retorno = {
                token: response.token,
                expiration: response.expiration,
                userName: response.userName,
                status: 200
            };
        } else if (statusCode === 401) {
            // Obtendo a mensagem de erro do corpo da resposta JSON            
            retorno = {
                status: 401,
                message: response.message // Supondo que o servidor retorne a mensagem de erro em um campo "message" no corpo da resposta JSON
            };
        } else {
            
            retorno = {
                status: 500,
                message: response.message // Supondo que o servidor retorne a mensagem de erro em um campo "message" no corpo da resposta JSON
            };
        }
        return retorno;
    } catch (error: any) {
        throw new CustomError(error.message, 500);
    }
}