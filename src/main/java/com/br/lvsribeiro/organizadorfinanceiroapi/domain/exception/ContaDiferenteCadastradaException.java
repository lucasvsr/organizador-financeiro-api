package com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ContaDiferenteCadastradaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ContaDiferenteCadastradaException(String mensagem) {
		
		super(mensagem);
		
	}

}
