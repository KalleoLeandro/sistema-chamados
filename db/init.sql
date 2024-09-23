/*

create database IF NOT EXISTS sistema_chamados;
use sistema_chamados;

create table usuarios(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	nome varchar(100) not null,
	cpf varchar(20) not null,
	sexo char(1) not null,
	data_nascimento varchar(20) not null,
	id_dados_login bigint not null,
	id_contato bigint not null,
        id_endereco bigint not null	 
);

create table dados_login(
	id bigint PRIMARY KEY AUTO_INCREMENT,
        login varchar(64) not null UNIQUE,
	senha varchar(64) not null,
	perfil varchar(3) not null
);

create table endereco(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	cep varchar(10)	not null,
	rua varchar(100) not null,
	numero int not null,
	complemento varchar(100),
	bairro varchar(100) not null,
	cidade varchar(100) not null,
	uf varchar(2) not null
);

create table contato(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	telefone varchar(15) not null,
	celular varchar(15) not null,
	email varchar(15) not null
);

alter table usuarios add foreign key (id_dados_login) references dados_login(id);
alter table usuarios add foreign key (id_endereco) references endereco(id);
alter table usuarios add foreign key (id_contato) references contato(id);

insert into dados_login(login, senha, perfil) values('admin', 'systemroot', 'adm');
insert into contato(telefone, celular, email) values('(41) 3333-2222', '(41) 99999-8888', 'teste@teste.com');
insert into endereco(cep, rua, numero, bairro, cidade, uf) values('83085-200', 'Jose de Almeida Sobrinho', 100, 'Centro', 'São José dos Pinhais', 'PR');
insert into usuarios(nome, cpf, data_nascimento, sexo, id_dados_login, id_endereco, id_contato) values('Master User', '222.333.444-05', '1990-01-01', 'm', 1, 1, 1);



*/