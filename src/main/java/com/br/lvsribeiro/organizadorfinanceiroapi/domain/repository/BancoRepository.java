package com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Banco;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.enums.TipoBancoEnum;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
	
	boolean existsByNomeAndTipo(String nome, TipoBancoEnum tipo);

}
