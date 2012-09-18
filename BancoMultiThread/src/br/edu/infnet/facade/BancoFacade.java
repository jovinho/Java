package br.edu.infnet.facade;

import java.math.BigDecimal;

import br.edu.infnet.dao.BancoDAOImpl;
import br.edu.infnet.dao.BancoDAO;
import br.edu.infnet.vo.Conta;

public class BancoFacade {
	
	BancoDAO bancoDAO = new BancoDAOImpl();
	
	
	public void sacar(Conta destino, BigDecimal valor) {
		
	}
	
	public void depositar(Conta destino, BigDecimal valor) {
		
	}
	
	public void transferir(Conta origem, Conta destino, BigDecimal valor) {
	
	}
	
	public Conta criarConta(Integer numero, BigDecimal saldoInicial) {
		return bancoDAO.criarConta(numero, saldoInicial); 
	}
	

}
