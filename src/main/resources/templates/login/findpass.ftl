<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>找回密码</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!--忽略页面中的数字识别为电话号码-->  
<meta name="format-detection" content="telephone=no" />
<!--忽略页面中的邮箱格式为邮箱-->
<meta name="format-detection" content="email=no"/>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>


<body>
<!--头部公用-->
<div id="header">
	<div class="header fixed">
		<div class="headerCont box">
		<div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
		<div class="headerC boxflex"><p class="font17">找回密码</p></div>
		<div class="headerR"></div>
		</div>
	</div>
</div>
<div class="wrapper login">
	<div class="tabCont" style="display:block">
		<ul class="blockinput formLogin">
			<li class="box font14">
				<span class="tit2">手机</span>
				<div class="cont2 input phone boxflex">
					<a href="javascript:void(0)" class="close"></a>
					<input id="telphone" type="text" placeholder="11位数字" >
				</div>
				<a id="get-verification-code" data-disabled="1" href="javascript:void(0)" class="btn gray">获取验证码</a>
			</li>
            <!--   验证码接口返回 -->
            <input id="validId" type="hidden">

			<li class="box font14">
				<span class="tit2">验证码</span>
				<div class="cont2 input boxflex">
					<a href="javascript:void(0)" class="close"></a>
					<input id="valid-code"  type="text" placeholder="6位数字" >
				</div>
			</li>
			<li class="box font14">
				<span class="tit2">新密码</span>
				<div class="cont2 input boxflex">
					<a href="javascript:void(0)" class="close"></a>
					<input id="password" type="text" placeholder="6-16位数字或字母" >
				</div>
			</li>
		</ul>
		<div class="applyBtn font17">
			<a id="find-password-submit" href="javascript:void(0)">完成</a>
		</div>
	</div>
</div>
<script language="javascript" type="text/javascript" src="../js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="../js/bestdo.js"></script>
<script>
$(function(){
	$("input").inputEve({
		cls:'.header',
		clearVal:'.close'
	});


    //获取验证码
    $("body").on("click", "#get-verification-code", function () {

        //验证账号是否可用
        var telphone = $("#telphone").val();
        if (telphone.length < 1) {
            alert("请输入手机号!!");
            return;
        }
        var partten = /^1[3,5,8]\d{9}$/;
        if (!partten.test(telphone)) {
            alert('手机号码格式不正确!');
            return;
        }

        $.ajax({
            type: "POST",
            async: false,
            url: "../login/checkAccount",
            data: {
                "account": telphone
            },
            success: function (result) {
                if (200 != result.code) {
                    alert(result.msg);
                    return
                }
            }
        });


        var disabled = $(this).data("disabled");
        if (0 == disabled) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "../login/fastloginVerificationSend",
            data: {
                "account": telphone
            },
            success: function (result) {
                if (200 === result.code) {
                    time();
                    $("#validId").val(result.object);
                } else {
                    alert(result.msg);
                }
            }
        });

    })

	//完成
	$("body").on("click","#find-password-submit",function () {

        var validCode = $("#valid-code").val();
        if (validCode.length < 1) {
            alert("请输入验证码")
            return;
        }
        var password = $("#password").val();
        if(password.length < 1){
            alert("请输入密码!!");
            return;
        }

        var validId = $("#validId").val();

        var telphone = $("#telphone").val();

        $.ajax({
            type: "POST",
            url: "../login/registerAccount",
            data: {
                "telephone": telphone,
                "validId": validId,
                "validCode": validCode,
                "password": password
            },
            success: function (result) {
                if (200 === result.code) {
                    alert("找回密码成功");
                    window.location.href = "/login/index";
                } else {
                    alert(result.msg);
                }
            }
        })

    })
	

})


//验证按倒计时
var wait = 60;
function time() {
    if (wait == 0) {
        $("#get-verification-code").html('获取验证码');
        $("#get-verification-code").data("disabled", 1);
        wait = 60;
    } else {
        $("#get-verification-code").html("(" + wait + "s)");
        $("#get-verification-code").data("disabled", 0);
        wait--;
        setTimeout(function () {
            time()
        }, 1000);
    }
}
</script>
</body>
</html>