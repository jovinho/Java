package br.edu.infnet.main;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.conta.vo.Conta;
import br.edu.infnet.conta.vo.ContaCorrente;
import br.edu.infnet.conta.vo.ContaPoupanca;
import br.edu.infnet.operacoes.Criador;
import br.edu.infnet.operacoes.Deposito;
import br.edu.infnet.operacoes.Lucro;
import br.edu.infnet.operacoes.Operacao;
import br.edu.infnet.operacoes.Saque;
import br.edu.infnet.operacoes.Transferencia;
import br.edu.infnet.transacao.vo.Transacao;


public class Main {
	
	public static void main(String[] args) {

	Operacao criador = Criador.getInstance();
	Operacao deposito = Deposito.getInstance();
	Operacao saque = Saque.getInstance();
	Operacao transferencia = Transferencia.getInstance();
	List<Conta> contas = new ArrayList<Conta>();
	
	

	
//Gera as contas no programa
	for(int i = 0; i < 4; i++){
		Conta conta;
		if (i%2 == 0){
			conta = new ContaPoupanca();
		}else{
		    conta = new ContaCorrente();
		}
		conta.setSaldoAtual(BigDecimal.TEN);
		conta.setNumero(i);
		contas.add(conta);
	}
	
	// Cria contas no banco 
	for(Conta c : contas){
		Integer numeroTransacao = Transacao.getTransacoes().size()+1;
		Transacao transacao = new Transacao();
		transacao.setContaOrigem(c);
		transacao.setTipoOperacao("Criação de Conta");
		transacao.setValorTransacao(new BigDecimal(10.0));
		Transacao.getTransacoes().put(numeroTransacao, transacao);
		Thread thread = new Thread(criador);
		thread.setName(numeroTransacao.toString());
		thread.start();
	}
	
	 new Lucro(300,contas);
	
	
	for(Conta c : contas){
		Integer numeroTransacao = Transacao.getTransacoes().size()+1;
		Transacao transacao = new Transacao();
		transacao.setContaOrigem(c);
		transacao.setTipoOperacao("Deposito");
		transacao.setValorTransacao(new BigDecimal(50));
		Transacao.getTransacoes().put(numeroTransacao, transacao);
		Thread thread = new Thread(deposito);
		thread.setName(numeroTransacao.toString());
		thread.start();
		
	}	
	
	
	
	for (Conta c : contas){
		Integer numeroTransacao = Transacao.getTransacoes().size()+1;
		Transacao transacao = new Transacao();
		transacao.setContaOrigem(c);
		transacao.setTipoOperacao("Saque");
		transacao.setValorTransacao(new BigDecimal(30));
		Transacao.getTransacoes().put(numeroTransacao, transacao);
		Thread thread = new Thread(saque);
		thread.setName(numeroTransacao.toString());
		thread.start();
	}
	

	
	for (Conta c : contas){
	Integer numeroTransacao = Transacao.getTransacoes().size()+1;
		Transacao transacao = new Transacao();
		transacao.setContaOrigem(c);
		transacao.setContaDestino(contas.get(1));
		transacao.setTipoOperacao("Transferencia");
		transacao.setValorTransacao(new BigDecimal(30));
		Transacao.getTransacoes().put(numeroTransacao, transacao);
		Thread thread = new Thread(transferencia);
		thread.setName(numeroTransacao.toString());
		
		thread.start();
	}
		
	
		
		
		
		
	}

}
