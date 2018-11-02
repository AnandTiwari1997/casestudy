package com.casestudy.rms.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.rms.dao.IBPolicyValueDao;
import com.casestudy.rms.dao.ICreditDao;
import com.casestudy.rms.dao.IUserDao;
import com.casestudy.rms.dto.CreditResponse;
import com.casestudy.rms.dto.TopCreditInfo;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.model.BPolicyValue;
import com.casestudy.rms.model.Credit;
import com.casestudy.rms.model.PolicyMatch;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.ICreditService;
import com.casestudy.rms.utils.ApplicationConstant;

/** This service class will make use of CreditDao to submit, retrieve, and update a credit request.
 * 
 * @author Anand Tiwari */
@Service
@Transactional
public class CreditServiceImpl implements ICreditService {

    /** The credit dao. */
    @Autowired
    private ICreditDao creditDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IBPolicyValueDao policyValueDao;

    /** Static Initialization. */
    private static final Logger LOGGER = Logger.getLogger(CreditServiceImpl.class);

    /** Method will save the request submitted by the borrower.
     * 
     * @param credit
     *            The credit request object containing request information.
     * @return saved credit request object. */
    @Override
    public Credit saveCredit(Credit credit) {
        LOGGER.debug(" Got object in saveCredit... " + credit);
        long millis = System.currentTimeMillis();
        credit.setRequestDate((new Date(millis)));
        try {
            return creditDao.saveCredit(credit);
        } catch (DAOException e) {
        }
        return null;
    }

