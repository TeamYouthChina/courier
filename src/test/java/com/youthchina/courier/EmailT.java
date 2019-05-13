package com.youthchina.courier;

import com.youthchina.courier.dto.EmailSendingDTO;
import com.youthchina.courier.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.htmlunit.UrlRegexRequestMatcher;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: courier
 * @description: 邮件测试
 * @author: Qinghong Wang
 * @create: 2019-05-04 15:25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailT {

    @Autowired
    private MailService mailService;

    @Test
    public void testRegister() throws Exception{
        System.out.println("123123213");
        com.youthchina.courier.domain.MailResult mailResult = new com.youthchina.courier.domain.MailResult();
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("to", "hmgswqh@gmail.com");
        valueMap.put("subject", "注册邮箱");
        valueMap.put("firstname", "王");
        valueMap.put("lastname","清弘");
        valueMap.put("address","http://localhost:8080/api/v1/applicants/register/verify/email?token=");
        mailService.sendUserRegisterEmail(valueMap);

    }
    @Test
    public void testResume() throws Exception{
        EmailSendingDTO emailSendingDTO=new EmailSendingDTO();
        emailSendingDTO.setFileName("111");
        emailSendingDTO.setFirstName("清弘");
        emailSendingDTO.setLastName("王");
        URL url = new URL("https://www.bates.edu/purposeful-work/files/2019/03/resume-guide-fy19.pdf");
        emailSendingDTO.setUrl(url);
        emailSendingDTO.setJobName("backend");
        emailSendingDTO.setHrEmail("hmgswqh@gmail.com");
        emailSendingDTO.setOwnEmail("hmgswqh@gmail.com");
        mailService.sendResumeEmail(emailSendingDTO);
    }

    @Test
    public void testStudentSure() throws Exception{
        EmailSendingDTO emailSendingDTO=new EmailSendingDTO();
        emailSendingDTO.setFileName("111");
        emailSendingDTO.setFirstName("清弘");
        emailSendingDTO.setLastName("王");
        URL url = new URL("https://www.bates.edu/purposeful-work/files/2019/03/resume-guide-fy19.pdf");
        emailSendingDTO.setUrl(url);
        emailSendingDTO.setJobName("后端开发");
        emailSendingDTO.setHrEmail("hmgswqh@gmail.com");
        emailSendingDTO.setOwnEmail("hmgswqh@gmail.com");
        mailService.sendSimpleMail(emailSendingDTO);

    }

}
