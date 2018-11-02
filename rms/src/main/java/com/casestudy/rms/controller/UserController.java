package com.casestudy.rms.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.rms.dto.ErrorDetail;
import com.casestudy.rms.dto.UserResponse;
import com.casestudy.rms.exception.ResourceConflictException;
import com.casestudy.rms.exception.ResourceNotFoundException;
import com.casestudy.rms.exception.URIPathException;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.IUserService;

/** User Controller for handling all the user related operations like adding, reading, deleting and updating user. This controller is for all user
 * including ADMIN, LENDER, BORROWER, ANALYST.
 * 
 * @author Anand Tiwari */

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private IUserService userService;

    /** Static Initializer. */
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    /** Method to save user.
     * 
     * @param user
     *            user object containing user information.
     * @return saved user object. */
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        LOGGER.debug(" Got object in saveUser... " + user);
        User newUser = null;
        if (user.getEmail() != null) {
            try {
                newUser = userService.saveUser(user);
                return new ResponseEntity<>(newUser, HttpStatus.CREATED);
            } catch (ResourceConflictException e) {
                ErrorDetail detail = new ErrorDetail(new Date(), HttpStatus.CONFLICT.toString(), HttpStatus.CONFLICT, e.getMessage());
                return new ResponseEntity<>(detail, HttpStatus.CONFLICT);
            }
        } else {
            throw new URIPathException("User details are empty.");
        }
    }

    /** Method to get all users.
     * 
     * @return list of user objects. */
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        LOGGER.info(" Inside getAllUser... ");
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    /** Method to get user according to specified role.
     * 
     * @param role
     *            role for which user to find.
     * @return user object list according to role. */
    @GetMapping(value = "/role")
    public ResponseEntity<List<UserResponse>> getAllUserByRole(@RequestParam String role) {
        LOGGER.debug(" Got role in getAllUserByRole... " + role);
        List<UserResponse> userResponses = null;
        if (role != "") {
            try {
                userResponses = userService.getAllUserByRole(role);
                return new ResponseEntity<>(userResponses, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'role' parameter mut not be null or empty.");
        }
    }

    /** Method to get a user according to email ID.
     * 
     * @param email
     *            email ID of the user.
     * @return resultant user object. */
    @GetMapping(value = "/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        LOGGER.debug(" Got email in getUserByEmail... " + email);
        User user = null;
        if (email != "") {
            try {
                user = userService.getUserByEmail(email);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("The 'email' parameter mut not be null or empty.");
        }

    }

    /** Method to get user according to user ID.
     * 
     * @param userId
     *            unique user ID of the user.
     * @return object returned according to user ID. */
    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        LOGGER.debug(" Got user ID in getUserById... " + userId);
        if (!userId.isEmpty()) {
            try {
                User user = userService.getUserById(userId);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (ResourceNotFoundException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        } else {
            throw new URIPathException("userId not found on the path.");
        }
    }

    /** Method to remove user according to user ID.
     * 
     * @param userId
     *            user ID of the user.
     * @return boolean true if deleted else false. */
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String userId) {
        LOGGER.debug(" Got user ID in deleteUser... " + userId);
        Boolean result = false;
        if (!userId.isEmpty()) {
            try {
                result = userService.deleteUser(userId);
                return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
            } catch (ResourceNotFoundException exception) {
                throw new ResourceNotFoundException(exception.getMessage());
            }
        } else {
            throw new URIPathException("The 'userId' not on the path.");
        }

    }

    /** Method to update a user.
     * 
     * @param user
     *            new user data for update.
     * @return updated user object. */
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        LOGGER.debug(" Got object in updateUser... " + user);
        User updateuser = null;
        if (user.getUserId() != "") {
            try {
                updateuser = userService.updateUser(user);
                return new ResponseEntity<>(updateuser, HttpStatus.OK);
            } catch (ResourceNotFoundException exception) {
                throw new ResourceNotFoundException(exception.getMessage());
            }
        } else {
            throw new URIPathException("User details are empty.");
        }
    }

    /** @param enabled
     *            integer value denoting activated or deactivated user.
     * @return list of users. */
    @GetMapping(value = "/enabled")
    public ResponseEntity<List<User>> getUsersByEnabled(@RequestParam int enabled) {
        LOGGER.debug(" Got enabled in getUserByEnabled... " + enabled);
        try {
            List<User> users = userService.getUsersByEnabled(enabled);
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } catch (ResourceNotFoundException exception) {
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }

    /** @param user
     *            bank to activate.
     * @return activated bank. */
    @PutMapping(value = "/activate")
    public ResponseEntity<User> activateBank(@RequestBody User user) {
        LOGGER.debug(" Activating bank... " + user.getName());
        if (user.getEmail() != null) {
            try {
                User activatedUser = userService.activateBank(user);
                return new ResponseEntity<User>(activatedUser, HttpStatus.OK);
            } catch (ResourceNotFoundException exception) {
                throw new ResourceNotFoundException(exception.getMessage());
            }
        } else {
            throw new URIPathException("Bank details are empty.");
        }
    }

    /** @return */
    @GetMapping(value = "/count")
    public ResponseEntity<Map<String, Integer>> getUserCount() {
        LOGGER.info(" Inside getUserCount... ");
        Map<String, Integer> userCount = userService.getUserCount();
        return new ResponseEntity<Map<String, Integer>>(userCount, HttpStatus.OK);
    }
}
