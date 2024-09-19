import { teste } from '../../src/controllers/TesteController';
import { Request, Response } from 'express';

describe('Teste da função teste', () => {
  it('Deve retornar status 200 e mensagem correta', () => {
    // Mock do objeto Response
    const res = {
      status: jest.fn().mockReturnThis(),  // Mock da função status que retorna o próprio objeto
      json: jest.fn()  // Mock da função json
    } as unknown as Response;

    // Mock do objeto Request (não usado na função, então pode ser vazio)
    const req = {} as Request;

    // Chama a função que estamos testando
    teste(req, res);

    // Verificações
    expect(res.status).toHaveBeenCalledWith(200);  // Verifica se o status 200 foi chamado
    expect(res.json).toHaveBeenCalledWith('Teste request OK');  // Verifica se a função json foi chamada com a mensagem correta
  });
});