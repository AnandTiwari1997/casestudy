package com.casestudy.rms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.casestudy.rms.dao.IFADao;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.model.LenderAnalyst;

/** This FADAO class will perform actual operation of adding, and retrieving entities information from len_fa table in database.
 * 
 * @author Anand Tiwari */
@Repository
public class FADaoImpl implements IFADao {

    @PersistenceContext
    private EntityManager entityManager;

    /** Static Initialization. */
    private static final Logger LOGGER = Logger.getLogger(FADaoImpl.class);

    /** Method to get all financial analyst correspond to provided lender ID.
     * 
     * @param lenderId
     *            lender's ID for which all registered analyst to find.
     * @return list of all object containing analyst's details.
     * @throws DAOException
     */
    @Override
    public List<LenderAnalyst> getAnalystsIdByLenderId(String lenderId) {
        LOGGER.debug(" Getting Objects by lender ID... " + lenderId);
        String hql = "FROM LenderAnalyst WHERE lender_id=:lenderId";
        return (List<LenderAnalyst>) entityManager.createQuery(hql).setParameter("lenderId", lenderId).getResultList();
    }

    /** Method will find the lender corresponds to provided analyst ID.
     * 
     * @param analystId
     *            analyst's ID for which correspond lender to find.
     * @return object containing details of lender.
     * @throws DAOException
     */
    @Override
    public LenderAnalyst getLenderIdByAnalystId(String analystId) throws DAOException {
        LOGGER.debug(" Getting Objects by analyst ID... " + analystId);
        try {
            String hql = "FROM LenderAnalyst WHERE fa_id=:fa_id";
            return (LenderAnalyst) entityManager.createQuery(hql).setParameter("fa_id", analystId).getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error(e);
            throw new DAOException("No Lender found.");
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
        LOGGER.debug(" Saving Objects... " + lenderAnalyst);
        entityManager.persist(lenderAnalyst);
        return lenderAnalyst;
    }

}
