package com.youthchina.courier.service;

import com.youthchina.courier.dto.EmailSendingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: email
 * @description: 对于mailservice的实现
 * @author: Qinghong Wang
 * @create: 2019-01-27 15:27
 **/
@Service
@Component
public class MailServiceImpl implements MailService {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Resource
    private TemplateEngine templateEngine;

    @Value("noreply@weyouth.co")
    private String from;

    @Override
    public void sendSimpleMail(EmailSendingDTO emailSendingDTO) {
        Map<String,Object> valueMap=new HashMap<>();
        valueMap.put("to",emailSendingDTO.getHrEmail());
        valueMap.put("resume",emailSendingDTO.getUrl());
        valueMap.put("ownEmail",emailSendingDTO.getOwnEmail());
        valueMap.put("firstName",emailSendingDTO.getFirstName());
        valueMap.put("lastName",emailSendingDTO.getLastName());
        valueMap.put("jobName",emailSendingDTO.getJobName());
        valueMap.put("hrEmail",emailSendingDTO.getHrEmail());
        MimeMessage message=javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);

            helper.setTo(valueMap.get("to").toString());

            helper.setSubject("申请确认邮件");

            Context context=new Context();
            context.setVariables(valueMap);
            String content=templateEngine.process("studentSure",context);
            helper.setText(content,true);
            logger.info("带附件的邮件已发送");
            javaMailSender.send(message);
        }catch(MessagingException e){
            logger.error("发送带附件的邮件时发送异常",e);

        }


    }

    @Override
    public void sendtemplateMail(String to, String subject, String content) {
        MimeMessage message=javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            javaMailSender.send(message);
            logger.info("html邮件发送成功");
        }catch (MessagingException e){
            logger.error("发送html邮件发生异常！",e);
        }
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message=javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            FileSystemResource file=new FileSystemResource(new File(filePath));
            String fileName=file.getFilename();
            helper.addAttachment(fileName,file);

            logger.info("带附件的邮件已发送");
            javaMailSender.send(message);
        }catch(MessagingException e){
            logger.error("发送带附件的邮件时发送异常",e);

        }
    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message=javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            FileSystemResource res=new FileSystemResource(new File(rscPath));
            helper.addInline(rscId,res);
            logger.info("带附件的邮件已发送");
            javaMailSender.send(message);
        }catch(MessagingException e){
            logger.error("发送带附件的邮件时发送异常",e);

        }
    }

    @Override
    public void sendUserRegisterEmail(Map<String, Object> valueMap) {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(from);
            helper.setTo(valueMap.get("to").toString());
            helper.setSubject(valueMap.get("subject").toString());
            Context context=new Context();
            context.setVariables(valueMap);
            String content=templateEngine.process("registerEmail",context);
            helper.setText(content,true);
            javaMailSender.send(mimeMessage);
            System.out.println("注册验证邮件已发送");
        }catch (MessagingException e){
            logger.error("发送邮件异常");
        }

    }

    @Override
    public void sendResumeEmail(EmailSendingDTO emailSendingDTO) {
        Map<String,Object> valueMap=new HashMap<>();
        valueMap.put("to",emailSendingDTO.getHrEmail());
        valueMap.put("subject",emailSendingDTO.getJobName()+"-"+emailSendingDTO.getLastName()+emailSendingDTO.getFirstName());
        valueMap.put("resume",emailSendingDTO.getUrl());
        valueMap.put("ownEmail",emailSendingDTO.getOwnEmail());
        MimeMessage message=javaMailSender.createMimeMessage();
        try{
                MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);

            helper.setTo(valueMap.get("to").toString());

            helper.setSubject(valueMap.get("subject").toString());


            helper.addAttachment(emailSendingDTO.getFileName(),new URLDataSource(emailSendingDTO.getUrl()));


            Context context=new Context();
            context.setVariables(valueMap);
            String content=templateEngine.process("emailtemplates",context);
            helper.setText(content,true);
            logger.info("带附件的邮件已发送");
            javaMailSender.send(message);
        }catch(MessagingException e){
            logger.error("发送带附件的邮件时发送异常",e);

        }
    }
}

