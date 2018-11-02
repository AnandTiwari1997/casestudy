package com.casestudy.rms.dao.impl;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.stereotype.Repository;

import com.casestudy.rms.dao.IPolicyDao;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.utils.ApplicationConstant;

/** This DAO class will perform actual operation of saving, reading, deleting, and updating Policy Entity in database.
 * 
 * @author Anand Tiwari */
@Repository
public class PolicyDaoImpl implements IPolicyDao {

    /** The entity manager. */
    @PersistenceContext
    private EntityManager entityManager;

    /** Static Initializer. */
    private static final Logger LOGGER = Logger.getLogger(PolicyDaoImpl.class);

    /** Method will save the policy defined by the lender after setting the last modified time.
     * 
     * @param policy
     *            policy object containing policy information.
     * @return saved policy object.
     * @throws DAOException
     */
    @Override
    @Transactional
    public Policy savePolicy(Policy policy) throws DAOException {
        LOGGER.debug(" Saving object... " + policy);
        try {
            String hql = "SELECT MAX(CAST(SUBSTRING(policy_id, 4, length(policy_id)-2) AS UNSIGNED)) FROM lender_policy";
            int maxNumber = (((BigInteger) (entityManager.createNativeQuery(hql).getSingleResult())).intValue());
            String pid = ("PL" + (ApplicationConstant.START + maxNumber + 1));
            policy.setPolicyId(pid);
            entityManager.persist(policy);
            return policy;
        } catch (JDBCConnectionException e) {
            LOGGER.error(e);
            throw new DAOException(e.getMessage(), e);
        } catch (NullPointerException exception) {
            throw new DAOException(exception.getMessage(), exception);
        }
    }

    /** Method to delete policy using policy ID.
     * 
     * @param policy
     *            Policy Object.
     * @return boolean true if deleted else false.
     * @throws DAOException
     *             the DAO exception */
    @Override
    public boolean deletePolicy(Policy policy) {
        LOGGER.debug(" Deleting object with ... " + policy);
        if (policy != null) {
            entityManager.remove(policy);
            return true;
        }
        return false;
    }

    /** Method will find policy according to lenders ID.
     *
     * @param lenderId
     *            unique ID for the lender whose policy required.
     * @return returned policy object.
     * @throws DAOException
     *             the DAO exception */
    @Override
    public Policy getPolicyByLenderId(String lenderId) throws DAOException {
        LOGGER.debug(" Getting policy by lender ID... " + lenderId);
        Policy policy = null;
        try {
            String hql = "FROM Policy WHERE lender_id=?1";
            policy = (Policy) entityManager.createQuery(hql).setParameter(1, lenderId).getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error(e);
        }
        return policy;
    }

    /** Method to get policy on the basis of policy ID.
     *
     * @param policyId
     *            unique ID for the policy.
     * @return returned policy object.
     * @throws DAOException
     *             the DAO exception */
    @Override
    public Policy getPolicyById(String policyId) throws DAOException {
        LOGGER.debug(" Getting policy by policy ID... " + policyId);
        try {
            String hql = "FROM Policy WHERE policy_id=?1";
            return (Policy) entityManager.createQuery(hql).setParameter(1, policyId).getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error(e);
            throw new DAOException(e.getMessage(), e);
        }
    }

    /** Method to update policies information.
     *
     * @param policy
     *            policy object containing updated information.
     * @return updated policy object.
     * @throws DAOException
     *             the DAO exception */
    @Override
    public Policy updatePolicy(Policy policy) throws DAOException {
        LOGGER.debug(" Updating object... " + policy);
        try {
            Policy policyNew = getPolicyById(policy.getPolicyId());
            policyNew.setTurnover(policy.getTurnover());
            policyNew.setIncomeTaxReturn(policy.getIncomeTaxReturn());
            policyNew.setMinSatisfy(policy.getMinSatisfy());
            policyNew.setNetworth(policy.getNetworth());
            policyNew.setShares(policy.getShares());
            policyNew.setLastmodified(policy.getLastmodified());
            policyNew.setCompanysize(policy.getCompanysize());
            policyNew.setAmountRange(policy.getAmountRange());
            policyNew.setInterest(policy.getInterest());
            policyNew.setLowestEMI(policy.getLowestEMI());
            policyNew.setTenureRange(policy.getTenureRange());
            policyNew.setPfValue(policy.getPfValue());
            policyNew.setProcessingFee(policy.getProcessingFee());
            entityManager.flush();
            return policyNew;
        } catch (NoResultException e) {
            LOGGER.error(e);
            throw new DAOException(e.getMessage(), e);
        }
    }
}
