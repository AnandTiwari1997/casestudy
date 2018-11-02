package com.casestudy.rms.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.rms.dto.CreditResponse;
import com.casestudy.rms.dto.TopCreditInfo;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.exception.URIPathException;
import com.casestudy.rms.model.Credit;
import com.casestudy.rms.model.PolicyMatch;
import com.casestudy.rms.service.ICreditService;

/** Credit Controller is for handling all the credit request related operations like adding, reading, deleting and updating the request information.
 * This controller is for LENDER, ANALYST, and BORROWER.
 * 
 * @author Anand Tiwari */
@RestController
@RequestMapping(value = "/requests")
public class CreditController {

    /** The credit service. */
    @Autowired
    private ICreditService creditService;

    /** Static Initialization. */
    private static final Logger LOGGER = Logger.getLogger(CreditController.class);

    /** Method will save the request submitted by the borrower.
     * 
     * @param credit
     *            The request object containing request information.
     * @return saved request object. */
    @PostMapping
    public ResponseEntity<Credit> saveCredit(@RequestBody Credit credit) {
        LOGGER.debug(" Got object in saveCredit... " + credit);
        Credit newRequest = null;
        if (credit != null) {
            newRequest = creditService.saveCredit(credit);
            return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
        } else {
            throw new URIPathException("Credit Request details are empty.");
        }
    }

    /** Method will find the request corresponding to provided request ID.
     * 
     * @param requestId
     *            request's ID for which details to find.
     * @return founded request Object. */
    @GetMapping(value = "/{requestId}")
    public ResponseEntity<Credit> getCreditByRequestId(@PathVariable String requestId) {
        LOGGER.debug(" Got request ID in getCreditByRequestId... " + requestId);
        Credit credit = null;
        if (!requestId.isEmpty()) {
            try {
                credit = creditService.getCreditByRequestId(requestId);
                return new ResponseEntity<>(credit, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'requestId' parameter is not on the path.");
        }
    }

    /** Method will find the request corresponding to provided lender ID.
     * 
     * @param lenderId
     *            lender's ID to whom the request is submitted
     * @return list of object containing credit request information. */
    @GetMapping(value = "/lender/{lenderId}")
    public ResponseEntity<List<CreditResponse>> getCreditByLenderId(@PathVariable String lenderId) {
        LOGGER.debug(" Got lender ID in getCreditByLenderId... " + lenderId);
        List<CreditResponse> creditResponses = null;
        if (!lenderId.isEmpty()) {
            try {
                creditResponses = creditService.getCreditByLenderId(lenderId);
                return new ResponseEntity<>(creditResponses, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'requestId' parameter is not on the path.");
        }
    }

    /** Method will find the request assign to analyst corresponding to provided analyst ID.
     * 
     * @param analystId
     *            analyst's ID to whom request are assigned.
     * @return list of object containing credit request information. */
    @GetMapping(value = "/analyst/{analystId}")
    public ResponseEntity<List<CreditResponse>> getCreditByAnalystId(@PathVariable String analystId) {
        LOGGER.debug(" Got analyst ID in getCreditByAnalystId... " + analystId);
        List<CreditResponse> creditResponses = null;
        if (!analystId.isEmpty()) {
            try {
                creditResponses = creditService.getCreditByAnalystId(analystId);
                return new ResponseEntity<>(creditResponses, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'analystId' parameter is not on the path.");
        }
    }

    /** Gets the credit by borrower id.
     *
     * @param borrowerId
     *            the borrower id
     * @return the credit by borrower id */
    @GetMapping(value = "/borrower/{borrowerId}")
    public ResponseEntity<List<CreditResponse>> getCreditByBorrowerId(@PathVariable String borrowerId) {
        LOGGER.debug(" Got lender ID in getCreditByBorrowerId... " + borrowerId);
        List<CreditResponse> creditResponse = null;
        if (!borrowerId.isEmpty()) {
            try {
                creditResponse = creditService.getCreditByBorrowerId(borrowerId);
                return new ResponseEntity<>(creditResponse, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'requestId' parameter is not on the path.");
        }
    }

    /** Method will update the information of submitted credit request.
     * 
     * @param credit
     *            credit request object containing updated information.
     * @return updated credit request object. */
    @PutMapping
    public ResponseEntity<Credit> updateCredit(@RequestBody Credit credit) {
        LOGGER.debug(" Got object in updateCredit... " + credit);
        Credit updatecredit = null;
        if (credit != null) {
            try {
                updatecredit = creditService.updateCredit(credit);
                return new ResponseEntity<>(updatecredit, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("Credit Request details are empty.");
        }
    }

    /** Gets the top credits.
     *
     * @param lenderId
     *            the lender id
     * @return the top credits */
    @GetMapping(value = "/lender/{lenderId}/top")
    public ResponseEntity<TopCreditInfo> getTopCreditsByLenderId(@PathVariable String lenderId, @RequestParam String criteria) {
        LOGGER.debug(" Got lender ID in getTopCredits... " + lenderId + "\t" + criteria);
        TopCreditInfo creditInfo = null;
        if (!lenderId.isEmpty()) {
            try {
                creditInfo = creditService.getTopCreditsByLenderId(lenderId, criteria);
                return new ResponseEntity<>(creditInfo, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'analystId' parameter is not on the path.");
        }
    }

    /** Method to get passed policy by request ID.
     * 
     * @param requestId
     *            unique ID of the request.
     * @return list of matched policies. */
    @GetMapping(value = "/checked/{requestId}")
    public ResponseEntity<PolicyMatch> getMatchPolicy(@PathVariable String requestId) {
        LOGGER.debug(" Got request ID in getMatchPolicy... " + requestId);

        PolicyMatch policyMatch = null;
        if (!requestId.isEmpty()) {
            try {
                policyMatch = creditService.getMatchPolicy(requestId);
                return new ResponseEntity<>(policyMatch, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'requestId' parameter is not on the path.");
        }
    }
    
    /** @return */
    @GetMapping(value = "/count")
    public ResponseEntity<Map<String, Integer>> getRequestCount() {
        LOGGER.info(" Inside getRequestCount... ");
        Map<String, Integer> requestCount = creditService.getRequestCount();
        return new ResponseEntity<Map<String, Integer>>(requestCount, HttpStatus.OK);
    }

}
