package br.edu.infnet.operacoes;

import java.math.BigDecimal;
import java.util.List;
import java.util.TimerTask;

import br.edu.infnet.conta.dao.ContaDAO;
import br.edu.infnet.conta.dao.ContaDAOImpl;
import br.edu.infnet.conta.vo.Conta;
import br.edu.infnet.conta.vo.ContaPoupanca;
import br.edu.infnet.transacao.dao.TransacaoDAO;
import br.edu.infnet.transacao.dao.TransacaoDAOImpl;
import br.edu.infnet.transacao.vo.Transacao;

public class LucroTask extends TimerTask {

	private ContaDAO bancoDAO = new ContaDAOImpl();
	private TransacaoDAO transacaoDao = new TransacaoDAOImpl();


	@Override
	public void run() {
		synchronized (this) {
			List<Conta> listaDeContas = bancoDAO.recuperarContas();
			Transacao transacao = new Transacao();
			transacao.setTipoOperacao("Lucro de 0.8%");
			for (Conta c : listaDeContas) {
				if (c instanceof ContaPoupanca) {
					transacao.setContaOrigem(c);
					Conta retorno = bancoDAO.getConta(c.getNumero());
					double reajuste = retorno.getSaldoAtual().doubleValue() * ContaPoupanca.PERCENTUAL_LUCRO.doubleValue();
					double valor = retorno.getSaldoAtual().doubleValue() +  reajuste;
					BigDecimal saldoReajuste = new BigDecimal(valor);
					bancoDAO.depositar(c, saldoReajuste);
					c.setSaldoAtual(saldoReajuste);
					transacao.setValorTransacao(new BigDecimal(reajuste));
					transacaoDao.gravaTransacao(transacao); 
				
					System.out.println("Transacao de lucro da conta " + c.getNumero()
							+ " gravada com sucesso");

				}
			}

		}
		

	}

}
