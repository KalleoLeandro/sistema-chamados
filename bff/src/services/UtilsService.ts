import dotenv from 'dotenv';
import { CustomError } from '../exceptions/CustomError';
import { logger } from '../utils/Utils';

dotenv.config();

const log = logger;

export const validarDataNascimento = (dataNascimento: string) => {
    try {
        let partesData = dataNascimento;
        let dataNascimentoObj = new Date(`${partesData}`);
        
        let dataAtual = new Date();

        
        if (dataNascimentoObj < dataAtual) {
            return {
                status: 200
            }
        } else {
            return {
                status: 401
            }
        }
    } catch (error: any) {
        log.error(`Erro: ${error}`);
        throw new CustomError(error.message, 500);
    }
}