package com.merin.userService.util.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpServiceUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    private final Random random = new Random();

    public String generateOtp() {
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("MERIN Bank's OTP Code");
        message.setText("Hi User," +
                "Below is your OTP code: " + otp+". " +
                "Kindly, please do not share this OTP with anyone." +
                "" +
                "Regards," +
                "Merin Team");
        javaMailSender.send(message);
    }
}
