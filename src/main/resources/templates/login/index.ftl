<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no"/>
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>
    <link rel="stylesheet" type="text/css" href="../css/style.css"/>
</head>

<body>
<!--头部公用-->
<div id="header">
    <div class="header fixed">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17" style="margin:0 auto" >登录</p></div>
            <div class="headerR"><a href="../login/register" class="txt2 font14">注册</a></div>
        </div>
    </div>
</div>
<div class="wrapper login">
    <ul class="loginTab font14">
        <li class="on" data-tab="0">快速登录</li>
        <li data-tab="1">普通登录</li>
    </ul>
    <div class="tabCont" style="display:block">
        <ul class="blockinput formLogin">
            <li class="box font14">
                <span class="tit2">手机</span>
                <div class="cont2 input phone boxflex">
                    <a href="javascript:void(0)" class="close"></a>
                    <input id="fast-login-account" type="text" placeholder="11位数字">
                </div>
                <a id="fast-login-account-identifying-code" data-disabled="1" href="javascript:void(0)"
                   class="btn gray">获取验证码</a>
            </li>
            <li class="box font14">
                <span class="tit2">验证码</span>
                <div class="cont2 input boxflex">
                    <a href="javascript:void(0)" class="close"></a>
                    <input id="fast-login-account-identifying-value" type="text" placeholder="6位数字">
                </div>
            </li>
            <!--   快速登录验证码接口返回 -->
            <input id="fast-login-account-validId" type="hidden">

        </ul>
        <div class="applyBtn font17">
            <a id="fast-login-account-submit" href="javascript:void(0)">登录</a>
        </div>
        <div class="getPswTxt font13"><a href="/login/findPassword">忘记密码</a></div>
    </div>
    <div class="tabCont">
        <ul class="blockinput formLogin">
            <li class="box font14">
                <span class="tit2">手机</span>
                <div class="cont2 input boxflex">
                    <a href="javascript:void(0)" class="close"></a>
                    <input id="login-account" type="text" placeholder="请输入手机号">
                </div>
            </li>
            <li class="box font14">
                <span class="tit2">密码</span>
                <div class="cont2 input boxflex">
                    <a href="javascript:void(0)" class="close"></a>
                    <a href="javascript:void(0)" class="eye"></a>
                    <input class="login-password-cl" type="text" placeholder="6-16位数字或字母">
                    <input class="login-password-op" type="password" placeholder="6-16位数字或字母">
                </div>
            </li>
        </ul>
        <div class="applyBtn font17">
            <a id="login-submit" href="javascript:void(0)">登录</a>
        </div>
        <div class="getPswTxt font13"><a href="/login/findPassword">忘记密码</a></div>
    </div>
</div>
<script>
    //返回页面
    var backURL = '${back_url!'/'}';
</script>
<script language="javascript" type="text/javascript" src="../js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="../js/bestdo.js"></script>
<!--    登录相关js     -->
<script language="javascript" type="text/javascript" src="../js/login/index.js"></script>
<script type="text/javascript">
    var key = true;
    var val;
    $('.eye').click(function(){
        if (key == true) {
            $(this).addClass('eyeclose');
            val = $('.login-password-op').attr('value');
            $('.login-password-op').hide().attr('value','');
            $('.login-password-cl').show().attr('value',val);
            key = false;
        }else{
            $(this).removeClass('eyeclose');
            val = $('.login-password-cl').attr('value');
            $('.login-password-op').show().attr('value',val);
            $('.login-password-cl').hide().attr('value','');
            key = true;
        }
    })
</script>
</body>
</html>
