package com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = -1438317558094531603L;

	public EmailJaCadastradoException(String mensagem) {
		
		super(mensagem);
		
	}
	
	

}
