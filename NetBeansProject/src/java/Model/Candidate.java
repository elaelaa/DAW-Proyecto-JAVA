/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Database;
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

    public Candidate(double expectation, String name, String address, 
            String phone, String email, Date dateOfBirth) {
        super(name, address, phone, email, dateOfBirth);
        this.expectation = expectation;
    }
    
    public List<Certificate> getCertificates(){
        List<Certificate> certificates = new ArrayList<Certificate>();
        
        try {
            String query = "SELECT T.id, T.person_id, T.type, T.name, T.organization, T.dateAquired " +
                           "FROM Certificate AS T, Candidate As C " +
                           "WHERE %d = T.person_id";
            ResultSet rs = Database.query(query, this.id);
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
    
    public static Candidate getById(int id){
        Candidate candidate = null;
        
        try {
            String query = "SELECT * FROM Candidate WHERE id = %d";
            ResultSet rs = Database.query(query, id);
            if (rs.next()){
                candidate = new Candidate(
                        rs.getDouble("expectation"),
                        rs.getString("name"),
                        rs.getString("address"), 
                        rs.getString("phone"), 
                        rs.getString("email"), 
                        rs.getDate("dateOfBirth")
                );
                candidate.id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return candidate;
    }
    
    public static List<Candidate> getAll(){
        List<Candidate> candidates = new ArrayList<Candidate>();
   
        try {
            String query = "SELECT id, name, email, address, phone, dateOfBirth, expectation " + 
                           "FROM Candidate";
            ResultSet rs = Database.query(query);
            while(rs.next()) {
                Candidate candidate = new Candidate(
                        rs.getDouble("expectation"),
                        rs.getString("name"),
                        rs.getString("address"), 
                        rs.getString("phone"), 
                        rs.getString("email"), 
                        rs.getDate("dateOfBirth")
                );
                candidate.id = rs.getInt("id");
                candidates.add(candidate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return candidates;
    }
    
}