    /** Method will find the request corresponding to provided request ID.
     *
     * @param requestId
     *            request's ID for which details to find.
     * @return founded request Object.
     * @throws DAOException
     *             the DAO exception */
    @Override
    public Credit getCreditByRequestId(String requestId) {
        LOGGER.debug(" Got request ID in getCreditByRequestId... " + requestId);
        try {
            return creditDao.getCreditByRequestId(requestId);
        } catch (DAOException daoException) {
            throw new ResourceNotFoundException("No Resquest Found.");
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
    public List<CreditResponse> getCreditByAnalystId(String analystId) {
        LOGGER.debug(" Got analyst ID in getCreditByAnalystId... " + analystId);
        List<CreditResponse> creditResponses = new ArrayList<>();
        List<Credit> credits = creditDao.getCreditByAnalystId(analystId);
        if (credits.isEmpty())
            throw new ResourceNotFoundException("No request assigned to this analyst.");

        try {
            for (Credit credit : credits) {
                User borrower = userDao.getUserById(credit.getBorrowerId());
                User lender = userDao.getUserById(credit.getLenderId());
                User analyst = null;
                if (credit.getAnalystId() != null)
                    analyst = userDao.getUserById(credit.getAnalystId());
                BPolicyValue bPolicyValue = policyValueDao.getPolicyByBorrowerId(borrower.getUserId());
                CreditResponse creditResponse = new CreditResponse();
                creditResponse.setAmount(credit.getAmount());
                creditResponse.setLender(lender);
                creditResponse.setAnalystId(analyst);
                creditResponse.setRequestDate(credit.getRequestDate());
                creditResponse.setRequestId(credit.getRequestId());
                creditResponse.setResponseDate(credit.getResponseDate());
                creditResponse.setLoc(credit.getLoc());
                creditResponse.setBorrower(borrower);
                creditResponse.setbPolicyValue(bPolicyValue);
                creditResponse.setStatus(credit.getStatus());
                creditResponses.add(creditResponse);

            }
            return creditResponses;
        } catch (DAOException e) {
            throw new ResourceNotFoundException("No request assigned to this analyst.");
        }
    }

    /** Method will find the request corresponding to provided lender ID.
     *
     * @param lenderId
     *            lender's ID to whom the request is submitted
     * @return list of object containing credit request information.
     * @throws DAOException
     *             the DAO exception */
    @Override
    public List<CreditResponse> getCreditByLenderId(String lenderId) {
        LOGGER.debug(" Got lender ID in getCreditByLenderId... " + lenderId);
        List<CreditResponse> creditResponses = new ArrayList<>();
        List<Credit> credits = creditDao.getCreditByLenderId(lenderId);
        if (credits.isEmpty())
            throw new ResourceNotFoundException("No request made to this lender.");

        try {
            for (Credit credit : credits) {
                User borrower = userDao.getUserById(credit.getBorrowerId());
                User lender = userDao.getUserById(credit.getLenderId());
                User analyst = null;
                if (credit.getAnalystId() != null)
                    analyst = userDao.getUserById(credit.getAnalystId());
                BPolicyValue bPolicyValue = policyValueDao.getPolicyByBorrowerId(borrower.getUserId());
                CreditResponse creditResponse = new CreditResponse();
                creditResponse.setAmount(credit.getAmount());
                creditResponse.setLender(lender);
                creditResponse.setAnalystId(analyst);
                creditResponse.setRequestDate(credit.getRequestDate());
                creditResponse.setRequestId(credit.getRequestId());
                creditResponse.setResponseDate(credit.getResponseDate());
                creditResponse.setLoc(credit.getLoc());
                creditResponse.setBorrower(borrower);
                creditResponse.setbPolicyValue(bPolicyValue);
                creditResponse.setStatus(credit.getStatus());
                creditResponses.add(creditResponse);

            }
            return creditResponses;
        } catch (DAOException e) {
            throw new ResourceNotFoundException("No request made to this lender.");
        }
    }

    /** Gets the credit by borrower id.
     *
     * @param borrowerId
     *            the borrower id
     * @return the credit by borrower id
     * @throws DAOException
     *             the DAO exception */
    @Override
    public List<CreditResponse> getCreditByBorrowerId(String borrowerId) {
        LOGGER.debug(" Got borrower ID in getCreditByBorrowerId... " + borrowerId);
        List<CreditResponse> creditResponses = new ArrayList<>();
        List<Credit> credits = creditDao.getCreditByBorrowerId(borrowerId);
        if (credits.isEmpty())
            throw new ResourceNotFoundException("No request made by this borrower.");

        try {
            for (Credit credit : credits) {
                User borrower = userDao.getUserById(credit.getBorrowerId());
                User lender = userDao.getUserById(credit.getLenderId());
                User analyst = null;
                if (credit.getAnalystId() != null)
                    analyst = userDao.getUserById(credit.getAnalystId());
                BPolicyValue bPolicyValue = policyValueDao.getPolicyByBorrowerId(borrower.getUserId());
                CreditResponse creditResponse = new CreditResponse();
                creditResponse.setAmount(credit.getAmount());
                creditResponse.setLender(lender);
                creditResponse.setAnalystId(analyst);
                creditResponse.setRequestDate(credit.getRequestDate());
                creditResponse.setRequestId(credit.getRequestId());
                creditResponse.setResponseDate(credit.getResponseDate());
                creditResponse.setLoc(credit.getLoc());
                creditResponse.setBorrower(borrower);
                creditResponse.setbPolicyValue(bPolicyValue);
                creditResponse.setStatus(credit.getStatus());
                creditResponses.add(creditResponse);

            }
            return creditResponses;
        } catch (DAOException e) {
            throw new ResourceNotFoundException("No request made by this borrower.");
        }
    }

    /** Method will update the information of submitted credit request.
     *
     * @param credit
     *            credit request object containing updated information.
     * @return updated credit request object.
     * @throws DAOException
     *             the DAO exception */
    @Override
    public Credit updateCredit(Credit credit) {
        LOGGER.debug("Got object in updateCredit... " + credit);
        try {
            return creditDao.updateCredit(credit);
        } catch (DAOException e) {
            throw new ResourceNotFoundException("No request to update.");
        }
    }

    /** Method will find the Top 5 request corresponding to provided lender ID.
     *
     * @param lenderId
     *            lender's ID to whom the request is submitted
     * @return list of object containing credit request information.
     * @throws DAOException
     */
    @Override
    public TopCreditInfo getTopCreditsByLenderId(String lenderId, String criteria) {
        LOGGER.debug(" Got lender ID in getTOPcredit... " + lenderId + "\t" + criteria);

        try {
            List<Credit> credits = creditDao.getTopCreditsByLenderId(lenderId);
            if (credits.isEmpty()) {
                throw new ResourceNotFoundException("No request for this lender.");
            }

            LOGGER.info(credits);
            TopCreditInfo creditInfo = new TopCreditInfo();
            List<String> companyNames = new ArrayList<>();
            List<Long> values = new ArrayList<>();
            Map<String, Long> topMap = new HashMap<>();

            for (Credit credit : credits) {
                User borrower = userDao.getUserById(credit.getBorrowerId());
                BPolicyValue bPolicyValue = policyValueDao.getPolicyByBorrowerId(credit.getBorrowerId());
                String value;
                switch (criteria) {
                    case "amount":
                        value = ApplicationConstant.getFormattedValue(credit.getAmount());
                        topMap.put(borrower.getName(), Long.valueOf(value));
                        break;

                    case "turnover":
                        value = ApplicationConstant.getFormattedValue(bPolicyValue.getTurnover());
                        topMap.put(borrower.getName(), Long.valueOf(value));
                        break;

                    case "networth":
                        value = ApplicationConstant.getFormattedValue(bPolicyValue.getNetworth());
                        topMap.put(borrower.getName(), Long.valueOf(value));
                        break;

                    case "shares":
                        value = ApplicationConstant.getFormattedValue(bPolicyValue.getShares());
                        topMap.put(borrower.getName(), Long.valueOf(value));
                        break;

                    default:
                        throw new ResourceNotFoundException("Wrong Criteria Choosen");
                }
            }

            Set<Entry<String, Long>> set = topMap.entrySet();
            List<Entry<String, Long>> list = new ArrayList<>(set);
            Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
                public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            });

            int count = 0;

            for (Map.Entry<String, Long> entry : list) {
                if (count == ApplicationConstant.TEN)
                    break;
                companyNames.add(entry.getKey());
                values.add(entry.getValue());
                count++;
            }
            creditInfo.setCompanyNames(companyNames);
            creditInfo.setValues(values);
            return creditInfo;
        } catch (DAOException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    /** Method will fetch the details of passed policy.
     * 
     * @param requestId
     *            unique ID of the credit request.
     * @return policyMatch object. */
    @Override
    public PolicyMatch getMatchPolicy(String requestId) {
        LOGGER.debug(" Got request ID in getCreditByRequestId... " + requestId);
        PolicyMatch policyMatch = creditDao.getMatchPolicy(requestId);
        if (policyMatch == null) {
            throw new ResourceNotFoundException("Policy is yet to check.");
        }
        return policyMatch;
    }
    
    @Override
    public Map<String, Integer> getRequestCount() {
        LOGGER.info(" Inside getRequestCount... ");
        String[] allStatus = new String[] { "submitted", "pending", "inprogress","rejected","approved" };
        Map<String, Integer> requestCount = new HashMap<>();
        for (String status : allStatus) {
            List<Credit> list = creditDao.getCreditsByStatus(status);
            requestCount.put(status, list.size());
        }
        return requestCount;
    }
}
