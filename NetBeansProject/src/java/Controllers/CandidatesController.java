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
import java.util.Set;
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
        else if (paramId != null && paramId.matches("\\d+") &&
				(operation.equals("edit") || operation.equals("show") || operation.equals("delete"))){ //if operation is only digits??
            int id = Integer.parseInt(paramId);
            Candidate candidate = Candidate.getById(id);
            if (candidate != null){                                                  // else, set route to candidate view
                request.setAttribute("candidate", candidate);   // and init the req parameter
            }
            if (operation.equals("show"))
            {
                url = "/show_candidate.jsp";
            }
            else if (operation.equals("edit"))
            {
                url = "/edit_candidate.jsp";
            }
            else if (operation.equals("delete"))
            {
                //IMPLEMENT ME!!! 
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
        String personId = request.getParameter("id"); 
        
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
        String professionalTitle = request.getParameter("professionalTitle");
        String birthday = request.getParameter("birthday");
        
        String[] certIDs = request.getParameterValues("certId"); 
        String[] types = request.getParameterValues("type"); 
        String[] degrees = request.getParameterValues("degreename"); 
        String[] universities = request.getParameterValues("university"); 
        String[] dates = request.getParameterValues("dateacquired"); 
        
        String[] jobIDs = request.getParameterValues("jobId"); 
        String[] previousJobs = request.getParameterValues("jobTitle");
        String[] previousCompanies = request.getParameterValues("company"); 
        String[] descriptions = request.getParameterValues("description");
        String[] salaries = request.getParameterValues("salary");
        String[] startDates = request.getParameterValues("startdate");
        String[] endDates = request.getParameterValues("enddate");
        
        String economicExpect = request.getParameter("expectation");
        
        //flag to check if there was errors in processing parameters
        Boolean errorFlag = false; 
        
        //processing parameters
        double expectation = 0;
        if (!economicExpect.isEmpty())
        {
            expectation = Double.parseDouble(economicExpect); 
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = new Date();
        try{
            dateOfBirth = df.parse(birthday);
        } 
        catch (ParseException ex){
           
            errorFlag = true; 

            String bdError = "Insert dates in format yyyy-mm-dd! <br>";
            request.setAttribute("bdError", bdError); 
        }
        phone = phone.replaceAll(" ", "");
        phone = phone.replaceAll(".", "");
        phone = phone.replaceAll("-", ""); 
        
        
        Candidate candidate = null; 
        
        if (creating)
        {
            candidate = new Candidate(expectation, firstName, lastName, address, 
                    phone, email, professionalTitle, dateOfBirth);
        }
        else 
        {
            int id = Integer.parseInt(personId);
            candidate = Candidate.getById(id); 
            candidate.Update(expectation, firstName, lastName, address, 
                    phone, email, professionalTitle, dateOfBirth);
        }

        
        ArrayList degreeList = new ArrayList(); 
        ArrayList jobList = new ArrayList(); 
        String type; 
        String degree; 
        String university; 
        String dateStr; 

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

                    String certDateError = "Insert dates in format yyyy-mm-dd! <br>";
                    request.setAttribute("certDateError", certDateError);
                }

                if (!errorFlag)
                {

                    int certID = -1; 

                    if (certIDs != null)
                    {
                        if (certIDs.length > i)
                        {
                            certID = Integer.parseInt(certIDs[i]); 
                        }
                        
                    }
                    Certificate cert = null;  
                    if (certID > -1)
                    {
                        //cert = Certificate.getById(certID); 
                        //cert.Update(type, degree, university, dateOfCert); 
                    }
                    else
                    {
                        cert = new Certificate(type, degree, university, dateOfCert);
                    }
                   
                    degreeList.add(cert);
                }
            }

            String jobTitle; 
            String company; 
            String description; 
            String salaryStr; 
            String startDateStr;
            String endDateStr; 
            
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

                    String jobDateError = "Insert dates in format yyyy-mm-dd! <br>";
                    request.setAttribute("jobDateError", jobDateError);
                }
                try{
                    endDate = df.parse(endDateStr);
                } 
                catch (Exception e){
                    errorFlag = true; 

                    String jobDateError = "Insert dates in format yyyy-mm-dd! <br>";
                    request.setAttribute("jobDateError", jobDateError);
                }



                if (!errorFlag)
                {
                    double salary = Double.parseDouble(salaryStr); 
                   
                    int jobID = -1; 
                    
                    if (jobIDs != null)
                    {
                        if (jobIDs.length > i)
                        {
                            jobID = Integer.parseInt(jobIDs[i]); 
                        }
                        
                    }
                    PreviousJob job = null; 
                    if (jobID > -1)
                    {
                        //job = PreviousJob.getById(jobID); 
                        //job.Update(jobTitle, description, salary, startDate, endDate); 
                    }
                    else
                    {
                        job = new PreviousJob(jobTitle, description, salary, startDate, endDate); 
                    }
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
                candidate.save(); 
                int id = candidate.getId(); 
                
                for (Object prevJob : jobList) {
                    PreviousJob job = (PreviousJob) prevJob;
                    job.setPerson_id(id);
                    //job.save(); 
                }
                
                for (Object cert : degreeList) {
                    Certificate certificate = (Certificate) cert;
                    certificate.setPersonId(id);
                    certificate.save(); 
                }
                
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
                candidate.save(); 
                int id = candidate.getId(); 
                
                ArrayList newCertIDs = new ArrayList();
                ArrayList newJobIDs = new ArrayList(); 
                
                for (Object prevJob : jobList) {
                    PreviousJob job = (PreviousJob) prevJob;
                    if (job.getId() == -1)
                    {
                        job.setPerson_id(id);
                    }
                    //job.save(); 
                    newJobIDs.add(job.getId());
                }
                
                for (Object cert : degreeList) {
                    Certificate certificate = (Certificate) cert;
                    if (certificate.getId() == -1)
                    {
                        certificate.setPersonId(id);
                    }
                    certificate.save(); 
                    newCertIDs.add(certificate.getId()); 
                }
                
                for (Certificate oldCert : candidate.getCertificates())
                {
                    int oldId = oldCert.getId(); 
                    if (!newCertIDs.contains(oldId))
                    {
                        //oldCert.remove(); 
                    }
                }
                
                for (PreviousJob oldJob : candidate.getPreviousJobs())
                {
                    int oldId = oldJob.getId(); 
                    if (!newJobIDs.contains(oldId))
                    {
                        //oldJob.remove(); 
                    }
                }
                
                String url = "/show_candidate.jsp";
                
                request.setAttribute("candidate", candidate); 
                
                RequestDispatcher dispatcher = context.getRequestDispatcher(url);
                dispatcher.forward(request, response); 
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
