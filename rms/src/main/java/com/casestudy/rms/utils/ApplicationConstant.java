package com.casestudy.rms.utils;

import java.util.List;

/** The Class ApplicationConstant. */
public class ApplicationConstant {

    /** The Constant START. */
    public static final int START = 10000;

    /** The Constant FIVE. */
    public static final int FIVE = 5;

    /** The Constant TEN. */
    public static final int TEN = 10;

    /** The Constant SUBJECT. */
    public static final String MAILSUBJECT = "Credit Application";

    /** The Constant MAXPOLICY. */
    public static final int MAXPOLICY = 5;

    public static final String ROLE_LENDER = "ROLE_LENDER";

    public static final String ROLE_BORROWER = "ROLE_BORROWER";

    public static final String ROLE_ANALYST = "ROLE_ANALYST";

    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    /** Status ENUM.
     * 
     * @author anand.tiwari */
    public enum Status {
        SUBMITTED, APPROVED, PENDING, WAITING_FOR_ANALYST, REJECTED
    }

    /** Method for creating mail body for Borrower.
     * 
     * @param id
     *            Credit Request ID.
     * @param status
     *            Status of the request.
     * @param satisfiedPolicies
     *            list of policies satisfy by the borrower.
     * @return string body of the message. */
    public static String createBorrowerMailBody(String id, String status, List<String> satisfiedPolicies) {

        String body = "Hi, " + "\nYour request with ID " + id + " is " + status + ". \n\n" + "List of Policies satisfied: \n";

        for (String policy : satisfiedPolicies) {
            body = body + policy + "\n";
        }
        return body;
    }

    /** Method for creating mail body for Lender.
     * 
     * @param id
     *            Credit Request ID.
     * @param status
     *            Status of the request.
     * @param satisfiedPolicies
     *            list of policies satisfy by the borrower.
     * @return string body of the message. */
    public static String createLenderMailBody(String id, String status, List<String> satisfiedPolicies) {

        String body = "Hi, " + "\nCredit Request with ID " + id + " has been checked by automated policies.\n\n" + "Current Status  : " + status
                + ". \n\n" + "List of policies satisfied: \n";

        for (String policy : satisfiedPolicies) {
            body = body + policy + "\n";
        }
        return body;
    }

    /** Method will convert the currency to numeric representation of string.
     * 
     * @param str
     *            currency in string.
     * @return string containing numeric representation. */
    public static String getFormattedValue(String str) {

        if (str.contains("crores")) {
            str = str.replace(" crores", "0000000");
        } else {
            str = str.replace(" lacs", "00000");
        }
        return str;
    }

}
