package com.saidian.web.controller;

import com.saidian.config.AccessServices;
import com.saidian.utils.HttpUtil;
import com.saidian.web.platform.PlatformService;
import com.saidian.web.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/1/4.
 */
@Controller
public class PlatformController {

    private static String AUTH_URL = "config/v1/api/service/getKeys";

    @Autowired
    UserService userService;

    @Autowired
    PlatformService platformService;

    @RequestMapping(value = "test")
    public void test() throws Exception {
       /* String data = "{'internalIp':'10.14.22.65','internalPort':'8080','externalIp':'10.14.22.65','externalPort':'8080' }";

        System.out.println(HttpUtil.doPost(HttpParams.url + AUTH_URL, data, new String(DESedeUtil.Key)));
*/
       /* try {
            ObjectMapper objectMapper = new ObjectMapper();
            resultBean = objectMapper.readValue(HttpUtil.doPost(AUTH_URL, data, new String(DESedeUtil.Key)), new TypeReference<ResultBean>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }*/

       /* String uuu = AccessServices.USER_SERVICE_URL + "/uccore/v1/api/account/validLoginRegister";

        String uudata = "{'telephone':'15810045436','validId':'1111','validCode':'1111'}";

        System.out.println(HttpUtil.doPost(uuu, uudata, AccessServices.accessServices.get("04").getSercertKey()));
*/
       // platformService.regionGetChildren("230");
    }

}
