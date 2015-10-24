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
public class Interview {
    
    // Attributes
    private int id = -1;
    private int employeeId = -1;
    private int candidateId = -1;
    private String feedback;
    private String platform;
    private Date date;

    
    public Interview(int id, int eId, int cId, String feedback, String platform, 
            Date date) {
        this.id = id;
        this.employeeId = eId;
        this.candidateId = cId;
        this.feedback = feedback;
        this.platform = platform;
        this.date = date;
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
    
}
