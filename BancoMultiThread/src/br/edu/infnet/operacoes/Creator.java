package br.edu.infnet.operacoes;

import java.math.BigDecimal;

import br.edu.infnet.dao.BancoDAO;
import br.edu.infnet.dao.BancoDAOImpl;
import br.edu.infnet.dao.TransacaoDAO;
import br.edu.infnet.dao.TransacaoDAOImpl;
import br.edu.infnet.vo.Conta;
import br.edu.infnet.vo.ContaCorrente;

public class Creator implements Runnable{
	
	private BancoDAO bancoDao = new BancoDAOImpl();
	private TransacaoDAO transacaoDao = new TransacaoDAOImpl();
	private static Creator creator = new Creator();
	private static Conta conta;
	@Override
	public void run() {
		
			synchronized (this) {
			conta.setNumero(Integer.parseInt(Thread.currentThread().getName()));
			System.out.println("Criando Conta " + Thread.currentThread().getName());
			bancoDao.criarConta(Integer.parseInt(Thread.currentThread().getName()), new BigDecimal(10.0));
			System.out.println("Gravando Transacao da conta " + Thread.currentThread().getName());
			transacaoDao.gravaTransacao(conta, null, BigDecimal.ZERO, "Criação de conta");
			System.out.println("Transacao da conta " + Thread.currentThread().getName() + " gravada com sucesso");
			System.out.println("Conta " + Thread.currentThread().getName() + " criada com Sucesso");
			}
		
		
	}
	public static Creator getInstance() {
		// TODO Auto-generated method stub
		return creator;
	}
	
	public static void setConta(Conta conta){
		Creator.conta = conta;
	}

}
