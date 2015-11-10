<%-- 
    Document   : show_employee
    Created on : Oct 24, 2015, 6:43:20 PM
    Author     : elaela
--%>

<%@page import="java.util.List"%>
<%@page import="Model.PreviousJob"%>
<%@page import="Model.Certificate"%>
<%@page import="Model.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
	<title>Mostrar empleado</title>
</head>
<body>

	<%@include file="header.html" %>

	<div id="main">
		<div class="wrapper">
			<div id="data">
				<% Employee employee = (Employee)request.getAttribute("employee"); %>
				<p>Nombre: <span><%= employee.getFirstName() %></span></p>
				<p>Apellidos: <span><%= employee.getLastName() %></span></p>
				<p>Dirección: <span><%= employee.getAddress() %></span></p>
				<p>Teléfono: <span><%= employee.getPhone() %></span></p>
				<p>E-mail: <span><%= employee.getEmail() %></span></p>
				<p>Fecha de nacimiento: <span><%= employee.getDateOfBirth() %></span></p>
                                <p>Puesto: <span><%= employee.getStartDate() %></span></p>
                                <p>Fecha de inicio: <span><%= employee.getJobTitle() %></span></p>
				<p>Título profesional: <span><%= employee.getProfessionalTitle() %></span></p>
				<p>Salario: <span><%= employee.getSalary() %></span></p>
                                <p>Días de vacaciones: <span><%= employee.getVacationDays() %></span></p>
                                <%	List<Certificate> certificates = employee.getCertificates();
					int size = (certificates != null) ? certificates.size() : 0;
					if (size > 0) { %>
					<p>Certificados obtenidos:</p>
				<%	}
					for (int i=0; i<size; i++) { %>
					<p class="indented">Tipo: <span><%= certificates.get(i).getType() %></span></p>
					<p class="indented">Nombre: <span><%= certificates.get(i).getName() %></span></p>
					<p class="indented">Organización: <span><%= certificates.get(i).getOrganization() %></span></p>
					<p class="indented">Fecha de adquisición: <span><%= certificates.get(i).getDateAquired() %></span></p>
					<span class="separator"></span>
				<%	} %>
				
				<%	List<PreviousJob> jobs = employee.getPreviousJobs();
					size = (jobs != null) ? jobs.size() : 0;
					if (size > 0) { %>
					<p>Trabajos anteriores: </p>
				<%	}
					for (int i=0; i<size; i++) { %>
					<p class="indented">Descripción: <span><%= jobs.get(i).getJobDescription() %></span></p>
					<p class="indented">Título: <span><%= jobs.get(i).getJobTitle() %></span></p>
                                        <p class="intended">Empresa: <span><%= jobs.get(i).getCompany()%></span></p>
					<span class="separator"></span>
				<%	} %>
				
				<p>Entrevistas: </p>
				<ul id="interviews">
						<li>Interviewzzz</li>
				</ul> 
			</div>
			<div id="buttons">
                            <a class="button" id="edit" href="employees?operation=edit&id=<%= employee.getId()%>">Editar</a>
                            <a class="button" id="delete" href="employees?operation=delete&id=<%= employee.getId() %>">Borrar</a>
			</div>
		</div>
	</div>
	<%@include  file="footer.html" %>
</body>
</html>
