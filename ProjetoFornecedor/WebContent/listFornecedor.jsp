<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>CRUD</title>
	</head>
<body>
<center>
	<p><b><u>TABELA</u></b></p>
	<HR>
	<table border="1">
		<thead>
		
			<th ALIGN="left">Id</th>
			<th ALIGN="left">Nome</th>
			<th ALIGN="left">Endereco</th>
			<th ALIGN="left">Bairro</th>
			<th ALIGN="left">Cidade</th>
			<th ALIGN="left">Estado</th>
		    <th ALIGN="left">Telefone 1</th>
			<th ALIGN="left">Telefone 2</th>
			<th ALIGN="left">Cnpj</th>	
			<th ALIGN="left">Contato</th>
			<th ALIGN="left">Telefone de Contato</th>
			<th ALIGN="left">Ramal do Contato</th>			
		</thead>
		<tbody>
		<!-- percorre contatos montando as linhas da tabela -->
		<c:forEach var="forn" items="${lista}" varStatus="id">
			<tr bgcolor="#${id.count % 2 == 0 ? 'CCFFFF' : 'FFFFFF' }">
				<td ALIGN="left">
					<a href="retrieveFornecedor.do?id=${forn.id}">${forn.id}</a>
				</td>
				
				<td ALIGN="left">${forn.nome}</td>
				<td ALIGN="left">${forn.endereco}</td>
				<td ALIGN="left">${forn.bairro}</td>
				<td ALIGN="left">${forn.cidade}</td>
				<td ALIGN="left">${forn.estado}</td>
				<td ALIGN="left">${forn.telefone1}</td>
				<td ALIGN="left">${forn.telefone2}</td>
				<td ALIGN="left">${forn.cnpj}</td>
				<td ALIGN="left">${forn.contato}</td>
				<td ALIGN="left">${forn.telefone_contato}</td>
				<td ALIGN="left">${forn.ramal_contato}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br />
	
	<form action="/ProjetoFornecedor/addFornecedor.do"  name="pessoaForm"><input
		type="submit" value="Incluir"></form>

</center>
</body>
</html>