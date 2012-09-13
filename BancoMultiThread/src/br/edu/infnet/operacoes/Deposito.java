package br.edu.infnet.operacoes;

import br.edu.infnet.dao.BancoDAO;
import br.edu.infnet.dao.DAO;
import br.edu.infnet.vo.Conta;

public class Deposito implements Runnable {

	private DAO bancoDao = new BancoDAO();
	private static Conta conta;
	private static Deposito deposito = new Deposito();

	private Deposito() {

	}

	public Deposito getInstance() {
		return deposito;
	}

	@Override
	public void run() {
		if (conta != null) {
			synchronized (conta) {
				Conta retorno = bancoDao.getConta(conta.getNumero());
				if (null != retorno) {
					conta.setSaldoAnterior(retorno.getSaldoAtual());
					conta.setSaldoAtual(retorno.getSaldoAtual().add(conta.getValorOperacao()));
					boolean result = bancoDao.depositar(conta,
							conta.getValorOperacao());
					if (result) {
						System.out.println("Deposito no valor de "
								+ conta.getValorOperacao()
								+ " realizado na conta " + conta.getNumero()
								+ " Saldo Atual : " + conta.getSaldoAtual());
					}
				} else {
					System.out.println("Conta " + conta.getNumero()
							+ " inexistente");
				}

			}
		}
	}

	public void setConta(Conta conta) {
		Deposito.conta = conta;
	}

}
