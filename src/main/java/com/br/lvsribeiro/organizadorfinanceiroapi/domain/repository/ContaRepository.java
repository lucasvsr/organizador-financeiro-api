package com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Conta;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Usuario;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	Optional<Conta> findByIdAndDono(Long conta, Usuario dono);

}
