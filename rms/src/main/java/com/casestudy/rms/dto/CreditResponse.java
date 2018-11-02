package com.casestudy.rms.dto;

import java.io.Serializable;
import java.util.Date;

import com.casestudy.rms.model.BPolicyValue;
import com.casestudy.rms.model.User;

/** Response object for Credit Requests.
 * 
 * @author Anand Tiwari */
public class CreditResponse implements Serializable {

    /** Serial Version ID. */
    private static final long serialVersionUID = 1L;

    private String requestId;

    private User lender;

    private User borrower;

    private User analyst;

    private String loc;

    private Date requestDate;

    private Date responseDate;

    private String amount;

    private String status;

    private BPolicyValue bPolicyValue;

    /** Getter for reuestId.
     * 
     * @return unique ID of the request. */
    public String getRequestId() {
        return requestId;
    }

    /** Setter for the requestId.
     * 
     * @param requestId
     *            unique ID of the request. */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /** Getter for lenderId.
     * 
     * @return lender user object. */
    public User getLender() {
        return lender;
    }

    /** Setter for the lenderId.
     * 
     * @param lender
     *            lender user object. */
    public void setLender(User lender) {
        this.lender = lender;
    }

    /** Getter for borrowerId.
     * 
     * @return borrower user object. */
    public User getBorrower() {
        return borrower;
    }

    /** Setter for the borrowerId.
     * 
     * @param borrower
     *            borrower user object. */
    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    /** Getter for analystId.
     * 
     * @return analyst user object. */
    public User getAnalyst() {
        return analyst;
    }

    /** Setter for the analystId.
     * 
     * @param analyst
     *            analyst user object. */
    public void setAnalystId(User analyst) {
        this.analyst = analyst;
    }

    /** Getter for length of credit.
     * 
     * @return length of credit. */
    public String getLoc() {
        return loc;
    }

    /** Setter for the length of credit.
     * 
     * @param loc
     *            length of credit. */
    public void setLoc(String loc) {
        this.loc = loc;
    }

    /** Getter for requestDate.
     * 
     * @return request date of the credit request. */
    public Date getRequestDate() {
        return requestDate;
    }

    /** Setter for the requestDate.
     * 
     * @param requestDate
     *            request date of the credit request. */
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    /** Getter for responseDate.
     * 
     * @return response date of the request. */
    public Date getResponseDate() {
        return responseDate;
    }

    /** Setter for the responseDate.
     * 
     * @param responseDate
     *            response date of the request. */
    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    /** Getter for amount.
     * 
     * @return amount of the credit. */
    public String getAmount() {
        return amount;
    }

    /** Setter for the amount.
     * 
     * @param amount
     *            amount of the credit. */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /** Getter for status.
     * 
     * @return status of the request. */
    public String getStatus() {
        return status;
    }

    /** Setter for the status.
     * 
     * @param status
     *            status of the request. */
    public void setStatus(String status) {
        this.status = status;
    }

    /** Getter for Policy Value.
     * 
     * @return bPolicyValue object. */
    public BPolicyValue getbPolicyValue() {
        return bPolicyValue;
    }

    /** Setter for policy Value.
     * 
     * @param bPolicyValue
     *            Policy value object. */
    public void setbPolicyValue(BPolicyValue bPolicyValue) {
        this.bPolicyValue = bPolicyValue;
    }

    @Override
    public String toString() {
        return "Credit [requestId=" + requestId + ", lender=" + lender + ", borrower=" + borrower + ", analystId=" + analyst + ", loc=" + loc
                + ", requestDate=" + requestDate + ", responseDate=" + responseDate + ", amount=" + amount + ", status=" + status + ", bPolicyValue="
                + bPolicyValue + "]";
    }

}
