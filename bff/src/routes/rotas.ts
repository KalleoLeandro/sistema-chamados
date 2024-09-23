import { Router } from "express";
import * as TesteController from '../controllers/TesteController';
import * as LoginController from '../controllers/LoginController';
import * as UtilsController from '../controllers/UtilsController';
import * as UsuarioController from '../controllers/UsuarioController';
import * as Middleware from '../middlewares/middleware';

const rotas = Router();

//Rotas Teste
rotas.get('/teste', TesteController.teste);

//Rotas Login
rotas.post('/login', LoginController.validarLogin);
rotas.post('/valida-token', LoginController.validarToken);

//Rotas Utils
rotas.post('/valida-data-nascimento', Middleware.verificaTokenValido, UtilsController.validarDataNascimento);
rotas.post('/valida-cpf', Middleware.verificaTokenValido, UtilsController.validarCpf);

//Rotas Usuario
rotas.post('/gravar-usuario', Middleware.verificaTokenValido, UsuarioController.gravarUsuario);

export default rotas;