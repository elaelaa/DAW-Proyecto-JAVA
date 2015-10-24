/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Candidate;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
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
        RequestDispatcher dispatcher = null;
        
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
        // TODO: implement the create an update methods
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
