import { Request, Response } from 'express';
import { validarLogin, validarToken } from '../../src/controllers/LoginController';
import * as LoginService from '../../src/services/LoginService';
import { decriptografia, logger } from '../../src/utils/Utils';

// Mock dos módulos
jest.mock('../../src/services/LoginService');
jest.mock('../../src/utils/Utils');

describe('Teste da função validarLogin', () => {
  let req: Request;
  let res: Response;
  
  beforeEach(() => {
    req = {} as Request;
    res = {
      status: jest.fn().mockReturnThis(),  // Mock da função status que retorna o próprio objeto
      json: jest.fn()  // Mock da função json
    } as unknown as Response;
  });

  it('Deve retornar status 200 e dados do login', async () => {
    // Configurando mocks
    (decriptografia as jest.Mock).mockReturnValue(JSON.stringify({ userName: 'user', password: 'pass' }));
    (LoginService.validarLogin as jest.Mock).mockResolvedValue({ status: 200, token: 'token', userName: 'user', expiration: '01/01/2025' });

    req.body = { hash: 'somehash' };

    await validarLogin(req, res);

    expect(res.status).toHaveBeenCalledWith(200);
    expect(res.json).toHaveBeenCalledWith({ status: 200, token: 'token', userName: 'user', expiration: '01/01/2025' });
  });

  it('Deve retornar status 401 e mensagem de erro', async () => {
    (decriptografia as jest.Mock).mockReturnValue(JSON.stringify({ userName: 'user', password: 'pass' }));
    (LoginService.validarLogin as jest.Mock).mockResolvedValue({ status: 401, mensagem: 'Credenciais inválidas' });

    req.body = { hash: 'somehash' };

    await validarLogin(req, res);

    expect(res.status).toHaveBeenCalledWith(401);
    expect(res.json).toHaveBeenCalledWith('Credenciais inválidas');
  });

  it('Deve retornar status 500 em caso de erro', async () => {
    (decriptografia as jest.Mock).mockReturnValue(JSON.stringify({ userName: 'user', password: 'pass' }));
    (LoginService.validarLogin as jest.Mock).mockRejectedValue(new Error('Erro interno'));

    req.body = { hash: 'somehash' };

    await validarLogin(req, res);

    expect(res.status).toHaveBeenCalledWith(500);
    expect(res.json).toHaveBeenCalledWith('Erro: Error: Erro interno');
  });
});

describe('Teste da função validarToken', () => {
  let req: Request;
  let res: Response;
  
  beforeEach(() => {
    req = {} as Request;
    res = {
      status: jest.fn().mockReturnThis(),  // Mock da função status que retorna o próprio objeto
      json: jest.fn()  // Mock da função json
    } as unknown as Response;
  });

  it('Deve retornar status 200 e dados do token', async () => {
    (LoginService.validarToken as jest.Mock).mockResolvedValue({ status: 200, valid: true });

    req.body = { token: 'validtoken' };

    await validarToken(req, res);

    expect(res.status).toHaveBeenCalledWith(200);
    expect(res.json).toHaveBeenCalledWith({ status: 200, valid: true });
  });

  it('Deve retornar status 500 em caso de erro', async () => {
    (LoginService.validarToken as jest.Mock).mockRejectedValue(new Error('Erro interno'));

    req.body = { token: 'validtoken' };

    await validarToken(req, res);

    expect(res.status).toHaveBeenCalledWith(500);
    expect(res.json).toHaveBeenCalledWith('Erro: Error: Erro interno');
  });
});
