package com.saidian.web.user;

import com.saidian.bean.ResultBean;
import com.saidian.config.AccessServices;
import com.saidian.utils.HttpResultUtil;
import com.saidian.utils.HttpUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/5.
 */
@Service
public class UserService {

    private static String ACCOUNT_REGISTER = "uccore/v1/api/account/register";

    private static String ACCOUNT_LOGIN = "uccore/v1/api/account/login";

    private static String ACCOUNT_MODIFY_PASSWORD = "uccore/v1/api/account/modifyPassword";

    private static String ACCOUNT_FIND_PASSWORD = "uccore/v1/api/account/findPassword";

    private static String SECURITY_VERIFICATION_SEND = "uccore/v1/api/securityVerification/send";

    private static String ACCOUNT_VALID_LOGIN_REGISTER = "uccore/v1/api/account/validLoginRegister";

    private static String ACCOUNT_CHECK = "uccore/v1/api/account/checkAccount";//检查账户是否存在

    private static String SECURITY_VERIFICATION_VALID = "uccore/v1/api/securityVerification/valid"; //验证接口仅验证

    private static String ACCOUNT_FIND_PASSPORT = "uccore/v1/api/account/findPassport"; //查询登录账户信息

    private static String ACCOUNT_USER_INFO = "uccore/v1/api/account/findUserInfo"; //查询用户信息


    /**
     * 普通用户注册 ok
     *
     * @param telephone
     * @param email
     * @param loginName
     * @param password
     * @param regOrigin
     */
    public ResultBean accountRegister(String telephone, String email, String loginName, String password, String regOrigin) throws Exception {

           JSONObject jsonObject = new JSONObject();
        jsonObject.put("telephone", telephone);
        jsonObject.put("email", email);
        jsonObject.put("loginName", loginName);
        jsonObject.put("password", password);
        jsonObject.put("regOrigin", regOrigin);

        String result = HttpUtil.doPost(AccessServices.USER_SERVICE_URL + ACCOUNT_REGISTER, jsonObject.toString(), AccessServices.USER_SERVICE_KEY);

        return HttpResultUtil.result2Bean(result);
    }

    /**
     * 普通用户登录
     *
     * @param account
     * @param password
     */
    public ResultBean accountLogin(String account, String password) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account", account);
        jsonObject.put("password", password);
        String result = HttpUtil.doPost(AccessServices.USER_SERVICE_URL + ACCOUNT_LOGIN, jsonObject.toString(), AccessServices.USER_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);
    }


    /**
     * 修改密码
     *
     * @param uid
     * @param oldPassword
     * @param newPassword
     */
    public void accountModifyPassword(String uid, String oldPassword, String newPassword) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", uid);
        jsonObject.put("oldPassword", oldPassword);
        jsonObject.put("newPassword", newPassword);

        String result = HttpUtil.doPost(AccessServices.USER_SERVICE_URL + ACCOUNT_MODIFY_PASSWORD, jsonObject.toString(), AccessServices.USER_SERVICE_KEY);
        ResultBean resultBean = HttpResultUtil.result2Bean(result);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(resultBean);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }


    /**
     * 找回密码
     *
     * @param telephone
     * @param validId
     * @param validCode
     * @param newPassword
     */
    public ResultBean setAccountFindPassword(String telephone, String validId, String validCode, String newPassword) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("telephone", telephone);
        jsonObject.put("validId", validId);
        jsonObject.put("validCode", validCode);
        jsonObject.put("newPassword", newPassword);

        String result = HttpUtil.doPost(AccessServices.USER_SERVICE_URL + ACCOUNT_FIND_PASSWORD, jsonObject.toString(), AccessServices.USER_SERVICE_KEY);
        ResultBean resultBean = HttpResultUtil.result2Bean(result);

        return resultBean;
    }


    /**
     * 获取验证码
     *
     * @param uid
     * @param validType
     * @param account
     * @param validDesc
     * @param reqOrigin
     * @param smsHead
     */
    public ResultBean securityVerificationSend(String uid, String validType, String account, String validDesc, String reqOrigin, String smsHead) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", uid);
        jsonObject.put("validType", validType);
        jsonObject.put("account", account);
        jsonObject.put("validDesc", validDesc);
        jsonObject.put("reqOrigin", reqOrigin);
        jsonObject.put("smsHead", smsHead);

        String result = HttpUtil.doPost(AccessServices.USER_SERVICE_URL + SECURITY_VERIFICATION_SEND, jsonObject.toString(), AccessServices.USER_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);

    }


    /**
     * @param telephone
     * @param validId
     * @param validCode
     */
    public ResultBean accountValidLoginRegister(String telephone, String validId, String validCode) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("telephone", telephone);
        jsonObject.put("validId", validId);
        jsonObject.put("validCode", validCode);

        String result = HttpUtil.doPost(AccessServices.USER_SERVICE_URL + ACCOUNT_VALID_LOGIN_REGISTER, jsonObject.toString(), AccessServices.USER_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);
    }


    /**
     * 检查账号是否可用
     *
     * @param account
     * @return
     */
    public ResultBean checkAccount(String account) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account", account);

        String result = HttpUtil.doPost(AccessServices.USER_SERVICE_URL + ACCOUNT_CHECK, jsonObject.toString(), AccessServices.USER_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);
    }

    /**
     *  验证接口仅验证
     * @param account
     * @param validId
     * @param validCode
     * @return
     * @throws Exception
     */
    public ResultBean securityVerificationValid(String account,String validId,String validCode) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("account", account);
        jsonObject.put("validId", validId);
        jsonObject.put("validCode", validCode);

        String result = HttpUtil.doPost(AccessServices.USER_SERVICE_URL + SECURITY_VERIFICATION_VALID, jsonObject.toString(), AccessServices.USER_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);
    }

    /**
     * 查询登录账户信息
     * @param uid
     * @param loginName
     * @param telephone
     * @param email
     * @param account
     * @return
     */
    public ResultBean accountFindPassport(String uid,String loginName,String telephone,String email,String account) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", uid);
        jsonObject.put("loginName", loginName);
        jsonObject.put("telephone", telephone);
        jsonObject.put("email", email);
        jsonObject.put("account", account);
        String result = HttpUtil.doPost(AccessServices.USER_SERVICE_URL + ACCOUNT_FIND_PASSPORT, jsonObject.toString(), AccessServices.USER_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);
    }

    /**
     * 查询用户基本信息
     * @param uid
     * @return
     */
    public ResultBean findUserInfo(String uid) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", uid);
        String result = HttpUtil.doPost(AccessServices.USER_SERVICE_URL + ACCOUNT_USER_INFO, jsonObject.toString(), AccessServices.USER_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);
    }

}
