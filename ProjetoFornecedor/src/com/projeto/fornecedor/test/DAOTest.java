package com.projeto.fornecedor.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import com.projeto.fornecedor.dao.DAO;
import com.projeto.fornecedor.dao.FornecedorDAO;
import com.projeto.fornecedor.vo.FornecedorVO;


public class DAOTest {
	
	private static DAO dao;
	
	@BeforeClass
	public static void instanciar(){
		dao = new FornecedorDAO();
	}
	
	@Test
	public void adicionar() throws Exception{
		FornecedorVO vo = new FornecedorVO();
		vo.setNome("Leonardo");
		vo.setBairro("Centro");
		vo.setCidade("RJ");
		vo.setCnpj("123");
		vo.setEstado("Rj");
		vo.setContato("Lol");
		vo.setRamal_contato("123");
		vo.setTelefone1("12314");
		vo.setTelefone2("12314");
		vo.setTelefone_contato("12314");
		vo.setEndereco("Rua taltal");
		FornecedorVO teste = dao.inserir(vo);
		Assert.assertNotNull(teste);
		
		
	}
	
	@Test
	public void excluir() throws Exception{
		FornecedorVO vo = new FornecedorVO();
		vo.setId(4);
		boolean teste = dao.excluir(vo);
		Assert.assertTrue(teste);
		
	}
	
	@Test
	public void listarFornecedores() throws Exception{
		List<FornecedorVO> lista = dao.getLista();
		for (FornecedorVO forn : lista){
			System.out.println(forn.getId());
			System.out.println(forn.getNome());
		}
		Assert.assertNotNull(lista);
		
		
	}
	
	@Test
	public void buscarUmFornecedor(){
		FornecedorVO teste = new FornecedorVO();
		teste = dao.get(3);
		Assert.assertNotNull(teste);
	}
	
	@Test
	public void alterar() throws Exception{
		FornecedorVO vo = new FornecedorVO();
		vo.setNome("Leonardo alterado");
		vo.setBairro("Centro");
		vo.setCidade("RJ");
		vo.setCnpj("123");
		vo.setEstado("Rj");
		vo.setContato("Lol");
		vo.setRamal_contato("123");
		vo.setTelefone1("12314");
		vo.setTelefone2("12314");
		vo.setTelefone_contato("12314");
		vo.setEndereco("Rua taltal");
		vo.setId(3);
		boolean teste = dao.alterar(vo);
		Assert.assertTrue(teste);
		
		
	}

}
