package br.edu.infnet.operacoes;


import java.util.Date;
import java.util.List;
import java.util.Timer;

import br.edu.infnet.conta.vo.Conta;

public class Lucro {
	

	private Timer timer;


	public Lucro(Integer seconds, List<Conta> contas){
		timer = new Timer();
		LucroTask lucroTask = new LucroTask();
		timer.schedule(lucroTask, new Date(), seconds*1000);
	}
	
	
	


	
	
	
	
	

}
