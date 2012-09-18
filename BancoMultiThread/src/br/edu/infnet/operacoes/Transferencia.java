package br.edu.infnet.operacoes;

import br.edu.infnet.dao.BancoDAOImpl;
import br.edu.infnet.dao.BancoDAO;
import br.edu.infnet.vo.Conta;

public class Transferencia implements Runnable {

	private static Conta contaOrigem;
	private static Conta contaDestino;
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
		synchronized (this) {
			if (contaOrigem != null && contaDestino != null) {
				if (!contaOrigem.equals(contaDestino)) {
					saque = Saque.getInstance();
					deposito = Deposito.getInstance();
					Saque.setConta(contaOrigem);
					Deposito.setConta(contaDestino);
					Thread deposito = new Thread(this.deposito);
					Thread saque = new Thread(this.saque);
					deposito.setPriority(Thread.MAX_PRIORITY);
					saque.setPriority(Thread.NORM_PRIORITY);
					try {
						deposito.start();
					} catch (Exception e) {
						System.out
								.println("Ocorreu um erro na transferência, impossível continuar");
					}
					try {
						saque.start();
						deposito.join();
					} catch (Exception e) {
						System.out
								.println("Ocorreu um erro na transferência, impossível continuar");
					}

				} else {
					System.out
							.println("Impossível realizar transferências na mesma conta");
				}
			} else {
				System.out.println("Contas nulas");
			}
		}

	}
	
	public static void setContaOrigem(Conta contaOrigem) {
		Transferencia.contaOrigem = contaOrigem;
	}

	public static void setContaDestino(Conta contaDestino) {
		Transferencia.contaDestino = contaDestino;
	}


}
