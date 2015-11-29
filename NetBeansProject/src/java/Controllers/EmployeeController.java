/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Candidate;
import Model.Certificate;
import Model.Employee;
import Model.PreviousJob;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class EmployeeController extends HttpServlet {

  
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
            List<Employee> employees = Employee.getAll(); // all of the employees
            request.setAttribute("employees", employees);  // setting the attribute
            url = "/employees.jsp";                         // url to redirect to
        }
        else if (paramId != null && paramId.matches("\\d+") &&
			    (operation.equals("edit") || operation.equals("show") || 
                 operation.equals("delete") || operation.equals("hire"))){ //if operation is only digits??
            int id = Integer.parseInt(paramId);
            Employee employee = Employee.getById(id);
            if (employee != null){                                                  // else, set route to candidate view
            request.setAttribute("employee", employee);   // and init the req parameter
            }
            switch (operation) {
                case "show":
                    url = "/show_employee.jsp";
                    break;
                case "edit":
                    url = "/edit_employee.jsp";
                    break;
                case "delete":
                    DeleteEmployee(employee);
                    response.sendRedirect("employees");
                    redirect = true;
                    break; 
                case "hire": // super special case
                    url = "/hire_candidate_form.jsp";
                    Candidate candidate = Candidate.getById(id);
                    request.setAttribute("candidate", candidate);
            }
        }
        else if (operation.equals("create")){
            url = "/create_employee.jsp";
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
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            
        ServletContext context = getServletContext();

        switch (operation){
            case "create":
                creating = true;
                break;
            case "hire": // super special case...
                String strCandidateId = request.getParameter("candidateId");
                int candidateId = Integer.parseInt(strCandidateId);
                Candidate candidate = Candidate.getById(candidateId);
                Employee newEmployee = makeEmployee(candidate, request);

                // repeated code!
                String url = "/show_employee.jsp"; 
                request.setAttribute("employee", newEmployee);
                RequestDispatcher dispatcher = context.getRequestDispatcher(url);
                dispatcher.forward(request, response);
        }

        Employee employee = createEmployee(request, creating, df); 
        
        employee.save(); 
        int id = employee.getId(); 
        
        List<Certificate> oldCertificates = employee.getCertificates();
        List<PreviousJob> oldJobs = employee.getPreviousJobs();
        
        ArrayList newCertIDs = createCertificates(request, creating, df, id);
        ArrayList newJobIDs = createJobs(request, creating, df, id); 
        
        if (!creating)
        {
            for (Certificate oldCert : oldCertificates)
            {
                int oldId = oldCert.getId(); 
                if (!newCertIDs.contains(oldId))
                {
                    Certificate.deleteById(oldId);
                }
            }
             
            for (PreviousJob oldJob : oldJobs)
            {
                int oldId = oldJob.getId(); 
                if (!newJobIDs.contains(oldId))
                {
                    PreviousJob.deleteById(oldId); 
                }
            }
        }
        
        String url = "/show_employee.jsp";

        request.setAttribute("employee", employee); 

        RequestDispatcher dispatcher = context.getRequestDispatcher(url);
        dispatcher.forward(request, response); 
    }
    
    
    /**
     * Creates or updates new employee object and adds it to database.
     * @param creating if creating or not
     * @param df dateformat
     * @return Employee created.
     */
    private Employee createEmployee(HttpServletRequest request, Boolean creating, DateFormat df)
    {
        
        String personId = request.getParameter("id"); 
        
        // get parameters from the request
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("lastname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String currentTitle = request.getParameter("currentTitle");
        String professionalTitle = request.getParameter("professionalTitle");
        String currentSalaryStr = request.getParameter("currentSalary");
        String birthday = request.getParameter("birthday");
        String vacationDaysStr = request.getParameter("vacations");
        String startDateStr = request.getParameter("startDate");
        
        double currentSalary = Double.parseDouble(currentSalaryStr);
        int vacationDays = Integer.parseInt(vacationDaysStr);
        
        Date dateOfBirth = new Date();
        try{
            dateOfBirth = df.parse(birthday);
        } 
        catch (ParseException ex){
            
        }
        
        Date startDate = new Date();
        try{
            startDate = df.parse(startDateStr);
        } 
        catch (ParseException ex){
            
        }
        phone = phone.replaceAll(" ", "");
        phone = phone.replaceAll("\\.", "");
        phone = phone.replaceAll("-", ""); 
        
        
        Employee employee = null; 
        
        
        if (creating)
        {
            employee = new Employee(currentSalary, currentTitle, startDate, firstName, lastName, address, 
                    phone, email, professionalTitle, dateOfBirth, vacationDays);     
        }
        else 
        {
            int id = Integer.parseInt(personId);
            employee = Employee.getById(id); 
            employee.Update(currentSalary, currentTitle, startDate, firstName, lastName, address, 
                    phone, email, professionalTitle, dateOfBirth, vacationDays);
        }
        
        return employee; 
        
    }
    
    
    /**
     * Creates or updates previous jobs to given id.
     * @return ArrayList of jobs. 
     */
    private ArrayList createJobs(HttpServletRequest request, Boolean creating, DateFormat df, int personID){
        
        String[] jobIDs = request.getParameterValues("jobId"); 
        String[] previousJobs = request.getParameterValues("jobTitle");
        String[] previousCompanies = request.getParameterValues("company"); 
        String[] descriptions = request.getParameterValues("description");
        String[] salaries = request.getParameterValues("salary");
        String[] startDates = request.getParameterValues("startdate");
        String[] endDates = request.getParameterValues("enddate");
        
        ArrayList jobList = new ArrayList(); 
        
        String jobTitle; 
        String company; 
        String description; 
        String salaryStr; 
        String startDateStr;
        String endDateStr; 

        
        if (previousJobs != null){
            
            for(int i = 0; i<previousJobs.length; i++){
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
                }
                try{
                    endDate = df.parse(endDateStr);
                } 
                catch (Exception e){
                }

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
                    job = PreviousJob.getById(jobID); 
                    job.Update(jobTitle, company, description, salary, startDate, endDate); 
                }   
                else
                {
                    job = new PreviousJob(personID, jobTitle, company, description, salary, startDate, endDate); 
                }
                job.save(); 
                jobList.add(job.getId());
            }
        }
        return jobList; 
    }
    
    
    /**
     * Creates or updates certificates to given id.
     * @return ArrayList of certificates. 
     */
    private ArrayList createCertificates(HttpServletRequest request, Boolean creating, DateFormat df, int personID){
        
        String[] certIDs = request.getParameterValues("certId"); 
        String[] types = request.getParameterValues("type"); 
        String[] degrees = request.getParameterValues("degreename"); 
        String[] organizations = request.getParameterValues("organization"); 
        String[] dates = request.getParameterValues("dateacquired"); 
        
        ArrayList degreeList = new ArrayList(); 
        String type; 
        String degree; 
        String organization; 
        String dateStr; 

        if (types != null)
        {
            for(int i = 0; i<types.length; i++)
            {
                type = types[i];
                degree = degrees[i]; 
                organization = organizations[i];
                dateStr = dates[i]; 
                Date dateOfCert = null; 
                try{
                    dateOfCert = df.parse(dateStr);
                } 
                catch (Exception e){
                
                }
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
                    cert = Certificate.getById(certID); 
                    cert.Update(type, degree, organization, dateOfCert); 
                }
                else
                {
                    cert = new Certificate(personID, type, degree, organization, dateOfCert);
                }
                
                cert.save();
                degreeList.add(cert.getId());
            }
        }
        return degreeList; 
        
    }

    /**
     * makeEmployee
     * 
     * Transforms a candidate to an employee with the parameters of the request
     * @param candidate
     * @param request
     * @return The newly hired Employee object
     */
    private Employee makeEmployee(Candidate candidate, HttpServletRequest request){
        // gathering params from te request
        String jobTitle = request.getParameter("jobTitle");

        // parsing the salary str
        String strSalary = request.getParameter("salary");
        double salary = Double.parseDouble(strSalary);

        // parsing the vacation days int
        String strVacationDays = request.getParameter("vacationDays");
        int vacationDays = Integer.parseInt(strVacationDays);

        // parsing the date with the date format
        String strStartDate = request.getParameter("startDate");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        try {
            startDate = df.parse(strStartDate);
        } catch (ParseException ex) {
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // actually hiring the candidate
        Employee newHire = candidate.hire(jobTitle, salary, startDate, vacationDays);
        return newHire;
    }
    
    /**
     * Deletes Employee, previousJobs and certificates from database.
     */
    private void DeleteEmployee(Employee employee){
        
        for (PreviousJob job : employee.getPreviousJobs()){
            int jobId = job.getId();
            PreviousJob.deleteById(jobId);
        }
        for (Certificate cert : employee.getCertificates()){
            int certId = cert.getId();
            Certificate.deleteById(certId);
        }
        Employee.deleteById(employee.getId());
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
