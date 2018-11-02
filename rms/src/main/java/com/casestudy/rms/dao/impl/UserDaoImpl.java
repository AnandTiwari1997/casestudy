package com.casestudy.rms.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.casestudy.rms.dao.IUserDao;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.model.User;

/** This UserDAO class will perform actual operation of adding, deleting and updating a user from database.
 * 
 * @author Anand Tiwari */
@Repository
public class UserDaoImpl implements IUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    /** Static Initializer. */
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    /** Method to persist user information.
     * 
     * @param user
     *            user object to store in database.
     * @return saved user object. */
    @Override
    public User saveUser(User user) {
        LOGGER.debug(" Saving object... " + user);
        entityManager.persist(user);
        return user;
    }

    /** Method to get all users.
     * 
     * @return list of user objects. */
    @Override
    public List<User> getAllUser() {
        LOGGER.debug(" Getting All Objects...");
        String hql = "FROM User";
        return (List<User>) entityManager.createQuery(hql).getResultList();
    }

    /** Method to get user according to specified role.
     * 
     * @param role
     *            role for which user to find.
     * @return user object list according to role.
     * @throws DAOException
     * @throws ResourceNotFoundException
     */
    @Override
    public List<User> getAllUserByRoles(String role) throws DAOException {
        LOGGER.debug(" Getting User by role... " + role);
        try {
            String hql = "FROM User WHERE role = ?1";
            return (List<User>) entityManager.createQuery(hql).setParameter(1, role).getResultList();
        } catch (NoResultException noResultException) {
            LOGGER.error(noResultException);
            throw new DAOException(noResultException.getMessage(), noResultException);
        }
    }

    /** Method to get user according to user ID.
     * 
     * @param userId
     *            unique user id of the user.
     * @return object returned according to user ID.
     * @throws DAOException
     */
    @Override
    public User getUserById(String userId) throws DAOException {
        User user = null;
        LOGGER.debug(" Getting User by ID... " + userId);
        try {
            String hql = "FROM User WHERE user_id=?1";
            Query query = entityManager.createQuery(hql).setParameter(1, userId);
            user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException noResultException) {
            LOGGER.error(noResultException);
            throw new DAOException("No User exist.", noResultException);
        }
    }

    /** Method to remove user according to user ID.
     * 
     * @param user
     *            User object.
     * @return boolean true if deleted else false. */
    @Override
    public boolean deleteUser(User user) {
        LOGGER.debug(" Deleting User... " + user);
        if (user != null) {
            entityManager.remove(user);
            return true;
        }
        return false;
    }

    /** Method to update a user.
     * 
     * @param user
     *            new user data for update.
     * @return updated user object.
     * @throws DAOException
     */
    @Override
    public User updateUser(User user) throws DAOException {
        LOGGER.debug(" Updating object... " + user);
        User userNew = getUserById(user.getUserId());
        LOGGER.info("Now Updating...");
        if (user.getLastLogin() != null)
            userNew.setLastLogin(user.getLastLogin());
        userNew.setAddress(user.getAddress());
        userNew.setContactno(user.getContactno());
        entityManager.flush();
        return userNew;
    }

    /** Method to get a user according to email id.
     * 
     * @param email
     *            email id of the user.
     * @return resultant user object.
     * @throws DAOException
     */
    @Override
    public User getUserByEmail(String email) throws DAOException {
        LOGGER.debug(" Getting User by email... " + email);
        User user = null;
        try {
            String hql = "FROM User WHERE email=?1";
            Query query = entityManager.createQuery(hql).setParameter(1, email);
            user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException noResultException) {
            LOGGER.error(noResultException);
            throw new DAOException(noResultException.getMessage(), noResultException);
        }
    }

    @Override
    public List<User> getUsersByEnabled(int enabled) throws DAOException {
        LOGGER.debug(" Getting User by enabled... " + enabled);
        try {
            String hql = "FROM User WHERE enabled=?1";
            Query query = entityManager.createQuery(hql).setParameter(1, enabled);
            return (List<User>) query.getResultList();
        } catch (NoResultException noResultException) {
            LOGGER.error(noResultException);
            throw new DAOException(noResultException.getMessage(), noResultException);
        }
    }

    @Override
    public User activateBank(User user) throws DAOException {
        LOGGER.debug(" Activating Bank... " + user.getName());
        User newUser = getUserById(user.getUserId());
        newUser.setEnabled(user.getEnabled());
        newUser.setLastLogin(user.getLastLogin());
        entityManager.flush();
        return newUser;
    }

}
