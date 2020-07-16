create table banco
(
	id bigserial
		constraint banco_pk
			primary key,
	nome varchar(80) not null,
	tipo varchar(20) not null,
	dt_cadastro timestamp
);

create unique index banco_nome_uindex
	on banco (nome);

