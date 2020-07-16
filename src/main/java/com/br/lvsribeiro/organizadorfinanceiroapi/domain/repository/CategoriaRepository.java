package com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Categoria;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Usuario;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	boolean existsByDescricaoAndCriador(String descricao, Usuario criador);

}
