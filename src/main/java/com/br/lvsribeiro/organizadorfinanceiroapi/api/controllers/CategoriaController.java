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

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.CategoriaJaCadastradaException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Categoria;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.CategoriaRepository;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.service.CategoriaService;

@RestController // Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository repository;
	
	@Autowired
	CategoriaService service;
	
	@GetMapping
	public List<Categoria> listar() {
		
		return repository.findAll();
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria salvar(@RequestBody Categoria categoria) throws CategoriaJaCadastradaException {
		
		return service.salvar(categoria);
		
	}
	
	@GetMapping("/{id}")
	public Categoria buscar(@PathVariable Long id) {
		
		return service.buscar(id);
		
	}
	
	@PutMapping("/{id}")
	public Categoria atualizar(@PathVariable Long id,
							   @RequestBody Categoria atualizado) throws CategoriaJaCadastradaException {
		
		Categoria atual = service.buscar(id);
		
		BeanUtils.copyProperties(atualizado, atual, "id", "dtCriacao", "criador");
					
		return service.salvar(atual);
			

	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		
		service.remover(id);
		
	}

}
