package com.casestudy.rms.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.rms.dto.ErrorDetail;
import com.casestudy.rms.exception.ResourceConflictException;
import com.casestudy.rms.exception.URIPathException;
import com.casestudy.rms.model.User;
import com.casestudy.rms.service.IUserService;

/** Account Controller for handling login action.
 * 
 * @author anand.tiwari */
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    /** Static Initializer. */
    private static final Logger LOGGER = Logger.getLogger(AccountController.class);

    @Autowired
    private IUserService userService;

    /** Method will register a user.
     * 
     * @param user
     *            User object containing the information about user.
     * @return saved User Object. */
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody User user) {
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

    /** Method will execute and return principle if login success.
     * 
     * @param principal
     *            Principle object containing user details.
     * @return Principle object containing user details. */
    @GetMapping(value = "/login")
    public Principal loginUser(Principal principal) {
        LOGGER.debug("LoggedIn User... " + principal.getName());
        User user = userService.getUserByEmail(principal.getName());
        user.setLastLogin(new Date(System.currentTimeMillis()));
        userService.updateUser(user);
        return principal;
    }

    /** Logout Method.
     * 
     * @param session
     *            HttpSession object. */
    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        LOGGER.info("Logging Out...");
        session.invalidate();
    }

}
