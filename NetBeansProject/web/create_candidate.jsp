<%-- 
    Document   : create_candidate
    Created on : Oct 20, 2015, 10:22:17 PM
    Author     : elaela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Candidato</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        
        <%@include file="header.html" %>
        
	<div id="main">
            <div class="wrapper">
                <form action="CreateCandidate" method="get">
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
                    <fieldset> <legend> Certificados obtenidos: </legend>
                    <p><label>Tipo: 
                        <select name="type" value="profesional">
                            <option value="profesional">Titulo profesional</option>
                            <option value="posgrado">Posgrado</option>
                            <option value="certificado">Certificado</option>
                        </select>
                        </label>
                    </p>
                    <p><label>Nombre del certificado:
                            <input type="text" name="degreename"></label>
                    </p>
                    <p><label>Universidad: 
                        <input type="text" name="university"></label>
                    </p>
                    </fieldset>
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