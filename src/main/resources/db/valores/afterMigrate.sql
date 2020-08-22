ALTER SEQUENCE usuario_id_seq RESTART WITH 1;
ALTER SEQUENCE categoria_id_seq RESTART WITH 1;
ALTER SEQUENCE transacao_id_seq RESTART WITH 1;
ALTER SEQUENCE conta_id_seq RESTART WITH 1;
ALTER SEQUENCE banco_id_seq RESTART WITH 1;

DELETE FROM transacao;
DELETE FROM conta;



--USUARIO

DELETE from usuario;
INSERT INTO usuario (nome, dt_nascimento, email, senha, dt_criacao)
VALUES ('Sistema', now(), 'sis@orgfinanceiro.com', '000', now());

--CATEGORIAS
DELETE from categoria;

---ENTRADAS
INSERT INTO categoria (descricao, tipo, dt_criacao)
VALUES ('Bônus', 'ENTRADA', now());

INSERT INTO categoria (descricao, tipo, dt_criacao)
VALUES ('Recebimento de empréstimo', 'ENTRADA', now());

INSERT INTO categoria (descricao, tipo, dt_criacao)
VALUES ('Outras rendas', 'ENTRADA', now());

---SAIDAS
INSERT INTO categoria (descricao, tipo, dt_criacao)
VALUES ('Contas residencias', 'SAIDA', now());

INSERT INTO categoria (descricao, tipo, dt_criacao)
VALUES ('Moradia', 'SAIDA', now());

INSERT INTO categoria (descricao, tipo, dt_criacao)
VALUES ('Saúde', 'SAIDA', now());

INSERT INTO categoria (descricao, tipo, dt_criacao)
VALUES ('Lazer', 'SAIDA', now());

INSERT INTO categoria (descricao, tipo, dt_criacao)
VALUES ('Saque', 'SAIDA', now());

INSERT INTO categoria (descricao, tipo, dt_criacao)
VALUES ('Serviços', 'SAIDA', now());

--BANCOS
DELETE from banco;
INSERT INTO banco (nome, tipo, dt_cadastro)
VALUES ('Nubank', 'BANCO', now());

INSERT INTO banco (nome, tipo, dt_cadastro)
VALUES ('Next', 'BANCO', now());

INSERT INTO banco (nome, tipo, dt_cadastro)
VALUES ('Bradesco', 'BANCO', now());

INSERT INTO banco (nome, tipo, dt_cadastro)
VALUES ('Itaú', 'BANCO', now());

INSERT INTO banco (nome, tipo, dt_cadastro)
VALUES ('Em espécie', 'CARTEIRA', now());