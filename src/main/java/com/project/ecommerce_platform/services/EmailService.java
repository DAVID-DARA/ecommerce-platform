package com.project.ecommerce_platform.services;


import com.mailersend.sdk.MailerSend;
import com.mailersend.sdk.MailerSendResponse;
import com.mailersend.sdk.Recipient;
import com.mailersend.sdk.emails.Email;
import com.mailersend.sdk.exceptions.MailerSendException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);


    public void sendRegistrationEmail(String to, String userName, int otp) {
        String otpText = Integer.toString(otp);

        Email email = new Email();
        email.setFrom("Dara", "info@domain.com");

        Recipient recipient = new Recipient(userName, to);
        email.AddRecipient(recipient);

        email.setTemplateId("0p7kx4xxv5849yjr");
        email.AddVariable("otp", otpText);
        email.AddVariable("name", userName);
        email.AddVariable("help_url", "google.com");

        MailerSend mailSender = new MailerSend();
        mailSender.setToken("mlsn.829f9d57130ee4864ebfe19b499b144d1b143b5f94c372a1239a40897404ad50");

        try {
            MailerSendResponse response = mailSender.emails().send(email);
            System.out.println(response.messageId);
        } catch (MailerSendException e) {
            logger.info("Failed to send email: {}", e.getMessage());
            System.out.println(e.getMessage());
        }
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

        message.setFrom("davidfamoyegun1@gmail.com");
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject(subject);
        message.setContent(finalEmailContent, "text/html; charset=utf-8");

        javaMailSender.send(message);
    }

    public void sendRegEmail(String to, String userName, int otp) throws MessagingException {
        jakarta.mail.internet.MimeMessage message = javaMailSender.createMimeMessage();

        String otpText = Integer.toString(otp);
        String subject = "Welcome to Our Service!";
        String emailTemplate = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title></title>\n" +
                "\n" +
                "    <!--[if !mso]><!-->\n" +
                "    <style type=\"text/css\">\n" +
                "@import url('https://fonts.mailersend.com/css?family=Inter:400,600');\n" +
                "    </style>\n" +
                "    <!--<![endif]-->\n" +
                "\n" +
                "    <style type=\"text/css\" rel=\"stylesheet\" media=\"all\">\n" +
                "@media only screen and (max-width: 640px) {\n" +
                "\n" +
                "        .ms-header {\n" +
                "            display: none !important;\n" +
                "        }\n" +
                "        .ms-content {\n" +
                "            width: 100% !important;\n" +
                "            border-radius: 0;\n" +
                "        }\n" +
                "        .ms-content-body {\n" +
                "            padding: 30px !important;\n" +
                "        }\n" +
                "        .ms-footer {\n" +
                "            width: 100% !important;\n" +
                "        }\n" +
                "        .mobile-wide {\n" +
                "            width: 100% !important;\n" +
                "        }\n" +
                "        .info-lg {\n" +
                "            padding: 30px;\n" +
                "        }\n" +
                "    }\n" +
                "    </style>\n" +
                "    <!--[if mso]>\n" +
                "        <style type=\"text/css\">\n" +
                "        body { font-family: Arial, Helvetica, sans-serif!important  !important; }\n" +
                "        td { font-family: Arial, Helvetica, sans-serif!important  !important; }\n" +
                "        td * { font-family: Arial, Helvetica, sans-serif!important  !important; }\n" +
                "        td p { font-family: Arial, Helvetica, sans-serif!important  !important; }\n" +
                "        td a { font-family: Arial, Helvetica, sans-serif!important  !important; }\n" +
                "        td span { font-family: Arial, Helvetica, sans-serif!important  !important; }\n" +
                "        td div { font-family: Arial, Helvetica, sans-serif!important  !important; }\n" +
                "        td ul li { font-family: Arial, Helvetica, sans-serif!important  !important; }\n" +
                "        td ol li { font-family: Arial, Helvetica, sans-serif!important  !important; }\n" +
                "        td blockquote { font-family: Arial, Helvetica, sans-serif!important  !important; }\n" +
                "        th * { font-family: Arial, Helvetica, sans-serif!important  !important; }\n" +
                "        </style>\n" +
                "        <![endif]-->\n" +
                "</head>\n" +
                "<body style=\"font-family:'Inter', Helvetica, Arial, sans-serif; width: 100% !important; height: 100%; margin: 0; padding: 0; -webkit-text-size-adjust: none; background-color: #f4f7fa; color: #4a5566;\">\n" +
                "\n" +
                "    <div class=\"preheader\" style=\"display:none !important;visibility:hidden;mso-hide:all;font-size:1px;line-height:1px;max-height:0;max-width:0;opacity:0;overflow:hidden;\"></div>\n" +
                "\n" +
                "    <table class=\"ms-body\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;background-color:#f4f7fa;width:100%;margin-top:0;margin-bottom:0;margin-right:0;margin-left:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">\n" +
                "        <tr>\n" +
                "            <td align=\"center\" style=\"word-break:break-word;font-family:'Inter', Helvetica, Arial, sans-serif;font-size:16px;line-height:24px;\">\n" +
                "\n" +
                "                <table class=\"ms-container\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;width:100%;margin-top:0;margin-bottom:0;margin-right:0;margin-left:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" style=\"word-break:break-word;font-family:'Inter', Helvetica, Arial, sans-serif;font-size:16px;line-height:24px;\">\n" +
                "\n" +
                "                            <table class=\"ms-header\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:collapse;\">\n" +
                "                                <tr>\n" +
                "                                    <td height=\"40\" style=\"font-size:0px;line-height:0px;word-break:break-word;font-family:'Inter', Helvetica, Arial, sans-serif;\">\n" +
                "                                        &nbsp;\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td align=\"center\" style=\"word-break:break-word;font-family:'Inter', Helvetica, Arial, sans-serif;font-size:16px;line-height:24px;\">\n" +
                "\n" +
                "                            <table class=\"ms-content\" width=\"640\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;width:640px;margin-top:0;margin-bottom:0;margin-right:auto;margin-left:auto;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;background-color:#FFFFFF;border-radius:6px;box-shadow:0 3px 6px 0 rgba(0,0,0,.05);\">\n" +
                "                                <tr>\n" +
                "                                    <td class=\"ms-content-body\" style=\"word-break:break-word;font-family:'Inter', Helvetica, Arial, sans-serif;font-size:16px;line-height:24px;padding-top:40px;padding-bottom:40px;padding-right:50px;padding-left:50px;\">\n" +
                "\n" +
                "                                        <p class=\"logo\" style=\"margin-right:0;margin-left:0;line-height:28px;font-weight:600;font-size:21px;color:#111111;text-align:center;margin-top:0;margin-bottom:40px;\">\n" +
                "                                            <span style=\"color:#0052e2;font-family:Arial, Helvetica, sans-serif;font-size:30px;vertical-align:bottom;\">❖&nbsp;</span>LOREM\n" +
                "                                        </p>\n" +
                "\n" +
                "                                        <h1 style=\"margin-top:0;color:#111111;font-size:24px;line-height:36px;font-weight:600;margin-bottom:24px;\">Welcome, {$name}!</h1>\n" +
                "                                        <p style=\"color:#4a5566;margin-top:20px;margin-bottom:20px;margin-right:0;margin-left:0;font-size:16px;line-height:28px;\">\n" +
                "                                            Thanks for trying LOREM Clothing Store. We’re thrilled to have you on board. To get the most out of LOREM, do this next step:\n" +
                "                                        </p>\n" +
                "\n" +
                "\n" +
                "                                        <p style=\"color:#4a5566;margin-top:20px;margin-bottom:20px;margin-right:0;margin-left:0;font-size:16px;line-height:28px;\">\n" +
                "                                            For reference, here's your otp for verification:\n" +
                "                                        </p>\n" +
                "\n" +
                "                                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;\">\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"info\" style=\"word-break:break-word;font-family:'Inter', Helvetica, Arial, sans-serif;font-size:16px;line-height:24px;padding-top:20px;padding-bottom:20px;padding-right:20px;padding-left:20px;border-radius:4px;background-color:#f4f7fa;\">\n" +
                "\n" +
                "                                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;\">\n" +
                "\n" +
                "                                                        <tr>\n" +
                "                                                            <td style=\"word-break:break-word;font-family:'Inter', Helvetica, Arial, sans-serif;font-size:16px;line-height:24px;\">\n" +
                "                                                                <strong style=\"font-weight:600;\">OTP:</strong> {$otp}\n" +
                "                                                            </td>\n" +
                "                                                        </tr>\n" +
                "                                                    </table>\n" +
                "\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "\n" +
                "                                        <p style=\"color:#4a5566;margin-top:20px;margin-bottom:20px;margin-right:0;margin-left:0;font-size:16px;line-height:28px;\">\n" +
                "                                            Cheers,\n" +
                "                                            <br>Dara and the LOREM Team\n" +
                "                                        </p>\n" +
                "\n" +
                "                                        <p style=\"color:#4a5566;margin-top:20px;margin-bottom:20px;margin-right:0;margin-left:0;font-size:16px;line-height:28px;\">\n" +
                "                                            <strong style=\"font-weight:600;\">P.S.</strong> Need help getting started? Check out our <a href=\"{$help_url}\" style=\"color:#0052e2;\">help documentation</a>.\n" +
                "                                        </p>\n" +
                "\n" +
                "                                        <table width=\"100%\" style=\"border-collapse:collapse;\">\n" +
                "                                           \n" +
                "                                            <tr>\n" +
                "                                                <td height=\"20\" style=\"font-size:0px;line-height:0px;border-top-width:1px;border-top-style:solid;border-top-color:#e2e8f0;word-break:break-word;font-family:'Inter', Helvetica, Arial, sans-serif;\">\n" +
                "                                                    &nbsp;\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                                <tr>\n" +
                "                                    <td align=\"center\" style=\"word-break:break-word;font-family:'Inter', Helvetica, Arial, sans-serif;font-size:16px;line-height:24px;\">\n" +
                "\n" +
                "                                        <table class=\"ms-footer\" width=\"640\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;width:640px;margin-top:0;margin-bottom:0;margin-right:auto;margin-left:auto;\">\n" +
                "                                            <tr>\n" +
                "                                                <td class=\"ms-content-body\" align=\"center\" style=\"word-break:break-word;font-family:'Inter', Helvetica, Arial, sans-serif;font-size:16px;line-height:24px;padding-top:40px;padding-bottom:40px;padding-right:50px;padding-left:50px;\">\n" +
                "                                                    <p class=\"small\" style=\"margin-top:20px;margin-bottom:20px;margin-right:0;margin-left:0;color:#96a2b3;font-size:14px;line-height:21px;\">\n" +
                "                                                        &copy; 2024 LOREM. All rights reserved.\n" +
                "                                                    </p>\n" +
                "                                                    <p class=\"small\" style=\"margin-top:20px;margin-bottom:20px;margin-right:0;margin-left:0;color:#96a2b3;font-size:14px;line-height:21px;\">\n" +
                "                                                        1234 Street Rd.\n" +
                "                                                        <br>Suite 1234\n" +
                "                                                        <br>City, State, ZIP Code\n" +
                "                                                    </p>\n" +
                "                                                </td>\n" +
                "                                            </tr>\n" +
                "                                        </table>\n" +
                "\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "\n" +
                "</body>\n" +
                "</html>";


        String finalEmailContent = emailTemplate.replace("{$name}", userName).replace("{$otp}", otpText).replace("{$help_url}", "google.com");

        message.setFrom("no-reply@lorem.com");
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject(subject);
        message.setContent(finalEmailContent, "text/html; charset=utf-8");

        javaMailSender.send(message);
    }
}
