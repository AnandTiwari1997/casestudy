package com.casestudy.rms.config;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.casestudy.rms.dao.IUserDao;
import com.casestudy.rms.exception.DAOException;
import com.casestudy.rms.utils.MyUserDetails;

/** Service class for checking the existence of user.
 * 
 * @author anand.tiwari */
@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserDao userDAO;

    /** Static Initializer. */
    private static final Logger LOGGER = Logger.getLogger(ApplicationUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        LOGGER.debug(" username for request... " + userName);
        try {
            com.casestudy.rms.model.User activeUserInfo = userDAO.getUserByEmail(userName);
            if (activeUserInfo.getEnabled() == 0) {
                throw new UsernameNotFoundException("Account not activated");
            }
            GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());

            return new MyUserDetails(activeUserInfo.getUserId(), activeUserInfo.getName(), activeUserInfo.getEmail(), activeUserInfo.getPassword(),
                    Arrays.asList(authority));
        } catch (DAOException e) {
            throw new UsernameNotFoundException("Invalid Credentials.");
        }
    }
}
