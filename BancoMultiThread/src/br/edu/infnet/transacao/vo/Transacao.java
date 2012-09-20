package br.edu.infnet.transacao.vo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import br.edu.infnet.conta.vo.Conta;

public class Transacao {
	
	private static Map<Integer,Transacao> transacoes = new HashMap<Integer, Transacao>();
	
	private Conta contaOrigem;
	private Conta contaDestino;
	private String tipoOperacao;
	private BigDecimal valorTransacao;
	
	
	public Conta getContaOrigem() {
		return contaOrigem;
	}
	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	public Conta getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}
	public String getTipoOperacao() {
		return tipoOperacao;
	}
	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	public BigDecimal getValorTransacao() {
		return valorTransacao;
	}
	public void setValorTransacao(BigDecimal valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public static Map<Integer, Transacao> getTransacoes() {
		return transacoes;
	}
	
	
	
	
	

}
