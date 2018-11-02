package com.casestudy.rms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** User Entity corresponds to user table in database.
 * 
 * @author Anand Tiwari */
@Entity
@Table(name = "user")
public class User implements Serializable {

    /** Serial Version UID. */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private String userId;

    private String email;

    private String name;

    private String role;

    private String password;

    private String contactno;

    private String address;

    @Column(name = "lastlogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    private int enabled;

    /** Setter for userId.
     * 
     * @param userId
     *            unique ID of the user. */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /** Getter for userId.
     * 
     * @return unique ID of the user. */
    public String getUserId() {
        return userId;
    }

    /** Getter for name.
     * 
     * @return name of the user. */
    public String getName() {
        return name;
    }

    /** Setter for name.
     * 
     * @param name
     *            name of the user. */
    public void setName(String name) {
        this.name = name;
    }

    /** Getter for email.
     * 
     * @return email ID the user. */
    public String getEmail() {
        return email;
    }

    /** Setter for email.
     * 
     * @param email
     *            email ID of the user. */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Getter for password.
     * 
     * @return password the user. */
    public String getPassword() {
        return password;
    }

    /** Setter for password.
     * 
     * @param password
     *            password the user. */
    public void setPassword(String password) {
        this.password = password;
    }

    /** Getter for contactno.
     * 
     * @return contactno of the user. */
    public String getContactno() {
        return contactno;
    }

    /** Setter for contactno.
     * 
     * @param contactno
     *            contactno of the user. */
    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    /** Getter for address.
     * 
     * @return address of the user. */
    public String getAddress() {
        return address;
    }

    /** Setter for address.
     * 
     * @param address
     *            address of the user. */
    public void setAddress(String address) {
        this.address = address;
    }

    /** Getter for lastLogin.
     * 
     * @return lastLogin of the user. */
    public Date getLastLogin() {
        return lastLogin;
    }

    /** Setter for lastLogin.
     * 
     * @param lastLogin
     *            lastLogin of the user. */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /** Getter for role.
     * 
     * @return role of the user. */
    public String getRole() {
        return role;
    }

    /** Setter for role.
     * 
     * @param role
     *            role of the user. */
    public void setRole(String role) {
        this.role = role;
    }

    /** Getter for enabled.
     * 
     * @return integer enabled value. */
    public int getEnabled() {
        return enabled;
    }

    /** Setter for enabled.
     * 
     * @param enabled
     *            integer enabled value. */
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role + ", contactno="
                + contactno + ", address=" + address + ", lastLogin=" + lastLogin + "]";
    }

}
