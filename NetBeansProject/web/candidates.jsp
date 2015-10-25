<%-- 
	Document	: candidates
	Created on	: Oct 24, 2015, 6:26:01 PM
	Author		: manolo
--%>

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
			<a class="button" id="new" href="">Nuevo</a>
			<div id="search">
				<div id="searchImage"><div class="svg"></div></div>
				<input id="searchBox" type="text" name="search" value="" placeholder="Search" />
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Correo</th>
						<th>Puesto</th>
						<th>Sueldo</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Bruce Wayne</td>
						<td>not-batman@gotham.com</td>
						<td>CEO</td>
						<td>$1,000,000.00 USD</td>
						<td>
							<a href="https://google.com" class="button">Mostrar</a>
						</td>
					</tr>
					<tr>
						<td>The Joker</td>
						<td>knockknock@whosthere.com</td>
						<td>Villain</td>
						<td>$0 USD</td>
						<td>
							<a href="https://google.com" class="button">Mostrar</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<%@include file="footer.html" %>
</body>
</html>