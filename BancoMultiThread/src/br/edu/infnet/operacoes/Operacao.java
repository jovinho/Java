package br.edu.infnet.operacoes;

import br.edu.infnet.conta.dao.ContaDAO;
import br.edu.infnet.conta.dao.ContaDAOImpl;
import br.edu.infnet.transacao.dao.TransacaoDAO;
import br.edu.infnet.transacao.dao.TransacaoDAOImpl;

public abstract class Operacao implements Runnable {

	protected ContaDAO bancoDao = new ContaDAOImpl();
	protected TransacaoDAO transacaoDao = new TransacaoDAOImpl();
	

	
	

}
