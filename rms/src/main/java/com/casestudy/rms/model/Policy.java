package com.casestudy.rms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/** Policy Entity correspond to lender_policy table in database.
 * 
 * @author Anand Tiwari */
@Entity
@Table(name = "lender_policy")
public class Policy implements Serializable {

    /** Serial Version ID. */
    private static final long serialVersionUID = 1L;

    /** The policy id. */
    @Id
    @Column(name = "policy_id")
    private String policyId;

    /** The turnover. */
    private String turnover;

    /** The networth. */
    private String networth;

    /** The income tax return. */
    @Column(name = "itr")
    private String incomeTaxReturn;

    /** The shares. */
    private String shares;

    /** The min satisfy. */
    @Column(name = "min_satisfy")
    private int minSatisfy;

    /** The lender id. */
    @Column(name = "lender_id")
    private String lenderId;

    /** The companysize. */
    private String companysize;
    
    private String interest;
    
    @Column(name = "amount_range")
    private String amountRange;
    
    @Column(name = "tenure_range")
    private String tenureRange;
    
    @Column(name = "processing_fee")
    private String processingFee;
    
    @Column(name = "lowest_emi")
    private String lowestEMI;
    
    @Column(name = "p_f_value")
    private String pfValue;

    /** The lastmodified. */
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastmodified;

    /** Getter for policyId.
     * 
     * @return unique ID of the policy. */
    public String getPolicyId() {
        return policyId;
    }

    /** Setter for policyId.
     * 
     * @param policyId
     *            unique ID of the policy. */
    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    /** Getter for turnover.
     * 
     * @return turnover provided by lender. */
    public String getTurnover() {
        return turnover;
    }

    /** Setter for turnover.
     * 
     * @param turnover
     *            turnover provided by lender. */
    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    /** Getter for net worth.
     * 
     * @return net worth provided by lender. */
    public String getNetworth() {
        return networth;
    }

    /** Setter for net worth.
     * 
     * @param networth
     *            net worth provided by lender. */
    public void setNetworth(String networth) {
        this.networth = networth;
    }

    /** Getter for income Tax Return.
     * 
     * @return income Tax Return provided by lender. */
    public String getIncomeTaxReturn() {
        return incomeTaxReturn;
    }

    /** Setter for income Tax Return.
     * 
     * @param incomeTaxReturn
     *            income Tax Return provided by lender. */
    public void setIncomeTaxReturn(String incomeTaxReturn) {
        this.incomeTaxReturn = incomeTaxReturn;
    }

    /** Getter for shares.
     * 
     * @return shares provided by lender. */
    public String getShares() {
        return shares;
    }

    /** Setter for shares.
     * 
     * @param shares
     *            shares provided by lender. */
    public void setShares(String shares) {
        this.shares = shares;
    }

    /** Getter for minSatisfy.
     * 
     * @return minimum number of policies to satisfy. */
    public int getMinSatisfy() {
        return minSatisfy;
    }

    /** Setter for minSatisfy.
     * 
     * @param minSatisfy
     *            minimum number of policies to satisfy. */
    public void setMinSatisfy(int minSatisfy) {
        this.minSatisfy = minSatisfy;
    }

    /** Gets the companysize.
     *
     * @return the companysize */
    public String getCompanysize() {
        return companysize;
    }

    /** Sets the companysize.
     *
     * @param companysize
     *            the new companysize */
    public void setCompanysize(String companysize) {
        this.companysize = companysize;
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
    
    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getAmountRange() {
        return amountRange;
    }

    public void setAmountRange(String amountRange) {
        this.amountRange = amountRange;
    }

    public String getTenureRange() {
        return tenureRange;
    }

    public void setTenureRange(String tenureRange) {
        this.tenureRange = tenureRange;
    }

    public String getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(String processingFee) {
        this.processingFee = processingFee;
    }

    public String getLowestEMI() {
        return lowestEMI;
    }

    public void setLowestEMI(String lowestEMI) {
        this.lowestEMI = lowestEMI;
    }

    public String getPfValue() {
        return pfValue;
    }

    public void setPfValue(String pfValue) {
        this.pfValue = pfValue;
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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Policy [policy_id=" + policyId + ", turnover=" + turnover + ", networth=" + networth + ", itr=" + incomeTaxReturn + ", shares="
                + shares + ", min_satisfy=" + minSatisfy + ", companySize=" + minSatisfy + ", lender_id=" + lenderId + ", interest="
                + interest + ", amountRange=" + amountRange + ", tenureRange=" + tenureRange + ", processingFee=" + processingFee + ", lowestEMI="
                + lowestEMI + ", pfValue=" + pfValue + ", lastmodified=" + lastmodified + "]";
    }
}
