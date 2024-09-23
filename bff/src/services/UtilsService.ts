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

export const validarCpf = async (cpf: string) => {
    try {
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: cpf.replace(/[^\d]/g, "")
        };

        log.info("Executando a /api/utils/validar-cpf");
        let retorno: boolean = await fetch(`${process.env.HOST_MS}/utils/validar-cpf`, options).then((res) => res.json());
        if (retorno) {
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