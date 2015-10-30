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
public class Candidate extends Person {
    private int id = -1;
    private double expectation;

    public Candidate(double expectation, String name, String lastName, 
            String address, String phone, String email, String professionalTitle,
            Date dateOfBirth) {
        super(name, lastName, address, phone, email, professionalTitle, dateOfBirth);
        this.expectation = expectation;
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
            query = "INSERT INTO Candidate (firstName, lastName, email, address, phone, dateOfBirth, expectation)" +
                    "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')";   
        } else {
            query = "UPDATE Candidate SET firstName='%s', lastName='%s', email='%s, address='%s', phone='%s', dateOfBirth='%s', expectation='%s'" +
                    "WHERE id = " + Integer.toString(this.id);
        }

        try {
            Database.update(query, this.firstName, this.lastName, this.email, 
                this.address, this.phone, this.dateOfBirth, this.expectation);
            ResultSet rs = Database.query("SELECT id FROM Candidate ORDER BY id DESC LIMIT 1");
            this.setId(!rs.next() ? -1 : rs.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    
    public void Update(double newExpectation, String newName, String newLastname, 
            String newAddress, String newPhone, String newEmail, String newProfessionalTitle,
            Date newDateOfBirth)
    {
        this.setExpectation(newExpectation);
        this.setFirstName(newName);
        this.setLastName(newLastname); 
        this.setAddress(newAddress);
        this.setPhone(newPhone);
        this.setEmail(newEmail);
        this.setProfessionalTitle(newProfessionalTitle);
        this.setDateOfBirth(newDateOfBirth);
    }
    
    
    /**
     * getCertificates
     * 
     * Retrieves all the certificates for a given candidate
     * @return a list of type List<Certificate>, empty if none present in DB
     */
    public List<Certificate> getCertificates(){
        List<Certificate> certificates = new ArrayList<>();
        
        try {
            String query = "SELECT T.id, T.person_id, T.type, T.name, T.organization, T.dateAquired " +
                           "FROM Certificate AS T, Candidate As C " +
                           "WHERE %d = T.person_id";
            ResultSet rs = Database.query(query, this.getId());
            while (rs.next()){
                Certificate certificate = new Certificate(
                        rs.getInt("id"),
                        rs.getInt("person_id"),
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
     * Retrieves all the previous jobs for a given candidate
     * @return a list of type List<PreviousJob>, empty if none present in DB
     */
    public List<PreviousJob> getPreviousJobs(){
        List<PreviousJob> previousJobs = new ArrayList<>();
        
        try {
            String query = "SELECT P.id, P.person_id, P.jobTitle, P.jobDescription, P.salary, P.startDate, P.endDate " +
                           "FROM Candidate AS C, PreviousJob AS P" +
                           "WHERE P.person_id = %d";
            ResultSet rs = Database.query(query, this.getId());
            while (rs.next()){
                PreviousJob pj = new PreviousJob(
                        rs.getInt("id"),
                        rs.getInt("person_id"),
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
     * @return a Candidate object if found, null if not present in DB.
     */
    public static Candidate getById(int id){
        Candidate candidate = null;
        
        try {
            String query = "SELECT * FROM Candidate WHERE id = %d";
            ResultSet rs = Database.query(query, id);
            if (rs.next()){
                candidate = new Candidate(
                        rs.getDouble("expectation"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("address"), 
                        rs.getString("phone"), 
                        rs.getString("email"),
                        rs.getString("professionalTitle"),
                        rs.getDate("dateOfBirth")
                );
                candidate.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return candidate;
    }
    
    /**
     * getAll
     * 
     * Method to retrieve all the Candidates from the database.
     * @return a list of type List<Candidate>, empty list if there are none in DB.
     */
    public static List<Candidate> getAll(){
        List<Candidate> candidates = new ArrayList<>();
   
        try {
            String query = "SELECT id, firstName, lastName, email, address, phone, professionalTitle, dateOfBirth, expectation " + 
                           "FROM Candidate";
            ResultSet rs = Database.query(query);
            while(rs.next()) {
                Candidate candidate = new Candidate(
                        rs.getDouble("expectation"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("address"), 
                        rs.getString("phone"), 
                        rs.getString("email"),
                        rs.getString("professionalTitle"),
                        rs.getDate("dateOfBirth")
                );
                candidate.setId(rs.getInt("id"));
                candidates.add(candidate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return candidates;
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
    
    /* -------- GETTERS AND SETTERS ----------------------------------------- */

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
     * @return the expectation
     */
    public double getExpectation() {
        return expectation;
    }

    /**
     * @param expectation the expectation to set
     */
    public void setExpectation(double expectation) {
        this.expectation = expectation;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
}
