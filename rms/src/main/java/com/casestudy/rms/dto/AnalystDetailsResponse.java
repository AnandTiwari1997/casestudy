package com.casestudy.rms.dto;

import java.io.Serializable;

/** Data Transfer Object class for sending response for analyst details.
 * 
 * @author Anand Tiwari */
public class AnalystDetailsResponse implements Serializable {

    /** Default */
    private static final long serialVersionUID = 1L;

    private String userId;

    private String email;

    private String name;

    private String role;

    private String password;

    private String contactno;

    private String address;

    private int noOfPendingRequest;

    private int noOfApprovedRequest;

    private int noOfRejectedRequest;

    private int noOfInProgressRequest;

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

    /** Getter for noOfPendingRequest.
     * 
     * @return number of pending request. */
    public int getNoOfPendingRequest() {
        return noOfPendingRequest;
    }

    /** Setter for noOfPendingRequest.
     * 
     * @param noOfPendingRequest
     *            number of pending request. */
    public void setNoOfPendingRequest(int noOfPendingRequest) {
        this.noOfPendingRequest = noOfPendingRequest;
    }

    /**
     * Getter for noOfApprovedRequest.
     * @return integer number of approved request.
     */
    public int getNoOfApprovedRequest() {
        return noOfApprovedRequest;
    }

    /**
     * Setter for noOfApprovedRequest.
     * @param noOfApprovedRequest number of approved request.
     */
    public void setNoOfApprovedRequest(int noOfApprovedRequest) {
        this.noOfApprovedRequest = noOfApprovedRequest;
    }

    /**
     * Getter for noOfRejectedRequest.
     * @return integer number of rejected request.
     */
    public int getNoOfRejectedRequest() {
        return noOfRejectedRequest;
    }

    /**
     * Setter for noOfRejectedRequest.
     * @param noOfRejectedRequest number of rejected request.
     */
    public void setNoOfRejectedRequest(int noOfRejectedRequest) {
        this.noOfRejectedRequest = noOfRejectedRequest;
    }

    /**
     * Getter for noOfInProgressRequest.
     * @return integer number of in progress request.
     */
    public int getNoOfInProgressRequest() {
        return noOfInProgressRequest;
    }

    /**
     * Setter for noOfInProgressRequest.
     * @param noOfInProgressRequest number of in progress request.
     */
    public void setNoOfInProgressRequest(int noOfInProgressRequest) {
        this.noOfInProgressRequest = noOfInProgressRequest;
    }

}
