package com.casestudy.rms.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/** Class for storing the user information and sending it to client.
 * 
 * @author Anand Tiwari */
public class MyUserDetails implements UserDetails {

    /** Serial Version ID. */
    private static final long serialVersionUID = 1L;

    private String userId;
    private String email;
    private String name;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    /** Parameterized Constructor with 4 arguments.
     * 
     * @param userId
     *            unique ID of the user.
     * @param name
     *            name of the user
     * @param email
     *            email ID of the user.
     * @param password
     *            password of the user.
     * @param authorities
     *            role provided to user. */
    public MyUserDetails(String userId, String name, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this(userId, name, email, password, true, true, true, true, authorities);
    }

    /** Parameterized Constructor with 8 arguments.
     * 
     * @param userId
     *            unique ID of the user.
     * @param name
     *            name of the user
     * @param email
     *            email ID of the user.
     * @param password
     *            password of the user.
     * @param accountNonExpired
     *            account expired or not.
     * @param accountNonLocked
     *            account locked or not.
     * @param credentialsNonExpired
     *            credential expired or not.
     * @param enabled
     *            is user enabled or not.
     * @param authorities
     *            role provided to user. */
    public MyUserDetails(String userId, String name, String email, String password, boolean accountNonExpired, boolean accountNonLocked,
            boolean credentialsNonExpired, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    /** Getter for userId.
     * 
     * @return userId unique ID. */
    public String getUserId() {
        return userId;
    }

    /** Getter for name.
     * 
     * @return name of the user. */
    public String getName() {
        return name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
