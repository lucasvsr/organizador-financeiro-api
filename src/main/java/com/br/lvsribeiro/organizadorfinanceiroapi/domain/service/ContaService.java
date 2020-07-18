package com.br.lvsribeiro.organizadorfinanceiroapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.ContaInformadaNaoPertenceAoUsuarioException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.EntidadeNaoEncontradaException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Banco;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Conta;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Usuario;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;
	
	@Autowired
	BancoService bancoService;
	

	public Conta buscar(Long id) {

		return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Conta não encontrada"));

	}

	public Conta salvar(Conta conta) {

		Banco banco = bancoService.buscar(conta.getBanco().getId());

		conta.setBanco(banco);

		return repository.save(conta);

	}

	public void remover(Long conta, Usuario u) {
		
		Conta c = buscar(conta);
		
		if(c.getDono().getId() == u.getId()) {
			
			repository.delete(c);
			
		} else {
			
			throw new ContaInformadaNaoPertenceAoUsuarioException("A conta informada não pertence ao usuário");
		}
		
		
	}

}
