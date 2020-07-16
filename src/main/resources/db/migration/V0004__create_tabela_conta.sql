create table conta
(
	id serial
		constraint conta_pk
			primary key,
	dono bigint not null
		constraint conta_usuario_id_fk
			references usuario,
	banco bigint not null
		constraint conta_banco_id_fk
			references banco,
	saldo decimal not null,
	dt_criacao timestamp,
	dt_atualizacao timestamp
);

