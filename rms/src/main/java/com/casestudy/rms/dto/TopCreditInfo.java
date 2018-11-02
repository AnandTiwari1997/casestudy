package com.casestudy.rms.dto;

import java.io.Serializable;
import java.util.List;

public class TopCreditInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    List<String> companyNames;
    
    List<Long> values;
    


    public List<String> getCompanyNames() {
        return companyNames;
    }

    public void setCompanyNames(List<String> companyNames) {
        this.companyNames = companyNames;
    }

    public List<Long> getValues() {
        return values;
    }

    public void setValues(List<Long> values) {
        this.values = values;
    }

    
    

    @Override
    public String toString() {
        return "TopCreditInfo [companyNames=" + companyNames + ", values=" + values +"]";
    }
    
}
