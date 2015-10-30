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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beto
 */
public class PreviousJob {
    private int id = -1;
    private int personId = -1;
    private String jobTitle;
    private String company; 
    private String jobDescription;
    private double salary;
    private Date startDate;
    private Date endDate;

    public PreviousJob(int id, int personId, String jobTitle, String company, 
            String jobDescription, double salary, Date startDate, Date endDate) {
        this.id = id;
        this.personId = personId;
        this.jobTitle = jobTitle;
        this.company = company; 
        this.jobDescription = jobDescription;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PreviousJob(int personId, String jobTitle, String company, String jobDescription, 
        double salary, Date startDate, Date endDate) {
        this.personId = personId;
        this.jobTitle = jobTitle;
        this.company = company; 
        this.jobDescription = jobDescription;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void Update(String jobTitle, String company, String jobDescription, double salary, 
        Date startDate, Date endDate){
        this.jobTitle = jobTitle;
        this.company = company; 
        this.jobDescription = jobDescription;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    /**
     * save
     * 
     * Handles both INSERT and UPDATE actions to the Database
     * @return true if the insert / update was successful, false otherwise
     */
    public boolean save(){
        if (!this.isValid()){
            return false;
        }
        String query;
        if (!this.existsInDB()) {
            query = "INSERT INTO PreviousJob (personId, jobTitle, company, jobDescription, salary, startDate, endDate)" +
                    "VALUES (%d, '%s', '%s', '%s', %f, '%s', '%s')";   
        } else {
            query = "UPDATE PreviousJob SET personId=%d, jobTitle='%s', company='%s', jobDescription='%s', salary=%f, startDate='%s', endDate='%s'" +
                    "WHERE id = " + Integer.toString(this.id);
        }

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String startDate2 = df.format(this.startDate);
            String endDate2 = df.format(this.endDate);
            Database.update(query, this.personId, this.jobTitle, this.company, this.jobDescription, 
                this.salary, startDate2, endDate2);
            ResultSet rs = Database.query("SELECT id FROM PreviousJob ORDER BY id DESC LIMIT 1");
            this.setId(!rs.next() ? -1 : rs.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(PreviousJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    /**
     * getById
     * 
     * Method to retrieve a PreviousJob from the database by its ID.
     * @return a PreviousJob object if found, null if not present in DB.
     */
    public static PreviousJob getById(int id){
        PreviousJob previousJob = null;
        
        try {
            String query = "SELECT * FROM PreviousJob WHERE id = %d";
            ResultSet rs = Database.query(query, id);
            if (rs.next()){
                previousJob = new PreviousJob(
                        rs.getInt("personId"),
                        rs.getString("jobTitle"),
                        rs.getString("company"),
                        rs.getString("jobDescription"),
                        rs.getDouble("salary"),
                        rs.getDate("startDate"), 
                        rs.getDate("endDate")
                );
                previousJob.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PreviousJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return previousJob;
    }

    /**
     * deleteById
     * 
     * Method to delete a PreviousJob record from the database by its ID
     * @return true if the fields are filled correctly
     */
    public static boolean deleteById(int id){
        int res = 0;
        try {
            String query = "DELETE FROM PreviousJob WHERE id = %d";
            res = Database.update(query, id);
        } catch (SQLException ex) {
            Logger.getLogger(PreviousJob.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res == 1;
    }

    /**
     * isValid
     * 
     * @return true if the fields are filled correctly
     */
    public boolean isValid(){ // TODO: Careful with these
        if (this.personId < 0){ return false; }
        Field[] attrs = getClass().getDeclaredFields();
        for (Field attr : attrs){
            try {
                if (attr.get(this) == null || attr.get(this).equals("")){
                    return false;
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(PreviousJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    /**
     * existsInDB
     * 
     * Method to check if the object exists in the Database.
     * @return true if the candidate exists in database, false otherwise.
     */
    public boolean existsInDB(){
        return (getById(this.id) != null);
    }


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the personId
     */
    public int getPersonId() {
        return personId;
    }

    /**
     * @param personId the personId to set
     */
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    /**
     * @return the jobTitle
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @param jobTitle the jobTitle to set
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the jobDescription
     */
    public String getJobDescription() {
        return jobDescription;
    }

    /**
     * @param jobDescription the jobDescription to set
     */
    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
}
