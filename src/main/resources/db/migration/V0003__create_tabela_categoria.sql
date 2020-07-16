create table categoria
(
	id bigserial
		constraint categoria_pk
			primary key,
	descricao varchar(40) not null,
	tipo varchar(20) not null,
	dt_criacao timestamp,
	usuario_criador bigint not null
		constraint categoria_usuario_id_fk
			references usuario
);

