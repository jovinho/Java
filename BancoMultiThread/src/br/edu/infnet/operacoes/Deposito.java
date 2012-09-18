package br.edu.infnet.operacoes;

import java.math.BigDecimal;

import br.edu.infnet.dao.BancoDAOImpl;
import br.edu.infnet.dao.BancoDAO;
import br.edu.infnet.dao.TransacaoDAO;
import br.edu.infnet.dao.TransacaoDAOImpl;
import br.edu.infnet.vo.Conta;

public class Deposito implements Runnable {

	private BancoDAO bancoDao = new BancoDAOImpl();
	private static Conta conta;
	private static Deposito deposito = new Deposito();
	private TransacaoDAO transacaoDao = new TransacaoDAOImpl();

	private Deposito() {

	}

	public static Deposito getInstance() {
		return deposito;
	}

	@Override
	public void run() {
		if (conta != null) {
			synchronized (this) {
				Conta retorno = bancoDao.getConta(conta.getNumero());
				if (null != retorno) {
					boolean result = bancoDao.depositar(conta,
							conta.getValorOperacao());
					if (result) {
						transacaoDao.gravaTransacao(conta, null, conta.getValorOperacao(), "Deposito");
						System.out.println("Transacao da conta " + conta.getNumero() + " gravada com sucesso");
						System.out.println("Deposito no valor de "
								+ conta.getValorOperacao()
								+ " realizado na conta " + conta.getNumero()
								+ " Saldo Atual : " + conta.getSaldoAtual());
					}
				} else {
					System.out.println("Conta " + conta.getNumero()
							+ " inexistente");
					throw new RuntimeException();
				}

			}
		}else{
			throw new RuntimeException();
		}
	}


	public static void setConta(Conta conta) {
		Deposito.conta = conta;
	}

}
