package com.saidian.web.controller;

import com.saidian.bean.ReqOrigin;
import com.saidian.bean.ResultBean;
import com.saidian.bean.ValidType;
import com.saidian.web.bean.User;
import com.saidian.web.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/1/7.
 */
@RequestMapping(value = "login")
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/index")
    public String index() throws Exception {
        return "login/index";
    }

    @ResponseBody
    @RequestMapping(value = "/accountLogin")
    public ResultBean accountLogin(String account, String password, HttpSession httpSession) throws Exception {
        System.out.println("======================  普通登录    begin   ==================================");
        System.out.println(httpSession.getId());
        System.out.println("======================  普通登录    end   ==================================");

        ResultBean resultBean = userService.accountLogin(account, password);

        if (200 == resultBean.getCode()) {
            JSONObject dataJSONObject = new JSONObject(resultBean.getData());
            User user = new User();
            user.setId(dataJSONObject.getInt("id"));
            user.setSid(dataJSONObject.getString("sid"));
            user.setUid(dataJSONObject.getString("uid"));
            user.setLoginAccount(dataJSONObject.getString("loginAccount"));
            user.setAccountType(dataJSONObject.getString("accountType"));
            user.setLoginType(dataJSONObject.getString("loginType"));
            user.setScopeTime(dataJSONObject.getInt("scopeTime"));
            //  user.setLoginCookie(dataJSONObject.getString("loginCookie"));
            httpSession.setAttribute("user", user);
            resultBean.setData(StringUtils.EMPTY);

            resultBean = userService.findUserInfo(user.getUid());
            JSONObject userinfo = new JSONObject(resultBean.getData());
            httpSession.setAttribute("userinfo", userinfo);
        }
        return resultBean;
    }


    @RequestMapping(value = "register")
    public String accountRegister() throws Exception {
        return "login/register";
    }

    /**
     * 快速登录-获取验证码
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "fastloginVerificationSend")
    public ResultBean fastloginVerificationSend(String account) throws Exception {
        ResultBean<String> resultBean = userService.securityVerificationSend(account, ValidType.TEL_CODE.toString(), account,
                "", ReqOrigin.C_QUICK_LOGIN.toString(), "【快速登录】");
        if (200 == resultBean.getCode()) {
            JSONObject data = new JSONObject(resultBean.getData());
            System.out.println(data.get("validId"));
            resultBean.setObject(data.getString("validId"));
        }
        return resultBean;
    }

    /**
     * 快速登录
     *
     * @param account
     * @param identifying
     * @param validId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "fastlogin")
    public ResultBean fastlogin(String account, String identifying, String validId, HttpSession httpSession) throws Exception {

        System.out.println("======================  快速登录    begin   ==================================");
        System.out.println(httpSession.getId());
        System.out.println("======================  快速登录    end   ==================================");

        ResultBean resultBean = userService.accountValidLoginRegister(account, validId, identifying);
        if (200 == resultBean.getCode()) {
            JSONObject dataJSONObject = new JSONObject(resultBean.getData());
            User user = new User();
            user.setId(dataJSONObject.getInt("id"));
            user.setSid(dataJSONObject.getString("sid"));
            user.setUid(dataJSONObject.getString("uid"));
            user.setLoginAccount(dataJSONObject.getString("loginAccount"));
            user.setAccountType(dataJSONObject.getString("accountType"));
            user.setLoginType(dataJSONObject.getString("loginType"));
            user.setScopeTime(dataJSONObject.getInt("scopeTime"));
            //  user.setLoginCookie(dataJSONObject.getString("loginCookie"));
            httpSession.setAttribute("user", user);
            resultBean.setData(StringUtils.EMPTY);
            resultBean = userService.findUserInfo(user.getUid());
            JSONObject userinfo = new JSONObject(resultBean.getData());
            httpSession.setAttribute("userinfo", userinfo);
        }
        return resultBean;
    }

    /**
     * 检查账号是否可用
     *
     * @param account
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "checkAccount")
    public ResultBean checkAccount(String account) throws Exception {
        ResultBean resultBean = userService.checkAccount(account);
        return resultBean;
    }

    /**
     * 注册-下一步
     *
     * @param account
     * @param identifying
     * @param validId
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "registerNext")
    public ResultBean registerNext(String account, String identifying, String validId) throws Exception {
        ResultBean resultBean = userService.securityVerificationValid(account, validId, identifying);
        return resultBean;
    }


    /**
     * 注册
     *
     * @param telephone
     * @param email
     * @param loginName
     * @param password
     * @param regOrigin
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "registerAccount")
    public ResultBean register(String telephone, String email, String loginName, String password, String regOrigin) throws Exception {
        return userService.accountRegister(telephone, email, loginName, password, regOrigin);
    }

    /**
     * 找回密码
     *
     * @return
     */
    @RequestMapping(value = "findPassword")
    public String findPassword() {
        return "login/findpass";
    }

    @ResponseBody
    @RequestMapping(value = "setAccountFindPassword")
    public ResultBean setAccountFindPassword(String telephone, String validId, String validCode, String password) throws Exception {
        return userService.setAccountFindPassword(telephone, validId, validCode, password);
    }


}
