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
        <script src="js/CreateNew.js"></script> 
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
                        <input type="number" name="phone" required></label>
                    </p> 
                    <p><label>Correo Electrónico:
                        <input type="email" name="email" required></label>
                    </p>
                    <p><label>Título Profesional: 
                        <input type="text" name="title"></label>
                    </p>
                    <fieldset> <legend> Certificados obtenidos: </legend>
                        <div id="allCertificates">
                        </div>
                        <a id="addCert">Agregar certificado…</a>
                    </fieldset>
                    <fieldset> <legend> Trabajos Anteriores: </legend>
                        <div id="allWorks">
                        </div>
                        <a id="addJob">Agregar trabajo…</a>
                    </fieldset>
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