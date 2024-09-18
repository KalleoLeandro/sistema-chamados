import { Router } from "express";
import * as TesteController from '../controllers/TesteController';
import * as LoginController from '../controllers/LoginController';

const rotas = Router();

//Rotas Teste
rotas.get('/teste', TesteController.teste);

//Rotas Login
rotas.post('/login', LoginController.validarLogin)

export default rotas;