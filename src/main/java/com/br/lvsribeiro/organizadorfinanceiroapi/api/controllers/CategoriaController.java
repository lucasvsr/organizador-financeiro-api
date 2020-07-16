package com.br.lvsribeiro.organizadorfinanceiroapi.api.controllers;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<?> listar() {
		
		return ResponseEntity.ok(repository.findAll());
		
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Categoria categoria) {
		
		try { 
		
			return ResponseEntity.status(HttpStatus.CREATED)
							 	 .body(service.salvar(categoria));
			
		} catch (CategoriaJaCadastradaException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
								 .body(e.getMessage());
			
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		
		Optional<Categoria> usuario = repository.findById(id);
		
		if(usuario.isPresent()) return ResponseEntity.ok(usuario);
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id,
									   @RequestBody Categoria atualizado) {
		
		Optional<Categoria> atual = repository.findById(id);
		
		if(atual.isPresent()) {
			
			BeanUtils.copyProperties(atualizado, atual.get(), "id", "dtCadastro");
			
			try {
				
				return ResponseEntity.ok(service.salvar(atual.get()));
				
			} catch (CategoriaJaCadastradaException e) {
				
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
				
			}
			
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		
		try {
			
			repository.deleteById(id);
			
			return ResponseEntity.noContent().build();
			
		} catch (EmptyResultDataAccessException e) {
			
			return ResponseEntity.notFound().build();
			
		}
		
	}

}
