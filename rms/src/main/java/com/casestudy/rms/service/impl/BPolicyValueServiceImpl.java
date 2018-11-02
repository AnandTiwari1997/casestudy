package com.casestudy.rms.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.rms.dao.IBPolicyValueDao;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.model.BPolicyValue;
import com.casestudy.rms.service.IBPolicyValueService;

/** This service class will use BPolicyValueDao for saving, reading, deleting, and updating the property or assets information of borrower.
 * 
 * @author Anand Tiwari */
@Service
@Transactional
public class BPolicyValueServiceImpl implements IBPolicyValueService {

    @Autowired
    private IBPolicyValueDao bPolicyValueDao;

    /** Static Initializer. */
    private static final Logger LOGGER = Logger.getLogger(BPolicyValueServiceImpl.class);

    /** Method will save the information provided by the borrower against each policy.
     * 
     * @param bPolicyValue
     *            bPolicyValue object containing information provided by borrower.
     * @return saved bPolicyValue object. */
    @Override
    public BPolicyValue addPolicyValues(BPolicyValue bPolicyValue) {
        LOGGER.debug(" Got object in addPolicyValues...  " + bPolicyValue);
        long millis = System.currentTimeMillis();
        bPolicyValue.setLastmodified(new Date(millis));
        return bPolicyValueDao.addPolicyValues(bPolicyValue);
    }

    /** Method will update the information provided by the borrower against each policy.
     * 
     * @param bPolicyValue
     *            bPolicyValue object containing updated information provided by borrower.
     * @return updated bPolicyValue object. 
     * @throws DAOException */
    @Override
    public BPolicyValue updatePolicyValues(BPolicyValue bPolicyValue) throws DAOException {
        LOGGER.debug(" Got object in updatepolicy... " + bPolicyValue);
        try {
        long millis = System.currentTimeMillis();
        bPolicyValue.setLastmodified(new Date(millis));
        return bPolicyValueDao.updatePolicyValues(bPolicyValue);
        } catch (Exception e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    /** Method will find the policies information according to provided borrower ID.
     * 
     * @param borrowerId
     *            borrower's ID whose policy value are needed.
     * @return bPolicyValue object. 
     * @throws DAOException */
    @Override
    public BPolicyValue getPolicyByBorrowerId(String borrowerId) {
        LOGGER.debug(" Got borrowerId in getPolicyByBorrowerId.. " + borrowerId);
        BPolicyValue bPolicyValue = bPolicyValueDao.getPolicyByBorrowerId(borrowerId);
        if (bPolicyValue == null) {
            throw new ResourceNotFoundException("No Policy added by this borrower.");
        }
        return bPolicyValue;
    }

}
