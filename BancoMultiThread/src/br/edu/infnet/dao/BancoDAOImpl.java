package br.edu.infnet.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.infnet.factory.ConnectionFactory;
import br.edu.infnet.vo.Conta;

public class BancoDAOImpl implements BancoDAO {

	private Connection connection;

	public BancoDAOImpl() {
		connection = ConnectionFactory.getConnection();
	}

	@Override
	public boolean depositar(Conta destino, BigDecimal valor) {
		String sql = "update Conta set saldo = ? where numero = ?";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setLong(1, destino.getSaldoAtual().longValue());
			prep.setInt(2, destino.getNumero());

			prep.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public BigDecimal sacar(Conta destino, BigDecimal valor) {
		synchronized (this) {
			String sql = "update Conta set saldo = ? where numero = ?";
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
			String sql = "Select numero, saldo from Conta where numero = ?";
			try {
				PreparedStatement prep = connection.prepareStatement(sql);
				prep.setInt(1, numero);

				ResultSet rs = prep.executeQuery();
				Conta conta = null;
				while (rs.next()) {
					conta = new Conta();
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
	public Conta criarConta(Integer numero, BigDecimal saldoInicial) {
		String sql = "Insert into Conta (numero,saldo) values (?,?)";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, numero);
			if (saldoInicial == null) {
				prep.setBigDecimal(2, saldoInicial);
			} else {
				prep.setBigDecimal(2, BigDecimal.ZERO);
			}

			boolean result = prep.execute();
			if (result) {
				Conta conta = new Conta();
				conta.setNumero(numero);
				conta.setSaldoAtual(saldoInicial);
				return conta;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
