package com.project.ecommerce_platform.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(text);
        javaMailSender.send(email);
    }

    public void sendWelcomeEmail(String to, String userName, int otp) throws MessagingException {
        jakarta.mail.internet.MimeMessage message = javaMailSender.createMimeMessage();


        String otpText = Integer.toString(otp);
        String subject = "Welcome to Our Service!";
        String text = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Your OTP Code</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            color: #333333;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .container {\n" +
                "            width: 100%;\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #ffffff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 8px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .header {\n" +
                "            text-align: center;\n" +
                "            padding: 10px 0;\n" +
                "        }\n" +
                "        .header img {\n" +
                "            width: 50px;\n" +
                "            height: 50px;\n" +
                "        }\n" +
                "        .content {\n" +
                "            margin: 20px 0;\n" +
                "        }\n" +
                "        .content h1 {\n" +
                "            font-size: 24px;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .content p {\n" +
                "            font-size: 16px;\n" +
                "            line-height: 1.5;\n" +
                "        }\n" +
                "        .otp {\n" +
                "            display: block;\n" +
                "            width: 100%;\n" +
                "            padding: 10px;\n" +
                "            font-size: 18px;\n" +
                "            text-align: center;\n" +
                "            background-color: #f9f9f9;\n" +
                "            border: 1px solid #dddddd;\n" +
                "            border-radius: 5px;\n" +
                "            margin: 20px 0;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            text-align: center;\n" +
                "            font-size: 14px;\n" +
                "            color: #777777;\n" +
                "            margin-top: 20px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <img src=\"logo.png\" alt=\"Company Logo\">\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <h1>Hello, ${username}!</h1>\n" +
                "            <p>We received a request to access your account. Use the OTP below to complete your login:</p>\n" +
                "            <div class=\"otp\">${otp}</div>\n" +
                "            <p>This OTP is valid for the next 10 minutes. If you did not request this code, please ignore this email.</p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>Thank you,<br>Your Company Team</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        String finalEmailContent = text.replace("${username}", userName).replace("${otp}", otpText);

        message.setFrom(new InternetAddress("test@gmail.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject(subject);
        message.setContent(finalEmailContent, "text/html; charset=utf-8");

        javaMailSender.send(message);
    }

}
