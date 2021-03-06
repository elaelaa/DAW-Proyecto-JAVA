<%-- 
    Document   : show_candidate
    Created on : Oct 24, 2015, 6:43:20 PM
    Author     : elaela
--%>

<%@page import="Model.Interview"%>
<%@page import="java.util.List"%>
<%@page import="Model.PreviousJob"%>
<%@page import="Model.Certificate"%>
<%@page import="Model.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
	<title>Mostrar candidato</title>
</head>
<body>

	<%@include file="header.jsp" %>

	<div id="main">
		<div class="wrapper">
			<div id="data">
				<% Candidate candidate = (Candidate)request.getAttribute("candidate"); %>
				<p>Nombre: <span><%= candidate.getFirstName() %></span></p>
				<p>Apellidos: <span><%= candidate.getLastName() %></span></p>
				<p>Dirección: <span><%= candidate.getAddress() %></span></p>
				<p>Teléfono: <span><%= candidate.getPhone() %></span></p>
				<p>E-mail: <span><%= candidate.getEmail() %></span></p>
				<p>Fecha de nacimiento: <span><%= candidate.getDateOfBirth() %></span></p>
				<p>Título profesional: <span><%= candidate.getProfessionalTitle() %></span></p>
				<%	List<Certificate> certificates = candidate.getCertificates();
					int size = (certificates != null) ? certificates.size() : 0;
					if (size > 0) { %>
					<p>Certificados obtenidos:</p>
                                        <span class="separator"></span>
				<%	}
					for (int i=0; i<size; i++) { %>
					<p class="indented">Tipo: <span><%= certificates.get(i).getType() %></span></p>
					<p class="indented">Nombre: <span><%= certificates.get(i).getName() %></span></p>
					<p class="indented">Organización: <span><%= certificates.get(i).getOrganization() %></span></p>
					<p class="indented">Fecha de adquisición: <span><%= certificates.get(i).getDateAquired() %></span></p>
					<span class="separator"></span>
				<%	} %>
				
				<%	List<PreviousJob> jobs = candidate.getPreviousJobs();
					size = (jobs != null) ? jobs.size() : 0;
					if (size > 0) { %>
					<p>Trabajos anteriores: </p>
                                        <span class="separator"></span>
				<%	}
					for (int i=0; i<size; i++) { %>
					<p class="indented">Descripción: <span><%= jobs.get(i).getJobDescription() %></span></p>
					<p class="indented">Título: <span><%= jobs.get(i).getJobTitle() %></span></p>
                                        <p class="indented">Empresa: <span><%= jobs.get(i).getCompany()%></span></p>
                                        <p class="indented">Salario: <span><%= jobs.get(i).getSalary()%></span></p>
                                        <p class="indented">Fecha de inicio: <span><%= jobs.get(i).getStartDate()%></span></p>
                                        <p class="indented">Fecha final: <span><%= jobs.get(i).getEndDate()%></span></p>
                                        <span class="separator"></span>
				<%	} %>
				<p>Expectativas Económicas: <span>$<%= candidate.getExpectation() %></span></p>
				<p>Entrevistas: </p>
				<ul id="interviews">
                                    <%	List<Interview> interviews = (List<Interview>)candidate.getInterviews();
					int sizei = (interviews != null) ? interviews.size() : 0;
					for (int i=0; i<sizei; i++) { %>
                                        <li><a href="interviews?operation=show&id=<%= interviews.get(i).getId() %>"><%= interviews.get(i).getJobTitle()%>, <%= interviews.get(i).getDate()%></a></li>
                                     <% } %>
				</ul> 
			</div>
			<div id="buttons">
                            <a class="button" id="edit" href="candidates?operation=edit&id=<%= candidate.getId()%>">Editar</a>
                            <a class="button" id="delete" href="candidates?operation=delete&id=<%= candidate.getId() %>">Borrar</a>
                            <a class="button hire-btn" id="hire-btn" href="employees?operation=hire&id=<%= candidate.getId() %>">Contratar</a>
			</div>
		</div>
	</div>
	<%@include  file="footer.html" %>
</body>
</html>
