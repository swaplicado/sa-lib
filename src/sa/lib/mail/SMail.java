/*
 * ToRecipients change this template, choose ToRecipientsols | Templates
 * and open the template in the editor.
 */

package sa.lib.mail;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 *
 * @author Sergio Flores, Edwin Carmona
 */
public class SMail {

    protected SMailSender moSender;
    protected String msSubject;
    protected String msBody;
    protected String msContentType;
    protected Date mtSentDate;
    protected ArrayList<String> maToRecipients;
    protected ArrayList<String> maCcRecipients;
    protected ArrayList<String> maBccRecipients;
    protected ArrayList<File> maAttachments;
    protected Map<String, String> mpInlineImages;

    /**
     * @param sender Mail sender.
     * @param subject Mail subject. Subject text can be UTF-8 encoded, if needed, with helper method SMailUtils.encodeSubjectUtf8().
     * @param body Mail body (it is assumed that content type is plain text).
     * @param toRecipients Mail recipients list.
     */
    public SMail(SMailSender sender, String subject, String body, ArrayList<String> toRecipients) {
        this(sender, subject, body, "", toRecipients, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * @param sender Mail sender.
     * @param subject Mail subject. Subject text can be UTF-8 encoded, if needed, with helper method SMailUtils.encodeSubjectUtf8().
     * @param body Mail body (it is assumed that content type is plain text).
     * @param toRecipients Mail recipients list.
     * @param ccRecipients Mail carbon copy recipients list.
     */
    public SMail(SMailSender sender, String subject, String body, ArrayList<String> toRecipients, ArrayList<String> ccRecipients) {
        this(sender, subject, body, "", toRecipients, ccRecipients, new ArrayList<>());
    }

    /**
     * @param sender Mail sender.
     * @param subject Mail subject. Subject text can be UTF-8 encoded, if needed, with helper method SMailUtils.encodeSubjectUtf8().
     * @param body Mail body (it is assumed that content type is plain text).
     * @param toRecipients Mail recipients list.
     * @param ccRecipients Mail carbon copy recipients list.
     * @param bccRecipients Mail blind carbon copy recipients list.
     */
    public SMail(SMailSender sender, String subject, String body, ArrayList<String> toRecipients, ArrayList<String> ccRecipients, ArrayList<String> bccRecipients) {
        this(sender, subject, body, "", toRecipients, ccRecipients, bccRecipients);
    }

    /**
     * @param sender Mail sender.
     * @param subject Mail subject. Subject text can be UTF-8 encoded, if needed, with helper method SMailUtils.encodeSubjectUtf8().
     * @param body Mail body (it is assumed that content type is plain text).
     * @param contentType  Mail content type (Constants of options defined in class sa.lib.mail.SMailConsts).
     * @param toRecipients Mail recipients list.
     * @param ccRecipients Mail carbon copy recipients list.
     * @param bccRecipients Mail blind carbon copy recipients list.
     */
    public SMail(SMailSender sender, String subject, String body, String contentType, ArrayList<String> toRecipients, ArrayList<String> ccRecipients, ArrayList<String> bccRecipients) {
        moSender = sender;
        msSubject = subject;
        msBody = body;
        msContentType = contentType;
        mtSentDate = null;
        maToRecipients = toRecipients;
        maCcRecipients = ccRecipients;
        maBccRecipients = bccRecipients;
        maAttachments = new ArrayList<>();
        mpInlineImages = new HashMap<>();
    }

    public void setSender(SMailSender o) { moSender = o; }
    public void setSubject(String s) { msSubject = s; }
    public void setBody(String s) { msBody = s; }
    public void setContentType(String s) { msContentType = s; }
    public void setSentDate(Date t) { mtSentDate = t; }

    public SMailSender getSender() { return moSender; }
    public String getSubject() { return msSubject; }
    public String getBody() { return msBody; }
    public String getContentType() { return msContentType; }
    public ArrayList<String> getToRecipients() { return maToRecipients; }
    public ArrayList<String> getCcRecipients() { return maCcRecipients; }
    public ArrayList<String> getBccRecipients() { return maBccRecipients; }
    public ArrayList<File> getAttachments() { return maAttachments; }
    public Map<String, String> getInlineImages() { return mpInlineImages; }

    public void send() throws MessagingException {
        BodyPart bodyPart = null;
        MimeMultipart mimeMultipart = null;
        MimeMessage mimeMessage = null;
        Transport transport = null;

        // Attachments:

        mimeMultipart = new MimeMultipart("related");

        for (int i = 0; i < maAttachments.size(); i++) {
            BodyPart attachment = new MimeBodyPart();

            attachment.setDataHandler(new DataHandler(new FileDataSource(maAttachments.get(i))));
            attachment.setFileName(maAttachments.get(i).getName());

            mimeMultipart.addBodyPart(attachment);
        }

        // Body:

        bodyPart = new MimeBodyPart();

        if (msContentType.isEmpty()) {
            bodyPart.setText(msBody);
        }
        else {
            bodyPart.setContent(msBody, msContentType);
        }

        mimeMultipart.addBodyPart(bodyPart);
        
        // adds inline image attachments
        if (mpInlineImages != null && mpInlineImages.size() > 0) {
            Set<String> setImageID = mpInlineImages.keySet();

            for (String contentId : setImageID) {
                MimeBodyPart imagePart = new MimeBodyPart();
                imagePart.setHeader("Content-ID", "<" + contentId + ">");
                imagePart.setDisposition(MimeBodyPart.INLINE);
                String imageFilePath = mpInlineImages.get(contentId);
                try {
                    imagePart.attachFile(imageFilePath);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }

                mimeMultipart.addBodyPart(imagePart);
            }
        }

        // Message:

        mimeMessage = new MimeMessage(moSender.getSession());
        mimeMessage.setFrom(new InternetAddress(moSender.getMailFrom()));
        mimeMessage.setSubject(msSubject);
        if (this.mtSentDate != null) {
            mimeMessage.setSentDate(this.mtSentDate);
        }

        if (msContentType.isEmpty()) {
            mimeMessage.setContent(mimeMultipart);
        }
        else {
            mimeMessage.setContent(mimeMultipart, msContentType);
        }

        for (int i = 0; i < maToRecipients.size(); i++) {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress((String) maToRecipients.get(i)));
        }

        for (int i = 0; i < maCcRecipients.size(); i++) {
            mimeMessage.addRecipient(Message.RecipientType.CC, new InternetAddress((String) maCcRecipients.get(i)));
        }

        for (int i = 0; i < maBccRecipients.size(); i++) {
            mimeMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress((String) maBccRecipients.get(i)));
        }
        
        if (moSender.getMailReplyTo() != null && ! moSender.getMailReplyTo().isEmpty()) {
            mimeMessage.setReplyTo(InternetAddress.parse(moSender.getMailReplyTo(), false));
            try {
                mimeMessage.setFrom(new InternetAddress(MimeUtility.encodeText(moSender.getMailReplyTo() + "<" + moSender.getMailReplyTo() + ">")));
                mimeMessage.setSender(new InternetAddress(moSender.getMailReplyTo()));
            }
            catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SMail.class.getName()).log(Level.SEVERE, null, ex);
                mimeMessage.setFrom(new InternetAddress(moSender.getMailFrom()));
            }
        }

        // Sending:

        transport = moSender.getSession().getTransport(moSender.getProtocol());
        transport.connect(moSender.getMailUser(), moSender.getMailPassword());
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();  // allways close the connection
    }
}
