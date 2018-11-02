package com.casestudy.rms.service.impl;

import java.util.Date;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.rms.dao.IPolicyDao;
import com.casestudy.rms.dao.IUserDao;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.service.IPolicyService;

/** This service class will communicate with PolicyDAO to store, retrieve, delete, and update the policies of the lender.
 * 
 * @author Anand Tiwari */
@Service
@Transactional
public class PolicyServiceImpl implements IPolicyService {

    @Autowired
    private IPolicyDao policyDao;
    
    @Autowired
    private IUserDao userDao;

    /** Static Initializer. */
    private static final Logger LOGGER = Logger.getLogger(PolicyServiceImpl.class);

    /** Method will save the policy defined by the lender after setting the last modified time.
     * 
     * @param policy
     *            policy object containing policy information.
     * @return saved policy object. */
    @Override
    public Policy savePolicy(Policy policy) {
        LOGGER.debug(" Got object in savePolicy... " + policy);
        try {
            long millis = System.currentTimeMillis();
            policy.setLastmodified((new Date(millis)));
            return policyDao.savePolicy(policy);
        } catch (DAOException e) {

        }
        return policy;
    }

    /** Method to delete policy using policy ID.
     * 
     * @param policyId
     *            unique ID for the policy.
     * @return boolean true if deleted else false. */
    @Override
    public boolean deletePolicy(String policyId) {
        LOGGER.debug(" Got policy ID in deletePolicy... " + policyId);
        Policy policy;
        try {
            policy = policyDao.getPolicyById(policyId);
            return policyDao.deletePolicy(policy);
        } catch (DAOException e) {
            throw new ResourceNotFoundException("No Policy to delete.");
        }
    }

    /** Method will find policy according to lenders ID.
     * 
     * @param lenderId
     *            unique ID for the lender whose policy required.
     * @return returned policy object.
     * @throws DAOException
     */
    @Override
    public Policy getPolicyByLenderId(String lenderId) {
        LOGGER.debug(" Got lender ID in getPolicyByLenderId... " + lenderId);
        try {
            userDao.getUserById(lenderId);
            return policyDao.getPolicyByLenderId(lenderId);
        } catch (DAOException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }

    }

    /** Method to get policy on the basis of policy ID.
     * 
     * @param policyId
     *            unique ID for the policy.
     * @return returned policy object.
     * @throws DAOException
     */
    @Override
    public Policy getPolicyById(String policyId) {
        LOGGER.debug(" Got policy ID in getPolicyById... " + policyId);
        try {
            return policyDao.getPolicyById(policyId);
        } catch (DAOException e) {
            throw new ResourceNotFoundException("No Policy found by ID " + policyId);
        }
    }

    /** Method to update policies information.
     * 
     * @param policy
     *            policy object containing updated information.
     * @return updated policy object. */
    @Override
    public Policy updatePolicy(Policy policy) {
        LOGGER.debug(" Got object in updatePolicy... " + policy);
        try {
            long millis = System.currentTimeMillis();
            policy.setLastmodified((new Date(millis)));
            return policyDao.updatePolicy(policy);
        } catch (DAOException e) {
            throw new ResourceNotFoundException("No policy to update.");
        }
    }
}