package com.saidian;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;


/**
 * Created by wubo on 2016/12/1.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port:0")
public class ExampleControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Value("${local.server.port}")
    private int port;

    private RestTemplate restTemplate = new TestRestTemplate();


    @Before
    public void setupMockMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

 /*   @Test
    public void userController() {
        String result = restTemplate.getForObject("http://localhost:" + port + "/user/accountRegister", String.class);
        assertNotNull(result);
        System.out.println(result);

    }*/

    @Test
    public void btiemController() {
       // String result = restTemplate.getForObject("http://localhost:" + port + "/test", String.class);
        String result = restTemplate.getForObject("http://localhost:" + port + "/login/fastloginVerificationSend?account=18591960682", String.class);
        // String result = restTemplate.getForObject("http://localhost:" + port + "/order/test", String.class);
        assertNotNull(result);
        System.out.println("*******************************************************");
        System.out.println(result);
        System.out.println("*******************************************************");

    }


}