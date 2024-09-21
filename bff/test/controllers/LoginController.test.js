"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
Object.defineProperty(exports, "__esModule", { value: true });
const LoginController_1 = require("../../src/controllers/LoginController");
const LoginService = __importStar(require("../../src/services/LoginService"));
const Utils_1 = require("../../src/utils/Utils");
// Mock dos módulos
jest.mock('../../src/services/LoginService');
jest.mock('../../src/utils/Utils');
describe('Teste da função validarLogin', () => {
    let req;
    let res;
    beforeEach(() => {
        req = {};
        res = {
            status: jest.fn().mockReturnThis(), // Mock da função status que retorna o próprio objeto
            json: jest.fn() // Mock da função json
        };
    });
    it('Deve retornar status 200 e dados do login', () => __awaiter(void 0, void 0, void 0, function* () {
        // Configurando mocks
        Utils_1.decriptografia.mockReturnValue(JSON.stringify({ userName: 'user', password: 'pass' }));
        LoginService.validarLogin.mockResolvedValue({ status: 200, token: 'token', userName: 'user', expiration: '01/01/2025' });
        req.body = { hash: 'somehash' };
        yield (0, LoginController_1.validarLogin)(req, res);
        expect(res.status).toHaveBeenCalledWith(200);
        expect(res.json).toHaveBeenCalledWith({ status: 200, token: 'token', userName: 'user', expiration: '01/01/2025' });
    }));
    it('Deve retornar status 401 e mensagem de erro', () => __awaiter(void 0, void 0, void 0, function* () {
        Utils_1.decriptografia.mockReturnValue(JSON.stringify({ userName: 'user', password: 'pass' }));
        LoginService.validarLogin.mockResolvedValue({ status: 401, mensagem: 'Credenciais inválidas' });
        req.body = { hash: 'somehash' };
        yield (0, LoginController_1.validarLogin)(req, res);
        expect(res.status).toHaveBeenCalledWith(401);
        expect(res.json).toHaveBeenCalledWith('Credenciais inválidas');
    }));
    it('Deve retornar status 500 em caso de erro', () => __awaiter(void 0, void 0, void 0, function* () {
        Utils_1.decriptografia.mockReturnValue(JSON.stringify({ userName: 'user', password: 'pass' }));
        LoginService.validarLogin.mockRejectedValue(new Error('Erro interno'));
        req.body = { hash: 'somehash' };
        yield (0, LoginController_1.validarLogin)(req, res);
        expect(res.status).toHaveBeenCalledWith(500);
        expect(res.json).toHaveBeenCalledWith('Erro: Error: Erro interno');
    }));
});
describe('Teste da função validarToken', () => {
    let req;
    let res;
    beforeEach(() => {
        req = {};
        res = {
            status: jest.fn().mockReturnThis(), // Mock da função status que retorna o próprio objeto
            json: jest.fn() // Mock da função json
        };
    });
    it('Deve retornar status 200 e dados do token', () => __awaiter(void 0, void 0, void 0, function* () {
        LoginService.validarToken.mockResolvedValue({ status: 200, valid: true });
        req.body = { token: 'validtoken' };
        yield (0, LoginController_1.validarToken)(req, res);
        expect(res.status).toHaveBeenCalledWith(200);
        expect(res.json).toHaveBeenCalledWith({ status: 200, valid: true });
    }));
    it('Deve retornar status 500 em caso de erro', () => __awaiter(void 0, void 0, void 0, function* () {
        LoginService.validarToken.mockRejectedValue(new Error('Erro interno'));
        req.body = { token: 'validtoken' };
        yield (0, LoginController_1.validarToken)(req, res);
        expect(res.status).toHaveBeenCalledWith(500);
        expect(res.json).toHaveBeenCalledWith('Erro: Error: Erro interno');
    }));
});
