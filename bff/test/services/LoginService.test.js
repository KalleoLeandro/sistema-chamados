"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const LoginService_1 = require("../../src/services/LoginService");
const CustomError_1 = require("../../src/exceptions/CustomError");
const jest_fetch_mock_1 = __importDefault(require("jest-fetch-mock"));
const Utils_1 = require("../../src/utils/Utils");
jest.mock('node-fetch', () => ({
    __esModule: true,
    default: jest.fn()
}));
jest.mock('../../src/utils/Utils');
jest_fetch_mock_1.default.enableMocks();
describe('Teste do serviço validarLogin', () => {
    let log;
    beforeEach(() => {
        log = Utils_1.logger;
        log.info.mockClear();
        log.error.mockClear();
    });
    it('Deve retornar um login válido com status 200', () => __awaiter(void 0, void 0, void 0, function* () {
        const mockResponse = new Response(JSON.stringify({ token: 'token', userName: 'user', expiration: '01/01/2025', status: 200 }), { status: 200 });
        jest_fetch_mock_1.default.mockResolvedValue(mockResponse);
        const loginRequest = { login: 'user', senha: 'pass' };
        const result = yield (0, LoginService_1.validarLogin)(loginRequest);
        expect(result).toEqual({
            token: 'token',
            expiration: '01/01/2025',
            userName: 'user',
            status: 200
        });
    }));
    it('Deve retornar status 401 com mensagem de erro', () => __awaiter(void 0, void 0, void 0, function* () {
        const mockResponse = new Response(JSON.stringify({ message: 'Credenciais inválidas', status: 401 }), { status: 401 });
        jest_fetch_mock_1.default.mockResolvedValue(mockResponse);
        const loginRequest = { login: 'user', senha: 'pass' };
        const result = yield (0, LoginService_1.validarLogin)(loginRequest);
        expect(result).toEqual({
            status: 401,
            message: 'Credenciais inválidas'
        });
    }));
    it('Deve lançar um erro CustomError em caso de exceção', () => __awaiter(void 0, void 0, void 0, function* () {
        jest_fetch_mock_1.default.mockRejectedValue(new Error('Erro interno'));
        const loginRequest = { login: 'user', senha: 'pass' };
        yield expect((0, LoginService_1.validarLogin)(loginRequest)).rejects.toThrow(CustomError_1.CustomError);
    }));
});
describe('Teste do serviço validarToken', () => {
    let log;
    beforeEach(() => {
        log = Utils_1.logger;
        log.info.mockClear();
        log.error.mockClear();
    });
    it('Deve retornar um token válido', () => __awaiter(void 0, void 0, void 0, function* () {
        const mockResponse = new Response(JSON.stringify(true), { status: 200 });
        jest_fetch_mock_1.default.mockResolvedValue(mockResponse);
        const tokenRequest = { token: 'validtoken', userName: 'teste' };
        const result = yield (0, LoginService_1.validarToken)(tokenRequest);
        expect(result).toBe(true);
    }));
    it('Deve lançar um erro CustomError em caso de exceção', () => __awaiter(void 0, void 0, void 0, function* () {
        jest_fetch_mock_1.default.mockRejectedValue(new Error('Erro interno'));
        const tokenRequest = { token: 'validtoken', userName: 'teste' };
        yield expect((0, LoginService_1.validarToken)(tokenRequest)).rejects.toThrow(CustomError_1.CustomError);
    }));
});
