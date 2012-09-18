package br.edu.infnet.operacoes;

import java.math.BigDecimal;

import br.edu.infnet.dao.BancoDAO;
import br.edu.infnet.dao.BancoDAOImpl;
import br.edu.infnet.dao.TransacaoDAO;
import br.edu.infnet.dao.TransacaoDAOImpl;
import br.edu.infnet.vo.Conta;

public class Saque implements Runnable {

	private BancoDAO bancoDao = new BancoDAOImpl();
	private static Conta conta;
	private static Saque saque = new Saque();
	private TransacaoDAO transacaoDao = new TransacaoDAOImpl();

	private Saque() {

	}

	public static Saque getInstance() {
		return saque;
	}

	@Override
	public void run() {

		synchronized (this) {
			Conta retorno = bancoDao.getConta(conta.getNumero());
			if (null != retorno) {
				if (retorno.getSaldoAtual().compareTo(conta.getValorOperacao()) > 0) {

					BigDecimal result = bancoDao.sacar(conta,
							conta.getValorOperacao());

					if (BigDecimal.ZERO.compareTo(result) <= 0) {
						conta.setSaldoAtual(retorno.getSaldoAtual().subtract(
								result));
						transacaoDao.gravaTransacao(conta, null, conta.getValorOperacao(), "Saque");
						System.out.println("Transacao da conta " + conta.getNumero() + " gravada com sucesso");
						
						System.out.println("Deposito no valor de "
								+ conta.getValorOperacao()
								+ " realizado na conta " + conta.getNumero()
								+ " Saldo Atual : " + conta.getSaldoAtual());
					}
				} else {
					System.out
							.println("Saldo insuficiente para realizar saque");
					throw new RuntimeException();
				}
			} else {
				System.out.println("Conta " + conta.getNumero()
						+ " inexistente");
				throw new RuntimeException();
			}

		}

	}

	public static void setConta(Conta conta) {
		Saque.conta = conta;
	}

}
