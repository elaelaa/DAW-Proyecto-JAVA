/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

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
