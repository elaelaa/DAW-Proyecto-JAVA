/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Candidate;
import Model.Certificate;
import Model.PreviousJob;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Beto
 */
public class CandidatesController extends HttpServlet {
    
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
        
        String operation = request.getParameter("operation");
        String paramId = request.getParameter("id");
        
        String url = "/404.jsp"; // starts default not found url
        
        //handling the rerouting based on opertion? 
        if (operation == null){
            List<Candidate> candidates = Candidate.getAll(); // all of the candidates
            request.setAttribute("candidates", candidates);  // setting the attribute
            url = "/candidates.jsp";                         // url to redirect to
        }
        else if (paramId != null && paramId.matches("\\d+")){ //if operation is only digits??
            int id = Integer.parseInt(paramId);
            Candidate candidate = Candidate.getById(id);
            if (candidate != null){                                                  // else, set route to candidate view
                request.setAttribute("candidate", candidate);   // and init the req parameter
                url = "/show_candidate.jsp";
            }
        }
        else if (operation.equals("create")){
            url = "/create_candidate.jsp";
        }

        // redirect after analyzing options above
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
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

            String bdError = "Insert dates in format MM/dd/yyyy! <br>";
            request.setAttribute("bdError", bdError); 
        }
        
        //VALIDATION OF PHONE NUMBER NEEDED
        
        Candidate candidate = null; 
        
        if (creating)
        {
            
            //Create the candidate object
            //title needed!!!!!
            candidate = new Candidate(expectation, firstName + lastName, address, phone, email, dateOfBirth);
            //add to db only when all the data checked
            //assign id?
        }
        else 
        {
            //find candidate, compare values...
        }

        
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
                Date dateOfCert = null; 
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
                    
                    Certificate cert = new Certificate(type, degree, university, dateOfCert);
                    
                    degreeList.add(cert);
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

                Date endDate = null; 
                Date startDate = null;
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
                    
                    PreviousJob job = new PreviousJob(jobTitle, description, salary, startDate, endDate); 

                    jobList.add(job);
                }
            }
        }
        
        //Some other validation? 
        
        ServletContext context = getServletContext();
        if (creating){
            
        
            if (errorFlag)
            {
                request.setAttribute("candidate", candidate);
                request.setAttribute("previousJobs", jobList);
                request.setAttribute("certificates", degreeList);
                
                String url = "/create_candidate.jsp";
                
                RequestDispatcher dispatcher = context.getRequestDispatcher(url);
                dispatcher.forward(request, response); 
            }
            else
            {
                //add candidate to db
                //add all jobs & certificates to db
                //set ids?
                
                String url = "/candidates.jsp";

                RequestDispatcher dispatcher = context.getRequestDispatcher(url);
                dispatcher.forward(request, response); 
                
                //any success messages needed? 
            }
        }
        else
        {
            if (errorFlag)
            {
                request.setAttribute("candidate", candidate);
                String url = "/edit_candidate.jsp";

                RequestDispatcher dispatcher = context.getRequestDispatcher(url);
                dispatcher.forward(request, response); 
            }
            else
            {
                //redirect to showing candidate with new data
            }
        
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
