<%-- 
    Document   : show_candidate
    Created on : Oct 24, 2015, 6:43:20 PM
    Author     : elaela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar candidato</title>
    </head>
    <body>
        
        <%@include file="header.html" %>
        
        <div id="main">
            <div class="wrapper">
                <div id="data">
                    <p>Nombre: <span id="firstname">Lucky</span></p>
                    <p>Apellidos: <span id="lastname">Luke </span></p>
                    <p>Direcci&oacute;n: <span id="direccion">Cowboy land, Texas</span></p>
                    <p>Tel&eacute;fono: <span id="phone">+2582012312312 </span></p>
                    <p>E-mail: <span id="email">luke@lucky.com</span></p>
                    <p>T&iacute;tulo profesional: <span id="title">Cowboy</span></p>
                    <p>Universidad: <span id="university">Life school</span></p>
                    <p>Certificados obtenidos: </p>
                    <ul id="certificates">
                            <li>Horse handler</li>
                            <li>Fastest shooter</li>
                            <li>Class of riding to setting sun</li>
                    </ul> 
                    <p>Trabajos anteriores: </p>
                    <ul id="previousWork">
                            <li>Poor lonesome man</li>
                    </ul>
                    <p>Expectativas Econ&oacute;micas: <span id="economExpect">Own horse</span></p>
                    <p>Entrevistas: </p>
                    <ul id="interviews">
                            <li>Post of cowboy</li>
                            <li>Post of sheriff</li>
                    </ul> 
                </div>
                <div id="buttons">
                        <a class="button" id="edit" href="updateCandidateExample.html">Editar</a>
                        <a class="button" id="delete" href="">Borrar</a>
                </div>
            </div>
	</div>
        
        <%@include  file="footer.html" %>
        
    </body>
</html>
