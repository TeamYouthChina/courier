package com.youthchina.courier;

import com.youthchina.courier.dto.VerifyEmailDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhongyangwu on 5/6/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailRequestReceiverTest {


    @Test
    public void testMessageConverter() {

    }
}

//@Component
//@RabbitListener(queues = "email")
//class EmailRequestTestReceiver {
//
//    @RabbitHandler
//    public void handleVerifyEmailDTO(VerifyEmailDTO verifyEmailDTO) {
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!===============================" + verifyEmailDTO.getCode());
//    }
//}
