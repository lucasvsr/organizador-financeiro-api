package com.br.lvsribeiro.organizadorfinanceiroapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.expection.TipoDeBancoNaoAceitoException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Banco;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.BancoRepository;

@Service
public class BancoService {
	
	@Autowired
	BancoRepository repository;
	
	public Banco salvar(Banco banco) throws TipoDeBancoNaoAceitoException {
		
		
		try {
			
			return repository.save(banco);
			
		} catch (HttpMessageNotReadableException e) {
			
			throw new TipoDeBancoNaoAceitoException();
			
		}
		
	}

}
