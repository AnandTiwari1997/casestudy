package com.casestudy.rms.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.casestudy.rms.dao.IBPolicyValueDao;
import com.casestudy.rms.dao.ICreditDao;
import com.casestudy.rms.dao.IPolicyDao;
import com.casestudy.rms.dao.IUserDao;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.BPolicyValue;
import com.casestudy.rms.model.Credit;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.model.PolicyMatch;
import com.casestudy.rms.model.User;

/** The Class ScheduledTasks has two scheduled jobs checkPolicy and send Notification. */
@Component
public class ScheduledTasks {

    /** The credit obj. */
    @Autowired
    private ICreditDao creditDao;

    /** The user obj. */
    @Autowired
    private IUserDao userDao;

    /** The simpleMail. */
    @Autowired
    private SimpleMail simpleMail;

    /** The bPolicyValue */
    @Autowired
    private IBPolicyValueDao bPolicyValue;

    /** The policyDao. */
    @Autowired
    private IPolicyDao policyDao;

    /** Static Initialization. */
    private static final Logger LOGGER = Logger.getLogger(ScheduledTasks.class);

    /** Check policy method is running automated policy checks and responding accordingly.
     *
     * @throws DAOException
     *             the DAO exception */
    @Scheduled(cron = "0/15 * * * * ?")
    public void checkPolicy() throws DAOException {

        LOGGER.info(" CHECK POLICY SCHEDULED TASK STARTED");
        List<Credit> credlist = creditDao.getCreditsByStatus(ApplicationConstant.Status.SUBMITTED.toString());

        LOGGER.debug("CREDLIST ::" + credlist);
        if (!credlist.isEmpty()) {

            for (Credit credit : credlist) {

                BPolicyValue bPolicy = bPolicyValue.getPolicyByBorrowerId(credit.getBorrowerId());
                Policy lPolicy = policyDao.getPolicyByLenderId(credit.getLenderId());
                LOGGER.info(bPolicy);
                LOGGER.info(lPolicy);
                List<String> satisfiedPolicies = getSatisfiedPolicy(bPolicy, lPolicy,credit.getRequestId());
                changeCreditStatus(credit, satisfiedPolicies, lPolicy);
            }
        } else {
            LOGGER.info("THERE IS NO PENDING APLLICATION FOR SCHEDULAR");
        }
    }

    /** @param count
     * @param bPolicy
     * @param lPolicy
     * @return */
    private List<String> getSatisfiedPolicy(BPolicyValue bPolicy, Policy lPolicy,String requestId) {
        
        List<String> satisfiedPolicies = new ArrayList<>();
        PolicyMatch policyMatch=new PolicyMatch();
        
        policyMatch.setRequestId(requestId);

        if (Long.valueOf(ApplicationConstant.getFormattedValue(bPolicy.getIncomeTaxRet())) >= Long.valueOf(ApplicationConstant.getFormattedValue(lPolicy.getIncomeTaxReturn()))) {
            satisfiedPolicies.add("Income Tex Return");
            policyMatch.setIncomeTaxReturn(1);
        }
        if (Long.valueOf(ApplicationConstant.getFormattedValue(bPolicy.getNetworth())) >= Long.valueOf(ApplicationConstant.getFormattedValue(lPolicy.getNetworth()))) {
            satisfiedPolicies.add("Net Worth");
            policyMatch.setIncomeTaxReturn(1);
        }
        if (Integer.valueOf(bPolicy.getShares()) >= Integer.valueOf(lPolicy.getShares())) {
            satisfiedPolicies.add("Shares");
            policyMatch.setShares(1);
        }
        if (Long.valueOf(ApplicationConstant.getFormattedValue(bPolicy.getTurnover())) >= Long.valueOf(ApplicationConstant.getFormattedValue(lPolicy.getTurnover()))) {
            satisfiedPolicies.add("Turnover");
            policyMatch.setTurnover(1);
        }
        String bsize = bPolicy.getCompanysize();
        bsize = bsize.substring(0, bsize.length() - 1);

        String lsize = lPolicy.getCompanysize();
        lsize = lsize.substring(0, lsize.length() - 1);

        if (Integer.valueOf(bsize) >= Integer.valueOf(lsize)) {
            satisfiedPolicies.add("Company Size");
            policyMatch.setCompanySize(1);
        }
        
        policyMatch.setMinSatisfy(lPolicy.getMinSatisfy());
        
        creditDao.savePolicyMatch(policyMatch);
        
        return satisfiedPolicies;
        
    }

