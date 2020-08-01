package com.br.lvsribeiro.organizadorfinanceiroapi.api.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.BancoJaCadastradoException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.CategoriaJaCadastradaException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.ContaDiferenteCadastradaException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.ContaInformadaNaoPertenceAoUsuarioException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.EmailJaCadastradoException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(BancoJaCadastradoException.class)
	public ResponseEntity<?> tratarBancoJaCadastradoException(BancoJaCadastradoException e) {
		
		Handler handler = new Handler(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT)
							 .body(handler);
		
	}
	
	@ExceptionHandler(CategoriaJaCadastradaException.class)
	public ResponseEntity<?> tratarCategoriaJaCadastradaException(CategoriaJaCadastradaException e) {
		
		Handler handler = new Handler(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT)
							 .body(handler);	
		
	}
	
	@ExceptionHandler(ContaDiferenteCadastradaException.class)
	public ResponseEntity<?> tratarContaDiferenteCadastradaException(ContaDiferenteCadastradaException e) {
		
		Handler handler = new Handler(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
							 .body(handler);	
		
	}
	
	@ExceptionHandler(ContaInformadaNaoPertenceAoUsuarioException.class)
	public ResponseEntity<?> tratarContaInformadaNaoPertenceAoUsuarioException(ContaInformadaNaoPertenceAoUsuarioException e) {
		
		Handler handler = new Handler(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							 .body(handler);	
		
	}
	
	@ExceptionHandler(EmailJaCadastradoException.class)
	public ResponseEntity<?> tratarEmailJaCadastradoException(EmailJaCadastradoException e) {
		
		Handler handler = new Handler(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT)
							 .body(handler);	
		
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
		
		Handler handler = new Handler(e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							 .body(handler);	
		
	}

}
