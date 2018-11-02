package com.casestudy.rms.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.casestudy.rms.dao.ICreditDao;
import com.casestudy.rms.dao.IUserDao;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.Credit;
import com.casestudy.rms.model.PolicyMatch;
import com.casestudy.rms.model.User;
import com.casestudy.rms.utils.ApplicationConstant;
import com.casestudy.rms.utils.SimpleMail;

/** THis CreditDAO class will perform actual operation of saving a request, retrieving a request and updating a request in database.
 * 
 * @author Anand Tiwari */
@Repository
public class CreditDaoImpl implements ICreditDao {

    /** The user dao. */
    @Autowired
    private IUserDao userDao;

    /** The mail. */
    @Autowired
    private SimpleMail mail;

    /** The entity manager. */
    @PersistenceContext
    private EntityManager entityManager;

    /** Static Initialization. */
    private static final Logger LOGGER = Logger.getLogger(CreditDaoImpl.class);

    /** Method will save the request submitted by the borrower.
     * 
     * @param credit
     *            The credit request object containing request information.
     * @return saved credit request object.
     * @throws DAOException
     */
    @Override
    @Transactional
    public Credit saveCredit(Credit credit) throws DAOException {
        LOGGER.debug(" Saving object... " + credit);
        try {
            String hql = "SELECT MAX(CAST(SUBSTRING(request_id, 4, length(request_id)-2) AS UNSIGNED)) FROM cred_request";
            int maxNumber = (((BigInteger) (entityManager.createNativeQuery(hql).getSingleResult())).intValue());
            String reqid = ("CR" + (ApplicationConstant.START + maxNumber + 1));
            LOGGER.debug("LAST ID GENERATED" + reqid);
            credit.setRequestId(reqid);
            entityManager.persist(credit);
            User borrower = userDao.getUserById(credit.getBorrowerId());
            User lender = userDao.getUserById(credit.getLenderId());
            String body = "hi " + borrower.getName() + ",\n Your Credit Request with RequestID " + credit.getRequestId()
                    + " is successfully submitted to " + lender.getName() + ".";
            String subject = "Credit Application";
            mail.sendMail(borrower.getEmail(), subject, body);
            return credit;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new DAOException(e.getMessage(), e);
        }
    }

    /** Method will find the request corresponding to provided request ID.
     *
     * @param requestId
     *            request's ID for which details to find.
     * @return founded request Object.
     * @throws DAOException
     *             the DAO exception */
    @Override
    public Credit getCreditByRequestId(String requestId) throws DAOException {
        LOGGER.debug(" Getting object by ID... " + requestId);
        try {
            String hql = "FROM Credit WHERE requestId=?1";
            return (Credit) entityManager.createQuery(hql).setParameter(1, requestId).getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error(e);
            throw new DAOException(e.getMessage(), e);
        }
    }

    /** Method will find the request assign to analyst corresponding to provided analyst ID.
     *
     * @param analystId
     *            analyst's ID to whom request are assigned.
     * @return list of object containing credit request information.
     * @throws DAOException
     *             the DAO exception */
    @Override
    public List<Credit> getCreditByAnalystId(String analystId) {
        LOGGER.debug(" Getting object by analyst ID... " + analystId);
        String hql = "FROM Credit WHERE analystId=?1";
        return (List<Credit>) entityManager.createQuery(hql).setParameter(1, analystId).getResultList();

    }

    /** Method will find the request corresponding to provided lender ID.
     *
     * @param lenderId
     *            lender's ID to whom the request is submitted
     * @return list of object containing credit request information.
     * @throws DAOException
     *             the DAO exception */
    @Override
    public List<Credit> getCreditByLenderId(String lenderId) {
        LOGGER.debug(" Getting object by lender ID... " + lenderId);
        String hql = "FROM Credit WHERE lenderId=?1";
        return (List<Credit>) entityManager.createQuery(hql).setParameter(1, lenderId).getResultList();
    }

    /** Method will update the information of submitted credit request.
     *
     * @param credit
     *            credit request object containing updated information.
     * @return updated credit request object.
     * @throws DAOException
     *             the DAO exception */
    @Override
    @Transactional
    public Credit updateCredit(Credit credit) throws DAOException {
        LOGGER.debug(" Updating object... " + credit);
        try {
            Credit creditNew = getCreditByRequestId(credit.getRequestId());
            creditNew.setAnalystId(credit.getAnalystId());
            creditNew.setResponseDate(credit.getResponseDate());
            creditNew.setStatus(credit.getStatus());
            entityManager.flush();
            return creditNew;
        } catch (DAOException e) {
            LOGGER.error(e);
            throw new DAOException(e.getMessage(), e);
        }
    }

    /** Gets the creditslist by status.
     *
     * @param status
     *            status of credit Application
     * @return the list of credits of corresponding status
     * @throws DAOException
     *             the DAO exception */
    @SuppressWarnings("unchecked")
    @Override
    public List<Credit> getCreditsByStatus(String status) {
        LOGGER.debug(" Getting credit List by status... " + status);
        String hql = "From Credit WHERE status=?1 ORDER BY RAND()";
        return (List<Credit>) entityManager.createQuery(hql).setParameter(1, status).getResultList();

    }

    /** Gets the credit by borrower id.
     *
     * @param borrowerId
     *            the borrower id
     * @return the credit by borrower id
     * @throws DAOException
     *             the DAO exception */
    @SuppressWarnings("unchecked")
    @Override
    public List<Credit> getCreditByBorrowerId(String borrowerId) {
        LOGGER.debug(" Getting object by borrowerID... " + borrowerId);
        String hql = "FROM Credit WHERE borrowerId=?1";
        return (List<Credit>) entityManager.createQuery(hql).setParameter(1, borrowerId).getResultList();
    }

    /**
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Credit> getTopCreditsByLenderId(String lenderId) {
        LOGGER.debug(" Getting object by lender ID... " + lenderId);
        String hql = "FROM Credit  WHERE lenderId=?1 AND status=?2";
        return (List<Credit>) entityManager.createQuery(hql).setParameter(1, lenderId).setParameter(2, "inprogress").getResultList();

    }

    /** Method will save details of passed policy.
     * 
     * @param policyMatch
     *            object containing details of passed policy.
     * @return saved policyMatch object. */
    @Override
    public PolicyMatch savePolicyMatch(PolicyMatch policyMatch) {
        LOGGER.debug(" Saving object... " + policyMatch);
        entityManager.persist(policyMatch);
        return policyMatch;
    }

    /** Method will fetch the details of passed policy.
     * 
     * @param requestId
     *            unique ID of the credit request.
     * @return policyMatch object. */
    @Override
    public PolicyMatch getMatchPolicy(String requestId) {
        LOGGER.debug(" Getting MatchPolicies by requestId... " + requestId);
        PolicyMatch policyMatch = null;
        try {
            String hql = "From PolicyMatch WHERE requestId=?1 ";
            policyMatch = (PolicyMatch) entityManager.createQuery(hql).setParameter(1, requestId).getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error(e);
        }
        return policyMatch;
    }
}
