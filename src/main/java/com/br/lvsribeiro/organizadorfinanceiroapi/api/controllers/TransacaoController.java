package com.br.lvsribeiro.organizadorfinanceiroapi.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Transacao;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.TransacaoRepository;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.service.TransacaoService;

@RestController // Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping(value = "/transacoes")
public class TransacaoController {
	
	@Autowired
	TransacaoRepository repository;
	
	@Autowired
	TransacaoService service;

	
	@GetMapping("/{usuario}") 
	public List<Transacao>listarPorUsuario(@PathVariable Long usuario) {
		
		return repository.findByUsuario(usuario);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Transacao salvar(@RequestBody Transacao transacao) {
		
		return service.salvar(transacao);
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Transacao alterar(@PathVariable Long id, @RequestBody Transacao transacao) {
		
		return service.alterar(id, transacao);
		
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		
		service.excluir(id);
		
	}

	

}
