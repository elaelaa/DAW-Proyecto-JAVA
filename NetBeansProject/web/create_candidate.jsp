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
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
         <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
        <script src="js/CreateNew.js"></script> 
    </head>
    <body>
        
        <%@include file="/header.html" %>
        
	<div id="main">
            <div class="wrapper">
                <form action="CreateCandidate" id="candidateform" method="post">
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
                    <fieldset> <legend> Certificados obtenidos: </legend>
                        <span class="errorMessage">${certDateError}</span>
                        <div id="allCertificates">
                            
                            <!--<c:forEach items=""statement for getting certificates"" var="cert">
                                <div class="certificate"> 
                                    <p><label>Tipo: 
                                        <select name="type" value="${cert.getType()}" required>
                                           <option value="profesional">Titulo profesional</option>
                                           <option value="posgrado">Posgrado</option>
                                           <option value="certificado">Certificado</option>
                                        </select>
                                        </label>
                                    </p> 
                                    <p><label>Nombre del certificado: 
                                        <input type="text" name="degreename" value="${cert.getName()}" required></label>
                                    </p>
                                    <p><label>Organización: 
                                        <input type="text" name="organization" value="${cert.getOrganization()}" required></label> 
                                    </p>
                                    <p><label>Fecha de adquisición: 
                                        <input type="text" class="datepicker" name="dateacquired" value="${cert.getDateAquired()}" required></label>
                                    </p>
                                </div>
                            </c:forEach>-->
                        </div>
                        <a id="addCert" href="#">Agregar certificado…</a>
                    </fieldset>
                    <fieldset> <legend> Trabajos Anteriores: </legend>
                        <span class="errorMessage">${jobDateError}</span>
                        <div id="allWorks">
                            <!--<c:forEach items="statement for getting jobs" var="job">
                                <div class="certficate">
                                    <p><label>Título profesional:
                                        <input type="text" name="jobTitle" value="${job.getTitle()}" required></label>
                                    </p>
                                    <p><label>Empresa: 
                                        <input type="text" name="company" value="${job.getCompany()}" required></label>
                                    </p>
                                    <p><label>Descripción: 
                                        <input type="text" rows="5" name="description" value="${job.getDescription()}" required></label>
                                    </p>
                                    <p><label>Salario: 
                                       <input type="number" name="salary" value="${jon.getSalary()}" required></label>
                                    </p>
                                    <p><label>Fecha de inicio: 
                                        <input type="text" class="datepicker" name="startdate" value="${job.getStartDate()}" required></label>
                                    </p>
                                    <p><label>Fecha final: 
                                        <input type="text" class="datepicker" name="enddate" value="${job.getEndDate()}" required></label>
                                    </p>
                                </div>
                            </c:forEach>-->
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
    
        <%@include  file="/footer.html" %>
        
    </body>	
</html>