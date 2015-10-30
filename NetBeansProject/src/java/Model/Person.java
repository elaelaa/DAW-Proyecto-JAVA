/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beto
 */
public class Person {
    
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String phone;
    protected String email;
    protected String professionalTitle;
    protected Date dateOfBirth;

    public Person(String name, String lastName, String address, String phone, 
            String email, String professionalTitle, Date dateOfBirth) {
        this.firstName = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.professionalTitle = professionalTitle;
        this.dateOfBirth = dateOfBirth;
    }

    // TODO: careful with this...
    public boolean isValid(){
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
     * @return the name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    public String getFullName(){
        return firstName + " " + getLastName();
    }

    public static String dateToSQL(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the professionalTitle
     */
    public String getProfessionalTitle() {
        return professionalTitle;
    }
}
