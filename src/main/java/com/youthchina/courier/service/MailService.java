package com.youthchina.courier.service;


import com.youthchina.courier.dto.EmailSendingDTO;

import java.io.File;
import java.util.Map;

public interface MailService {

    public void sendSimpleMail(EmailSendingDTO emailSendingDTO);
    public void sendtemplateMail(String to, String subject, String content);
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
    public void sendUserRegisterEmail(Map<String, Object> valueMap);
    public void sendResumeEmail(EmailSendingDTO emailSendingDTO);
}
