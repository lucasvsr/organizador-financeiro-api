package com.br.lvsribeiro.organizadorfinanceiroapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.CategoriaJaCadastradaException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Categoria;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Usuario;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.CategoriaRepository;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.UsuarioRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repository;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Categoria salvar(Categoria categoria) throws CategoriaJaCadastradaException {
		
		Usuario criador = usuarioRepository.findById(categoria.getCriador().getId()).get();
		
		categoria.setCriador(criador);
		
		if(repository.existsByDescricaoAndCriador(categoria.getDescricao(), categoria.getCriador())) {
		
			throw new CategoriaJaCadastradaException(categoria);
			
		}
			
			
		return repository.save(categoria);
			
	}

}
