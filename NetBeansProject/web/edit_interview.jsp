<%-- 
    Document   : create_candidate
    Created on : Oct 20, 2015, 10:22:17 PM
    Author     : elaela
--%>

<%@page import="Model.Interview"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Editar Entrevista</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
         <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
        <script>
            window.onload = function getFields()
            {
                var index=0;
                var platform="${interview.getPlatform()}";
                switch (platform){
                    case "Presencial": index=0;break;
                    case "Video": index=1;break;
                    case "Telefono": index=2;break;
                    case "Email": index=3;break;
                    case "Skype": index=4;break;

                }
                document.getElementById("platform").selectedIndex=index;
                
                var candidate = "${interview.getCandidateId()}";
                
                index=0; 
                <% List<Candidate> candidates1 = (List<Candidate>)request.getAttribute("candidates");
                    int size1 = (candidates1 != null) ? candidates1.size() : 0;
                    Interview interview = (Interview)request.getAttribute("interview");
                    int id = interview.getCandidateId(); 
                    int i1 = 0; 
                    while (id != candidates1.get(i1).getId() &&  i1 < size1) { 
                                i1++; %>
                                index = index + 1; 
                                
                <% } %>
                
                document.getElementById("candidateId").selectedIndex=index;
            }
        </script>
    </head>
    <body>
        
        <%@include file="/header.jsp" %>
        
	<div id="main">
            <div class="wrapper">
                <form action="interviews" id="createform" method="post">
                    <input type="hidden" name="interviewId" value="${interview.getId()}">
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
                            <select name = "candidateId" id="candidateId" required> 
                        <%	List<Candidate> candidates = (List<Candidate>)request.getAttribute("candidates");
					int size = (candidates != null) ? candidates.size() : 0;
					for (int i=0; i<size; i++) { %>
                                        <option value="<%=  candidates.get(i).getId() %>"><%= candidates.get(i).getFullName() %></option>
				<% } %>
                            </select>
                        </label>
                    </p>
                    <p><label>Plataforma:
                        <select name="platform" id="platform" required>   
				  <option value="Presencial">Presencial</option>
				  <option value="Video">Video</option>
				  <option value="Telefono">Telefono</option>
				  <option value="Email">Email</option>
                                  <option value="Skype">Skype</option>
			</select>
                        </label>
                    </p> 
                     <p><label>Feedback:
                        <textarea name = "feedback" rows="4" cols="50">${interview.getFeedback()}</textarea>
                    </p>
                    <input type ="submit"  value="Guardar">
                </form>
            </div>	
        </div>
    
        <%@include  file="/footer.html" %>
        <script src="js/edit_interview_functions.js"></script>
    </body>	
</html>
