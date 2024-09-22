import { Router } from "express";
import * as TesteController from '../controllers/TesteController';
import * as LoginController from '../controllers/LoginController';
import * as UtilsController from '../controllers/UtilsController';
import * as Middleware from '../middlewares/middleware';

const rotas = Router();

//Rotas Teste
rotas.get('/teste', TesteController.teste);

//Rotas Login
rotas.post('/login', LoginController.validarLogin);
rotas.post('/valida-token', LoginController.validarToken);

//Rotas Utils
rotas.post('/valida-data-nascimento', Middleware.verificaTokenValido, UtilsController.validarDataNascimento);

export default rotas;