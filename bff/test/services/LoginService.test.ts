import { validarLogin, validarToken } from '../../src/services/LoginService';
import { CustomError } from '../../src/exceptions/CustomError';
import fetch from 'node-fetch';
import fetchMock from 'jest-fetch-mock';
import { logger } from '../../src/utils/Utils';
import { LoginRequest } from '../../src/models/LoginRequest';

jest.mock('node-fetch', () => ({
    __esModule: true,
    default: jest.fn()
  }));
  
jest.mock('../../src/utils/Utils');

fetchMock.enableMocks();

describe('Teste do serviço validarLogin', () => {
  let log: any;

  beforeEach(() => {
    log = logger;
    (log.info as jest.Mock).mockClear();
    (log.error as jest.Mock).mockClear();
  });

  it('Deve retornar um login válido com status 200', async () => {
    const mockResponse = new Response(
      JSON.stringify({ token: 'token', userName: 'user', expiration: '01/01/2025', status: 200 }),
      { status: 200 }
    );

    fetchMock.mockResolvedValue(mockResponse);

    const loginRequest:LoginRequest = { login: 'user', senha: 'pass' };
    
    const result = await validarLogin(loginRequest);
    
    expect(result).toEqual({
      token: 'token',
      expiration: '01/01/2025',
      userName: 'user',
      status: 200
    });
  });

  it('Deve retornar status 401 com mensagem de erro', async () => {
    const mockResponse = new Response(
      JSON.stringify({ message: 'Credenciais inválidas', status: 401 }),
      { status: 401 }
    );

    fetchMock.mockResolvedValue(mockResponse);

    const loginRequest:LoginRequest = { login: 'user', senha: 'pass' };
    const result = await validarLogin(loginRequest);
    
    expect(result).toEqual({
      status: 401,
      message: 'Credenciais inválidas'
    });
  });

  it('Deve lançar um erro CustomError em caso de exceção', async () => {
    fetchMock.mockRejectedValue(new Error('Erro interno'));

    const loginRequest:LoginRequest = { login: 'user', senha: 'pass' };

    await expect(validarLogin(loginRequest)).rejects.toThrow(CustomError);
  });
});

describe('Teste do serviço validarToken', () => {
  let log: any;

  beforeEach(() => {
    log = logger;
    (log.info as jest.Mock).mockClear();
    (log.error as jest.Mock).mockClear();
  });

  it('Deve retornar um token válido', async () => {
    const mockResponse = new Response(
      JSON.stringify(true),
      { status: 200 }
    );

    fetchMock.mockResolvedValue(mockResponse);

    const tokenRequest = { token: 'validtoken', userName: 'teste' };
    const result = await validarToken(tokenRequest);
    
    expect(result).toBe(true);
  });

  it('Deve lançar um erro CustomError em caso de exceção', async () => {
    fetchMock.mockRejectedValue(new Error('Erro interno'));

    const tokenRequest = { token: 'validtoken', userName: 'teste'};

    await expect(validarToken(tokenRequest)).rejects.toThrow(CustomError);
  });
});
