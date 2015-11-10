<%-- 
    Document   : interviews
    Created on : Oct 29, 2015, 7:49:47 PM
    Author     : manolo
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Interview"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script src="js/filter.js" type="text/javascript"></script>
	<title>Entrevistas</title>
</head>
<body>
	<%@include file="header.html" %>
	
	<div id="main">
		<div class="wrapper">
			<a class="button" id="new" href="create_interview.jsp">Nuevo</a>
			<div id="search">
				<div id="searchImage"><div class="svg"></div></div>
				<input id="searchBox" class="light-table-filter" data-table="order-table" type="text" name="search" value="" placeholder="Search" />
			</div>
			<table class="order-table table">
				<thead>
					<tr>
						<th>Nombre del Puesto</th>
						<th>Fecha</th>
						<th>Candidato</th>
						<th>Entrevistador</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<%	List<Interview> interviews = (List<Interview>)request.getAttribute("interviews");
					int size = (interviews != null) ? interviews.size() : 0;
					for (int i=0; i<size; i++) { %>
						<tr>
							<td><%= interviews.get(i).getJobTitle() %></td>
							<td><%= interviews.get(i).getDate() %></td>
							<td><%= interviews.get(i).getCandidateName() %></td>
							<td>$<%= interviews.get(i).getInterviewerName() %></td>
							<td><a href=<%= "interviews?operation=show&id="+interviews.get(i).getId() %> class="button">Mostrar</a></td>
						</tr>
				<% } %>
				</tbody>
			</table>
		</div>
	</div>
	
	<%@include file="footer.html" %>
</body>
</html>