package com.casestudy.rms.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.exception.URIPathException;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.service.IPolicyService;

/** Policy Controller for handling all the policy related operations like adding, reading, deleting and updating policies. This controller is only for
 * LENDER and ANALYST.
 * 
 * @author Anand Tiwari */
@RestController
@RequestMapping(value = "/policies")
public class PolicyController {

    @Autowired
    private IPolicyService policyService;

    /** Static Initializer. */
    private static final Logger LOGGER = Logger.getLogger(PolicyController.class);

    /** Method to save the policy defined by the lender.
     * 
     * @param policy
     *            policy object containing policy information.
     * @return saved policy object. */
    @PostMapping
    public ResponseEntity<Policy> savePolicy(@RequestBody Policy policy) {
        LOGGER.debug(" Got object in savePolicy... " + policy);
        Policy newPolicy = null;
        if (policy != null) {
            newPolicy = policyService.savePolicy(policy);
            return new ResponseEntity<Policy>(newPolicy, HttpStatus.CREATED);
        } else {
            throw new URIPathException("Policy details are empty.");
        }
    }

    /** Method to delete policy using policy ID.
     * 
     * @param policyId
     *            unique ID for the policy.
     * @return boolean true if deleted else false. */
    @DeleteMapping(value = "/{policyId}")
    public ResponseEntity<Boolean> deletePolicy(@PathVariable String policyId) {
        LOGGER.debug(" Got policy ID in deletePolicy... " + policyId);
        Boolean result = false;
        if (!policyId.isEmpty()) {
            try {
                result = policyService.deletePolicy(policyId);
                return new ResponseEntity<Boolean>(result, HttpStatus.NO_CONTENT);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'policyId' paramater is not on the path.");
        }
    }

    /** Method will find policy according to lenders ID.
     * 
     * @param lenderId
     *            unique ID for the lender whose policy required.
     * @return returned policy object. */
    @GetMapping(value = "/lender/{lenderId}")
    public ResponseEntity<Policy> getPolicyByLenderId(@PathVariable String lenderId) {
        LOGGER.debug(" Got lender ID in getPolicyByLenderId... " + lenderId);
        Policy policy = null;
        if (!lenderId.isEmpty()) {
            try {
                policy = policyService.getPolicyByLenderId(lenderId);
                return new ResponseEntity<Policy>(policy, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'lenId' paramater is not on the path.");
        }
    }

    /** Method to get policy on the basis of policy ID.
     * 
     * @param policyId
     *            unique ID for the policy.
     * @return returned policy object. */
    @GetMapping(value = "/{policyId}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable String policyId) {
        LOGGER.debug(" Got policy ID in getPolicyById... " + policyId);
        Policy policy = null;
        if (!policyId.isEmpty()) {
            try {
                policy = policyService.getPolicyById(policyId);
                return new ResponseEntity<Policy>(policy, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'policyId' paramater is not on the path.");
        }
    }

    /** Method to update policies information.
     * 
     * @param policy
     *            policy object containing updated information.
     * @return updated policy object. */
    @PutMapping
    public ResponseEntity<Policy> updatePolicy(@RequestBody Policy policy) {
        LOGGER.debug(" Got object in updatePolicy... " + policy);
        Policy updatepolicy = null;
        if (policy != null) {
            try {
                updatepolicy = policyService.updatePolicy(policy);
                return new ResponseEntity<Policy>(updatepolicy, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("Policy details are empty.");
        }
    }
}
