package br.edu.infnet.operacoes;

import br.edu.infnet.conta.vo.Conta;
import br.edu.infnet.transacao.vo.Transacao;

public class Criador extends Operacao{
	

	private static Criador creator = new Criador();
	
	
	

	@Override
	public void run() {
			Transacao transacao = Transacao.getTransacoes().get(Integer.parseInt(Thread.currentThread().getName()));
			Conta conta = transacao.getContaOrigem();
			synchronized (conta) {
			System.out.println("Criando Conta " + transacao.getContaOrigem().getNumero());
			bancoDao.criarConta(conta);
			System.out.println("Gravando Transacao da conta " + transacao.getContaOrigem().getNumero());
			transacaoDao.gravaTransacao(transacao);
			System.out.println("Transacao da conta " + transacao.getContaOrigem().getNumero() + " gravada com sucesso");
			System.out.println("Conta " + transacao.getContaOrigem().getNumero() + " criada com Sucesso");
			}
		
		
	}
	public static Criador getInstance() {
		// TODO Auto-generated method stub
		return creator;
	}
	
	
	
	


}
