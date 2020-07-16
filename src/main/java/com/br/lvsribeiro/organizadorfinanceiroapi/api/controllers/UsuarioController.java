package com.br.lvsribeiro.organizadorfinanceiroapi.api.controllers;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Usuario;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.UsuarioRepository;

@RestController // Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		
		return ResponseEntity.ok(repository.findAll());
		
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {
		
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(repository.save(usuario));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		
		return ResponseEntity.ok(repository.findById(id));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id,
									   @RequestBody Usuario atualizado) {
		
		Optional<Usuario> atual = repository.findById(id);
		
		if(atual.isPresent()) {
			
			BeanUtils.copyProperties(atualizado, atual.get(), "id");
			
			return ResponseEntity.ok(repository.save(atual.get()));
			
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		
		repository.deleteById(id);
		
	}

}
