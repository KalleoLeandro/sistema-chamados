import { Request, Response, NextFunction } from 'express';
import * as LoginService from '../../src/services/LoginService';
import { verificaTokenValido } from '../../src/middlewares/middleware';
import { CustomError } from '../../src/exceptions/CustomError';

jest.mock('../../src/services/LoginService');

let req: Request;
let res: Response;
let next: NextFunction;

describe('Teste da função verificarTokenValido', () => {     
    beforeEach(() => {
        req = {
            headers: {
                'authorization': 'token'
            }
        } as Request;
        res = {
            status: jest.fn().mockReturnThis(),
            json: jest.fn(),
            end: jest.fn()
        } as unknown as Response;
        next = jest.fn();
    });

    it('Deve chamar o next se o token for válido', async () => {
        (LoginService.validarToken as jest.Mock).mockResolvedValue(true); 

        await verificaTokenValido(req as Request, res as Response, next as NextFunction);

        expect(next).toHaveBeenCalled();  
        expect(res.status).not.toHaveBeenCalled(); 
    });

    it('Deve retornar status 401 se o token for inválido', async () => {
        (LoginService.validarToken as jest.Mock).mockResolvedValue(false); 

        await verificaTokenValido(req as Request, res as Response, next as NextFunction);

        expect(res.status).toHaveBeenCalledWith(401); 
        expect(next).not.toHaveBeenCalled();  
    });

    it('Deve retornar status 500 em caso de erro na validação do token', async () => {
        (LoginService.validarToken as jest.Mock).mockRejectedValue(new CustomError('Erro de validação', 500)); 

        await verificaTokenValido(req as Request, res as Response, next as NextFunction);

        expect(res.status).toHaveBeenCalledWith(500); 
        expect(next).not.toHaveBeenCalled(); 
    });
});

