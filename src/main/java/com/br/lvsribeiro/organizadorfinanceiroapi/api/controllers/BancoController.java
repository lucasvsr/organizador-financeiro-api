package com.br.lvsribeiro.organizadorfinanceiroapi.api.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.BancoJaCadastradoException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Banco;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.BancoRepository;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.service.BancoService;

@RestController // Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping(value = "/bancos")
public class BancoController {
	
	@Autowired
	BancoRepository repository;
	
	@Autowired
	BancoService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Banco> listar() {
		
		return repository.findAll();
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Banco salvar(@RequestBody Banco banco) throws BancoJaCadastradoException {

		return service.salvar(banco);
			
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Banco buscar(@PathVariable Long id) {
		
		return service.buscar(id);
		
	}
	
	@PutMapping("/{id}")
	public Banco atualizar(@PathVariable Long id,
						   @RequestBody Banco atualizado) throws BancoJaCadastradoException {
		
		Banco atual = service.buscar(id);
		
		BeanUtils.copyProperties(atualizado, atual, "id", "dtCadastro");

		return service.salvar(atual);
				

	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		
		service.remover(id);
		
	}

}
