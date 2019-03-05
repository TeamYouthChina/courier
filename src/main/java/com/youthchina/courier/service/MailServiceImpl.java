package com.youthchina.courier.service;

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
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
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

    @Autowired
    private TemplateEngine templateEngine;

    @Value("hmgswqh@gmail.com")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        System.out.print("111");

        try {
            javaMailSender.send(simpleMailMessage);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
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

        }catch (MessagingException e){
            logger.error("发送邮件异常");
        }

    }

    @Override
    public void sendResumeEmail(Map<String, Object> valueMap, MultipartFile file) {
        MimeMessage message=javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(valueMap.get("to").toString());
            helper.setSubject(valueMap.get("subject").toString());
            helper.setText("hi",true);
            String fileName=file.getName();
            helper.addAttachment(fileName,file);
            logger.info("带附件的邮件已发送");
            javaMailSender.send(message);
        }catch(MessagingException e){
            logger.error("发送带附件的邮件时发送异常",e);

        }
    }
}

