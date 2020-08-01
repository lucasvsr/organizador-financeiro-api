package com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception;


public class ContaDiferenteCadastradaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ContaDiferenteCadastradaException(String mensagem) {
		
		super(mensagem);
		
	}

}
