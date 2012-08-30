<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript">


	 	


function send(action){	
	switch(action) {		
		case 'gravar':		
			if (confirm("DESEJA SALVAR A ALTERAÇÃO?")){						
				
				url = '/ProjetoFornecedor/updateFornecedor.do';	
			}		
			break;	
		case 'deletar':		
			if (confirm("DESEJA EXCLUIR?")){							
				
				url = '/ProjetoFornecedor/deleteFornecedor.do';	
			}
			break;	
	}
	document.forms[0].action = url;
	document.forms[0].submit();	
}

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

function  valida(form){
	if (form.nome.value=="") {
		alert("Preencha o nome corretamente.");
		form.nome.focus();			
	} else if (form.idade.value==""){
		alert("Preencha a idade corretamente.");
		form.idade.focus();				
	} else {
		send('gravar');
	}
}

</script>

<title>Alterar</title>
</head>
<body>
<center>
<p><b><u>ALTERAR</u></b></p>
<HR>
<form name="alterarForm" onsubmit="return valida(this.form);">
		ID: <input type="text" name="id" value="${fornecedor.id}" id="id"/><br /><br /> 
		Nome: <input type="text" name="nome" size=80 value="${fornecedor.nome}"/><br />
		<br>
		Endereco: <input type="text" name="endereco" size=80 value="${fornecedor.endereco}"  /><br />
		<br>
		Bairro: <input type="text" name="bairro" size=50 value="${fornecedor.bairro}"/><br />
		<br>
		Cidade: <input type="text" name="cidade" size=50 value="${fornecedor.cidade}"/><br />
		<br>
		Estado: <input type="text" name="estado" size=2 value="${fornecedor.estado}"/><br />
		<br>
		Telefone 1: <input type="text" name="telefone1" value="${fornecedor.telefone1}"/><br />
		<br>
		Telefone 2: <input type="text" name="telefone2" value="${fornecedor.telefone2}"/><br />
		<br>
		Cnpj: <input type="text" name="cnpj" onkeypress="return SomenteNumero(event)" value="${fornecedor.cnpj}"/><br />
		<br>
		Contato: <input type="text" name="contato" size=80 value="${fornecedor.contato}"/><br />
		<br>
		Telefone de Contato: <input type="text" name="telefone_contato" onkeypress="return SomenteNumero(event)" value="${fornecedor.telefone_contato}"/><br />
		<br>
		Ramal de Contato: <input type="text" name="ramal_contato" onkeypress="return SomenteNumero(event)" size=5 value="${fornecedor.ramal_contato}" /><br />
		<br>
		
		<input type="button" value="Alterar" onclick="send('gravar')" id="alterar"/>
		<input type="button" value="Excluir" onclick="send('deletar')" id="excluir"/>
		<input type="button" value="Cancelar" onclick="location.href='/ProjetoFornecedor/listFornecedor.do'" id="cancelar"/>
		
</form>
</center>
</body>
</html>