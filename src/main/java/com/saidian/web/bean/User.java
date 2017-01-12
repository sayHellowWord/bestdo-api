package com.saidian.web.bean;

/**
 * Created by Administrator on 2017/1/10.
 */
public class User {


    private int id;//ID

    private String sid;//会话编号

    private String uid;//用户

    private String loginAccount;//登录账户

    /**
     LOGIN_NAME(0,"用户名"),
     TELEPHONE(1,"手机号"),
     EMAIL(2,"邮箱"),
     QQ(3,"qq"),
     SINAWEIBO(4,"新浪微博"),
     WEIXIN(5,"微信"),
     OTHER(6,"其它")
     */
    private String accountType;//账户类型

    /**
     WEB(0,"Web端"),
     API(1,"接口端"),
     WAP(2,"Wap端"),
     WEIXIN(3,"微信端"),
     APP(4,"手机app端")
     */
    private String 	loginType;//登录方式

    private int scopeTime;//用户在线有效时长

    private String loginCookie;//登录Cookie列表 cookiekey cookie 值 有效时长

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public int getScopeTime() {
        return scopeTime;
    }

    public void setScopeTime(int scopeTime) {
        this.scopeTime = scopeTime;
    }

    public String getLoginCookie() {
        return loginCookie;
    }

    public void setLoginCookie(String loginCookie) {
        this.loginCookie = loginCookie;
    }
}
