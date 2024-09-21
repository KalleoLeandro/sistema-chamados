"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const TesteController_1 = require("../../src/controllers/TesteController");
describe('Teste da função teste', () => {
    it('Deve retornar status 200 e mensagem correta', () => {
        // Mock do objeto Response
        const res = {
            status: jest.fn().mockReturnThis(), // Mock da função status que retorna o próprio objeto
            json: jest.fn() // Mock da função json
        };
        // Mock do objeto Request (não usado na função, então pode ser vazio)
        const req = {};
        // Chama a função que estamos testando
        (0, TesteController_1.teste)(req, res);
        // Verificações
        expect(res.status).toHaveBeenCalledWith(200); // Verifica se o status 200 foi chamado
        expect(res.json).toHaveBeenCalledWith('Teste request OK'); // Verifica se a função json foi chamada com a mensagem correta
    });
});
