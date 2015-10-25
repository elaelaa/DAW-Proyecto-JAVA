<%-- 
    Document   : update_candidate
    Created on : Oct 24, 2015, 6:37:00 PM
    Author     : elaela
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<form action="CandidatesController" method="post">
                    <p><label>Nombre:
                        <input type="text" name="name" value="${candidate.getName()}" required></label>
                    </p>
                    <p><label>Apellidos:
                        <input type="text" name="lastname" value="${candidate.getLastname()}" required></label>
                    </p>
                    <p><label>Dirección:
                        <input type="text" name="address" value="${candidate.getAddress()}" required></label>
                    </p>
                    <p><label>Teléfono
                        <input type="text" name="phone" value="${candidate.getNumber()}" required></label>
                    </p> 
                    <p><label>Correo Electrónico:
                        <input type="email" name="email" value="${candidate.getEmail()}" required></label>
                    </p>
                    <p><label>Título Profesional: 
                        <input type="text" name="title" value="${candidate.getTitle()}"></label>
                    </p>
                    <fieldset> <legend> Certificados obtenidos: </legend>
                        <span class="errorMessage">${degreeError}</span>
                        <div id="allCertificates">
                            <c:forEach items="${candidate.getCertificates()}" var="cert">
                                <div class="certificate"> 
                                    <p><label>Tipo: 
                                        <select name="type" value="${cert.getType()}">
                                           <option value="profesional">Titulo profesional</option>
                                           <option value="posgrado">Posgrado</option>
                                           <option value="certificado">Certificado</option>
                                        </select>
                                        </label>
                                    </p> 
                                    <p><label>Nombre del certificado: 
                                        <input type="text" name="degreename" value="${cert.getName()}"></label>
                                    </p>
                                    <p><label>organización: 
                                        <input type="text" name="university" value="${cert.getOrganization()}"></label> 
                                    </p>
                                </div>
                            </c:forEach>
                        </div>
                        <a id="addCert" href="#">Agregar certificado…</a>
                    </fieldset>
                    <fieldset> <legend> Trabajos Anteriores: </legend>
                        <span class="errorMessage">${jobError}</span>
                        <div id="allWorks">
                            <c:forEach items="${candidate.getJobs()}" var="job">
                                <div class="certficate">
                                    <p><label>Título profesional:
                                        <input type="text" name="jobTitle" value="${job.getTitle()}"></label>
                                    </p>
                                    <p><label>Empresa: 
                                        <input type="text" name="company" value="${job.getCompany()}"></label>
                                    </p>
                                    <p><label>Duración:
                                        <input type="text" name="duration" value="${job.getDuration()}"></label>
                                    </p>
                                </div>
                            </c:forEach>
                        </div>
                        <a id="addJob" href="#">Agregar trabajo…</a>
                    </fieldset>
                    <p><label>Expectativas Económicas:
                        <input type="number" name="expectation" value="${candidate.getExpectation()}"></label>
                    </p>
                    <input type ="submit"  value="Guardar">
                </form>
            </div>
	</div>
        
         <%@include  file="footer.html" %>
         
    </body>
</html>
