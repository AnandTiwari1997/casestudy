package com.casestudy.rms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.rms.dto.AnalystDetailsResponse;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.exception.URIPathException;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.model.LenderAnalyst;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.IFAService;

/** Financial Analyst Controller for handling all the analyst related operations like adding, fetching lender and analyst. This controller is only for
 * LENDER and ANALYST.
 * 
 * @author Anand Tiwari */
@RestController
@RequestMapping()
public class FAController {

    @Autowired
    private IFAService faService;

    /** Static Initialization. */
    private static final Logger LOGGER = Logger.getLogger(FAController.class);

    /** Method to save financial analyst correspond to provided lender ID.
     * 
     * @param lenderAnalyst
     *            object containing details of lender and analyst ID's.
     * @return saved lenderAnalyst object. */
    @PostMapping(value = "lender/analyst")
    public ResponseEntity<LenderAnalyst> saveLAMapping(@RequestBody LenderAnalyst lenderAnalyst) {
        LOGGER.debug(" Got object in saveLAMapping.... " + lenderAnalyst);
        if (lenderAnalyst != null) {
            LenderAnalyst savedLenderAnalyst;
            try {
                savedLenderAnalyst = faService.saveLAMapping(lenderAnalyst);
                return new ResponseEntity<LenderAnalyst>(savedLenderAnalyst, HttpStatus.CREATED);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("Either Lender or Analyst ID is not provided.");
        }
    }

    /** Method to get all financial analyst correspond to provided lender ID.
     * 
     * @param lenderId
     *            lender's ID for which all registered analyst to find.
     * @return list of all object containing analyst's details. */
    @GetMapping(value = "/lender/{lenderId}/analysts")
    public ResponseEntity<List<AnalystDetailsResponse>> getAnalystByLenderId(@PathVariable String lenderId) {
        LOGGER.debug(" Got lender ID in getFAByLenderId... " + lenderId);
        if (!lenderId.isEmpty()) {
            List<AnalystDetailsResponse> faUsers;
            try {
                faUsers = faService.getAnalystByLenderId(lenderId);
                LOGGER.debug(faUsers);
                return new ResponseEntity<List<AnalystDetailsResponse>>(faUsers, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'lenderId' parameter is not on the path.");
        }
    }

    /** Method will find the lender corresponds to provided analyst ID.
     * 
     * @param analystId
     *            analyst's ID for which correspond lender to find.
     * @return object containing details of lender. */
    @GetMapping(value = "/analyst/{analystId}/lender")
    public ResponseEntity<User> getLenderByAnalystId(@PathVariable String analystId) {
        LOGGER.debug(" Got analyst ID in getFAByLenderId... " + analystId);
        User lenderUsers = null;
        if (!analystId.isEmpty()) {
            try {
                lenderUsers = faService.getLenderByAnalystId(analystId);
                LOGGER.debug(lenderUsers);
                return new ResponseEntity<User>(lenderUsers, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'analystId' parameter is not on the path.");
        }
    }

}
