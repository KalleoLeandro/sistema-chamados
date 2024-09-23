import { Request, Response } from "express";
import { logger } from "../utils/Utils";
import { Usuario } from "../models/Usuario";
import * as UsuarioService from '../services/UsuarioService';
import { UsuarioRequest } from "../models/UsuarioRequest";

const log = logger;

export const gravarUsuario = async (req:Request, res:Response) => {
    try{
        const usuario:UsuarioRequest = req.body;
        const retorno:number = await UsuarioService.gravarUsuario(usuario);
        res.status(retorno).end();
    } catch (error) {
        log.error(`Erro: ${error}`);
        res.status(500).json(`Erro: ${error}`);
    }   

}