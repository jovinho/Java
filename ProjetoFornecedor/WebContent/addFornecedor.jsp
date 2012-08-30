<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
function SomenteNumero(e){
    var tecla=(window.event)?event.keyCode:e.which;   
    if((tecla>47 && tecla<58)) {
        return true;
    } else {
        if (tecla==8 || tecla==0) {
            return true;
        } else {
            return false;
        }
    }    
}

function valida(form){
	if (form.nome.value==""){
		alert("Preencha o nome corretamente.");
		form.nome.focus();
		return false;	
	}	
	if (form.idade.value==""){
		alert("Preencha a idade corretamente.");
		form.idade.focus();
		return false;
	}
}
</script>

<title>Incluir Fornecedor</title>
</head>
<body>
<center>
<p><b><u>INCLUIR</u></b></p>
<HR>
<form action="/ProjetoFornecedor/createFornecedor.do" name="incluirForm" onsubmit="return valida(this);">
	Nome: <input type="text" name="nome" size=80 /><br />
	<br>
	Endereco: <input type="text" name="endereco" size=80  /><br />
	<br>
	Bairro: <input type="text" name="bairro" size=50 /><br />
	<br>
	Cidade: <input type="text" name="cidade" size=50/><br />
	<br>
	Estado: <input type="text" name="estado" size=2 /><br />
	<br>
	Telefone 1: <input type="text" name="telefone1" /><br />
	<br>
	Telefone 2: <input type="text" name="telefone2" /><br />
	<br>
	Cnpj: <input type="text" name="cnpj" onkeypress="return SomenteNumero(event)"/><br />
	<br>
	Contato: <input type="text" name="contato" size=80/><br />
	<br>
	Telefone de Contato: <input type="text" name="telefone_contato" onkeypress="return SomenteNumero(event)" /><br />
	<br>
	Ramal de Contato: <input type="text" name="ramal_contato" onkeypress="return SomenteNumero(event)" size=5 /><br />
	<br>
	<input type="submit" value="Salvar"/>	
	<input type="button" value="Cancelar" onclick="location.href='/ProjetoFornecedor/listFornecedor.do'" id="cancelar"/>	
</form>
</center>	
</body>
</html>