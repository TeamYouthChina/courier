package com.youthchina.courier.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.youthchina.courier.DTO.EmailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            Map<String,Object> valueMap=new HashMap<>();
            String UPLOAD_FOLDER="/Users/wangqinghong/Desktop/";
            writeBytesToFile(emailDTO.getBytes(), UPLOAD_FOLDER + "resume.pdf");
            valueMap.put("to",emailDTO.getCompany_email());
            valueMap.put("id",emailDTO.getUser_id());
            String filePath = "/Users/wangqinghong/Desktop/resume.pdf";
            mailService.sendAttachmentsMail("hmgswqh@gmail.com", "test attachment mail", "hello this is a attachment mail", filePath);


        }catch (Exception e){
            System.out.println(e);
        }




    }
    private static void writeBytesToFile(byte[] bFile, String fileDest) {

        FileOutputStream fileOuputStream = null;

        try {
            fileOuputStream = new FileOutputStream(fileDest);
            fileOuputStream.write(bFile);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOuputStream != null) {
                try {
                    fileOuputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
