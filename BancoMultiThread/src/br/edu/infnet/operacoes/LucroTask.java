package br.edu.infnet.operacoes;

import java.math.BigDecimal;
import java.util.List;
import java.util.TimerTask;

import br.edu.infnet.dao.BancoDAO;
import br.edu.infnet.dao.BancoDAOImpl;
import br.edu.infnet.dao.TransacaoDAO;
import br.edu.infnet.dao.TransacaoDAOImpl;
import br.edu.infnet.vo.Conta;
import br.edu.infnet.vo.ContaPoupanca;

public class LucroTask extends TimerTask {

	private BancoDAO bancoDAO = new BancoDAOImpl();
	private List<Conta> listaDeContaPoupanca;
	private TransacaoDAO transacaoDao = new TransacaoDAOImpl();

	public void setListaDeContaPoupanca(List<Conta> listaDeContaPoupanca) {
		this.listaDeContaPoupanca = listaDeContaPoupanca;
	}

	@Override
	public void run() {
		synchronized (this) {
			for (Conta c : listaDeContaPoupanca) {
				if (c instanceof ContaPoupanca) {
					Conta retorno = bancoDAO.getConta(c.getNumero());
					BigDecimal saldoReajuste = retorno.getSaldoAtual();
					saldoReajuste.add(saldoReajuste
							.multiply(ContaPoupanca.PERCENTUAL_LUCRO));
					bancoDAO.depositar(c, saldoReajuste);
					c.setSaldoAtual(saldoReajuste);
					transacaoDao.gravaTransacao(c, null,
							saldoReajuste, "Lucro de 0.8%");
					System.out.println("Transacao da conta " + c.getNumero()
							+ " gravada com sucesso");

				}
			}

		}
		System.out.println("Teste");

	}

}
