<%-- 
    Document   : update_candidate
    Created on : Oct 24, 2015, 6:37:00 PM
    Author     : elaela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar candidato</title>
    </head>
    <body>
        
        <%@include file="header.html" %>
        
        <div id="main">
            <div class="wrapper">
		<form>
                    <p><label>Nombre:
                    <input type="text" name="name" required></label>
                    </p>
                    <p><label>Apellidos:
                    <input type="text" name="lastname" required></label>
                    </p>
                    <p><label>Dirección:
                    <input type="text" name="address" required></label>
                    </p>
                    <p><label>Teléfono
                    <input type="number" name="age" required></label>
                    </p> 
                    <p><label>Correo Electrónico:
                    <input type="email" name="email" required></label>
                    </p>
                    <p><label>Título Profesional: 
                    <input type="text" name="title"></label>
                    </p>
                    <p><label>Universidad: 
                    <input type="text" name="university"></label>
                    </p>
                    <p><label>Certificados obtenidos: 
                    <input type="text" name="certificates"></label>
                    </p>
                    <p><label>Trabajos Anteriores:
                    <input type="text" name="previousWorks"></label>
                    </p>
                    <p><label>Expectativas Económicas:
                    <input type="number" name="expectation"></label>
                    </p>
                    <input type ="submit"  value="Guardar">
                </form>
            </div>
	</div>
        
         <%@include  file="footer.html" %>
         
    </body>
</html>
