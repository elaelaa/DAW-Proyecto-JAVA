<%-- 
    Document   : login
    Created on : Nov 10, 2015, 2:31:16 PM
    Author     : manolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/login.css">
	
	<title>Human Capital System</title>
</head>

<body>
	<div id="main">
		<div id="login">
			<div id="logo">
				<h1>Human Capital System</h1>
			</div>
			<form method="post" action="LoginController">
				<input type="hidden" name="operation" value="login"/>
				<p><label>Email: <input type="text" name="user" required></label></p>
				
				<p><label>Contraseña: <input type="password" name="password" required></label></p>
				<% if (request.getParameter("error") != null) { %>
				<p class="error">Nombre de usuario o contraseña incorrectos.</p>
				<% } %>
				<input type="submit" value="Login">
			</form>
		</div>
	</div>
</body>
</html>
