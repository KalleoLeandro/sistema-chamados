export interface UsuarioRequest {
    id?:number,
    nome:string,
    cpf:string,
    dataNascimento:string,
    sexo:string,
    cep:string,
    rua:string,
    numero:number,
    complemento?: string,
    bairro:string,
    cidade:string,
    uf:string,
    telefone:string,
    celular:string,
    email:string,
    login:string,
    senha:string,
    perfil:string
}