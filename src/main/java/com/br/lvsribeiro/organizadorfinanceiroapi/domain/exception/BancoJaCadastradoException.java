package com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Banco;

public class BancoJaCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BancoJaCadastradoException(Banco banco) {
		
		super(String.format("Banco %s já cadastrado com o tipo %s", banco.getNome(), banco.getTipo()));
		
	}

}
