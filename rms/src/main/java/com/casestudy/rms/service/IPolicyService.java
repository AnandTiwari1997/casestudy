package com.casestudy.rms.service;

import org.springframework.security.access.annotation.Secured;

import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.Policy;

/** Interface for PolicyService class.
 * 
 * @author Anand Tiwari */
public interface IPolicyService {

    /** Method will save the policy defined by the lender after setting the last modified time.
     * 
     * @param policy
     *            policy object containing policy information.
     * @return saved policy object. */
    @Secured({ "ROLE_LENDER" })
    Policy savePolicy(Policy policy);

    /** Method to delete policy using policy ID.
     * 
     * @param policyId
     *            unique ID for the policy.
     * @return boolean true if deleted else false.
     * @throws DAOException
     *             exception throwing from DAO. */
    @Secured({ "ROLE_LENDER" })
    boolean deletePolicy(String policyId);

    /** Method will find policy according to lenders ID.
     * 
     * @param lenderId
     *            unique ID for the lender whose policy required.
     * @return returned policy object.
     * @throws DAOException
     *             exception throwing from DAO. */
    @Secured({ "ROLE_LENDER", "ROLE_ANALYST" })
    Policy getPolicyByLenderId(String lenderId);

    /** Method to get policy on the basis of policy ID.
     * 
     * @param policyId
     *            unique ID for the policy.
     * @return returned policy object.
     * @throws DAOException
     *             exception throwing from DAO. */
    @Secured({ "ROLE_LENDER" })
    Policy getPolicyById(String policyId);

    /** Method to update policies information.
     * 
     * @param policy
     *            policy object containing updated information.
     * @return updated policy object.
     * @throws DAOException
     *             exception throwing from DAO. */
    @Secured({ "ROLE_LENDER" })
    Policy updatePolicy(Policy policy);

}