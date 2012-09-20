package br.edu.infnet.operacoes;

import br.edu.infnet.conta.vo.Conta;
import br.edu.infnet.transacao.vo.Transacao;

public class Transferencia extends Operacao {


	private Saque saque;
	private Deposito deposito;
	private static Transferencia transferencia = new Transferencia();

	private Transferencia() {

	}

	public static Transferencia getInstance() {
		return transferencia;
	}

	@Override
	public void run() {
		Transacao transacao = Transacao.getTransacoes().get(Integer.parseInt(Thread.currentThread().getName()));
		Integer numeroTransacao = Integer.parseInt(Thread.currentThread().getName());
		Conta contaOrigem = transacao.getContaOrigem();
		Conta contaDestino = transacao.getContaDestino();
		synchronized (contaOrigem) {
			if (contaOrigem != null && contaDestino != null) {
				if (!contaOrigem.equals(contaDestino)) {
					saque = Saque.getInstance();
					deposito = Deposito.getInstance();
					Thread deposito = new Thread(this.deposito);
					deposito.setName(numeroTransacao.toString());
					Thread saque = new Thread(this.saque);
					saque.setName(numeroTransacao.toString());
					deposito.setPriority(Thread.MAX_PRIORITY);
					saque.setPriority(Thread.NORM_PRIORITY);
					try {
						
						saque.start();
						deposito.start();
						saque.join();
					} catch (Exception e) {
						System.out
								.println("Ocorreu um erro na transfer�ncia, imposs�vel continuar");
					}

				} else {
					System.out
							.println("Imposs�vel realizar transfer�ncias na mesma conta");
				}
			} else {
				System.out.println("Contas nulas");
			}
		}

	}
	
	


}
