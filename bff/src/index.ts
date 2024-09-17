import express, { Request, Response } from 'express';
import { createLogger, format, transports } from  'winston';
import cors from 'cors';
import dotenv from 'dotenv';
import path from 'path';
import rotas from './routes/rotas';

dotenv.config();


//Declaração do servidor
const server = express();

//Configuração inicial de entradas, cors e public
server.use(express.json());
server.use(express.static(path.join(__dirname, 'public')));
server.use(express.urlencoded({ extended: true }));
server.use(cors({
    origin: '*',
    methods: ['GET', 'POST', 'PUT', 'PATCH', 'DELETE']
}));

//Inicializador do logger
const logger = createLogger({
  level: 'info', 
  format: format.combine(
    format.colorize(),
    format.simple()
  ),
  transports: [
    new transports.Console()
  ]
});


//Rotas
server.use(rotas);

//Rota Default
server.use((req: Request, res: Response) => {
    res.status(404).json(`Recurso não encontrado`);
    logger.error(`Recurso não encontrado`);
    
});

//Inicialização do servidor
server.listen(process.env.PORT, ()=>{
    logger.info(`Servidor rodando na porta ${process.env.PORT}`);
})

//Inicialização de mensageria


