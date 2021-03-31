package com.bateng;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
@RestController
//@EnableSwagger2
public class DemoApplication {
    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class,args);

        /*try {
            ServerSocket server = new ServerSocket(8081,1);
            Socket socket = server.accept();
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
             int i=0;
             while(i<inputStream.available()){
                 bs.write(inputStream.read());
             }
            System.out.println(bs.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }


    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }
}
