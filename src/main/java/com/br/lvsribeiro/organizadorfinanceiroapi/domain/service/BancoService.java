package com.br.lvsribeiro.organizadorfinanceiroapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.BancoJaCadastradoException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.EntidadeNaoEncontradaException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Banco;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.BancoRepository;

@Service
public class BancoService {
	
	@Autowired
	BancoRepository repository;
	
	public Banco salvar(Banco banco) throws BancoJaCadastradoException {
		
		if(repository.existsByNomeAndTipo(banco.getNome(), banco.getTipo()))
			throw new BancoJaCadastradoException(banco);
			
		return repository.save(banco);
			
	}

	public Banco buscar(Long id) {
		
		return repository.findById(id).orElseThrow(
				 () -> new EntidadeNaoEncontradaException("Banco não encontrado"));
		
	}

}
