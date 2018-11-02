package com.casestudy.rms.dao;

import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.BPolicyValue;

/** Interface for BPolicyValueDaoImpl class.
 * 
 * @author anand.tiwari */
public interface IBPolicyValueDao {

    /** Method will save the information provided by the borrower against each policy.
     * 
     * @param bPolicyValue
     *            bPolicyValue object containing information provided by borrower.
     * @return saved bPolicyValue object. */
    BPolicyValue addPolicyValues(BPolicyValue bPolicyValue);

    /** Method will update the information provided by the borrower against each policy.
     * 
     * @param bPolicyValue
     *            bPolicyValue object containing updated information provided by borrower.
     * @return updated bPolicyValue object. 
     * @throws DAOException */
    BPolicyValue updatePolicyValues(BPolicyValue bPolicyValue) throws DAOException;

    /** Method will find the policies information according to provided borrower ID.
     * 
     * @param borrowerId
     *            borrower's ID whose policy value are needed.
     * @return bPolicyValue object. 
     * @throws DAOException */
    BPolicyValue getPolicyByBorrowerId(String borrowerId);

}