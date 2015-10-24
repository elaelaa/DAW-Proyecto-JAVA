/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
                type = types[i];
                degree = degrees[i]; 
                university = universities[i];
                if (!degree.isEmpty() && !university.isEmpty())
                {
                    //create new degree and append to list
                }
                else
                {
                //validation that every degree has all the values
                }
            }
        }
        
        
        String[] previousJobs = request.getParameterValues("jobTitle");
        String[] previousCompanies = request.getParameterValues("company"); 
        String[] jobDurations = request.getParameterValues("duration");
        
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
                job = previousJobs[i];
                company = previousCompanies[i]; 
                duration = jobDurations[i];
                if (!company.isEmpty() && !duration.isEmpty())
                {
                    //create new job and append to list
                }
                else
                {
                //validation that every job has all the values
                }
            }
        }
        
        String economicExpect = request.getParameter("expectation");
        
        //Some validation? 
        
        //send to database
        
        //Display message of success? 
        
        //processRequest(request, response);
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
        processRequest(request, response);
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
