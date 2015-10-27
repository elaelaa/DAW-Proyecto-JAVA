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
public class PreviousJob {
    private int id = -1;
    private int person_id;
    private String jobTitle;
    private String jobDescription;
    private double salary;
    private Date startDate;
    private Date endDate;

    public PreviousJob(int id, int person_id, String jobTitle, 
            String jobDescription, double salary, Date startDate, Date endDate) {
        this.id = id;
        this.person_id = person_id;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PreviousJob(String jobTitle, String jobDescription, double salary, Date startDate, Date endDate) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
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
     * @return the person_id
     */
    public int getPerson_id() {
        return person_id;
    }

    /**
     * @param person_id the person_id to set
     */
    public void setPerson_id(int person_id) {
        this.person_id = person_id;
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
