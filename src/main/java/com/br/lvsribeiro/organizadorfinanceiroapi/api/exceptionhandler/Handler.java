package com.br.lvsribeiro.organizadorfinanceiroapi.api.exceptionhandler;

import java.time.LocalDateTime;

public class Handler {

	public LocalDateTime dataHora;
	public String mensagem;
	
	public Handler(String e) {
		
		this.dataHora = LocalDateTime.now();
		this.mensagem = new String();
		
	}
	

}
