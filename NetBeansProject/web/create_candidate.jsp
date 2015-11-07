<%-- 
    Document   : create_candidate
    Created on : Oct 20, 2015, 10:22:17 PM
    Author     : elaela
--%>

<%@page import="Model.PreviousJob"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Certificate"%>
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
        <script src="js/edit_create_functionality.js"></script> 
    </head>
    <body>
        
        <%@include file="/header.html" %>
        
	<div id="main">
            <div class="wrapper">
                <form action="candidates" id="createform" method="post">
                    <input type="hidden" name="operation" value="create">
                    <p><label>Nombre:
                        <input type="text" name="name" value="${candidate.getFirstName()}" required></label>
                    </p>
                    <p><label>Apellidos:
                        <input type="text" name="lastname" value="${candidate.getLastName()}" required></label>
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
                        <input type="text" name="professionalTitle" value="${candidate.getProfessionalTitle()}"></label>
                    </p>
                    <fieldset> <legend> Certificados obtenidos: </legend>
                        <span class="errorMessage">${certDateError}</span>
                        <div id="allCertificates">
                            <% List<Certificate> certificates = (List<Certificate>)request.getAttribute("certificates");
                                int size = (certificates != null) ? certificates.size() : 0;
                                for (int i=0; i<size; i++) 
                                {
                                %>
                                <div class="certificate"> 
                                    <p><label>Tipo: 
                                        <select name="type" value="<%= certificates.get(i).getType() %>" required>
                                           <option value="profesional">Titulo profesional</option>
                                           <option value="posgrado">Posgrado</option>
                                           <option value="certificado">Certificado</option>
                                        </select>
                                        </label>
                                    </p> 
                                    <p><label>Nombre del certificado: 
                                        <input type="text" name="degreename" value="<%= certificates.get(i).getName()%>" required></label>
                                    </p>
                                    <p><label>Organización: 
                                            <input type="text" name="organization" value="<%= certificates.get(i).getOrganization() %>" required></label> 
                                    </p>
                                    <p><label>Fecha de adquisición: 
                                        <input type="text" class="datepicker" name="dateacquired" value="<%= certificates.get(i).getDateAquired()%>" required></label>
                                    </p>
                                    <a href="javascript:;" onclick="Remove(this)">Borrar</a>
                                </div>
                                        <% } %>
                        </div>
                        <a id="addCert" href="javascript:;">Agregar certificado…</a>
                    </fieldset>
                    <fieldset> <legend> Trabajos Anteriores: </legend>
                        <span class="errorMessage">${jobDateError}</span>
                        <div id="allWorks">
                            <% List<PreviousJob> jobs = (List<PreviousJob>)request.getAttribute("previousJobs");
                                int sizej = (jobs != null) ? jobs.size() : 0;
                                for (int i=0; i<sizej; i++) 
                                {
                                %>
                                <div class="certficate">
                                    <p><label>Título profesional:
                                            <input type="text" name="jobTitle" value="<%= jobs.get(i).getJobTitle()%>" required></label>
                                    </p>
                                    <p><label>Empresa: 
                                            <input type="text" name="company" value="<%= jobs.get(i).getCompany()%>" required></label>
                                    </p>
                                    <p><label>Descripción: 
                                            <input type="text" rows="5" name="description" value="<%= jobs.get(i).getJobDescription() %>" required></label>
                                    </p>
                                    <p><label>Salario: 
                                            <input type="number" name="salary" value="<%= jobs.get(i).getSalary() %>" required></label>
                                    </p>
                                    <p><label>Fecha de inicio: 
                                        <input type="text" class="datepicker" name="startdate" value="<%= jobs.get(i).getStartDate() %>" required></label>
                                    </p>
                                    <p><label>Fecha final: 
                                        <input type="text" class="datepicker" name="enddate" value="<%= jobs.get(i).getStartDate() %>" required></label>
                                    </p>
                                    <a href="javascript:;" onclick="Remove(this)">Borrar</a>
                                </div>
                            <% } %>
                        </div>
                        <a id="addJob" href="javascript:;">Agregar trabajo…</a>
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