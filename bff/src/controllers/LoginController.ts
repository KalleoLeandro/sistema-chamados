import { Request, Response } from "express";
import { LoginRequest } from "../models/LoginRequest";
import { decriptografia, logger } from "../utils/Utils";
import * as LoginService from '../services/LoginService';
import { TokenRequest } from "../models/TokenRequest";

const log = logger;

export const validarLogin = async (req: Request, res: Response) => {
    try {
        const login: LoginRequest = JSON.parse(decriptografia(req.body.hash));
        log.info("Executando a LoginService.validarLogin")     
        const retorno: any = await LoginService.validarLogin(login);
        if (retorno.status === 200) {
            res.status(retorno.status).json(retorno);
        } else {
            res.status(retorno.status).json(retorno.mensagem);
        }
    } catch (error) {
        log.error(`Erro: ${error}`);
        res.status(500).json(`Erro: ${error}`);
    }
}

export const validarToken = async (req:Request, res: Response) =>{
    try{
        const body:TokenRequest = req.body;
        log.info("Executando a LoginService.validarToken")
        const retorno: any = await LoginService.validarToken(body);        
        res.status(200).json(retorno);
    }catch(error){
        log.error(`Erro ao validar o token`);
        res.status(500).json(`Erro: ${error}`);
    }
}