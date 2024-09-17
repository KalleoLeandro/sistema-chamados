import { Router } from "express";
import * as TesteController from '../controller/TesteController';

const rotas = Router();

//Rotas Teste
rotas.get('/teste', TesteController.teste);

export default rotas;