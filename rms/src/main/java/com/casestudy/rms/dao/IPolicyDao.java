package com.casestudy.rms.dao;

import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.Policy;

/** Interface for PolicyDaoImpl class.
 * 
 * @author anand.tiwari */
public interface IPolicyDao {

    /** Method will save the policy defined by the lender after setting the last modified time.
     * 
     * @param policy
     *            policy object containing policy information.
     * @return saved policy object.
     * @throws DAOException
     */
    Policy savePolicy(Policy policy) throws DAOException;

    /** Method to delete policy using policy ID.
     * 
     * @param policy
     *            unique ID for the policy.
     * @return boolean true if deleted else false.
     * @throws DAOException
     */
    boolean deletePolicy(Policy policy);

    /** Method will find policy according to lenders ID.
     * 
     * @param lenderId
     *            unique ID for the lender whose policy required.
     * @return returned policy object.
     * @throws DAOException
     */
    Policy getPolicyByLenderId(String lenderId) throws DAOException;

    /** Method to get policy on the basis of policy ID.
     * 
     * @param policyId
     *            unique ID for the policy.
     * @return returned policy object.
     * @throws DAOException
     */
    Policy getPolicyById(String policyId) throws DAOException;

    /** Method to update policies information.
     * 
     * @param policy
     *            policy object containing updated information.
     * @return updated policy object.
     * @throws DAOException
     */
    Policy updatePolicy(Policy policy) throws DAOException;

}