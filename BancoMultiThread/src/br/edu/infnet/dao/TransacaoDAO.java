package br.edu.infnet.dao;

import java.math.BigDecimal;

import br.edu.infnet.vo.Conta;

public interface TransacaoDAO {
	
	

	void gravaTransacao(Conta origem, Conta destino, BigDecimal valor, String nomeOperacao);



}
