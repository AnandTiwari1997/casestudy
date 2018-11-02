package com.casestudy.rms.service;

import org.springframework.security.access.annotation.Secured;

import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.BPolicyValue;

/** Interface for BPolicyValueServiceImpl class.
 * 
 * @author Anand Tiwari */
public interface IBPolicyValueService {

    /** Method will save the information provided by the borrower against each policy.
     * 
     * @param bPolicyValue
     *            bPolicyValue object containing information provided by borrower.
     * @return saved bPolicyValue object. */
    @Secured({ "ROLE_BORROWER" })
    BPolicyValue addPolicyValues(BPolicyValue bPolicyValue);

    /** Method will update the information provided by the borrower against each policy.
     * 
     * @param bPolicyValue
     *            bPolicyValue object containing updated information provided by borrower.
     * @return updated bPolicyValue object. 
     * @throws DAOException */
    @Secured({ "ROLE_BORROWER" })
    BPolicyValue updatePolicyValues(BPolicyValue bPolicyValue) throws DAOException;

    /** Method will find the policies information according to provided borrower ID.
     * 
     * @param borrowerId
     *            borrower's ID whose policy value are needed.
     * @return bPolicyValue object. 
     * @throws DAOException */
    @Secured({ "ROLE_BORROWER", "ROLE_LENDER", "ROLE_ANALYST" })
    BPolicyValue getPolicyByBorrowerId(String borrowerId);

}