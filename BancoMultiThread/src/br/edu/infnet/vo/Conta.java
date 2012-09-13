package br.edu.infnet.vo;

import java.math.BigDecimal;

public class Conta {
	
	private Integer numero;
	private BigDecimal saldoAtual;
	private BigDecimal saldoAnterior;
	private BigDecimal valorOperacao;
	
	
	
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
	public BigDecimal getSaldoAnterior() {
		return saldoAnterior;
	}
	public void setSaldoAnterior(BigDecimal saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}
	public BigDecimal getValorOperacao() {
		return valorOperacao;
	}
	public void setValorOperacao(BigDecimal valorOperacao) {
		this.valorOperacao = valorOperacao;
	}
	
	

}