    /** Change credit status.
     *
     * @param credit
     *            the credit model Object
     * @param count
     *            the count
     * @param lPolicy
     *            lender's policy object.
     * @throws DAOException
     *             the DAO exception */
    private void changeCreditStatus(Credit credit, List<String> satisfiedPolicies, Policy lPolicy) throws DAOException {

        User borrower = userDao.getUserById(credit.getBorrowerId());
        User lender = userDao.getUserById(credit.getLenderId());
        int count = satisfiedPolicies.size();

        if (count >= ApplicationConstant.MAXPOLICY) {
            credit.setStatus(ApplicationConstant.Status.APPROVED.toString());
            long millis = System.currentTimeMillis();
            credit.setResponseDate((new Date(millis)));

            creditDao.updateCredit(credit);
            simpleMail.sendMail(borrower.getEmail(), ApplicationConstant.MAILSUBJECT, ApplicationConstant.createBorrowerMailBody(credit.getRequestId(), 
                    credit.getStatus(), satisfiedPolicies));

            simpleMail.sendMail(lender.getEmail(), ApplicationConstant.MAILSUBJECT, ApplicationConstant.createLenderMailBody(credit.getRequestId(), 
                    credit.getStatus(), satisfiedPolicies));
        } else {
            if (count >= lPolicy.getMinSatisfy()) {
                credit.setStatus(ApplicationConstant.Status.PENDING.toString());
                creditDao.updateCredit(credit);
                
                simpleMail.sendMail(lender.getEmail(), ApplicationConstant.MAILSUBJECT, ApplicationConstant.createLenderMailBody(credit.getRequestId(), 
                        credit.getStatus(), satisfiedPolicies));
            } else {
                credit.setStatus(ApplicationConstant.Status.REJECTED.toString());
                creditDao.updateCredit(credit);
                
                simpleMail.sendMail(borrower.getEmail(), ApplicationConstant.MAILSUBJECT, ApplicationConstant.createBorrowerMailBody(credit.getRequestId(), 
                        credit.getStatus(), satisfiedPolicies));

                simpleMail.sendMail(lender.getEmail(), ApplicationConstant.MAILSUBJECT, ApplicationConstant.createLenderMailBody(credit.getRequestId(), 
                        credit.getStatus(), satisfiedPolicies));
            }
        }

    }

    /** Method will convert the currency to numeric representation of string.
     * 
     * @param str
     *            currency in string.
     * @return string containing numeric representation. */
//    private String getValue(String str) {
//
//        if (str.contains("crores")) {
//            str = str.replace(" crores", "0000000");
//        } else {
//            str = str.replace(" lacs", "00000");
//        }
//        return str;
//    }

    /** Send notification will send simpleMail to all analyst with pending credit request.
     *
     * @throws DAOException
     *             the DAO exception */
    // @Scheduled(cron = "0/60 * * * * ?")
    private void sendNotification() throws DAOException {

        LOGGER.info("ANALYST NOTIFICATION SCHEDULAR STARTED");

        List<Credit> credlist = creditDao.getCreditsByStatus("INPROGRESS");

        LOGGER.debug("CREDITLIST ::" + credlist);
        if (!credlist.isEmpty()) {

            for (Credit cred : credlist) {

                User analyst = userDao.getUserById(cred.getAnalystId());

                String body = "hi " + analyst.getName() + ",\n You are having pending credit application assigned to you . Please make review to it.";
                simpleMail.sendMail(analyst.getEmail(), ApplicationConstant.MAILSUBJECT, body);
            }

        } else {
            LOGGER.info("NO PENDING REQUEST TO ANY ANLYST");
        }

    }

}
