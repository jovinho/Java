package br.edu.infnet.operacoes;

import java.math.BigDecimal;

import br.edu.infnet.conta.vo.Conta;
import br.edu.infnet.transacao.vo.Transacao;

public class Deposito extends Operacao {


	private static Deposito deposito = new Deposito();
	

	private Deposito() {

	}

	public static Deposito getInstance() {
		return deposito;
	}

	@Override
	public void run() {
		Transacao transacao = Transacao.getTransacoes().get(Integer.parseInt(Thread.currentThread().getName()));
		Conta conta = transacao.getContaOrigem();	
		
				Conta retorno = bancoDao.getConta(conta.getNumero());
				if (null != retorno) {
					double saldoComDeposito = retorno.getSaldoAtual().doubleValue() + transacao.getValorTransacao().doubleValue();
					BigDecimal valor = new BigDecimal(saldoComDeposito);
					boolean result = bancoDao.depositar(conta,
							valor);
					if (result) {
						transacaoDao.gravaTransacao(transacao);
						System.out.println("Transacao da conta " + conta.getNumero() + " gravada com sucesso");
						System.out.println("Deposito no valor de "
								+ transacao.getValorTransacao()
								+ " realizado na conta " + conta.getNumero());
					}
				} else {
					System.out.println("Conta " + conta.getNumero()
							+ " inexistente");
					throw new RuntimeException();
				}

			}
		
}
