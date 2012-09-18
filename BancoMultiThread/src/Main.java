import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.operacoes.Creator;
import br.edu.infnet.operacoes.Deposito;
import br.edu.infnet.operacoes.Lucro;
import br.edu.infnet.operacoes.Saque;
import br.edu.infnet.operacoes.Transferencia;
import br.edu.infnet.vo.Conta;
import br.edu.infnet.vo.ContaCorrente;
import br.edu.infnet.vo.ContaPoupanca;


public class Main {
	
	public static void main(String[] args) {
		
	Creator creator = Creator.getInstance();
	Deposito deposito = Deposito.getInstance();
	Transferencia transferencia = Transferencia.getInstance();
	List<Conta> contas = new ArrayList<Conta>();
	
	//Gera as contas no programa
	for(int i = 0; i < 4; i++){
		Conta conta;
		if (i%2 == 0){
			conta = new ContaPoupanca();
			
		}else{
		    conta = new ContaCorrente();
		}
		conta.setNumero(i);
		conta.setSaldoAtual(BigDecimal.ONE);
		contas.add(conta);
	}
	
	// Cria contas no banco 
	for(Conta c : contas){
		Creator.setConta(c);
		Thread thread = new Thread(creator);
		thread.setName(c.getNumero().toString());
		thread.start();
	}
	
	 new Lucro(10,contas);

	for(Conta c : contas){
		Deposito.setConta(c);
		Thread thread = new Thread(creator);
		thread.setName(c.getNumero().toString());
		thread.start();
		
	}	
	
	
	
	for (Conta c : contas){
		Saque.setConta(c);
		Thread thread = new Thread(deposito);
		thread.setName(c.getNumero().toString());
		thread.start();
	}
	
	
	
	
	for (Conta c : contas){
		Transferencia.setContaOrigem(c);
		Transferencia.setContaOrigem(contas.get(0));
		Thread thread = new Thread(transferencia);
		thread.start();
	}
		
	
		
		
		
		
	}

}
