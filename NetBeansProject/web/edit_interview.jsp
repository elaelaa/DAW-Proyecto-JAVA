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
        <script src="js/edit_create_functionality.js"></script> 
    </head>
    <body>
        
        <%@include file="/header.jsp" %>
        
	<div id="main">
            <div class="wrapper">
                <form action="interviews" id="createform" method="post">
                    <input type="hidden" name="operation" value="edit">
                    <input type="hidden" name="interviewId" value="${interview.getId()}">
                    <input type="hidden" name="employeeId" value="${interview.getEmployeeId()}">
                    <p>Entrevistador: <span>${interview.getInterviewerName()}</span></p>
                     <p><label>Puesto de Trabajo:
                        <input type="text" name="jobTitle" value="${interview.getJobTitle()}" required></label>
                    </p>
                    <p><label>Fecha de la Entrevista:
                        <input type="text" name="date" class="datepicker" value="${interview.getDate()}" required></label>
                    </p>
                    <p><label>Candidato:
                            <select name = "candidateId" required> 
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
				  <option value="faceToFace">Presencial</option>
				  <option value="video">Video</option>
				  <option value="phone">Teléfono</option>
				  <option value="email">Email</option>
                                  <option value="skype">Skype</option>
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
