package com.br.lvsribeiro.organizadorfinanceiroapi.domain.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.lvsribeiro.organizadorfinanceiroapi.domain.enums.TipoCategoriaEnum;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.ContaDiferenteCadastradaException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.exception.EntidadeNaoEncontradaException;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Categoria;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Conta;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.model.Transacao;
import com.br.lvsribeiro.organizadorfinanceiroapi.domain.repository.TransacaoRepository;

@Service
public class TransacaoService {

	@Autowired
	private ContaService contaService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private TransacaoRepository repository;

	public Transacao salvar(Transacao transacao) {

		Conta conta = contaService.buscar(transacao.getConta().getId());
		Categoria categoria = categoriaService.buscar(transacao.getCategoria().getId());
		BigDecimal saldo = atualizarSaldo(conta.getSaldo(), categoria.getTipo(), transacao.getValor());

		conta.setSaldo(saldo);

		transacao.setConta(conta);
		transacao.setCategoria(categoria);

		return repository.save(transacao);

	}

	public Transacao alterar(Long id, Transacao transacao) {

		Transacao t = buscar(id);
		Categoria categoriaSalva = t.getCategoria();
		Categoria categoriaNova = transacao.getCategoria().getId() == null ? null
				: categoriaService.buscar(transacao.getCategoria().getId());
		BigDecimal saldoAnterior = recuperarSaldoAnterior(t);

		t.setDescricao(transacao.getDescricao() == null ? t.getDescricao() : transacao.getDescricao());
		t.setDtTransacao(transacao.getDtTransacao() == null ? t.getDtTransacao() : transacao.getDtTransacao());

		if (t.getConta().getId() != transacao.getConta().getId())
			throw new ContaDiferenteCadastradaException("Não é possível alterar a conta de uma transação já salva");

		if (categoriaSalva.getId() != categoriaNova.getId()) {

			if (categoriaNova.getTipo() == TipoCategoriaEnum.GASTO) {

				t.setValor(transacao.getValor() == null ? t.getValor() : transacao.getValor());
				t.getConta().setSaldo(atualizarSaldo(saldoAnterior, TipoCategoriaEnum.GASTO, transacao.getValor()));

			} else {

				t.setValor(transacao.getValor() == null ? t.getValor() : transacao.getValor());
				t.getConta().setSaldo(atualizarSaldo(saldoAnterior, TipoCategoriaEnum.RENDA, transacao.getValor()));

			}

		}
		
		if(t.getValor() != transacao.getValor()) {
			
			t.setValor(transacao.getValor());
			t.getConta().setSaldo(atualizarSaldo(saldoAnterior, t.getCategoria().getTipo(), transacao.getValor()));
		}

		return repository.save(t);

	}

	public void excluir(Long id) {

		Transacao transacao = buscar(id);
		Conta conta = transacao.getConta();
		BigDecimal saldo = transacao.getConta().getSaldo();
		BigDecimal valor = transacao.getValor();

		if (transacao.getCategoria().getTipo() == TipoCategoriaEnum.GASTO) {

			conta.setSaldo(atualizarSaldo(saldo, TipoCategoriaEnum.RENDA, valor));
			contaService.salvar(conta);

		} else {

			conta.setSaldo(atualizarSaldo(saldo, TipoCategoriaEnum.GASTO, valor));
			contaService.salvar(conta);

		}

		repository.delete(transacao);

	}

	public Transacao buscar(Long id) {

		return repository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Transação não encontrada"));

	}

	private BigDecimal atualizarSaldo(BigDecimal saldo, TipoCategoriaEnum tipo, BigDecimal valor) {

		switch (tipo) {

			case GASTO: {

				return saldo.subtract(valor);
			}

			case RENDA: {

				return saldo.add(valor);

			}
		}

		return valor;

	}
	
	private BigDecimal recuperarSaldoAnterior(Transacao transacao) {

		if (transacao.getCategoria().getTipo() == TipoCategoriaEnum.GASTO) return atualizarSaldo(transacao.getConta().getSaldo(),
																								 TipoCategoriaEnum.RENDA,
																								 transacao.getValor());

		return atualizarSaldo(transacao.getConta().getSaldo(), TipoCategoriaEnum.GASTO, transacao.getValor());

	}

}