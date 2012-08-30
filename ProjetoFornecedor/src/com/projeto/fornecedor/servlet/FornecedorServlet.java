package com.projeto.fornecedor.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projeto.fornecedor.dao.DAO;
import com.projeto.fornecedor.dao.FornecedorDAO;
import com.projeto.fornecedor.vo.FornecedorVO;

/**
 * Servlet implementation class FornecedorServlet
 */
public class FornecedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = 
		    request.getServletPath().substring(1, request.getServletPath().indexOf("Fornecedor"));
		
		if ("create".equals(acao)) {
		    create(request, response);
		} else if ("retrieve".equals(acao)) {
            retrieve(request, response);
		} else if ("update".equals(acao)) {
            update(request, response);
        } else if ("delete".equals(acao)) {
            delete(request, response);
        } else if ("add".equals(acao)) {
            add(request, response);
        } else {
            list(request, response);
        }
	}

	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/addFornecedor.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void list(HttpServletRequest request, HttpServletResponse response) {
		DAO dao = new FornecedorDAO();
		try {
			request.setAttribute("lista", dao.getLista());
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/listFornecedor.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void create(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String telefone1 = request.getParameter("telefone1");
		String telefone2 = request.getParameter("telefone2");
		String cnpj = request.getParameter("cnpj");
		String contato = request.getParameter("contato");
		String telefone_contato = request.getParameter("telefone_contato");
		String ramal_contato = request.getParameter("ramal_contato");
		
		
		
		
		
		FornecedorVO fornecedor = new FornecedorVO();
		fornecedor.setNome(nome);
		fornecedor.setEndereco(endereco);
		fornecedor.setBairro(bairro);
		fornecedor.setCidade(cidade);
		fornecedor.setEstado(estado);
		fornecedor.setTelefone1(telefone1);
		fornecedor.setTelefone2(telefone2);
		fornecedor.setCnpj(cnpj);
		fornecedor.setContato(contato);
		fornecedor.setTelefone_contato(telefone_contato);
		fornecedor.setRamal_contato(ramal_contato);
		
		DAO dao = new FornecedorDAO();
		try {
			dao.inserir(fornecedor);
			response.sendRedirect("/ProjetoFornecedor/listFornecedor.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void retrieve(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		DAO dao = new FornecedorDAO();
		FornecedorVO fornecedor = dao.get(Long.parseLong(id));

		request.setAttribute("fornecedor", fornecedor);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/viewFornecedor.jsp");
		dispatcher.forward(request, response);
	}

	protected void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String telefone1 = request.getParameter("telefone1");
		String telefone2 = request.getParameter("telefone2");
		String cnpj = request.getParameter("cnpj");
		String contato = request.getParameter("contato");
		String telefone_contato = request.getParameter("telefone_contato");
		String ramal_contato = request.getParameter("ramal_contato");
		
		
		DAO dao = new FornecedorDAO();
		
		FornecedorVO fornecedor = dao.get(Long.parseLong(id));
		fornecedor.setNome(nome);
		fornecedor.setEndereco(endereco);
		fornecedor.setBairro(bairro);
		fornecedor.setCidade(cidade);
		fornecedor.setEstado(estado);
		fornecedor.setTelefone1(telefone1);
		fornecedor.setTelefone2(telefone2);
		fornecedor.setCnpj(cnpj);
		fornecedor.setContato(contato);
		fornecedor.setTelefone_contato(telefone_contato);
		fornecedor.setRamal_contato(ramal_contato);
		
		try {
			dao.alterar(fornecedor);
			response.sendRedirect("/ProjetoFornecedor/listFornecedor.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		try {
			DAO dao = new FornecedorDAO();
			FornecedorVO fornecedor = dao.get(Long.parseLong(id));

			dao.excluir(fornecedor);
			response.sendRedirect("/ProjetoFornecedor/listFornecedor.do");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
