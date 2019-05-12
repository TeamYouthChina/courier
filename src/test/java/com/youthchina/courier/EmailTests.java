package com.youthchina.courier;

import com.youthchina.courier.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

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
public class EmailTests {

    @Autowired
    private MailService mailService;

    @Test
    public void testRegister() throws Exception{
        System.out.println("123123213");
        com.youthchina.courier.domain.MailResult mailResult = new com.youthchina.courier.domain.MailResult();
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("to", "zhongyang.wu@outlook.com");
        valueMap.put("subject", "注册邮箱");
        valueMap.put("username", "hmgswqh");
        mailService.sendUserRegisterEmail(valueMap);

    }

}