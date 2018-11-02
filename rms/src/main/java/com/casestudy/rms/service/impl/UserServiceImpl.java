package com.casestudy.rms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.casestudy.rms.dao.IPolicyDao;
import com.casestudy.rms.dao.IUserDao;
import com.casestudy.rms.dto.UserResponse;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.exception.ResourceConflictException;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.model.Policy;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.IUserService;
import com.casestudy.rms.utils.ApplicationConstant;
import com.casestudy.rms.utils.SimpleMail;

/** User Service will perform operations like encoding the user passwords and send data to DAO for persisting the user.
 * 
 * @author Anand Tiwari */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private SimpleMail simpleMail;

    @Autowired
    private IPolicyDao policyDao;

    /** Static Initializer. */
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    /** Method to save user information.
     * 
     * @param user
     *            user object to store in database.
     * @return saved user object.
     * @throws ResourceConflictException
     */
    @Override
    public User saveUser(User user) throws ResourceConflictException {
        LOGGER.debug(" Got object in saveUser... " + user);
        User existedUser = null;
        try {
            existedUser = userDao.getUserByEmail(user.getEmail());
        } catch (DAOException e) {
            LOGGER.error(e);
        }

        if (existedUser != null) {
            throw new ResourceConflictException("User already Exist.");
        } else {
            user.setUserId(UUID.randomUUID().toString());
            String password = user.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encryptPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptPass);

            if (user.getRole().equalsIgnoreCase(ApplicationConstant.ROLE_LENDER)) {
                user.setEnabled(0);
            } else {
                user.setEnabled(1);
            }

            long millis = System.currentTimeMillis();
            user.setLastLogin((new Date(millis)));
            User savedUser = userDao.saveUser(user);
            if (savedUser != null && savedUser.getRole().equals(ApplicationConstant.ROLE_ANALYST)) {
                String body = "You are successfully registered with us.\n\n UserName :" + savedUser.getEmail() + " \n\n Password :" + password;
                simpleMail.sendMail(savedUser.getEmail(), "Registered With RMS", body);
            } else if (savedUser != null && savedUser.getRole().equals(ApplicationConstant.ROLE_BORROWER)) {
                String body = "You are successfully registered with us.";
                simpleMail.sendMail(savedUser.getEmail(), "Registered With RMS", body);
            }
            return savedUser;
        }
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
    public List<UserResponse> getAllUserByRole(String role) {
        LOGGER.debug(" Got role in getAllUserByRole... " + role);
        List<UserResponse> userResponses = new ArrayList<>();
        try {
            List<User> users = userDao.getAllUserByRoles(role);
            for (User user : users) {
                Policy policy = null;
                UserResponse userResponse = new UserResponse();
                userResponse.setUserId(user.getUserId());
                userResponse.setName(user.getName());
                userResponse.setEmail(user.getEmail());
                userResponse.setRole(user.getRole());
                userResponse.setPassword(user.getPassword());
                userResponse.setContactno(user.getContactno());
                userResponse.setAddress(user.getAddress());
                userResponse.setLastLogin(user.getLastLogin());
                if (user.getRole().equalsIgnoreCase(ApplicationConstant.ROLE_LENDER)) {
                    policy = policyDao.getPolicyByLenderId(user.getUserId());
                }
                if (policy != null) {
                    userResponse.setPolicy(policy);
                }
                userResponses.add(userResponse);
            }
            if (userResponses.isEmpty()) {
                throw new ResourceNotFoundException("No user for specified role.");
            }
            return userResponses;
        } catch (DAOException e) {
            throw new ResourceNotFoundException("No user for specified role.");
        }
    }

    /** Method to get user according to user ID.
     * 
     * @param userId
     *            unique user ID of the user.
     * @return object returned according to user ID. */
    @Override
    public User getUserById(String userId) {
        LOGGER.debug(" Got user ID in getUserById... " + userId);
        try {
            return userDao.getUserById(userId);
        } catch (DAOException e) {
            throw new ResourceNotFoundException("No User Exist.");
        }
    }

    /** Method to get a user according to email id.
     * 
     * @param email
     *            email id of the user.
     * @return resultant user object. */
    @Override
    public User getUserByEmail(String email) {
        LOGGER.debug(" Got email in getUserByEmail... " + email);
        try {
            return userDao.getUserByEmail(email);
        } catch (DAOException e) {
            throw new ResourceNotFoundException("No User associate with this email.");
        }
    }

    /** Method to remove user according to user ID.
     * 
     * @param userId
     *            user ID of the user.
     * @return boolean true if deleted else false. */
    @Override
    public boolean deleteUser(String userId) {
        LOGGER.debug(" Got user ID in deleteUser... " + userId);
        try {
            User user = userDao.getUserById(userId);
            return userDao.deleteUser(user);
        } catch (DAOException e) {
            throw new ResourceNotFoundException("No User to delete");
        }
    }

    /** Method to get all users.
     * 
     * @return list of user objects. */
    @Override
    public List<User> getAllUser() {
        LOGGER.info(" Inside getAllUser... ");
        return userDao.getAllUser();

    }

    /** Method to update a user.
     * 
     * @param user
     *            new user data for update.
     * @return updated user object. */
    @Override
    public User updateUser(User user) {
        LOGGER.debug(" Got object in updateUser... " + user);
        try {
            return userDao.updateUser(user);
        } catch (DAOException daoException) {
            LOGGER.error(daoException);
            throw new ResourceNotFoundException("No user to update");
        }
    }

    /** Method will fetch either activated or deactivated users.
     * 
     * @param enabled
     *            integer value denoting activated or deactivated user.
     * @return list of users. */
    @Override
    public List<User> getUsersByEnabled(int enabled) {
        LOGGER.debug(" Got enabled in getUsersByEnabled... " + enabled);
        try {
            List<User> users = userDao.getUsersByEnabled(enabled);
            if (users.isEmpty())
                throw new ResourceNotFoundException("No banks to activate.");
            return users;
        } catch (DAOException e) {
            throw new ResourceNotFoundException("No banks to activate.");
        }
    }

    /** Method will activate a bank.
     * 
     * @param user
     *            user to activate.
     * @return activated user. */
    @Override
    public User activateBank(User user) {
        LOGGER.debug(" Activating Bank... " + user.getName());
        try {
            user.setEnabled(1);
            long millis = System.currentTimeMillis();
            user.setLastLogin((new Date(millis)));
            return userDao.activateBank(user);
        } catch (DAOException e) {
            throw new ResourceNotFoundException("Unable to activate Bank.");
        }
    }

    public Map<String, Integer> getUserCount() {
        LOGGER.info(" Inside getUserCount... ");
        String[] roles = new String[] { "ROLE_LENDER", "ROLE_BORROWER", "ROLE_ANALYST" };
        Map<String, Integer> userCount = new HashMap<>();
        try {
            for (String role : roles) {
                List<User> list = userDao.getAllUserByRoles(role);
                userCount.put(role, list.size());
            }
        } catch (DAOException e) {
            throw new ResourceNotFoundException("Unable to get count.");
        }
        return userCount;
    }

}
