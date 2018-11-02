package com.casestudy.rms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "policy_match")
public class PolicyMatch {

    @Id
    @Column(name = "request_id")
    private String requestId;

    private int turnover;

    private int shares;

    private int networth;

    @Column(name = "companysize")
    private int companySize;

    @Column(name = "itr")
    private int incomeTaxReturn;

    @Column(name = "min_satisfy")
    private int minSatisfy;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getTurnover() {
        return turnover;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getNetworth() {
        return networth;
    }

    public void setNetworth(int networth) {
        this.networth = networth;
    }

    public int getCompanySize() {
        return companySize;
    }

    public void setCompanySize(int companySize) {
        this.companySize = companySize;
    }

    public int getIncomeTaxReturn() {
        return incomeTaxReturn;
    }

    public void setIncomeTaxReturn(int incomeTaxReturn) {
        this.incomeTaxReturn = incomeTaxReturn;
    }

    public int getMinSatisfy() {
        return minSatisfy;
    }

    public void setMinSatisfy(int minSatisfy) {
        this.minSatisfy = minSatisfy;
    }

    @Override
    public String toString() {
        return "PolicyMatch [requestId=" + requestId + ", turnover=" + turnover + ", shares=" + shares + ", networth=" + networth + ", companySize="
                + companySize + ", incomeTaxReturn=" + incomeTaxReturn + ", minSatisfy=" + minSatisfy + "]";
    }

}
