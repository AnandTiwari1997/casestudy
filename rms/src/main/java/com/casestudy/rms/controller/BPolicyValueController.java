package com.casestudy.rms.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.exception.URIPathException;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.model.BPolicyValue;
import com.casestudy.rms.service.IBPolicyValueService;

/** Borrowers Policy Value Controller will handles all the operation like saving, reading, deleting, and updating the property or assets information.
 * This controller is only for BORROWER.
 * 
 * @author Anand Tiwari */
@RestController
@RequestMapping(value = "/bdetails")
public class BPolicyValueController {

    @Autowired
    private IBPolicyValueService bPolicyValueServiceImpl;

    /** Static Initializer. */
    private static final Logger LOGGER = Logger.getLogger(BPolicyValueController.class);

    /** Method will save the information provided by the borrower against each policy.
     * 
     * @param bPolicyValue
     *            bPolicyValue object containing information provided by borrower.
     * @return saved bPolicyValue object. */
    @PostMapping
    public ResponseEntity<BPolicyValue> addPolicyValues(@RequestBody BPolicyValue bPolicyValue) {
        LOGGER.debug(" Got object in addPolicyValues... " + bPolicyValue);
        if (bPolicyValue != null) {
            BPolicyValue newPolicy = bPolicyValueServiceImpl.addPolicyValues(bPolicyValue);
            LOGGER.debug("Saved Value.. " + newPolicy);
            return new ResponseEntity<BPolicyValue>(newPolicy, HttpStatus.CREATED);
        } else {
            throw new URIPathException("Policies Values are empty");
        }
    }

    /** Method will update the information provided by the borrower against each policy.
     * 
     * @param bPolicyValue
     *            bPolicyValue object containing updated information provided by borrower.
     * @return updated bPolicyValue object. */
    @PutMapping
    public ResponseEntity<BPolicyValue> updatePolicyValues(@RequestBody BPolicyValue bPolicyValue) {
        LOGGER.debug(" Got object in updatePolicyValues... " + bPolicyValue);
        if (bPolicyValue != null) {
            try {
                BPolicyValue newPolicy = bPolicyValueServiceImpl.updatePolicyValues(bPolicyValue);
                LOGGER.debug("updated Value.. " + newPolicy);
                return new ResponseEntity<BPolicyValue>(newPolicy, HttpStatus.CREATED);
            } catch (DAOException e) {
                throw new ResourceNotFoundException("No Values to update.");
            }
        } else {
            throw new URIPathException("Policies Values are empty");
        }
    }

    /** Method will find the policies information according to provided borrower ID.
     * 
     * @param borrowerId
     *            borrower's ID whose policy value are needed.
     * @return bPolicyValue object. */
    @GetMapping(value = "/{borrowerId}")
    public ResponseEntity<BPolicyValue> getPolicyByBorrowerId(@PathVariable String borrowerId) {
        LOGGER.debug(" Got borrowerId in getPolicyByBorrowerId... " + borrowerId);
        if (borrowerId != null) {
            BPolicyValue newPolicy;
            try {
                newPolicy = bPolicyValueServiceImpl.getPolicyByBorrowerId(borrowerId);
                LOGGER.debug("returned Value.. " + newPolicy);
                return new ResponseEntity<BPolicyValue>(newPolicy, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage(), e);
            }
        } else {
            throw new URIPathException("The 'borrowerId' is not on the path.");
        }
    }

}
