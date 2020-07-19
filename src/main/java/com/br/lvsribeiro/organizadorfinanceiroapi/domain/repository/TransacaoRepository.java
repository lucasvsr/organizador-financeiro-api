package com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	List<Transacao> findByContaId(Long conta);

	@Query("SELECT t FROM Transacao t "
		 + "WHERE t.conta.dono.id = :usuario "
		 + "ORDER BY t.dtTransacao DESC")
	List<Transacao> findByUsuario(Long usuario);

}
