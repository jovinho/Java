package br.edu.infnet.operacoes;

import java.math.BigDecimal;

import br.edu.infnet.conta.vo.Conta;
import br.edu.infnet.transacao.vo.Transacao;

public class Saque extends Operacao {


	private static Saque saque = new Saque();


	private Saque() {

	}

	public static Saque getInstance() {
		return saque;
	}

	@Override
	public void run() {
		Transacao transacao = Transacao.getTransacoes().get(
				Integer.parseInt(Thread.currentThread().getName()));
		Conta conta = null;
		if (transacao.getTipoOperacao().equals("Transacao")){
			conta = transacao.getContaDestino();
		}else{
			conta = transacao.getContaOrigem();
		}
		synchronized (conta) {
			Conta retorno = bancoDao.getConta(conta.getNumero());
			if (null != retorno) {
				if (retorno.getSaldoAtual().compareTo(
						transacao.getValorTransacao()) > 0) {
					double saldoRestante = retorno.getSaldoAtual()
							.doubleValue()
							- transacao.getValorTransacao().doubleValue();

					BigDecimal result = bancoDao.sacar(conta, new BigDecimal(
							saldoRestante));

					if (BigDecimal.ZERO.compareTo(result) <= 0) {
						transacaoDao.gravaTransacao(transacao);
						System.out.println("Transacao da conta "
								+ conta.getNumero() + " gravada com sucesso");

						System.out.println("Saque no valor de "
								+ transacao.getValorTransacao()
								+ " realizado da conta " + conta.getNumero());
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

	

}
