import { Router } from "express";
import * as TesteController from '../controllers/TesteController';
import * as LoginController from '../controllers/LoginController';
import * as UtilsController from '../controllers/UtilsController';
import * as UsuarioController from '../controllers/UsuarioController';
import * as ProdutoController from '../controllers/ProdutoController';
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
rotas.get('/listar-usuarios', Middleware.verificaTokenValido, UsuarioController.listaUsuarios);
rotas.get('/buscar-por-id/:id', Middleware.verificaTokenValido, UsuarioController.buscarUsuarioPorId);
rotas.delete('/excluir-usuario-por-id/:id', Middleware.verificaTokenValido, UsuarioController.excluirUsuarioPorId);

//Rotas Produto
rotas.post('/gravar-produto', Middleware.verificaTokenValido, ProdutoController.gravarProduto);
rotas.get('/listar-produtos', Middleware.verificaTokenValido, ProdutoController.listarProdutos);
rotas.get('/buscar-produto-por-id/:id', Middleware.verificaTokenValido, ProdutoController.buscarProdutoPorId);
rotas.delete('/excluir-produto-por-id/:id', Middleware.verificaTokenValido, ProdutoController.excluirProdutoPorId);

export default rotas;