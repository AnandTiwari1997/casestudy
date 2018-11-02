package com.casestudy.rms.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.annotation.Secured;

import com.casestudy.rms.dto.CreditResponse;
import com.casestudy.rms.dto.TopCreditInfo;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.Credit;
import com.casestudy.rms.model.PolicyMatch;

/** Interface for CreditService class.
 * 
 * @author Anand Tiwari */
public interface ICreditService {

    /** Method will save the request submitted by the borrower.
     * 
     * @param credit
     *            The credit request object containing request information.
     * @return saved credit request object. */
    @Secured({ "ROLE_BORROWER" })
    Credit saveCredit(Credit credit);

    /** Method will find the request corresponding to provided request ID.
     *
     * @param requestId
     *            request's ID for which details to find.
     * @return founded request Object.
     * @throws DAOException
     *             the DAO exception */
    @Secured({ "ROLE_BORROWER", "ROLE_LENDER", "ROLE_ANALYST" })
    Credit getCreditByRequestId(String requestId);

    /** Method will find the request assign to analyst corresponding to provided analyst ID.
     *
     * @param analystId
     *            analyst's ID to whom request are assigned.
     * @return list of object containing credit request information.
     * @throws DAOException
     *             the DAO exception */
    @Secured({ "ROLE_LENDER", "ROLE_ANALYST" })
    List<CreditResponse> getCreditByAnalystId(String analystId);

    /** Method will find the request corresponding to provided lender ID.
     *
     * @param lenderId
     *            lender's ID to whom the request is submitted
     * @return list of object containing credit request information.
     * @throws DAOException
     *             the DAO exception */
    @Secured({ "ROLE_LENDER" })
    List<CreditResponse> getCreditByLenderId(String lenderId);

    /** Method will update the information of submitted credit request.
     *
     * @param credit
     *            credit request object containing updated information.
     * @return updated credit request object.
     * @throws DAOException
     *             the DAO exception */
    @Secured({ "ROLE_LENDER", "ROLE_ANALYST" })
    Credit updateCredit(Credit credit);

    /** Gets the credit by borrower id.
     *
     * @param borrowerId
     *            the borrower id
     * @return the credit by borrower id
     * @throws DAOException
     *             the DAO exception */
    @Secured({ "ROLE_BORROWER", "ROLE_LENDER", "ROLE_ANALYST" })
    List<CreditResponse> getCreditByBorrowerId(String borrowerId);

    /** Gets the top credits.
     *
     * @param lenderId
     *            the lender id
     * @return the top credits
     * @throws DAOException
     *             the DAO exception */
    @Secured({ "ROLE_LENDER", "ROLE_ANALYST" })
    TopCreditInfo getTopCreditsByLenderId(String lenderId,String criteria);

    /** Method will fetch the details of passed policy.
     * 
     * @param requestId
     *            unique ID of the credit request.
     * @return policyMatch object. */
    @Secured({ "ROLE_LENDER", "ROLE_ANALYST" })
    PolicyMatch getMatchPolicy(String requestId);

   
    /** @return */
    @Secured({ "ROLE_ADMIN" })
    Map<String, Integer> getRequestCount();

}