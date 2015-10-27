/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Candidate;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class CreateCandidate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateCandidate</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateCandidate at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        
        // get parameters from the request
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("lastname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email"); 
        String title = request.getParameter("title");
        String birthday = request.getParameter("birthday");
        
        String[] types = request.getParameterValues("type"); 
        String[] degrees = request.getParameterValues("degreename"); 
        String[] universities = request.getParameterValues("university"); 
        String[] dates = request.getParameterValues("dateacquired"); 
        
        String[] previousJobs = request.getParameterValues("jobTitle");
        String[] previousCompanies = request.getParameterValues("company"); 
        String[] descriptions = request.getParameterValues("description");
        String[] salaries = request.getParameterValues("salary");
        String[] startDates = request.getParameterValues("startdate");
        String[] endDates = request.getParameterValues("enddate");
        
        String economicExpect = request.getParameter("expectation");
        
        //flag to check if there was errors in processing parameters
        Boolean errorFlag = false; 
        double expectation = 0;
        if (!economicExpect.isEmpty())
        {
            expectation = Double.parseDouble(economicExpect); 
        }
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date dateOfBirth = new Date();
        try{
            dateOfBirth = df.parse(birthday);
        } 
        catch (ParseException ex){
           
            errorFlag = true; 

            String bdError = "Insert dates in format mm/dd/yyyy! <br>";
            request.setAttribute("bdError", bdError); 
        }
        
        //VALIDATION OF PHONE NUMBER NEEDED
        
        
        //title needed!!!!!
        Candidate candidate = new Candidate(expectation, firstName + lastName, address, phone, email, dateOfBirth);
        //add to db only when all the data checked
        //assign id?
        
        String type; 
        String degree; 
        String university; 
        String dateStr; 
        
        ArrayList degreeList = new ArrayList(); 
        
        if (types != null)
        {
            for(int i = 0; i<types.length; i++)
            {
                degree = ""; 
                university = ""; 
                dateStr = ""; 
                type = types[i];
                degree = degrees[i]; 
                university = universities[i];
                dateStr = dates[i]; 
                Date dateOfCert; 
                try{
                    dateOfCert = df.parse(dateStr);
                } 
                catch (Exception e){
                    errorFlag = true; 

                    String certDateError = "Insert dates in format mm/dd/yyyy! <br>";
                    request.setAttribute("certDateError", certDateError);
                }
                
                if (!errorFlag)
                {
                    
                    //Certificate cert = new Certificate(int id, int personId, type, degree, university, dateOfCert);
                    
                    //add cert to db
                    
                    //listing not needed? 
                    //degreeList.add(cert);
                }
            }
        }
        
        String jobTitle; 
        String company; 
        String description; 
        String salaryStr; 
        String startDateStr;
        String endDateStr; 
        
        ArrayList jobList = new ArrayList(); 
        
        if (previousJobs != null)
        {
            for(int i = 0; i<previousJobs.length; i++)
            {
                company = ""; 
                description = ""; 
                salaryStr = ""; 
                startDateStr = ""; 
                endDateStr = ""; 
                jobTitle = previousJobs[i];
                company = previousCompanies[i]; 
                description = descriptions[i];
                salaryStr = salaries[i];
                startDateStr = startDates[i];
                endDateStr = endDates[i];

                Date endDate; 
                Date startDate;
                try{
                    startDate = df.parse(startDateStr);
                } 
                catch (Exception e){

                    errorFlag = true; 

                    String jobDateError = "Insert dates in format mm/dd/yyyy! <br>";
                    request.setAttribute("jobDateError", jobDateError);
                }
                try{
                    endDate = df.parse(endDateStr);
                } 
                catch (Exception e){
                    errorFlag = true; 

                    String jobDateError = "Insert dates in format mm/dd/yyyy! <br>";
                    request.setAttribute("jobDateError", jobDateError);
                }

               
                
                if (!errorFlag)
                {
                    double salary = Double.parseDouble(salaryStr); 
                    
                    //PreviousJob job = PreviousJob(int id, int person_id, jobTitle, description, salary, startDate, endDate); 

                    //add job to db

                    //listing is not needed? 
                    //jobList.add(job);
                }
            }
        }
        
        //Some other validation? 
        
        ServletContext context = getServletContext();
        if (errorFlag = true)
        {
            request.setAttribute("candidate", candidate);
            String url = "/create_candidate.jsp";



            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response); 
        }
        else
        {
            //add candidate to db? 

            String url = "/candidates.jsp";

            RequestDispatcher dispatcher = context.getRequestDispatcher(url);
            dispatcher.forward(request, response); 

            //any success messages needed? 
        }
        
        
        
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
