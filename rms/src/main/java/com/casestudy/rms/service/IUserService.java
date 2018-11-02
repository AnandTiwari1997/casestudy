package com.casestudy.rms.service;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.annotation.Secured;

import com.casestudy.rms.dto.UserResponse;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.exception.ResourceConflictException;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.model.User;

/** Interface for UserService class.
 * 
 * @author Anand Tiwari */
public interface IUserService {

    /** Method to save user information.
     * 
     * @param user
     *            user object to store in database.
     * @return saved user object.
     * @throws ResourceConflictException
     *             entity is present. */
    // @Secured({ "ROLE_ADMIN", "ROLE_BORROWER", "ROLE_LENDER" })
    User saveUser(User user) throws ResourceConflictException;

    /** Method to get user according to specified role.
     * 
     * @param role
     *            role for which user to find.
     * @return user object list according to role.
     * @throws ResourceNotFoundException
     *             resource not found.
     * @throws DAOException
     */
    @Secured({ "ROLE_ADMIN", "ROLE_LENDER", "ROLE_BORROWER" })
    List<UserResponse> getAllUserByRole(String role) throws ResourceNotFoundException;

    /** Method to get user according to user ID.
     * 
     * @param userId
     *            unique user ID of the user.
     * @return object returned according to user ID. */
    @Secured({ "ROLE_ADMIN", "ROLE_BORROWER", "ROLE_LENDER", "ROLE_ANALYST" })
    User getUserById(String userId);

    /** Method to get a user according to email id.
     * 
     * @param email
     *            email id of the user.
     * @return resultant user object. */
    @Secured({ "ROLE_ADMIN", "ROLE_BORROWER", "ROLE_LENDER", "ROLE_ANALYST" })
    User getUserByEmail(String email);

    /** Method will remove user according to user ID.
     * 
     * @param userId
     *            user ID of the user.
     * @return boolean true if deleted else false. */
    @Secured({ "ROLE_ADMIN", "ROLE_LENDER" })
    boolean deleteUser(String userId);

    /** Method to get all users.
     * 
     * @return list of user objects. */
    @Secured({ "ROLE_ADMIN" })
    List<User> getAllUser();

    /** Method to update a user.
     * 
     * @param user
     *            new user data for update.
     * @return updated user object. */
    @Secured({ "ROLE_ADMIN", "ROLE_BORROWER", "ROLE_LENDER", "ROLE_ANALYST" })
    User updateUser(User user);

    /** @param enabled
     * @return */
    @Secured({ "ROLE_ADMIN" })
    List<User> getUsersByEnabled(int enabled);

    /** @param user
     * @return */
    @Secured({ "ROLE_ADMIN" })
    User activateBank(User user);

    /** @return */
    @Secured({ "ROLE_ADMIN" })
    Map<String, Integer> getUserCount();

}