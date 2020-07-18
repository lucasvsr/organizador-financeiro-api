package com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContaInformadaNaoPertenceAoUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ContaInformadaNaoPertenceAoUsuarioException(String mensagem) {
		
		super(mensagem);
		
	}

}
