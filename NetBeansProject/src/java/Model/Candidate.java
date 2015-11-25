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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        Boolean exists = this.existsInDB(); 
        if (!exists) {
            query = "INSERT INTO Person (firstName, lastName, email, address, phone, professionalTitle, dateOfBirth)" +
                    "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')";   
        } else {
            query = "UPDATE Person SET firstName='%s', lastName='%s', email='%s, " + 
                    "address='%s', phone='%s', professionalTitle='%s', dateOfBirth='%s'" +
                    "WHERE id = " + Integer.toString(this.id);
        }

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateOfBirth2 = df.format(this.dateOfBirth);
            Database.update(query, this.firstName, this.lastName, this.email, 
                this.address, this.phone, this.professionalTitle, 
                dateOfBirth2);
            ResultSet rs = Database.query("SELECT id FROM Person ORDER BY id DESC LIMIT 1");
            this.setId(!rs.next() ? -1 : rs.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!exists) {
            query = "INSERT INTO Candidate (id, expectation)" +
                    "VALUES (%d, %f)";  
         } else {
            query = "UPDATE Candidate SET id =%d, expectation=%f" +
                    "WHERE id = " + Integer.toString(this.id);
        }
        try {
            Database.update(query, this.getId(), this.expectation);
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    /**
     * Update
     * Updates all the fields
     */
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
     * getInterviews
     * 
     * Retrieves all the interviews for a given candidate
     * @return a list of type List<Interview>, empty if none present in DB
     */
    public List<Interview> getInterviews(){
        List<Interview> interviews = new ArrayList<>();
         try {
            String query = "SELECT * FROM Interview WHERE candidateId = " + Integer.toString(this.id);
            ResultSet rs = Database.query(query, this.getId());
            while (rs.next()){
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
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return interviews; 
        
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
     * Retrieves all the previous jobs for a given candidate
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
    public static Candidate getById(int id){
        Candidate candidate = null;
        
        try {
            String query = "SELECT * FROM Candidate c, Person p WHERE p.id = %d AND p.id = c.id";
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
            String query = "SELECT * FROM Candidate c, Person p WHERE p.id=c.id";
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
    
    /**
     * deleteById
     * 
     * Method to delete a Candidate record from the database by its ID
     * @return true if the fields are filled correctly
     */
    public static boolean deleteById(int id){
        int res = 0;
        try {
            String query = "DELETE FROM Candidate WHERE id = %d; DELETE FROM Person WHERE id=%d";
            res = Database.update(query, id);
        } catch (SQLException ex) {
            Logger.getLogger(Certificate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res == 1;
    }

    /**
     * hire
     * 
     * Hires the bitch.
     * @param candidate
     * @param jobTitle
     * @param salary
     * @param startDate
     * @param vacationDays
     * @return The newly hired Employee object
     */
    public Employee hire(String jobTitle, 
        double salary, Date startDate, int vacationDays){
        
        // first we delete the candidate, CAREFUL: might fail if id is invalid
        Candidate.deleteById(this.id);
        int personId = this.id;
        
        // create employee (w/o person attrs) object and save it
        String query = "INSERT INTO Employee (id, jobTitle, startDate, salary, vacationDays)" +
                       "VALUES (%d, '%s', '%s', %f, %d)";
        try {
            Database.update(query, personId, jobTitle, startDate, salary, vacationDays);
        } catch (SQLException ex) {
            Logger.getLogger(Certificate.class.getName()).log(Level.SEVERE, null, ex);
        }
        Employee newHire = Employee.getById(personId);
        return newHire;
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
