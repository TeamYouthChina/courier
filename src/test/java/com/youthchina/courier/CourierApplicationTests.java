package com.youthchina.courier;

import com.youthchina.courier.service.EmailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourierApplicationTests {
    @Autowired
    private EmailSender emailSender;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void contextLoads() throws IOException, ClassNotFoundException {
//        File file = new File("/Users/dreamer/Documents/Resume/Jinhao_resume.pdf");
//        InputStream inputStream = new FileInputStream(file);
//        byte[] filestring = new byte[inputStream.available()];
//        m.put("to",(Object)filestring);
        emailSender.send("222");
    }

//    public Object toObject (byte[] bytes) {
//        Object obj = null;
//        try {
//            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
//            ByteArrayInputStream bis = new ByteArrayInputStream (packet.getData());
//            ObjectInputStream ois = new ObjectInputStream (bis);
//            obj = ois.readObject();
//            ois.close();
//            bis.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return obj;
//    }
}
