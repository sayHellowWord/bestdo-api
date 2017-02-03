<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>姓名</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no" />
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>


<body>
<!--头部公用-->
<div id="header">
    <div class="header fixed">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">姓名</p></div>
            <div id="save-nick-name" class="headerR"><a href="#" class="txt2 font14">保存</a></div>
        </div>
    </div>
</div>
<div class="wrapper login">
    <div class="tabCont" style="display:block">
        <ul class="blockinput formLogin">
            <li class="box font14">
                <div class="cont2 input boxflex">
                    <a href="javascript:history.go(-1);" class="close"></a>
                    <input id="nick-name" type="text" placeholder="请输入姓名" >
                </div>
            </li>
        </ul>
    </div>
</div>
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script>
    $(function(){
        $("input").inputEve({
            cls:'.header',
            clearVal:'.close'
        });
        
        $("#save-nick-name").click(function () {
            var nickname= $("#nick-name").val();

            if(nickname.length < 1){
                alert("请填写姓名!");
                return;
            }
            var code = 400;
            $.ajax({
                type: "POST",
                url: "/user/modifyUserInfo",
                async : false,
                data: {
                    "key": 'realName',
                    "value": nickname
                },
                success: function (resultData) {
                    console.info(resultData);
                    code = resultData.code;
                }
            });

            if(200 == code){
                window.location.href = "/user/personalprofile";
            }

        })


    })
</script>
</body>
</html>