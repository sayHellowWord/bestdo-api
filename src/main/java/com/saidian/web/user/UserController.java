package com.saidian.web.user;

import com.saidian.bean.ReqOrigin;
import com.saidian.bean.ValidType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/1/5.
 */
@RequestMapping(value = "user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "accountRegister")
    public String accountRegister() throws Exception {
        //userService.accountRegister("15810045437", "", "", "000000", "TELEPHONE");
        //userService.accountLogin("15810045437","000000");
        //userService.accountModifyPassword("0h1170105151026m47","000000","000000");
        System.out.println(ValidType.TEL_CODE.toString());
        userService.securityVerificationSend("0h1170105151026m47", ValidType.TEL_CODE.toString(), "15810045436", "", ReqOrigin.FIND_PASSWORD.toString(), "找回密码");
        return "user";
    }

}
