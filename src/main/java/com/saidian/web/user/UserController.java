package com.saidian.web.user;

import com.google.common.base.Strings;
import com.saidian.bean.ReqOrigin;
import com.saidian.bean.ResultBean;
import com.saidian.bean.ValidType;
import com.saidian.web.bean.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/1/5.
 */
@RequestMapping(value = "user")
@Controller
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 当前url：http://localhost:8080/CarsiLogCenter_new/idpstat.jsp?action=idp.sptopn
     * <p>
     * request.getRequestURL() http://localhost:8080/CarsiLogCenter_new/idpstat.jsp
     * request.getRequestURI() /CarsiLogCenter_new/idpstat.jsp
     * request.getContextPath()/CarsiLogCenter_new
     * request.getServletPath() /idpstat.jsp
     * <p>
     * request.getQueryString()action=idp.sptopn
     */
    @RequestMapping(value = "/index")
    public String index(HttpSession httpSession, HttpServletRequest httpServletRequest, ModelMap map) throws Exception {
        User user = (User) httpSession.getAttribute("user");
        if (null == user) {
            map.addAttribute("back_url", httpServletRequest.getRequestURI() + (Strings.isNullOrEmpty(httpServletRequest.getQueryString()) ? "" : "?" + httpServletRequest.getQueryString()));
            return "login/index";
        }
        ResultBean resultBean = userService.findUserInfo(user.getUid());

        JSONObject userinfo = new JSONObject(resultBean.getData());

        httpSession.setAttribute("userinfo", userinfo);

        map.addAttribute("userinfo", userinfo);

        String name = userinfo.getString("telephone");
        if (userinfo.has("realName"))
            name = userinfo.getString("realName");
        map.addAttribute("name", name);
        return "user/index";
    }

    @RequestMapping(value = "/personalprofile")
    public String personalprofile(HttpSession httpSession, ModelMap map) throws Exception {

        JSONObject jsonObject = new JSONObject(httpSession.getAttribute("userinfo").toString());

        map.addAttribute("userinfo",jsonObject);

        String name = jsonObject.getString("telephone");
        if (jsonObject.has("realName"))
            name = jsonObject.getString("realName");
        map.addAttribute("name", name);

        if (jsonObject.has("nickName")) {
            map.addAttribute("nickName", jsonObject.getString("nickName"));
        }
        map.addAttribute("nickName", "");
        return "user/personalprofile";
    }


    @ResponseBody
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
