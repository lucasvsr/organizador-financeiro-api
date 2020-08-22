package com.br.lvsribeiro.organizadorfinanceiroapi.api.exceptionhandler;

public enum ProblemType {
	
	INFORMACAO_INCORRETA("Informação incorreta", "Alguma informação está incorreta"),
	PROBLEMA_SERVIDOR("Problema no servidor", "Houve algum problema com o servidor"),
	INFORMACAO_JA_CADASTRADA("Informação já cadastrada", "Esta informação já foi cadastrada"),
	NAO_PODE_ALTERAR_CONTA("Não é possível alterar a conta de uma transação já salva", "Não é possível alterar a conta de uma transação já salva. Favor remover a transação e cadastrar uma nova."),
	CONTA_NAO_PERTENCE_AO_USUARIO("A conta não pertence ao usuário", "A conta informada não pertence ao usuário. Favor verificar as informações repassadas."),
	INFORMACAO_NAO_ENCONTRADA("Informação não encontrada", "Informação não encontrada. Favor verificar as informações repassadas.");
	
	private String titulo;
	private String descricao;
	
	private ProblemType(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

}
