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

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Conta;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Usuario;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.UsuarioRepository;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.service.UsuarioService;

@RestController // Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	UsuarioService service;

	@GetMapping
	public List<Usuario> listar() {

		return repository.findAll();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody Usuario usuario) {

		return service.salvar(usuario);

	}

	@GetMapping("/{id}")
	public Usuario buscar(@PathVariable Long id) {

		return service.buscar(id);

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario atualizado) {

		Usuario atual = service.buscar(id);

		BeanUtils.copyProperties(atualizado, atual, "id", "contas");

		return service.salvar(atual);
		
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {

		repository.delete(service.buscar(id));

	}

	@GetMapping("/{id}/contas")
	@ResponseStatus(HttpStatus.OK)
	public List<Conta> listarContas(@PathVariable Long id) {

		Usuario usuario = service.buscar(id);

		return usuario.getContas();

	}
	
	@PutMapping("/{id}/contas")
	@ResponseStatus(HttpStatus.OK)
	public Conta adicionarConta(@PathVariable Long id, @RequestBody Conta conta) {
		
		return service.addConta(conta, id);
		
	}
	
	@DeleteMapping("/{id}/contas/{conta}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerConta(@PathVariable Long id, @PathVariable Long conta) {
		
		service.removerConta(conta, id);
		
	}

}
