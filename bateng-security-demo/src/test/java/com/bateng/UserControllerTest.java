package com.bateng;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;




    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void querySuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                           .contentType(MediaType.APPLICATION_JSON_UTF8)
                          .param("username","fjl")
                        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void query1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"));
    }

    @Test
    public void add() throws Exception {

        String s = mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"username\":\"fjl\",\"password\":null,\"birthday\":"+new Date().getTime()+"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)).andReturn().getResponse().getContentAsString();
        System.out.println(s);
    }

    @Test
    public void t(){
        List<String> list = Arrays.asList("a","B","c");
        list.forEach(x -> {
//            System.out.println(x);
        });


        System.out.println(new BCryptPasswordEncoder().encode("123456"));



    }

    @Test
    public void update() throws Exception {
        String body="{\"username\":\"test\",\"password\":9870}";
        mockMvc.perform(MockMvcRequestBuilders.put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(body))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void del() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void fileSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file").file(new MockMultipartFile("file","test.txt","multipart/form-data","abc".getBytes())))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }




}
