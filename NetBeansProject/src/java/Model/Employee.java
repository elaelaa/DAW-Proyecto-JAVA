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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beto
 */
public class Employee extends Person {
    private int id = -1;
    private double salary;
    private String jobTitle;
    private int vacationDays = 0;
    private Date startDate;

    public Employee(double salary, String jobTitle, Date startDate, String name, String lastName, String address, 
            String phone, String email, String professionalTitle, Date dateOfBirth, int vacationDays) {
        super(name, lastName, address, phone, email, professionalTitle, dateOfBirth);
        this.salary = salary;
        this.jobTitle = jobTitle;
        this.startDate = startDate;
        this.vacationDays = vacationDays; 
    }

    /**
     * save
     * 
     * Handles both INSERT and UPDATE actions to the Database
     * @return true if the insert / update was successful, false otherwise
     */
    public boolean save(){
        //TODO: IMPLEMENT ME
        return false;
    }
    
    /**
     * Update
     * Updates all the fields
     */
    public void Update(double salary, String jobTitle, Date startDate, String name, String lastName, String address, 
            String phone, String email, String professionalTitle, Date dateOfBirth, int vacationDays) {
        this.setSalary(salary);
        this.firstName = name; 
        this.lastName = lastName;  
        this.address = address;
        this.phone = phone; 
        this.email = email; 
        this.setJobTitle(jobTitle);
        this.professionalTitle = professionalTitle;
        this.dateOfBirth = dateOfBirth; 
        this.setStartDate(startDate);
        this.setVacationDays(vacationDays);
    }
    
    /**
     * getCertificates
     * 
     * Retrieves all the certificates for a given employee
     * @return a list of type List<Certificate>, empty if none present in DB
     */
    public List<Certificate> getCertificates(){
        List<Certificate> certificates = new ArrayList<>();
        
        try {
            String query = "SELECT T.id, T.personId, T.type, T.name, T.organization, T.dateAquired " +
                           "FROM Certificate AS T " +
                           "WHERE T.personId = " + Integer.toString(this.id);
            ResultSet rs = Database.query(query, this.getId());
            while (rs.next()){
                Certificate certificate = new Certificate(
                        rs.getInt("id"),
                        rs.getInt("personId"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("organization"),
                        rs.getDate("dateAquired")
                );
                certificates.add(certificate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return certificates;
    }
    
    /**
     * getPreviousJobs
     * 
     * Retrieves all the previous jobs for a given employee
     * @return a list of type List<PreviousJob>, empty if none present in DB
     */
    public List<PreviousJob> getPreviousJobs(){
        List<PreviousJob> previousJobs = new ArrayList<>();
        
        try {
            String query = "SELECT P.id, P.personId, P.jobTitle, P.company, P.jobDescription, P.salary, P.startDate, P.endDate " +
                           "FROM PreviousJob AS P " +
                           "WHERE P.personId = " + Integer.toString(this.id);
            ResultSet rs = Database.query(query, this.getId());
            while (rs.next()){
                PreviousJob pj = new PreviousJob(
                        rs.getInt("id"),
                        rs.getInt("personId"),
                        rs.getString("company"),
                        rs.getString("jobTitle"),
                        rs.getString("jobDescription"),
                        rs.getDouble("salary"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate")
                );
                previousJobs.add(pj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return previousJobs;
    }
    
    /**
     * getById
     * 
     * Method to retrieve a Candidate from the database by its ID.
     * @param id
     * @return a Candidate object if found, null if not present in DB.
     */
    public static Employee getById(int id){
        Employee employee = null;
        
        try {
            String query = "SELECT * FROM Employee WHERE id = %d";
            ResultSet rs = Database.query(query, id);
            if (rs.next()){
                employee = new Employee(
                        rs.getDouble("salary"),
                        rs.getString("jobTitle"),
                        rs.getDate("startDate"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("address"), 
                        rs.getString("phone"), 
                        rs.getString("email"),
                        rs.getString("professionalTitle"),
                        rs.getDate("dateOfBirth"),
                        rs.getInt("vacationDays")
                );
                employee.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return employee;
    }
    
    /**
     * getAll
     * 
     * Method to retrieve all the Candidates from the database.
     * @return a list of type List<Employee>, empty list if there are none in DB.
     */
    public static List<Employee> getAll(){
        List<Employee> employees = new ArrayList<>();
   
        try {
            String query = "SELECT id, firstName, lastName, email, address, phone, professionalTitle, dateOfBirth, expectation " + 
                           "FROM Employee";
            ResultSet rs = Database.query(query);
            while(rs.next()) {
                Employee employee = new Employee(
                        rs.getDouble("salary"),
                        rs.getString("jobTitle"),
                        rs.getDate("startDate"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("address"), 
                        rs.getString("phone"), 
                        rs.getString("email"),
                        rs.getString("professionalTitle"),
                        rs.getDate("dateOfBirth"),
                        rs.getInt("vacationDays")
                );
                employee.setId(rs.getInt("id"));
                employees.add(employee);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return employees;
    }

    /**
     * exists
     * 
     * Method to check if the object exists in the Database.
     * @return true if the candidate exists in database, false otherwise.
     */
    public boolean existsInDB(){
        return (getById(this.id) != null);
    }
    
    /**
     * isValid
     * 
     * @return true if the fields are filled
     */
    @Override
    public boolean isValid(){ // TODO: Careful with these
        boolean x = super.isValid();
        Field[] attrs = getClass().getDeclaredFields();
        for (Field attr : attrs){
            try {
                if (attr.get(this) == null || attr.get(this).equals("")){
                    return false;
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return x;
    }
    
    /**
     * deleteById
     * 
     * Method to delete a Candidate record from the database by its ID
     * @return true if the fields are filled correctly
     */
    public static boolean deleteById(int id){
        int res = 0;
        try {
            String query = "DELETE FROM Employee WHERE id = %d";
            res = Database.update(query, id);
        } catch (SQLException ex) {
            Logger.getLogger(Certificate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res == 1;
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
     * @return the vacationDays
     */
    public int getVacationDays() {
        return vacationDays;
    }

    /**
     * @param vacationDays the vacationDays to set
     */
    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
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
}
