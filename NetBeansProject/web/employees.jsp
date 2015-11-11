<%-- 
    Document   : interviews
    Created on : Oct 29, 2015, 7:49:47 PM
    Author     : manolo
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script src="js/filter.js" type="text/javascript"></script>
	<title>Empleados</title>
</head>
<body>
	<%@include file="header.html" %>
	
	<div id="main">
		<div class="wrapper">
			<a class="button" id="new" href="create_employee.jsp">Nuevo</a>
			<div id="search">
				<div id="searchImage"><div class="svg"></div></div>
				<input id="searchBox" class="light-table-filter" data-table="order-table" type="text" name="search" value="" placeholder="Search" />
			</div>
			<table class="order-table table">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Correo</th>
						<th>Telefono</th>
						<th>Puesto</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<%	List<Employee> employees = (List<Employee>)request.getAttribute("employees");
					int size = (employees != null) ? employees.size() : 0;
					for (int i=0; i<size; i++) { %>
						<tr>
							<td><%= employees.get(i).getFullName() %></td>
							<td><%= employees.get(i).getEmail() %></td>
							<td><%= employees.get(i).getPhone() %></td>
							<td><%= employees.get(i).getJobTitle()%></td>
							<td><a href=<%= "employees?operation=show&id="+employees.get(i).getId() %> class="button">Mostrar</a></td>
						</tr>
				<% } %>
				</tbody>
			</table>
		</div>
	</div>
	
	<%@include file="footer.html" %>
</body>
</html>