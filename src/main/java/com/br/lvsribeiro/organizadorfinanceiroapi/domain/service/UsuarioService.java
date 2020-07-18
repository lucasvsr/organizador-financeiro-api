package com.br.lvsribeiro.organizadorfinanceiroapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.EntidadeNaoEncontradaException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Conta;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Usuario;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	ContaService contaService;

	@Autowired
	BancoService bancoService;

	public Usuario buscar(Long id) {

		return repository.findById(id).orElseThrow(
						 () -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

	}
	
	public Usuario salvar(Usuario entidade) {
		
		return repository.save(entidade);
		
	}
	
	public Conta addConta(Conta conta, Long id) {
		
		Usuario usuario = buscar(id);
		conta.setDono(usuario);
		
		return contaService.salvar(conta);
		
		
		
	}
	
	public void removerConta(Long conta, Long usuario) {
		
		Usuario u = buscar(usuario);
		
		contaService.remover(conta, u);
		
		
	}

}
