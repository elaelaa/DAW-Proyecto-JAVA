<%-- 
    Document   : show_employee
    Created on : Oct 24, 2015, 6:43:20 PM
    Author     : elaela
--%>

<%@page import="java.util.List"%>
<%@page import="Model.PreviousJob"%>
<%@page import="Model.Certificate"%>
<%@page import="Model.Interview"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
	<title>Mostrar entrevista</title>
</head>
<body>

	<%@include file="header.jsp" %>

	<div id="main">
		<div class="wrapper">
			<div id="data">
				<% Interview interview = (Interview)request.getAttribute("interview"); %>
				<p>Puesto de trabajo: <span><%= interview.getJobTitle() %></span></p>
				<p>Fecha de entrevista: <span><%= interview.getDate() %></span></p>
				<p>Entrevistador: <span><%= interview.getInterviewerName() %></span></p>
                                <p>Candidato: <span><%= interview.getCandidateName() %></span></p>
				<p>Plataforma: <span><%= interview.getPlatform() %></span></p>
				<p>Feedback: <span><%= interview.getFeedback() %></span></p>
				
			</div>
			<div id="buttons">
                            <a class="button" id="edit" href="interviews?operation=edit&id=<%= interview.getId()%>">Editar</a>
                            <a class="button" id="delete" href="interviews?operation=delete&id=<%= interview.getId() %>">Borrar</a>
			</div>
		</div>
	</div>
	<%@include  file="footer.html" %>
</body>
</html>

