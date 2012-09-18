package br.edu.infnet.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;



import br.edu.infnet.factory.ConnectionFactory;
import br.edu.infnet.vo.Conta;

public class TransacaoDAOImpl implements TransacaoDAO {

	
	private Connection connection;

	
	public TransacaoDAOImpl(){
		connection = ConnectionFactory.getConnection();
	}


	@Override
	public void gravaTransacao(Conta origem, Conta destino, BigDecimal valor,
			String nomeOperacao) {
		String sql = "Insert into transacao (conta_origem, conta_destino, valor, tipo_operacao, data_operacao) values (?,?,?,?,?)";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, origem.getNumero());
			if (destino == null){
				prep.setObject(2, null);	
			}else{
				prep.setInt(2, destino.getNumero());
			}
			prep.setBigDecimal(3, valor);
			prep.setString(4, nomeOperacao);
			String data = new SimpleDateFormat("HH:mm:ss  /  yyyy-MM-dd ").format(new Date());
			prep.setString(5, data);
			
			
			prep.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
