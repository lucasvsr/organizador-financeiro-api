package com.br.lvsribeiro.organizadorfinanceiroapi.api.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

	@Autowired
	private MessageSource source;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> tratarGenericException(Exception e) {

		Handler handler = new Handler(ProblemType.PROBLEMA_SERVIDOR);

		e.printStackTrace();

		return retornarExcecao(HttpStatus.INTERNAL_SERVER_ERROR, handler);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

		Handler handler = new Handler(ProblemType.INFORMACAO_INCORRETA, listarProblemas(ex.getBindingResult()));

		return retornarExcecao(HttpStatus.BAD_REQUEST, handler);

	}

	@ExceptionHandler(BancoJaCadastradoException.class)
	public ResponseEntity<?> tratarBancoJaCadastradoException(BancoJaCadastradoException e) {

		Handler handler = new Handler(ProblemType.INFORMACAO_JA_CADASTRADA, e.getMessage());

		return retornarExcecao(HttpStatus.CONFLICT, handler);

	}

	@ExceptionHandler(CategoriaJaCadastradaException.class)
	public ResponseEntity<?> tratarCategoriaJaCadastradaException(CategoriaJaCadastradaException e) {

		Handler handler = new Handler(ProblemType.INFORMACAO_JA_CADASTRADA, e.getMessage());

		return retornarExcecao(HttpStatus.CONFLICT, handler);

	}

	@ExceptionHandler(ContaDiferenteCadastradaException.class)
	public ResponseEntity<?> tratarContaDiferenteCadastradaException(ContaDiferenteCadastradaException e) {

		Handler handler = new Handler(ProblemType.NAO_PODE_ALTERAR_CONTA);

		return retornarExcecao(HttpStatus.FORBIDDEN, handler);

	}

	@ExceptionHandler(ContaInformadaNaoPertenceAoUsuarioException.class)
	public ResponseEntity<?> tratarContaInformadaNaoPertenceAoUsuarioException(
			ContaInformadaNaoPertenceAoUsuarioException e) {

		Handler handler = new Handler(ProblemType.CONTA_NAO_PERTENCE_AO_USUARIO);

		return retornarExcecao(HttpStatus.BAD_REQUEST, handler);

	}

	@ExceptionHandler(EmailJaCadastradoException.class)
	public ResponseEntity<?> tratarEmailJaCadastradoException(EmailJaCadastradoException e) {

		Handler handler = new Handler(ProblemType.INFORMACAO_JA_CADASTRADA, e.getMessage());

		return retornarExcecao(HttpStatus.CONFLICT, handler);

	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {

		Handler handler = new Handler(ProblemType.INFORMACAO_NAO_ENCONTRADA, e.getMessage());

		return retornarExcecao(HttpStatus.NOT_FOUND, handler);

	}

	private ResponseEntity<?> retornarExcecao(HttpStatus status, Handler corpo) {

		return ResponseEntity.status(status).body(corpo);

	}

	private List<Handler.Problemas> listarProblemas(BindingResult binding) {

		List<Handler.Problemas> lista = new ArrayList<Handler.Problemas>();

		binding.getAllErrors().iterator().forEachRemaining(error -> {

			Handler.Problemas problema = new Handler().novoProblema();

			if (error instanceof ObjectError)
				problema.setCampo(error.getObjectName());
			if (error instanceof FieldError)
				problema.setCampo(((FieldError) error).getField());
			problema.setProblema(source.getMessage(error, LocaleContextHolder.getLocale()));

			lista.add(problema);

		});

		return lista;

	}

}
