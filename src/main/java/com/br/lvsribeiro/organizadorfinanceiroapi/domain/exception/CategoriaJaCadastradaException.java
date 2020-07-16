package com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Categoria;

public class CategoriaJaCadastradaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CategoriaJaCadastradaException(Categoria categoria) {
		
		super(String.format("Categoria %s já foi cadastrada pelo usuario %s",
							categoria.getDescricao(), categoria.getCriador().getNome()));
		
	}

}
