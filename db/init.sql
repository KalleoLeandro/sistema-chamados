/*

create database IF NOT EXISTS sistema_chamados;
use sistema_chamados;

create table usuarios(
	id bigint PRIMARY KEY AUTO_INCREMENT,
    nome varchar(100) not null,
    n_doc varchar(20) not null,
    login varchar(64) not null UNIQUE,
    senha varchar(255) not null
);

insert into usuarios(nome, n_doc, login, senha) values('Master User', '111.222.333.444-05', 'admin', 'sistemroot');

*/