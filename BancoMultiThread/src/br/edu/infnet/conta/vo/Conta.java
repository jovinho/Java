package br.edu.infnet.conta.vo;

import java.math.BigDecimal;

public abstract class Conta {
	
	private Integer numero;
	private BigDecimal saldoAtual;

	
	
	
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}
	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}
	

	
	

}
