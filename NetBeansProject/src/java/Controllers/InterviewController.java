/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Employee;
import Model.Candidate;
import Model.Interview;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author elaela
 */
public class InterviewController extends HttpServlet {

  
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throaws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         
        String operation = request.getParameter("operation");
        String paramId = request.getParameter("id");
        
        String url = "/404.jsp"; // starts default not found url
        
        Boolean redirect =false; 
        
        //handling the rerouting based on operation 
        if (operation == null){
            List<Interview> interviews = Interview.getAll(); // all of the employees
            request.setAttribute("interviews", interviews);  // setting the attribute
            url = "/interviews.jsp";                         // url to redirect to
        }
        else if (paramId != null && paramId.matches("\\d+") &&
				(operation.equals("edit") || operation.equals("show") || operation.equals("delete"))){ //if operation is only digits??
            int id = Integer.parseInt(paramId);
            Interview interview = Interview.getById(id);
            if (interview != null){                                                  // else, set route to candidate view
                request.setAttribute("interview", interview);   // and init the req parameter
            }
            switch (operation) {
                case "show":
                    url = "/show_interview.jsp";
                    break;
                case "edit":
                    List<Candidate> candidates = Candidate.getAll(); // all of the employees
                    request.setAttribute("candidates", candidates);
                    url = "/edit_interview.jsp";
                    break;
                case "delete":
                    DeleteInterview(interview);
                    response.sendRedirect("interviews");
                    redirect = true;
                    break; 
            }
        }
        else if (operation.equals("create")){
            List<Candidate> candidates = Candidate.getAll(); // all of the employees
            request.setAttribute("candidates", candidates);
            int employeeId = (int)request.getSession().getAttribute("loggedIn");
            Employee employee;
            employee = Employee.getById(employeeId);
            request.setAttribute("employee", employee);
            url = "/create_interview.jsp";
        }
        
        if (!redirect)
        {
        // redirect after analyzing options above
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }
        

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         //figure out if trying to modify or create
        String operation = request.getParameter("operation"); 
        
        Boolean creating = false; 
        
        if (operation.equals("create"))
        {
            creating = true; 
        }
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        Interview interview = createInterview(request, creating, df); 
        
        ServletContext context = getServletContext();
        
        interview.save(); 
        int id = interview.getId(); 
        
        String url = "/show_interview.jsp";

        request.setAttribute("interview", interview); 

        RequestDispatcher dispatcher = context.getRequestDispatcher(url);
        dispatcher.forward(request, response); 
        
        
    }
    
    
    /**
     * Creates or updates new employee object and adds it to database.
     * @param creating if creating or not
     * @param df dateformat
     * @return Employee created.
     */
    private Interview createInterview(HttpServletRequest request, Boolean creating, DateFormat df)
    {
        String interviewId = request.getParameter("interviewId");
        
        String employeeId = request.getParameter("employeeId");
        String jobTitle = request.getParameter("jobTitle");
        String dateStr = request.getParameter("date");
        String candidateId = request.getParameter("candidateId");
        String platform = request.getParameter("platform");
        String feedback = request.getParameter("feedback");
        
        int eId = Integer.parseInt(employeeId);
        int cId = Integer.parseInt(candidateId);
        
        Date date = new Date();
        try{
            date = df.parse(dateStr);
        } 
        catch (ParseException ex){
            
        }
        
        Interview interview = null; 
        
        
        if (creating)
        {
            interview = new Interview(eId, cId, jobTitle, feedback, platform, date);     
        }
        else 
        {
            int id = Integer.parseInt(interviewId);
            interview = interview.getById(id); 
            interview.Update(eId, cId, jobTitle, feedback, platform, date);
        }
        
        return interview; 
        
    }
    
    /**
     * Deletes Employee, previousJobs and certificates from database.
     */
    private void DeleteInterview(Interview interview){
        Interview.deleteById(interview.getId());
        
    }
  
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
