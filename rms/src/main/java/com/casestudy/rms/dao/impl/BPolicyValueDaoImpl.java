package com.casestudy.rms.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.casestudy.rms.dao.IBPolicyValueDao;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.BPolicyValue;

/** This BPolicyValueDAO class will perform operation of saving policy values for borrower, updating values, and retrieving from database.
 * 
 * @author Anand Tiwari */
@Repository
public class BPolicyValueDaoImpl implements IBPolicyValueDao {

    @PersistenceContext
    private EntityManager entityManager;

    /** Static Initializer. */
    private static final Logger LOGGER = Logger.getLogger(BPolicyValueDaoImpl.class);

    /** Method will save the information provided by the borrower against each policy.
     * 
     * @param bPolicyValue
     *            bPolicyValue object containing information provided by borrower.
     * @return saved bPolicyValue object. */
    @Override
    public BPolicyValue addPolicyValues(BPolicyValue bPolicyValue) {
        LOGGER.debug(" Saving object... " + bPolicyValue);
        entityManager.persist(bPolicyValue);
        return bPolicyValue;
    }

    /** Method will update the information provided by the borrower against each policy.
     * 
     * @param bPolicyValue
     *            bPolicyValue object containing updated information provided by borrower.
     * @return updated bPolicyValue object.
     * @throws DAOException
     */
    @Override
    public BPolicyValue updatePolicyValues(BPolicyValue bPolicyValue) throws DAOException {
        LOGGER.debug(" Updating object... " + bPolicyValue);
        try {
            BPolicyValue updatedBPolicyValue = getPolicyByBorrowerId(bPolicyValue.getBorrowerId());
            updatedBPolicyValue.setIncomeTaxRet(bPolicyValue.getIncomeTaxRet());
            updatedBPolicyValue.setNetworth(bPolicyValue.getNetworth());
            updatedBPolicyValue.setTurnover(bPolicyValue.getTurnover());
            updatedBPolicyValue.setShares(bPolicyValue.getShares());
            updatedBPolicyValue.setLastmodified(bPolicyValue.getLastmodified());
            updatedBPolicyValue.setCompanysize(bPolicyValue.getCompanysize());
            entityManager.flush();
            return updatedBPolicyValue;
        } catch (NoResultException e) {
            LOGGER.error(e);
            throw new DAOException(e.getMessage(), e);
        }
    }

    /** Method will find the policies information according to provided borrower ID.
     * 
     * @param borrowerId
     *            borrower's ID whose policy value are needed.
     * @return bPolicyValue object.
     * @throws DAOException
     */
    @Override
    public BPolicyValue getPolicyByBorrowerId(String borrowerId) {
        LOGGER.debug(" Getting object by borrower ID.. " + borrowerId);
        BPolicyValue bPolicyValue = null;
        try {
            String hql = "FROM BPolicyValue WHERE borrower_id=:borrowerId";
            bPolicyValue = (BPolicyValue) entityManager.createQuery(hql).setParameter("borrowerId", borrowerId).getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error(e);
        }
        return bPolicyValue;
    }

}
