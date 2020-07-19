--USUARIO
INSERT INTO usuario (nome, dt_nascimento, email, senha, dt_criacao)
VALUES ('Sistema', now(), 'sis@orgfinanceiro.com', '000', now());

--CATEGORIAS

---ENTRADAS
INSERT INTO categoria (descricao, tipo, dt_criacao, usuario_criador)
VALUES ('Bônus', 'ENTRADA', now(), (select id from usuario where nome = 'Sistema'));

INSERT INTO categoria (descricao, tipo, dt_criacao, usuario_criador)
VALUES ('Recebimento de empréstimo', 'ENTRADA', now(), (select id from usuario where nome = 'Sistema'));

INSERT INTO categoria (descricao, tipo, dt_criacao, usuario_criador)
VALUES ('Outras rendas', 'ENTRADA', now(), (select id from usuario where nome = 'Sistema'));

---SAIDAS
INSERT INTO categoria (descricao, tipo, dt_criacao, usuario_criador)
VALUES ('Contas residencias', 'SAIDA', now(), (select id from usuario where nome = 'Sistema'));

INSERT INTO categoria (descricao, tipo, dt_criacao, usuario_criador)
VALUES ('Moradia', 'SAIDA', now(), (select id from usuario where nome = 'Sistema'));

INSERT INTO categoria (descricao, tipo, dt_criacao, usuario_criador)
VALUES ('Saúde', 'SAIDA', now(), (select id from usuario where nome = 'Sistema'));

INSERT INTO categoria (descricao, tipo, dt_criacao, usuario_criador)
VALUES ('Lazer', 'SAIDA', now(), (select id from usuario where nome = 'Sistema'));

INSERT INTO categoria (descricao, tipo, dt_criacao, usuario_criador)
VALUES ('Saque', 'SAIDA', now(), (select id from usuario where nome = 'Sistema'));

INSERT INTO categoria (descricao, tipo, dt_criacao, usuario_criador)
VALUES ('Serviços', 'SAIDA', now(), (select id from usuario where nome = 'Sistema'));

--BANCOS
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