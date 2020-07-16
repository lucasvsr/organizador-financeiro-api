create table usuario
(
	id bigserial,
	nome varchar(100) not null,
	dt_nascimento date not null,
	email varchar(100) not null,
	senha varchar(100) not null,
	dt_criacao timestamp
);

create unique index usuario_id_uindex
	on usuario (id);

alter table usuario
	add constraint usuario_pk
		primary key (id);

