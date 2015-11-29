<%-- 
    Document   : create_candidate
    Created on : Oct 20, 2015, 10:22:17 PM
    Author     : elaela
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Nueva Entrevista</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
         <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
        <script src="js/edit_interview_functions.js"></script> 
    </head>
    <body>
        
        <%@include file="/header.jsp" %>
        
	<div id="main">
            <div class="wrapper">
                <form action="interviews" id="createform" method="post">
                    <input type="hidden" name="operation" value="create">
                    <input type="hidden" name="employeeId" value="${employee.getId()}">
                    <p>Entrevistador: <span>${employee.getFullName()}</span></p>
                     <p><label>Puesto de Trabajo:
                        <input type="text" name="jobTitle" value="${interview.getJobTitle()}" required></label>
                    </p>
                    <p><label>Fecha de la Entrevista:
                        <input type="text" name="date" class="datepicker" value="${interview.getDate()}" required></label>
                    </p>
                    <p><label>Candidato:
                            <select name = "candidateId" required > 
                        <%	List<Candidate> candidates = (List<Candidate>)request.getAttribute("candidates");
					int size = (candidates != null) ? candidates.size() : 0;
					for (int i=0; i<size; i++) { %>
                                        <option value="<%=  candidates.get(i).getId() %>"><%= candidates.get(i).getFullName() %></option>
				<% } %>
                            </select>
                        </label>
                    </p>
                    <p><label>Plataforma:
                        <select name="platform" required value ="${interview.getPlatform()}">   
				  <option value="Presencial">Presencial</option>
				  <option value="Video">Video</option>
				  <option value="Telefono">Telefono</option>
				  <option value="Email">Email</option>
                                  <option value="Skype">Skype</option>
			</select>
                        </label>
                    </p> 
                     <p><label>Feedback:
                        <textarea name = "feedback" rows="4" cols="50">
                        ${interview.getFeedback()}
                        </textarea>
                    </p>
                    <input type ="submit"  value="Guardar">
                </form>
            </div>	
        </div>
    
        <%@include  file="/footer.html" %>
        
    </body>	
</html>
