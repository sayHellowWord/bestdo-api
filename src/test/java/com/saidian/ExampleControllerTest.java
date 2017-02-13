package com.saidian;


import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
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

import java.security.NoSuchAlgorithmException;


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
        //String result = restTemplate.getForObject("http://localhost:" + port + "/site/toOneDayMerItemPrice?" +
        //"mer_item_id=10200001000731&mer_price_id=1429&day=2017-01-14", String.class);
        //  String result = restTemplate.getForObject("http://localhost:" + port + "/order/detail", String.class);
        String result = restTemplate.getForObject("http://localhost:" + port + "/order/orderListsSearch?status=9", String.class);
        // assertNotNull(result);
        System.out.println("*******************************************************");
        System.out.println(result);
        System.out.println("*******************************************************");

    }

    @Test
    public void testPay() throws NoSuchAlgorithmException {
        String result = restTemplate.getForObject("http://localhost:" + port + "/pay/test", String.class);
        System.out.println("*******************************************************");
        System.out.println(result);
        System.out.println("*******************************************************");
    }

    @Test
    public void test() {
        DateTime createTimeDateTime = new DateTime(2017, 02, 11, 16, 43, 55);
        DateTime currentTimeDateTime = new DateTime(2017, 02, 11, 16, 53, 31);
        int diffMinutes = 0;
        int diffSeconds = Seconds.secondsBetween(createTimeDateTime, currentTimeDateTime).getSeconds();
        System.out.println(diffSeconds);
        if (diffSeconds < 15 * 60) {//小于30秒 才让倒计时不然太快没意义（减少网络传输误差）
            diffMinutes = diffSeconds / 60;
            diffSeconds = diffSeconds % 60;
            System.out.println(diffMinutes + ":" + diffSeconds);
        } else {
        }
    }


}