package com.casestudy.rms.dao;

import java.util.List;

import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.Credit;
import com.casestudy.rms.model.PolicyMatch;

/** Interface for CreditDaoImpl class.
 * 
 * @author anand.tiwari */
public interface ICreditDao {

    /** Method will save the request submitted by the borrower.
     * 
     * @param credit
     *            The credit request object containing request information.
     * @return saved credit request object.
     * @throws DAOException
     *             DAOException object. */
    Credit saveCredit(Credit credit) throws DAOException;

    /** Method will find the request corresponding to provided request ID.
     *
     * @param requestId
     *            request's ID for which details to find.
     * @return founded request Object.
     * @throws DAOException
     *             the DAO exception */
    Credit getCreditByRequestId(String requestId) throws DAOException;

    /** Method will find the request assign to analyst corresponding to provided analyst ID.
     *
     * @param analystId
     *            analyst's ID to whom request are assigned.
     * @return list of object containing credit request information.
     * @throws DAOException
     *             the DAO exception */
    List<Credit> getCreditByAnalystId(String analystId);

    /** Method will find the request corresponding to provided lender ID.
     *
     * @param lenderId
     *            lender's ID to whom the request is submitted
     * @return list of object containing credit request information.
     * @throws DAOException
     *             the DAO exception */
    List<Credit> getCreditByLenderId(String lenderId);

    /** Method will update the information of submitted credit request.
     *
     * @param credit
     *            credit request object containing updated information.
     * @return updated credit request object.
     * @throws DAOException
     *             the DAO exception */
    Credit updateCredit(Credit credit) throws DAOException;

    /** Gets the credits by status.
     *
     * @param status
     *            the status
     * @return the credits by status
     * @throws DAOException
     *             the DAO exception */
    List<Credit> getCreditsByStatus(String status);

    /** Gets the credit by borrower id.
     *
     * @param borrowerId
     *            the borrower id
     * @return the credit by borrower id
     * @throws DAOException
     *             the DAO exception */
    List<Credit> getCreditByBorrowerId(String borrowerId);

    /** Gets the top credits lender id.
     *
     * @param lenderId
     *            the lender id
     * @return the top credits lender id
     * @throws DAOException
     *             the DAO exception */
    List<Credit> getTopCreditsByLenderId(String lenderId);

    /** Method will fetch the details of passed policy.
     * 
     * @param requestId
     *            unique ID of the credit request.
     * @return policyMatch object. */
    PolicyMatch getMatchPolicy(String requestId);

    /** Method will save details of passed policy.
     * 
     * @param policyMatch
     *            object containing details of passed policy.
     * @return saved policyMatch object. */
    PolicyMatch savePolicyMatch(PolicyMatch policyMatch);
}