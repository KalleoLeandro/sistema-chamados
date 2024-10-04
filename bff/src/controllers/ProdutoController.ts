import { Request, Response } from "express";
import { logger } from "../utils/Utils";
import { Produto } from "../models/Produto";
import * as ProdutoService from '../services/ProdutoService';


const log = logger;

export const gravarProduto = async (req: Request, res: Response) => {
    try{
        const body:Produto = req.body;
        const retorno:number = await ProdutoService.gravarProduto(body);
        res.status(retorno).end();
    }catch(error) {
        log.error(`Erro: ${error}`);
        res.status(500).json(`Erro: ${error}`);
    }    
}

export const listarProdutos = async (req:Request, res:Response) =>{
    try{
        const retorno: any = await ProdutoService.listarProdutos();
        res.status(retorno.status).json(retorno.body);
    }catch(error){
        log.error(`Erro: ${error}`);
        res.status(500).json(`Erro: ${error}`);
    }
}

export const buscarProdutoPorId = async (req:Request, res:Response) =>{
    try{        
        const retorno: any  = await ProdutoService.buscarProdutoPorId(req.params.id);
        res.status(200).json(retorno);
    }catch(error){
        log.error(`Erro: ${error}`);
        res.status(500).json(`Erro: ${error}`);
    }
}

export const excluirProdutoPorId = async(req:Request, res: Response)=>{
    try{        
        await ProdutoService.excluirProdutoPorId(req.params.id);
        res.status(204).end();        
    } catch (error) {
        log.error(`Erro: ${error}`);
        res.status(500).json(`Erro: ${error}`);
    } 
}