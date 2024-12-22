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

create table categorias(
		id bigint PRIMARY KEY AUTO_INCREMENT,
        descricao varchar(15) not null
);

create table medidas(
	id bigint PRIMARY KEY AUTO_INCREMENT,
	sigla varchar(2) not null,
        descricao varchar(15) not null
);

insert into medidas(sigla, descricao) values('Un', 'Unidade');
insert into medidas(sigla, descricao) values('Kg', 'Quilo');
insert into medidas(sigla, descricao) values('Cx', 'Caixa');
insert into medidas(sigla, descricao) values('Pc', 'Pacote');
insert into medidas(sigla, descricao) values('Lt', 'Litro');
insert into medidas(sigla, descricao) values('Mt', 'Metro');

insert into categorias(descricao) values('Vestuário');
insert into categorias(descricao) values('Eletrônicos');
insert into categorias(descricao) values('Alimentos');
insert into categorias(descricao) values('Limpeza');
insert into categorias(descricao) values('Diversos');

CREATE TABLE produtos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    preco_compra DECIMAL(10, 2) NOT NULL,
    preco_venda DECIMAL(10, 2) NOT NULL,
    quantidade int NOT NULL,
    id_medida BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    FOREIGN KEY (id_medida) REFERENCES medidas(id),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id)
);

INSERT INTO produtos (nome, preco_compra, preco_venda, id_medida, id_categoria, quantidade) VALUES
('Camisa Polo', 30.00, 50.00, 1, 1, 45),
('Calça Jeans', 60.00, 90.00, 1, 1, 73),
('Tênis Esportivo', 80.00, 120.00, 1, 1, 28),
('Jaqueta de Couro', 150.00, 250.00, 1, 1, 15),
('Relógio Digital', 100.00, 180.00, 1, 2, 67),
('Smartphone', 800.00, 1200.00, 1, 2, 32),
('Tablet', 600.00, 900.00, 1, 2, 24),
('Teclado Mecânico', 200.00, 350.00, 1, 2, 54),
('Monitor LED', 400.00, 600.00, 1, 2, 42),
('Mouse Sem Fio', 80.00, 120.00, 1, 2, 78),
('Arroz 5kg', 10.00, 15.00, 2, 3, 96),
('Feijão 1kg', 4.00, 6.00, 2, 3, 83),
('Açúcar 1kg', 3.00, 5.00, 2, 3, 49),
('Óleo de Soja 1L', 5.00, 8.00, 5, 3, 57),
('Leite Integral 1L', 4.00, 6.50, 5, 3, 68),
('Farinha de Trigo 1kg', 2.50, 4.00, 2, 3, 91),
('Detergente 500ml', 2.00, 3.50, 5, 4, 86),
('Sabão em Pó 1kg', 6.00, 9.00, 4, 4, 21),
('Desinfetante 2L', 5.00, 7.50, 5, 4, 33),
('Esponja de Aço', 1.00, 2.00, 1, 4, 98),
('Vassoura', 8.00, 12.00, 1, 4, 59),
('Pasta de Dente', 2.50, 4.00, 1, 4, 36),
('Saco de Lixo 50L', 3.50, 5.50, 4, 4, 48),
('Bala de Hortelã', 1.00, 1.50, 1, 3, 74),
('Chocolate 100g', 4.00, 6.50, 1, 3, 62),
('Refrigerante 2L', 6.00, 9.00, 5, 3, 84),
('Suco de Laranja 1L', 4.00, 6.50, 5, 3, 40),
('Biscoito 200g', 2.50, 4.00, 1, 3, 65),
('Macarrão 500g', 2.00, 3.50, 2, 3, 27),
('Shampoo 500ml', 8.00, 12.00, 5, 4, 30),
('Condicionador 500ml', 8.00, 12.00, 5, 4, 88),
('Barra de Sabão 200g', 1.50, 2.50, 1, 4, 56),
('Carregador de Celular', 15.00, 25.00, 1, 2, 99),
('Fone de Ouvido', 25.00, 40.00, 1, 2, 71),
('Câmera de Segurança', 120.00, 200.00, 1, 2, 19),
('Batedeira', 150.00, 220.00, 1, 2, 44),
('Micro-ondas', 400.00, 600.00, 1, 2, 39),
('Panela de Pressão', 80.00, 120.00, 1, 4, 53),
('Fogão 4 bocas', 700.00, 1000.00, 1, 4, 25),
('Geladeira', 1200.00, 1800.00, 1, 4, 18),
('Freezer', 1000.00, 1500.00, 1, 4, 12),
('Café 500g', 10.00, 15.00, 2, 3, 72),
('Queijo Mussarela 1kg', 30.00, 45.00, 2, 3, 66),
('Presunto 1kg', 25.00, 40.00, 2, 3, 80),
('Molho de Tomate 340g', 3.00, 5.00, 1, 3, 93),
('Sardinha em Lata', 2.50, 4.00, 1, 3, 11),
('Sal 1kg', 1.00, 2.00, 2, 3, 50),
('Cereal Matinal 200g', 6.00, 9.00, 1, 3, 41),
('Leite Condensado 395g', 3.50, 5.50, 1, 3, 23);
