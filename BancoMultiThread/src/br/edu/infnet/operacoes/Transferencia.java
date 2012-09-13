package br.edu.infnet.operacoes;

import br.edu.infnet.dao.BancoDAO;
import br.edu.infnet.dao.DAO;
import br.edu.infnet.vo.Conta;

public class Transferencia implements Runnable{
	
	private DAO bancoDao = new BancoDAO();
	private static Conta conta;
	
	
	@Override
	public void run() {
		
		
	}

}
