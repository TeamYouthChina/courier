package com.youthchina.courier.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by zhongyangwu on 2/21/19.
 */
@Component
@RabbitListener(queues = "hello")
public class EmailRequestReceiver extends MessageListenerAdapter {

    @RabbitHandler
    public void onReceived(String m) throws IOException {
        System.out.println("m");
//        byte[] bytes = toByteArray(o);
//
//        File file2 = new File("/Users/dreamer/Documents/6234/NFLB.pdf");
//        InputStream inputStream = new FileInputStream(file2);
//        byte[] filestring = new byte[inputStream.available()];
//
//        if(Arrays.equals(filestring,bytes)){
//            System.out.println("!!!!");
//        }
    }
    public byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
}
