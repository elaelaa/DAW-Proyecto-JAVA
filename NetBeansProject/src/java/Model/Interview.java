/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Database;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beto
 */
public class Interview {
    
    // Attributes
    private int id = -1;
    private int employeeId = -1;
    private int candidateId = -1;
    private String jobTitle; 
    private String feedback;
    private String platform;
    private Date date;

    
    public Interview(int eId, int cId, String jobTitle, String feedback, String platform, 
            Date date) {
        this.employeeId = eId;
        this.candidateId = cId;
        this.jobTitle = jobTitle; 
        this.feedback = feedback;
        this.platform = platform;
        this.date = date;
    }
    
    /**
     * save
     * 
     * Handles both INSERT and UPDATE actions to the Database
     * @return true if the insert / update was successful, false otherwise
     */
    public boolean save(){
        
        String query;
        Boolean exists = this.existsInDB(); 
        if (!exists) {
            query = "INSERT INTO Interview (employeeId, candidateId, jobTitle, interviewDate, feedback, platform)" +
                    "VALUES (%d, %d, '%s', '%s', '%s', '%s')";   
        } else {
            query = "UPDATE Interview SET employeeId=%d, candidateId=%d, jobTitle='%s', " + 
                    "interviewDate='%s', feedback='%s', platform='%s'" +
                    "WHERE id = " + Integer.toString(this.id);
        }

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date2 = df.format(this.date);
            Database.update(query, this.employeeId, this.candidateId, this.jobTitle, 
                date2, this.feedback, this.platform);
            ResultSet rs = Database.query("SELECT id FROM Interview ORDER BY id DESC LIMIT 1");
            this.setId(!rs.next() ? -1 : rs.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true; 
    }
    
    /**
     * Update
     * Updates all the fields
     */
    public void Update(int eId, int cId, String jobTitle, String feedback, String platform, 
            Date date) {
        this.candidateId = cId;
        this.employeeId = eId; 
        this.jobTitle = jobTitle;
        this.feedback = feedback;
        this.platform = platform; 
        this.date = date; 
    }
    
    /**
     * getById
     * 
     * Method to retrieve a Employee from the database by its ID.
     * @param id
     * @return a Employee object if found, null if not present in DB.
     */
    public static Interview getById(int id){
        Interview interview = null;
        
        try {
            String query = "SELECT * FROM Interview WHERE id = %d";
            ResultSet rs = Database.query(query, id);
            if (rs.next()){
                interview = new Interview(
                        rs.getInt("employeeId"),
                        rs.getInt("candidateId"),
                        rs.getString("jobTitle"),
                        rs.getString("feedback"),
                        rs.getString("platform"),
                        rs.getDate("interviewDate")
                );
                interview.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return interview;
    }
    
    /**
     * getAll
     * 
     * Method to retrieve all the Interview from the database.
     * @return a list of type List<Interview>, empty list if there are none in DB.
     */
    public static List<Interview> getAll(){
        List<Interview> interviews = new ArrayList<>();
   
        try {
            String query = "SELECT * FROM Interview";
            ResultSet rs = Database.query(query);
            while(rs.next()) {
                Interview interview = new Interview(
                        rs.getInt("employeeId"),
                        rs.getInt("candidateId"),
                        rs.getString("jobTitle"),
                        rs.getString("feedback"),
                        rs.getString("platform"),
                        rs.getDate("interviewDate")
                );
                interview.setId(rs.getInt("id"));
                interviews.add(interview);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return interviews;
    }

    /**
     * exists
     * 
     * Method to check if the object exists in the Database.
     * @return true if the employee exists in database, false otherwise.
     */
    public boolean existsInDB(){
        return (getById(this.id) != null);
    }
    
    /**
     * deleteById
     * 
     * Method to delete a Interview record from the database by its ID
     * @return true if the fields are filled correctly
     */
    public static boolean deleteById(int id){
        int res = 0;
        try {
            String query = "DELETE FROM Interview WHERE id = %d";
            res = Database.update(query, id);
        } catch (SQLException ex) {
            Logger.getLogger(Certificate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res == 1;
    }
    
    /**
     * @return the Candidates name 
     */
    public String getCandidateName() {
        Candidate candidate = Candidate.getById(this.candidateId);
        return candidate.getFullName();
    }
    
    /**
     * @return the Interviewers name 
     */
    public String getInterviewerName() {
        Employee employee = Employee.getById(this.employeeId);
        return employee.getFullName();
    }
    
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @return the candidateId
     */
    public int getCandidateId() {
        return candidateId;
    }

    /**
     * @return the feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the job title
     */
    public String getJobTitle() {
        return jobTitle;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    
    
    
}
