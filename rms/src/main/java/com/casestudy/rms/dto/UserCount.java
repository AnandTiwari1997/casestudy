package com.casestudy.rms.dto;

import java.io.Serializable;

public class UserCount implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int banks;

    private int borrowers;

    private int analysts;

    public int getBanks() {
        return banks;
    }

    public void setBanks(int banks) {
        this.banks = banks;
    }

    public int getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(int borrowers) {
        this.borrowers = borrowers;
    }

    public int getAnalysts() {
        return analysts;
    }

    public void setAnalysts(int analysts) {
        this.analysts = analysts;
    }

    @Override
    public String toString() {
        return "UserCount [banks=" + banks + ", borrowers=" + borrowers + ", analysts=" + analysts + "]";
    }
}
