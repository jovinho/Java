package br.edu.infnet.facade;

import java.math.BigDecimal;

import br.edu.infnet.dao.BancoDAO;
import br.edu.infnet.dao.DAO;
import br.edu.infnet.vo.Conta;

public class BancoFacade {
	
	DAO bancoDAO = new BancoDAO();
	
	
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
