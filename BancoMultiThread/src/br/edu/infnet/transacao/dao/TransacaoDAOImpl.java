package br.edu.infnet.transacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.infnet.jdbc.factory.ConnectionFactory;
import br.edu.infnet.transacao.vo.Transacao;

public class TransacaoDAOImpl implements TransacaoDAO {

	
	private Connection connection;

	
	public TransacaoDAOImpl(){
		connection = ConnectionFactory.getConnection();
	}


	
	public void gravaTransacao(Transacao transacao) {
		String sql = "Insert into transacao (conta_origem, conta_destino, valor, tipo_operacao, data_operacao) values (?,?,?,?,?)";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, transacao.getContaOrigem().getNumero());
			if (transacao.getContaDestino() == null){
				prep.setObject(2, null);	
			}else{
				prep.setInt(2, transacao.getContaDestino().getNumero());
			}
			prep.setBigDecimal(3, transacao.getValorTransacao());
			prep.setString(4, transacao.getTipoOperacao());
			String data = new SimpleDateFormat("HH:mm:ss  /  yyyy-MM-dd ").format(new Date());
			prep.setString(5, data);
			
			prep.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
