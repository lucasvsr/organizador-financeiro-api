package com.br.lvsribeiro.organizadorfinanceiroapi.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

public class Handler {

	public LocalDateTime dataHora;
	public String titulo;
	public String mensagem;
	public List<Problemas> problemas;

	public Handler(ProblemType problema) {
		
		this.dataHora = LocalDateTime.now();
		this.mensagem = problema.getDescricao();
		this.titulo = problema.getTitulo();
		
	}
	
	public Handler(ProblemType problema, List<Problemas> problemas) {
		
		this.dataHora = LocalDateTime.now();
		this.mensagem = problema.getDescricao();
		this.titulo = problema.getTitulo();
		this.problemas = problemas;
		
	}
	
	public Handler(ProblemType problema, String descricao) {
		
		this.dataHora = LocalDateTime.now();
		this.mensagem = descricao.isBlank() ? problema.getDescricao() : descricao;
		this.titulo = problema.getTitulo();
		
	}
	
	public Handler() {
		
	}

	public Problemas novoProblema() {
		
		
		return new Problemas();
	}
	
	class Problemas {
		
		private String campo;
		private String problema;
		
		public Problemas() {
			
		}

		public String getCampo() {
			return campo;
		}
		public void setCampo(String campo) {
			this.campo = campo;
		}


		public String getProblema() {
			return problema;
		}
		public void setProblema(String problema) {
			this.problema = problema;
		}
		
	}
	

}
