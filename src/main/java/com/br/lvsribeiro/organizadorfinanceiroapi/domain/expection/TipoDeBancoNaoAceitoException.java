package com.br.lvsribeiro.organizadorfinanceiroapi.domain.expection;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.enums.TipoBancoEnum;

public class TipoDeBancoNaoAceitoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public TipoDeBancoNaoAceitoException() {
		
		super(String.format("Apenas os seguintes tipos de banco são aceitos: %d", TipoBancoEnum.values().toString()));
		
	}

}
