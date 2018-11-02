package com.casestudy.rms.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.casestudy.rms.dto.ErrorDetail;

/** Application Authentication Entry Point.
 * 
 * @author anand.tiwari */
@Component
public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    /** Static Initializer. */
    private static final Logger LOGGER = Logger.getLogger(CustomAuthenticationEntryPoint.class);

    @Autowired
    private Jackson2JsonObjectMapper jackson2JsonObjectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        LOGGER.debug("INSDE COMMENCE");
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        ErrorDetail errorDetail = new ErrorDetail(new Date(), HttpStatus.UNAUTHORIZED.toString(), HttpStatus.UNAUTHORIZED, authException.getMessage());

        try {
            String errorInJson = jackson2JsonObjectMapper.toJson(errorDetail);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().write(errorInJson);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("End COMMENCE");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.debug("INSDE AFTERSET");
        setRealmName("MY APP REALM");
        LOGGER.debug("END AFTERSET");
    }
}
