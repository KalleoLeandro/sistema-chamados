import { Request, Response } from "express";
import { LoginRequest } from "../models/LoginRequest";
import { decriptografia, logger } from "../utils/Utils";
import * as LoginService from '../services/LoginService';

const log = logger;

export const validarLogin = async (req: Request, res: Response) => {
    try {
        const login: LoginRequest = JSON.parse(decriptografia(req.body.hash));        
        const retorno: any = await LoginService.validarLogin(login);
        if (retorno.status === 200) {
            res.status(retorno.status).json(retorno);
        } else {
            res.status(retorno.status).json(retorno.mensagem);
        }
    } catch (e) {
        log.error(e);
        res.status(500).json(`Erro: ${e}`);
    }
}