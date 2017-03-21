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
 * 用户相关
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

        String name = userinfo.getString("loginTel");
        if (userinfo.has("realName"))
            name = userinfo.getString("realName");
        map.addAttribute("name", name);
        return "user/index";
    }

    @RequestMapping(value = "/personalprofile")
    public String personalprofile(HttpSession httpSession, ModelMap map) throws Exception {
        JSONObject jsonObject = new JSONObject(httpSession.getAttribute("userinfo").toString());
        map.addAttribute("userinfo", jsonObject);
        String name = jsonObject.getString("loginTel");
        if (jsonObject.has("realName")){
            name = jsonObject.getString("realName");
        }
        map.addAttribute("name", name);
        if (jsonObject.has("nickName")) {
            map.addAttribute("nickName", jsonObject.getString("nickName"));
        }else {
            map.addAttribute("nickName", "");
        }
        return "user/personalprofile";
    }

    @RequestMapping(value = "/loginout")
    public String loginout(HttpSession httpSession, ModelMap map) throws Exception {
        httpSession.setAttribute("userinfo",null);
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(value = "modifyUserInfo")
    public ResultBean modifyUserInfo(String key, String value, HttpSession httpSession) throws Exception {
        JSONObject jsonObject = (JSONObject) httpSession.getAttribute("userinfo");
        String uid = jsonObject.getString("uid");
        ResultBean resultBean = userService.modifyUserInfo(uid, key, value);
        if (200 == resultBean.getCode()) {
            ResultBean userBean = userService.findUserInfo(uid);
            JSONObject userinfo = new JSONObject(userBean.getData());
            httpSession.setAttribute("userinfo", userinfo);
        }
        return resultBean;
    }

    @RequestMapping(value = "modifynickname")
    public String modifynickname(){
        return "/user/modifynickname";
    }


    @RequestMapping(value = "modifyrealname")
    public String modifyrealname(){
        return "/user/modifyrealname";
    }



    @ResponseBody
    @RequestMapping(value = "accountRegister")
    public String accountRegister() throws Exception {
        //userService.accountRegister("15810045437", "", "", "000000", "TELEPHONE");
        //userService.accountLogin("15810045437","000000");
        //userService.accountModifyPassword("0h1170105151026m47","000000","000000");
//        System.out.println(ValidType.TEL_CODE.toString());
        userService.securityVerificationSend("0h1170105151026m47", ValidType.TEL_CODE.toString(), "15810045436", "", ReqOrigin.FIND_PASSWORD.toString(), "找回密码");
        return "user";
    }

}
