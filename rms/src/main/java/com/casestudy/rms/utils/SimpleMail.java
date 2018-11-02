package com.casestudy.rms.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/** The Class SimpleMailController has logic to send simple email. */
@Component
public class SimpleMail {

    /** The sender. */
    @Autowired
    private JavaMailSender sender;

    /** Static Initialization. */
    private static final Logger LOGGER = Logger.getLogger(SimpleMail.class);

    /** Send mail.
     *
     * @param reciever
     *            the emailId of reciever
     * @param subject
     *            the subject of email
     * @param body
     *            the body of email */
    public void sendMail(String reciever, String subject, String body) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper;

        try {

            helper = new MimeMessageHelper(message);
            // mail of the recipient
            helper.setTo(reciever);

            // Subject of the mail
            helper.setSubject(subject);

            // Body of the mail
            helper.setText(body);
            
            sender.send(message);

            LOGGER.info("Mail Sent Success!");

        } catch (MessagingException e) {
            LOGGER.error(e + ": some error occured while sending mail");
        }

    }

}
