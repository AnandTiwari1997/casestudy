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

/** BPolicyValue Entity correspond to borrower_cred table in database.
 * 
 * @author Anand Tiwari */
@Entity
@Table(name = "borrower_cred")
@JsonIgnoreProperties(value = { "lastmodified" }, allowGetters = true)
public class BPolicyValue implements Serializable {

    /** Serial Version ID. */
    private static final long serialVersionUID = 1L;

    /** The borrower id. */
    @Id
    @Column(name = "borrower_id")
    private String borrowerId;

    /** The turnover. */
    private String turnover;

    /** The networth. */
    private String networth;

    /** The income tax ret. */
    @Column(name = "itr")
    private String incomeTaxRet;

    /** The shares. */
    private String shares;
    
    /** The companysize. */
    private String companysize;

    /** The lastmodified. */
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;

    /** Getter for borrowerId.
     * 
     * @return unique ID of the borrower. */
    public String getBorrowerId() {
        return borrowerId;
    }

    /** Setter for borrowerId.
     * 
     * @param borrowerId
     *            unique ID of the borrower. */
    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    /** Getter for turnover.
     * 
     * @return turnover of the borrower. */
    public String getTurnover() {
        return turnover;
    }

    /** Setter for turnover.
     * 
     * @param turnover
     *            turnover of the borrower. */
    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    /** Getter for net worth.
     * 
     * @return net worth of the borrower. */
    public String getNetworth() {
        return networth;
    }

    /** Setter for net worth.
     * 
     * @param networth
     *            net worth of the borrower. */
    public void setNetworth(String networth) {
        this.networth = networth;
    }

    /** Setter for income Tax Return.
     * 
     * @param incomeTaxRet
     *            income Tax Return of the borrower. */
    public void setIncomeTaxRet(String incomeTaxRet) {
        this.incomeTaxRet = incomeTaxRet;
    }

    /** Getter for income Tax Return.
     * 
     * @return income Tax Return of the borrower. */
    public String getIncomeTaxRet() {
        return incomeTaxRet;
    }

    /** Getter for shares.
     * 
     * @return shares of the borrower. */
    public String getShares() {
        return shares;
    }

    /** Setter for shares.
     * 
     * @param shares
     *            shares of the borrower. */
    public void setShares(String shares) {
        this.shares = shares;
    }

    
    /**
     * Gets the companysize.
     *
     * @return the companysize
     */
    public String getCompanysize() {
        return companysize;
    }

    /**
     * Sets the companysize.
     *
     * @param companysize the new companysize
     */
    public void setCompanysize(String companysize) {
        this.companysize = companysize;
    }

    /** Getter for last modified time.
     * 
     * @return last modified time. */
    public Date getLastmodified() {
        return lastmodified;
    }

    /** Setter for last modified time.
     * 
     * @param lastmodified
     *            last modified time. */
    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BPolicyValue [borrowerId=" + borrowerId + ", turnover=" + turnover + ", networth=" + networth + ", income_tax_ret=" + incomeTaxRet
                + ", shares=" + shares + ", companySize=" + companysize + ", lastmodified=" + lastmodified + "]";
    }

}
