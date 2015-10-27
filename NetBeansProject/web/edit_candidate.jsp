<%-- 
    Document   : update_candidate
    Created on : Oct 24, 2015, 6:37:00 PM
    Author     : elaela
--%>

<%@page import="Model.Certificate"%>
<%@page import="Model.PreviousJob"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar candidato</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        
        <%@include file="header.html" %>
        
        <div id="main">
            <div class="wrapper">
                <form action="candidates" id="candidateform" method="post">
                    <input type="hidden" name="operation" value="edit">
                    <input type="hidden" name="id" value="${candidate.getId()}">
                    <p><label>Nombre:
                        <input type="text" name="name" value="${candidate.getName()}" required></label>
                    </p>
                    <p><label>Apellidos:
                        <input type="text" name="lastname" value="" required></label>
                    </p>
                    <p><label>Dirección:
                        <input type="text" name="address" value="${candidate.getAddress()}" required></label>
                    </p>
                    <p><label>Teléfono: 
                        <input type="text" name="phone" value="${candidate.getPhone()}" required></label>
                    </p> 
                    <p><label>Correo Electrónico:
                        <input type="email" name="email" value="${candidate.getEmail()}" required></label>
                    </p>
                    <p>
                        <label>Fecha de nacimiento: 
                        <span class="errorMessage">${bdError}</span>
                        <input type="text" name="birthday" class="datepicker" value="${candidate.getDateOfBirth()}" required></label>
                    </p>
                    <p><label>Título Profesional: 
                        <input type="text" name="title" value=""></label>
                    </p>
                    <p><label>Expectativas Económicas:
                        <input type="number" name="expectation" value="${candidate.getExpectation()}"></label>
                    </p>
                    <input type ="submit"  value="Guardar">
                </form>
            </div>	
        </div>
    
        <%@include  file="/footer.html" %>
         
    </body>
</html>
