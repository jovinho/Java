package br.edu.infnet.operacoes;

import java.math.BigDecimal;

import br.edu.infnet.dao.BancoDAO;
import br.edu.infnet.dao.DAO;
import br.edu.infnet.vo.Conta;

public class Saque implements Runnable {

	private DAO bancoDao = new BancoDAO();
	private static Conta conta;

	@Override
	public void run() {
		if (conta != null) {
			synchronized (conta) {
				Conta retorno = bancoDao.getConta(conta.getNumero());
				if (null != retorno) {
					if (retorno.getSaldoAtual().compareTo(conta.getValorOperacao()) > 0){
						conta.setSaldoAtual(retorno.getSaldoAtual().subtract(conta.getValorOperacao()));
						BigDecimal result = bancoDao.sacar(conta,
								conta.getValorOperacao());
						if (BigDecimal.ZERO.compareTo(result) <= 0) {
							System.out.println("Deposito no valor de "
									+ conta.getValorOperacao()
									+ " realizado na conta " + conta.getNumero()
									+ " Saldo Atual : " + conta.getSaldoAtual());
						}
					}else{
						System.out.println("Saldo insuficiente para realizar saque");
					}
				} else {
					System.out.println("Conta " + conta.getNumero()
							+ " inexistente");
				}

			}
		}

	}

	public void setConta(Conta conta) {
		Saque.conta = conta;
	}

}
