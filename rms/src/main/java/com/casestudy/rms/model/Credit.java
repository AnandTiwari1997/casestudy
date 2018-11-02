package com.casestudy.rms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/** Credit Entity correspond to cred_request table in database.
 * 
 * @author Anand Tiwari */
@Entity
@Table(name = "cred_request")
public class Credit implements Serializable {

    /**
     * Serial Version ID.
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "request_id")
    private String requestId;

    @Column(name = "lender_id")
    private String lenderId;

    @Column(name = "borrower_id")
    private String borrowerId;

    @Column(name = "fa_id")
    private String analystId;

    @Column(name = "cred_length")
    private String loc;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "req_date")
    private Date requestDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "res_date")
    private Date responseDate;

    @Column(name = "loan_amount")
    private String amount;

    private String status;

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
     * @return unique ID of the lender. */
    public String getLenderId() {
        return lenderId;
    }

    /** Setter for the lenderId.
     * 
     * @param lenderId
     *            unique ID of the lender. */
    public void setLenderId(String lenderId) {
        this.lenderId = lenderId;
    }

    /** Getter for borrowerId.
     * 
     * @return unique ID of the borrower. */
    public String getBorrowerId() {
        return borrowerId;
    }

    /** Setter for the borrowerId.
     * 
     * @param borrowerId
     *            unique ID of the borrower. */
    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    /** Getter for analystId.
     * 
     * @return unique ID of the analyst. */
    public String getAnalystId() {
        return analystId;
    }

    /** Setter for the analystId.
     * 
     * @param analystId
     *            unique ID of the analyst. */
    public void setAnalystId(String analystId) {
        this.analystId = analystId;
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

    @Override
    public String toString() {
        return "Credit [requestId=" + requestId + ", lenderId=" + lenderId + ", borrowerId=" + borrowerId + ", analystId=" + analystId + ", loc="
                + loc + ", requestDate=" + requestDate + ", responseDate=" + responseDate + ", amount=" + amount + ", status=" + status + "]";
    }

}
