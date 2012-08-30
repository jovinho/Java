package com.projeto.fornecedor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.projeto.fornecedor.factory.ConnectionFactory;
import com.mysql.jdbc.Statement;
import com.projeto.fornecedor.vo.FornecedorVO;

public class FornecedorDAO implements DAO {

	public FornecedorVO inserir(FornecedorVO fornecedor) throws Exception {
		Connection conn =  ConnectionFactory.getConnection();

		try {
			String sql = "insert into fornecedor (nome, endereco, bairro, cidade, estado, telefone1, telefone2, cnpj, contato, telefone_contato, ramal_contato)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getEndereco());
			stmt.setString(3, fornecedor.getBairro());
			stmt.setString(4, fornecedor.getCidade());
			stmt.setString(5, fornecedor.getEstado());
			stmt.setString(6, fornecedor.getTelefone1());
			stmt.setString(7, fornecedor.getTelefone2());
			stmt.setString(8, fornecedor.getCnpj());
			stmt.setString(9, fornecedor.getContato());
			stmt.setString(10, fornecedor.getTelefone_contato());
			stmt.setString(11, fornecedor.getRamal_contato());
			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				fornecedor.setId(rs.getInt(1));
			}
			return fornecedor;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public boolean alterar(FornecedorVO fornecedor) throws Exception {
		
		Connection conn = ConnectionFactory.getConnection();
		
		try {
			String sql = "update fornecedor set nome=?, endereco=?, bairro=?, cidade=?, estado=?, telefone1=?, telefone2=?, cnpj=?, contato=?, telefone_contato=?, ramal_contato=? where ID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, fornecedor.getNome());
			stmt.setString(2, fornecedor.getEndereco());
			stmt.setString(3, fornecedor.getBairro());
			stmt.setString(4, fornecedor.getCidade());
			stmt.setString(5, fornecedor.getEstado());
			stmt.setString(6, fornecedor.getTelefone1());
			stmt.setString(7, fornecedor.getTelefone2());
			stmt.setString(8, fornecedor.getCnpj());
			stmt.setString(9, fornecedor.getContato());
			stmt.setString(10, fornecedor.getTelefone_contato());
			stmt.setString(11, fornecedor.getRamal_contato());
			stmt.setInt(12, fornecedor.getId());
		    stmt.execute();
		    return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
	}
	

	public boolean excluir(FornecedorVO fornecedor) throws Exception {
		
		Connection conn = ConnectionFactory.getConnection();

		try {
			String sql = "delete from fornecedor where ID=? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fornecedor.getId());
			stmt.execute();
			return true;
	}catch (SQLException e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	} finally {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}

	public FornecedorVO get(long l) {

		Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            String sql = "Select * from fornecedor where id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setLong(1, l);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FornecedorVO fornecedor = new FornecedorVO();
                fornecedor.setId(rs.getInt(1));
                fornecedor.setNome(rs.getString(2));
                fornecedor.setEndereco(rs.getString(3));
                fornecedor.setBairro(rs.getString(4));
                fornecedor.setCidade(rs.getString(5));
                fornecedor.setEstado(rs.getString(6));
                fornecedor.setTelefone1(rs.getString(7));
                fornecedor.setTelefone2(rs.getString(8));
                fornecedor.setCnpj(rs.getString(9));
                fornecedor.setContato(rs.getString(10));
                fornecedor.setTelefone_contato(rs.getString(11));
                fornecedor.setRamal_contato(rs.getString(12));
                
                return fornecedor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return null;
    }

	public List<FornecedorVO> getLista() throws Exception {
		Connection conn = ConnectionFactory.getConnection();
		List<FornecedorVO> lista = new ArrayList<FornecedorVO>();

		try {
			String sql = "Select * from fornecedor order by nome";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FornecedorVO fornecedor = new FornecedorVO();
                fornecedor.setId(rs.getInt(1));
                fornecedor.setNome(rs.getString(2));
                fornecedor.setEndereco(rs.getString(3));
                fornecedor.setBairro(rs.getString(4));
                fornecedor.setCidade(rs.getString(5));
                fornecedor.setEstado(rs.getString(6));
                fornecedor.setTelefone1(rs.getString(7));
                fornecedor.setTelefone2(rs.getString(8));
                fornecedor.setCnpj(rs.getString(9));
                fornecedor.setContato(rs.getString(10));
                fornecedor.setTelefone_contato(rs.getString(11));
                fornecedor.setRamal_contato(rs.getString(12));
                lista.add(fornecedor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return lista;
	}
		


}
