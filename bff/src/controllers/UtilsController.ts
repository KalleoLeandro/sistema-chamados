import { Request, Response } from "express";
import { logger } from "../utils/Utils";
import * as UtilsService from '../services/UtilsService';


const log = logger;

export const validarDataNascimento = async(req:Request, res:Response) =>{
    const dataNascimento:string = req.body.dataNascimento;    
    try{        
        const retorno = await UtilsService.validarDataNascimento(dataNascimento);
        res.status(retorno.status).end();
    }catch(error){
        log.error(`Erro: ${error}`);
        res.status(500).json(`Erro: ${error}`);
    }
}