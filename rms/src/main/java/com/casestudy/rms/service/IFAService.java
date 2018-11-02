package com.casestudy.rms.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.casestudy.rms.dto.AnalystDetailsResponse;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.LenderAnalyst;
import com.casestudy.rms.model.User;

/** Interface for FAService class.
 * 
 * @author Anand Tiwari */
public interface IFAService {

    /** Method to get all financial analyst correspond to provided lender ID.
     * 
     * @param lenderId
     *            lender's ID for which all registered analyst to find.
     * @return list of all object containing analyst's details. 
     * @throws DAOException */
    @Secured({ "ROLE_LENDER" })
    List<AnalystDetailsResponse> getAnalystByLenderId(String lenderId);

    /** Method will find the lender corresponds to provided analyst ID.
     * 
     * @param analystId
     *            analyst's ID for which correspond lender to find.
     * @return object containing details of lender. 
     * @throws DAOException */
    @Secured({ "ROLE_ANALYST" })
    User getLenderByAnalystId(String analystId);

    /** Method to save financial analyst correspond to provided lender ID.
     * 
     * @param lenderAnalyst
     *            object containing details of lender and analyst ID's.
     * @return saved lenderAnalyst object. 
     * @throws DAOException */
    @Secured({ "ROLE_LENDER" })
    LenderAnalyst saveLAMapping(LenderAnalyst lenderAnalyst);

}