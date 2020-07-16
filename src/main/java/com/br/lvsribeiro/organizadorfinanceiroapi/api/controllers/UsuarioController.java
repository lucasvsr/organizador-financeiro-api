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

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Banco;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Conta;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Usuario;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.BancoRepository;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.ContaRepository;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.UsuarioRepository;

@RestController // Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	BancoRepository bancoRepository;

	@GetMapping
	public ResponseEntity<?> listar() {

		return ResponseEntity.ok(repository.findAll());

	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {

		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {

		Optional<Usuario> usuario = repository.findById(id);

		if (usuario.isPresent())
			return ResponseEntity.ok(usuario);

		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario atualizado) {

		Optional<Usuario> atual = repository.findById(id);

		if (atual.isPresent()) {

			BeanUtils.copyProperties(atualizado, atual.get(), "id", "contas");

			return ResponseEntity.ok(repository.save(atual.get()));

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

	@GetMapping("/{id}/contas")
	public ResponseEntity<?> listarContas(@PathVariable Long id) {

		Optional<Usuario> usuario = repository.findById(id);

		if (usuario.isPresent())
			return ResponseEntity.ok(usuario.get().getContas());

		return ResponseEntity.notFound().build();

	}
	
	@PutMapping("/{id}/contas")
	public ResponseEntity<?> adicionarConta(@PathVariable Long id, @RequestBody Conta conta) {
		
		Optional<Usuario> usuario = repository.findById(id);
		Optional<Banco> banco = bancoRepository.findById(conta.getBanco().getId());
		
		if(usuario.isPresent() && banco.isPresent()) {
			
			conta.setDono(usuario.get());
			conta.setBanco(banco.get());
			
			return ResponseEntity.ok(contaRepository.save(conta));
			
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}/contas/{conta}")
	public ResponseEntity<?> removerConta(@PathVariable Long id, @PathVariable Long conta) {
		
		try {

			contaRepository.deleteById(conta);

			return ResponseEntity.noContent().build();

		} catch (EmptyResultDataAccessException e) {

			return ResponseEntity.notFound().build();

		}
		
	}

}
