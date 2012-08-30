package com.projeto.fornecedor.dao;

import java.util.List;

import com.projeto.fornecedor.vo.FornecedorVO;



public interface DAO {
	
	public FornecedorVO inserir(FornecedorVO fornecedor) throws Exception;
	
	public boolean alterar(FornecedorVO fornecedor) throws Exception;
	
	public boolean excluir(FornecedorVO fornecedor) throws Exception;
	
	public FornecedorVO get(long l);
	
	public List<FornecedorVO> getLista() throws Exception;
	
}
