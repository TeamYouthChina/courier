package com.youthchina.courier.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.courier.dto.EmailDTO;
import com.youthchina.courier.dto.VerifyEmailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhongyangwu on 2/21/19.
 */
@Component
@RabbitListener(queues = "email")
public class EmailRequestReceiver {
    @Autowired
    private MailService mailService;

    @RabbitHandler
    public void onReceived(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            EmailDTO emailDTO = mapper.readValue(message, EmailDTO.class);
            Map<String, Object> valueMap = new HashMap<>();
            String UPLOAD_FOLDER = "/home/zhongyangwu/code/youthchina/courier/resume.pdf";
            File file = writeBytesToFile(emailDTO.getBytes(), UPLOAD_FOLDER);
            valueMap.put("to", "yihao_guo@gwu.edu");
            valueMap.put("id", 1);
            mailService.sendResumeEmail(valueMap, file);


        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @RabbitHandler
    public void receivedVerifyEmail(VerifyEmailDTO verifyEmailDTO) {

    }

    private static File writeBytesToFile(byte[] b, String outputFile) {
        File ret = null;
        BufferedOutputStream stream = null;
        try {
            ret = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(ret);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            // log.error("helper:get file from byte process error!");
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // log.error("helper:get file from byte process error!");
                    e.printStackTrace();
                }
            }
        }
        return ret;

    }
}
