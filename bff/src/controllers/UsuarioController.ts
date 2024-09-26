import { Request, Response } from "express";
import { logger } from "../utils/Utils";
import { Usuario } from "../models/UsuarioResponse";
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

export const listaUsuarios = async(req:Request, res: Response)=> {
    try{        
        const retorno:any = await UsuarioService.listaUsuarios();
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

export const buscarUsuarioPorId = async(req:Request, res: Response)=>{
    try{        
        const retorno:any = await UsuarioService.buscarUsuarioPorId(req.params.id);
        res.status(200).json(retorno);        
    } catch (error) {
        log.error(`Erro: ${error}`);
        res.status(500).json(`Erro: ${error}`);
    } 
}

export const excluirUsuarioPorId = async(req:Request, res: Response)=>{
    try{        
        await UsuarioService.excluirUsuarioPorId(req.params.id);
        res.status(200).end();        
    } catch (error) {
        log.error(`Erro: ${error}`);
        res.status(500).json(`Erro: ${error}`);
    } 
}