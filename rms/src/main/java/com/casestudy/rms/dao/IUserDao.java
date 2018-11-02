package com.casestudy.rms.dao;

import java.util.List;

import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.model.User;

/** Interface for UserDaoImpl class.
 * 
 * @author anand.tiwari */
public interface IUserDao {

    /** Method to persist user information.
     * 
     * @param user
     *            user object to store in database.
     * @return saved user object.
     * @throws DAOException
     *             exception wrapper. */
    User saveUser(User user);

    /** Method to get all users.
     * 
     * @return list of user objects. */
    List<User> getAllUser();

    /** Method to get user according to specified role.
     * 
     * @param role
     *            role for which user to find.
     * @return user object list according to role.
     * @throws ResourceNotFoundException
     *             occurs at when nothing found.
     * @throws DAOException
     */
    List<User> getAllUserByRoles(String role) throws DAOException;

    /** Method to get user according to user ID.
     * 
     * @param userId
     *            unique user id of the user.
     * @return object returned according to user ID.
     * @throws DAOException
     */
    User getUserById(String userId) throws DAOException;

    /** Method to remove user according to user ID.
     * 
     * @param userId
     *            object containing details of the user.
     * @return boolean true if deleted else false. */
    boolean deleteUser(User user);

    /** Method to update a user.
     * 
     * @param user
     *            new user data for update.
     * @return updated user object.
     * @throws DAOException
     *             exception throwing from DAO. */
    User updateUser(User user) throws DAOException;

    /** Method to get a user according to email id.
     * 
     * @param email
     *            email id of the user.
     * @return resultant user object.
     * @throws DAOException
     *             exception throwing from DAO. */
    User getUserByEmail(String email) throws DAOException;

    /** @param enabled
     * @return
     * @throws DAOException
     */
    List<User> getUsersByEnabled(int enabled) throws DAOException;

    /** @param user
     * @return
     * @throws DAOException
     */
    User activateBank(User user) throws DAOException;

}