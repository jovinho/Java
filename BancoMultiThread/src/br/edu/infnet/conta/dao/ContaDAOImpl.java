package br.edu.infnet.conta.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.conta.vo.Conta;
import br.edu.infnet.conta.vo.ContaCorrente;
import br.edu.infnet.conta.vo.ContaPoupanca;
import br.edu.infnet.jdbc.factory.ConnectionFactory;

public class ContaDAOImpl implements ContaDAO {

	private Connection connection;

	public ContaDAOImpl() {
		connection = ConnectionFactory.getConnection();
	}

	@Override
	public boolean depositar(Conta destino, BigDecimal valor) {
		synchronized (destino) {
			String sql = "update conta set saldo = ? where numero = ?";
			try {
				PreparedStatement prep = connection.prepareStatement(sql);
				prep.setBigDecimal(1, valor);
				prep.setInt(2, destino.getNumero());

				prep.execute();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	@Override
	public BigDecimal sacar(Conta destino, BigDecimal valor) {
		synchronized (destino) {
			String sql = "update conta set saldo = ? where numero = ?";
			try {
				PreparedStatement prep = connection.prepareStatement(sql);
				prep.setLong(1, valor.longValue());
				prep.setInt(2, destino.getNumero());
				prep.execute();
				return valor;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public Conta getConta(Integer numero) {
		synchronized (numero) {
			String sql = "Select numero, saldo, tipo_conta from conta where numero = ?";
			try {
				PreparedStatement prep = connection.prepareStatement(sql);
				prep.setInt(1, numero);

				ResultSet rs = prep.executeQuery();
				Conta conta = null;
				while (rs.next()) {
					if(rs.getString("tipo_conta").trim().toUpperCase().equals("CONTA POUPANCA")){
						 conta = new ContaPoupanca();
					}else{
						conta = new ContaCorrente();
					}
					conta.setNumero(rs.getInt("numero"));
					conta.setSaldoAtual(rs.getBigDecimal("saldo"));
				}
				return conta;

			} catch (SQLException e) {

				e.printStackTrace();

			}
		}

		return null;
	}

	@Override
	public Conta criarConta(Conta conta) {
		String sql = "Insert into conta (numero,saldo,tipo_conta) values (?,?,?)";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, conta.getNumero());
			if (conta.getSaldoAtual() == null) {
				prep.setBigDecimal(2, conta.getSaldoAtual());
			} else {
				prep.setBigDecimal(2, BigDecimal.ZERO);
			}
			if(conta instanceof ContaPoupanca){
				prep.setString(3, "Conta Poupanca");
			}else{
				prep.setString(3, "Conta Corrente");
			}

			boolean result = prep.execute();
			if (result) {
				return conta;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Conta> recuperarContas() {
		String sql = "Select numero,saldo,tipo_conta from conta";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			List<Conta> listaDeContas = new ArrayList<Conta>();
			while(rs.next()){
				Conta conta;
				if(rs.getString("tipo_conta").trim().toUpperCase().equals("CONTA POUPANCA")){
					 conta = new ContaPoupanca();
				}else{
					conta = new ContaCorrente();
				}
				conta.setNumero(rs.getInt("numero"));
				conta.setSaldoAtual(rs.getBigDecimal("saldo"));
				listaDeContas.add(conta);
			}
			return listaDeContas;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return null;
	}

}
