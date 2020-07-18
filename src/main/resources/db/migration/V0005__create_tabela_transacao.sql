create table transacao
(
	id bigserial
		constraint transacao_pk
			primary key,
	conta bigint not null
		constraint transacao_conta_id_fk
			references conta,
	descricao varchar(100) not null,
	valor decimal not null,
	categoria bigint not null
		constraint transacao_categoria_id_fk
			references categoria,
	dt_transacao timestamp not null,
	dt_cadastro timestamp
);

