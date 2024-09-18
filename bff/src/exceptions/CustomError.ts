export class CustomError extends Error {
    statusCode: number;

    constructor(message: string, statusCode: number) {
        super(message); // Chama o construtor da classe Error
        this.name = this.constructor.name; // Define o nome do erro como o nome da classe
        this.statusCode = statusCode; // Define um código de status (opcional para APIs)
        Object.setPrototypeOf(this, new.target.prototype); // Corrige a cadeia de protótipos
        Error.captureStackTrace(this, this.constructor); // Captura a stack trace (opcional)
    }
}