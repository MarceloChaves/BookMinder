<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BookMinder - Programar Notifica��o</title>
</head>
<h1>Programar Notifica��o por E-mail</h1>
<body>

	C�digo do Empr�stimo: <input type="text" name="IDEmprestimo"/>
					  <input type= "submit" value="CONSULTAR"/>
	
	<h3>Dados do Aluno</h3>
	Nome: ${nomeAluno}
	<br>
	Email: ${emailAluno} <input type="submit" value = "ATUALIZAR" />
	<br>
	Matricula: ${matriculaAluno}
	<br>
	Curso: ${cursoAluno}
	<br> <br>
	<h3>Dados do Livro</h3>
					  
	Livro: ${tituloLivro}
	<br>				 
	Autor: ${autor}
	<br>
	Data de empr�stimo: ${dataEmprestimo}
	<br>
	Data de devolu��o: ${dataDevolucao}
	<br> 
<br> <br>

</body>
</html>