package com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
