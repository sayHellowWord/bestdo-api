$(function () {

    $("input").inputEve({
        cls: '.header',
        clearVal: '.close'
    });

    $(".loginTab li").tabEve({
        cls: '.tabCont',
        selected: 'on'
    });

    //普通登录-确定
    $("body").on("click", "#login-submit", function () {
        var account = $("#login-account").val();
        if (account.length < 1) {
            alert("请输入手机号!!");
            return;
        }
        // var partten = /^1[3,5,8,7]\d{9}$/;
        var partten = /^1[34578]\d{9}$/;
        if (!partten.test(account)) {
            alert('手机号码格式不正确!');
            return;
        }

        function getPassword(){

            var Opassword = $(".login-password-op").attr('value');
            var Cpassword = $(".login-password-cl").attr('value');
            if (Opassword == '') {
                return Cpassword;
            }else if (Cpassword == '') {
                return Opassword;
            }
        }

        $.ajax({
            type: "POST",
            url: "../login/accountLogin",
            data: {
                "account": account,
                "password": getPassword()
            },
            success: function (result) {
                console.info(result.data.validId)
                if (200 === result.code) {
                   // alert("登录成功");
                    back();
                } else {
                    alert(result.msg);
                }
            }
        });

    })

    //快速登录-获取验证码
    $("body").on("click", "#fast-login-account-identifying-code", function () {
        var disabled = $(this).data("disabled");
        if (0 == disabled) {
            return;
        }

        var account = $("#fast-login-account").val();
        if (account.length < 1) {
            alert("请输入手机号!!");
            return;
        }
        // var partten = /^1[3,5,8,7]\d{9}$/;
        var partten = /^1[34578]\d{9}$/;
        if (!partten.test(account)) {
            alert('手机号码格式不正确!');
            return;
        }
        //倒计时
        time();

        $.ajax({
            type: "POST",
            url: "../login/fastloginVerificationSend",
            data: {
                "account": account
            },
            success: function (result) {
                if (200 === result.code) {
                    $("#fast-login-account-validId").val(result.object);
                } else {
                    alert(result.msg);
                }
            }
        });

    });


    //快速登录-确定
    $("body").on("click", "#fast-login-account-submit", function () {
        var account = $("#fast-login-account").val();
        if (account.length < 1) {
            alert("请输入手机号!!");
            return;
        }
        // var partten = /^1[3,5,8,7]\d{9}$/;
        var partten = /^1[34578]\d{9}$/;
        if (!partten.test(account)) {
            alert('手机号码格式不正确!');
            return;
        }

        var identifying = $("#fast-login-account-identifying-value").val();
        if (identifying.length < 1) {
            alert("请输入验证码!!");
            return;
        }

        var validId = $("#fast-login-account-validId").val();

        $.ajax({
            type: "POST",
            url: "../login/fastlogin",
            async:false,
            data: {
                "account": account,
                "identifying": identifying,
                "validId": validId
            },
            success: function (result) {
                if (200 === result.code) {
                    back();
                } else {
                    alert(result.msg);
                }
            }
        });
    })

})

function  back() {
    window.location.href = backURL;
}


//验证按倒计时
var wait = 90;
function time() {
    if (wait == 0) {
        $("#fast-login-account-identifying-code").html('获取验证码');
        $("#fast-login-account-identifying-code").data("disabled", 1);
        wait = 60;
    } else {
        $("#fast-login-account-identifying-code").html("(" + wait + "s)");
        $("#fast-login-account-identifying-code").data("disabled", 0);
        wait--;
        setTimeout(function () {
            time()
        }, 1000);
    }
}
