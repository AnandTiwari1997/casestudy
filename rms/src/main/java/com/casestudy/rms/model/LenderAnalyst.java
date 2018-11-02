package com.casestudy.rms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** LenderAnalyst Entity corresponding to len_fa table in database.
 * 
 * @author Anand Tiwari */
@Entity
@Table(name = "len_fa")
public class LenderAnalyst implements Serializable {

    /** Serial Version ID. */
    private static final long serialVersionUID = 1L;

    private String lenderId;

    @Id
    @Column(name = "fa_id")
    private String analystId;

    /** Getter for lenderId.
     * 
     * @return unique ID of the lender. */
    public String getLenderId() {
        return lenderId;
    }

    /** Setter for lenderId.
     * 
     * @param lenderId
     *            unique ID of the lender. */
    public void setLenderId(String lenderId) {
        this.lenderId = lenderId;
    }

    /** Getter for analystId.
     * 
     * @return unique ID of the analyst. */
    public String getAnalystId() {
        return analystId;
    }

    /** Setter for analystId.
     * 
     * @param analystId
     *            unique ID of the analyst. */
    public void setAnalystId(String analystId) {
        this.analystId = analystId;
    }

    @Override
    public String toString() {
        return "LenderAnalyst [lender_id=" + lenderId + ", analyst_id=" + analystId + "]";
    }
}
