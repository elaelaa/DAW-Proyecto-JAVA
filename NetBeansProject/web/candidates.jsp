<%-- 
	Document	: candidates
	Created on	: Oct 24, 2015, 6:26:01 PM
	Author		: manolo
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<title>Candidatos</title>
</head>
<body>
	<%@include file="header.html" %>
	
	<div id="main">
		<div class="wrapper">
			<a class="button" id="new" href="create_candidate.jsp">Nuevo</a>
			<div id="search">
				<div id="searchImage"><div class="svg"></div></div>
				<input id="searchBox" type="text" name="search" value="" placeholder="Search" />
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Correo</th>
						<th>Telefono</th>
						<th>Expectativas</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<%	List<Candidate> candidates = (List<Candidate>)request.getAttribute("candidates");
					int size = (candidates != null) ? candidates.size() : 0;
					for (int i=0; i<size; i++) { %>
						<tr>
							<td><%= candidates.get(i).getFullName() %></td>
							<td><%= candidates.get(i).getEmail() %></td>
							<td><%= candidates.get(i).getPhone() %></td>
							<td>$<%= candidates.get(i).getExpectation() %></td>
							<td><a href=<%= "candidates?operation=show&id="+candidates.get(i).getId() %> class="button">Mostrar</a></td>
						</tr>
				<% } %>
				</tbody>
			</table>
		</div>
	</div>
	
	<%@include file="footer.html" %>
</body>
</html>