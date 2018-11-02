package com.casestudy.rms.dao;

import java.util.List;

import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.LenderAnalyst;

/** Interface for FADaoImpl class.
 * 
 * @author anand.tiwari */
public interface IFADao {

    /** Method to get all financial analyst correspond to provided lender ID.
     * 
     * @param lenderId
     *            lender's ID for which all registered analyst to find.
     * @return list of all object containing analyst's details. 
     * @throws DAOException */
    List<LenderAnalyst> getAnalystsIdByLenderId(String lenderId);

    /** Method will find the lender corresponds to provided analyst ID.
     * 
     * @param analystId
     *            analyst's ID for which correspond lender to find.
     * @return object containing details of lender. 
     * @throws DAOException */
    LenderAnalyst getLenderIdByAnalystId(String analystId) throws DAOException;

    /** Method to save financial analyst correspond to provided lender ID.
     * 
     * @param lenderAnalyst
     *            object containing details of lender and analyst ID's.
     * @return saved lenderAnalyst object. 
     * @throws DAOException */
    LenderAnalyst saveLAMapping(LenderAnalyst lenderAnalyst);

}