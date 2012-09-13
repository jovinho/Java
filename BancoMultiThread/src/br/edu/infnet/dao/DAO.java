package br.edu.infnet.dao;

import java.math.BigDecimal;

import br.edu.infnet.vo.Conta;

public interface DAO {
	
	public boolean depositar(Conta destino, BigDecimal valor);
	public BigDecimal sacar(Conta destino, BigDecimal valor);
	public Conta getConta(Integer numero);
	public Conta criarConta(Integer numero, BigDecimal saldoInicial);
	
	
	
	

}
