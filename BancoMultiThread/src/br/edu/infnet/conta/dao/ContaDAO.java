package br.edu.infnet.conta.dao;

import java.math.BigDecimal;
import java.util.List;

import br.edu.infnet.conta.vo.Conta;

public interface ContaDAO {
	
	public boolean depositar(Conta destino, BigDecimal valor);
	public BigDecimal sacar(Conta destino, BigDecimal valor);
	public Conta getConta(Integer numro);
	public Conta criarConta(Conta conta);
	public List<Conta> recuperarContas();
}
	