package com.casestudy.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.casestudy.rms.dao.ICreditDao;
import com.casestudy.rms.dao.IFADao;
import com.casestudy.rms.dao.IUserDao;
import com.casestudy.rms.dto.AnalystDetailsResponse;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.model.Credit;
import com.casestudy.rms.model.LenderAnalyst;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.IFAService;
import com.casestudy.rms.utils.ApplicationConstant;

/** This service class will communicate with FADao to store, and retrieve the lender and it's corresponding analyst.
 * 
 * @author Anand Tiwari */
@Service
@Transactional
public class FAServiceImpl implements IFAService {

    @Autowired
    private IFADao faDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ICreditDao creditDao;

    /** Static Initialization. */
    private static final Logger LOGGER = Logger.getLogger(FAServiceImpl.class);

    /** Method to get all financial analyst correspond to provided lender ID.
     * 
     * @param lenderId
     *            lender's ID for which all registered analyst to find.
     * @return list of all object containing analyst's details.
     * @throws DAOException
     */
    @Override
    public List<AnalystDetailsResponse> getAnalystByLenderId(String lenderId) {
        LOGGER.debug(" Got lender ID in getAnalystByLenderId... " + lenderId);
        List<AnalystDetailsResponse> analystDetailsResponses = new ArrayList<>();

        try {
            // checking user exist or not.
            userDao.getUserById(lenderId);

            // get the id's of analyst corresponding to lender id.
            List<LenderAnalyst> analysts = faDao.getAnalystsIdByLenderId(lenderId);

            if (analysts.isEmpty())
                throw new ResourceNotFoundException("No Analyst Added.");

            // loop through each id and get their details from the user table.
            for (LenderAnalyst analyst : analysts) {
                LOGGER.debug(analyst);
                User user = userDao.getUserById(analyst.getAnalystId());
                List<Credit> credits = creditDao.getCreditByAnalystId(user.getUserId());
                int inProgress = 0;
                int pending = 0;
                int approved = 0;
                int rejected = 0;
                for (Credit credit : credits) {

                    if (credit.getStatus().equalsIgnoreCase("INPROGRESS"))
                        inProgress++;
                    if (credit.getStatus().equalsIgnoreCase(ApplicationConstant.Status.PENDING.toString()))
                        pending++;
                    if (credit.getStatus().equalsIgnoreCase(ApplicationConstant.Status.APPROVED.toString()))
                        approved++;
                    if (credit.getStatus().equalsIgnoreCase(ApplicationConstant.Status.REJECTED.toString()))
                        rejected++;

                }
                AnalystDetailsResponse analystDetailsResponse = new AnalystDetailsResponse();
                analystDetailsResponse.setUserId(user.getUserId());
                analystDetailsResponse.setName(user.getName());
                analystDetailsResponse.setEmail(user.getEmail());
                analystDetailsResponse.setContactno(user.getContactno());
                analystDetailsResponse.setAddress(user.getAddress());
                analystDetailsResponse.setRole(user.getRole());
                analystDetailsResponse.setNoOfPendingRequest(pending);
                analystDetailsResponse.setNoOfApprovedRequest(approved);
                analystDetailsResponse.setNoOfInProgressRequest(inProgress);
                analystDetailsResponse.setNoOfRejectedRequest(rejected);

                analystDetailsResponses.add(analystDetailsResponse);

            }
            return analystDetailsResponses;
        } catch (DAOException daoException) {
            throw new ResourceNotFoundException(daoException.getMessage());
        }
    }

    /** Method will find the lender corresponds to provided analyst ID.
     * 
     * @param analystId
     *            analyst's ID for which correspond lender to find.
     * @return object containing details of lender.
     * @throws DAOException
     */
    @Override
    public User getLenderByAnalystId(String analystId) {
        LOGGER.debug(" Got analyst ID in getLenderByAnalystId... " + analystId);

        LenderAnalyst lenderAnalyst = null;
        try {
            // checking user exist or not.
            userDao.getUserById(analystId);

            // get the id of lender corresponding to analyst id
            lenderAnalyst = faDao.getLenderIdByAnalystId(analystId);

            // get the detail of that lender from user table.
            return userDao.getUserById(lenderAnalyst.getLenderId());
        } catch (DAOException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    /** Method to save financial analyst correspond to provided lender ID.
     * 
     * @param lenderAnalyst
     *            object containing details of lender and analyst ID's.
     * @return saved lenderAnalyst object.
     * @throws DAOException
     */
    @Override
    public LenderAnalyst saveLAMapping(LenderAnalyst lenderAnalyst) {
        LOGGER.debug(" Got object in saveLAMapping... " + lenderAnalyst);
        List<LenderAnalyst> analysts = faDao.getAnalystsIdByLenderId(lenderAnalyst.getLenderId());
        if (analysts.isEmpty()) {
            return faDao.saveLAMapping(lenderAnalyst);
        } else {
            throw new ResourceNotFoundException("Analyst already Exist");
        }
    }

}
