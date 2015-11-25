<%-- 
    Document   : hire_candidate_form
    Created on : Nov 24, 2015, 4:00:07 PM
    Author     : Beto
--%>

<%@page import="Model.PreviousJob"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Candidate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contratación de candidato</title>
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
         <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
        <script src="js/edit_create_functionality.js"></script> 
    </head>
    <body>
        
        <!-- Special style -->
        <style>
            h2 {
                font-size: 20px;
                font-weight: 800;
            }
            b { font-weight: 600; }
            h3 { margin: 10px 10px; }
            hr {
                background-color: #EEE;
                border: 1px solid #333;
                margin: 15px 0px 20px 0px;
            }

        </style>

        <%@include file="/header.jsp" %>
        
    <div id="main">
            <div class="wrapper">
                <form action="employees" id="hireForm" method="post">

                    <!-- Declaring vairables and setting hidden POST fields -->
                    <% Candidate candidate = (Candidate)request.getAttribute("candidate"); %>
                    <input type="hidden" name="operation" value="hire">
                    <input type="hidden" name="candidateId" value="${candidate.id}">

                    <!-- Person to hire data -->
                    <div style="text-align: left;">
                        <h2>Datos de la persona a contratar</h2>
                        <h3><b>Nombre:</b> ${candidate.getFirstName()}</h3>
                        <h3><b>Apellidos:</b> ${candidate.getLastName()}</h3>
                        <h3><b>Dirección:</b> ${candidate.getAddress()}</h3>
                        <h3><b>Teléfono:</b> ${candidate.getPhone()}</h3>
                        <h3><b>Correo Electrónico:</b> ${candidate.getEmail()}</h3>
                        <h3><b>Fecha de Nacimiento:</b> ${candidate.getDateOfBirth()}</h3>
                    </div>
                        
                    <hr>

                    <h2>Datos del nuevo empleado</h2>

                    <!-- The actual form with the new employee data -->
                    <p>
                        <label>Puesto: </label>
                        <input type="text" name="jobTitle"  required>
                    </p>
                    <p>
                        <label>Fecha de inicio: </label>
                        <input type="text" name="startDate" class="datepicker" required>
                    </p>
                    <p>
                        <label>Salario: </label>
                        <input type="number" name="salary" required>
                    </p>
                    <p>
                        <label>Días de vacaciones: </label>
                        <input type="number" name="vacationDays" required title="Amount of vacation days left.">
                    </p>

                    <!-- Submit Button -->
                    <div id="buttons">
                        <input type ="submit" id="hire-btn" value="Contratar!">
                        <a href="candidates" class="button" style="color:black; background-color:white;">Cancelar</a>
                    </div>
                </form>
            </div>  
        </div>

        <!-- Special script -->
        <script>
            document.getElementById("hire-btn").onclick = function (e){
                var message = "Esta seguro de que quiere contratar a al candidato ${candidate.getFirstName()}?";
                if (!confirm(message)) {
                    e.preventDefault();
                };
            }
        </script>
    
        <%@include  file="/footer.html" %>
        
    </body> 
</html>