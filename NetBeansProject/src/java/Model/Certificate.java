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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beto
 */
public class Certificate {
    private int id = -1;
    private int personId = -1;
    private String type;
    private String name;
    private String organization;
    private Date dateAquired;

    
    public Certificate(int id, int personId, String type, String name, String organization, Date dateAquired) {
        this.id = id;
        this.personId = personId;
        this.type = type;
        this.name = name;
        this.organization = organization;
        this.dateAquired = dateAquired;
    }

    public Certificate(String type, String name, String organization, Date dateAquired) {
        this.type = type;
        this.name = name;
        this.organization = organization;
        this.dateAquired = dateAquired;
    }
    
    public void Update(String type, String name, String organization, Date dateAquired){
        this.type = type;
        this.name = name;
        this.organization = organization;
        this.dateAquired = dateAquired;
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
            query = "INSERT INTO Certificate (personId, type, name, organization, dateAquired)" +
                    "VALUES (%d, '%s', '%s', '%s', '%s')";   
        } else {
            query = "UPDATE Certificate SET personId=%d, type='%s', name='%s', organization='%s', dateAquired='%s'" +
                    "WHERE id = " + Integer.toString(this.id);
        }

        try {
            Database.update(query, this.personId, this.type, this.name, 
                this.organization, this.dateAquired);
            ResultSet rs = Database.query("SELECT id FROM Certificate ORDER BY id DESC LIMIT 1");
            this.setId(!rs.next() ? -1 : rs.getInt(1));
        } catch (SQLException ex) {
            Logger.getLogger(Certificate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * isValid
     * 
     * @return true if the fields are filled
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
                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
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
     * getById
     * 
     * Method to retrieve a Certificate object from the Database.
     * @return null if the certificate_id doesn't exists in database, 
     * the Certificate object otherwise.
     */
    public static Certificate getById(int id){
        Certificate certificate = null;
        
        try {
            String query = "SELECT * FROM Certificate WHERE id = %d";
            ResultSet rs = Database.query(query, id);
            if (rs.next()){
                certificate = new Certificate(
                        id,
                        rs.getInt("personId"),
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getString("organization"), 
                        rs.getDate("dateAquired")
                );
                certificate.setId(rs.getInt("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Certificate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return certificate;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return the dateAquired
     */
    public Date getDateAquired() {
        return dateAquired;
    }

    /**
     * @param dateAquired the dateAquired to set
     */
    public void setDateAquired(Date dateAquired) {
        this.dateAquired = dateAquired;
    }
}
