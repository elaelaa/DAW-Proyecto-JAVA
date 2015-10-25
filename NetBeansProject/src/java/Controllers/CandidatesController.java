/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Candidate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
     * Gets the ID from the <code>GET</code> url.
     *
     * @param url the route string
     * @return id => the id number in url
     */
    private int getIdFromURL(String url){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(url);
        int id = Integer.parseInt(m.group());
        return id;
    }
    
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
        
        // initializing variables
        String action = request.getServletPath();
        String url = "/404.jsp"; // starts default not found url
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        
        // route to candidates index
        if (action.equals("/candidates")){
            List<Candidate> candidates = Candidate.getAll(); // all of the candidates
            request.setAttribute("candidates", candidates);  // setting the attribute
            url = "/candidates.jsp";                         // url to redirect to
        }
        // route  candidates show (has id in url)
        else if (action.matches("/candidates/\\d+")){
            int id = getIdFromURL(action);
            Candidate candidate = Candidate.getById(id);
            if (candidate == null){                         // if it didn't find the candidate
                dispatcher.forward(request, response);      // forward to default 404 page
            }                                               // else, set route to candidate view
            request.setAttribute("candidate", candidate);   // and init the req parameter
            url = "/candidate.jsp";
        }
        // redirect after analyzing options above
        dispatcher = getServletContext().getRequestDispatcher(url);
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
        
        String action = request.getPathInfo(); 
        
        // get parameters from the request
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("lastname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email"); 
        String title = request.getParameter("title");
        
        String[] types = request.getParameterValues("type"); 
        String[] degrees = request.getParameterValues("degreename"); 
        String[] universities = request.getParameterValues("university"); 
        
        String[] previousJobs = request.getParameterValues("jobTitle");
        String[] previousCompanies = request.getParameterValues("company"); 
        String[] jobDurations = request.getParameterValues("duration");
        
        String economicExpect = request.getParameter("expectation");
        
        //flag to check if there was errors in processing parameters
        Boolean errorFlag = false; 
        
        String type; 
        String degree; 
        String university; 
        
        ArrayList degreeList = new ArrayList(); 
        
        if (types != null)
        {
            for(int i = 0; i<types.length; i++)
            {
                degree = ""; 
                university = ""; 
                if (i < universities.length || i < degrees.length)
                {
                    
                    type = types[i];
                    degree = degrees[i]; 
                    university = universities[i];
                    //Certificate cert = new cert(type, degree, university); 
                    //degreeList.add(cert);
                }
                else
                {
                    errorFlag = true; 
                    String degreeError = "Fill all the fields for all degrees! <br>";
                    request.setAttribute("degreeError", degreeError);
                }
            }
        }
        
        String job; 
        String company; 
        String duration; 
        
        ArrayList jobList = new ArrayList(); 
        
        if (previousJobs != null)
        {
            for(int i = 0; i<previousJobs.length; i++)
            {
                company = ""; 
                duration = ""; 
                if (i < previousCompanies.length && i < jobDurations.length)
                {
                    
                    job = previousJobs[i];
                    company = previousCompanies[i]; 
                    duration = jobDurations[i];
                    //Job job = new job(title, company, duration); 
                    //jobList.add(job);
                }
                else
                {
                    //this shouldn't be necessary anymore
                    errorFlag = true; 
                    String jobError = "Fill all the fields for all jobs! <br>";
                    request.setAttribute("jobError", jobError);
                }
            }
        }
        
        //Some other validation? 
        
        //Create the candidate object
        //Candidate candidate = new Candidate(firstName, lastName, address, phone, email, title, degreeList, jobList, economicExpect);
        
        //Send error messages if there was errors
        
        if (action.equals("/create")){
            
        
            if (errorFlag = true)
            {
                //request.setAttribute("candidate", candidate); 

                ServletContext context = getServletContext();
                String url = "/create_candidate.jsp";

                RequestDispatcher dispatcher = context.getRequestDispatcher(url);
                dispatcher.forward(request, response); 
            }
            else
            {
                //send to database
            }
        }
        //add handling of editing 
        
        //Display message of success? 
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
