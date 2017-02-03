<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>个人资料</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no"/>
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>


<body>
<!--头部公用-->
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">个人资料</p></div>
            <div class="headerR"></div>
        </div>
    </div>
</div>
<div class="wrapper">
    <div class="set">
        <ul class="blockinput">
            <li class="font14">
                <span id="monity-nick-name" class="count">${nickName!}</span>
                <span class="tit">昵称</span>
            </li>
            <li class="font14">
                <span id="monity-real-name"  class="count">${name!}</span>
                <span class="tit">姓名</span>
            </li>
            <li class="sex font14">
                <span class="count"><#if userinfo.sex == 'MALE'>男<#elseif  userinfo.sex == 'FAMALE'>女<#else>
                    未知</#if></span>
                <span class="tit">性别</span>
            </li>
        </ul>

    </div>
</div>
<!-- 性别 -->
<div class="dateControl">
    <div class="dateControlBg"></div>
    <div class="dateControlCont">
        <div class="datelist font14">
            <div class="dateline"></div>
            <h1>
                <a href="javascript:void(0)" class="cancle">取消</a>
                <a id="modify-sex-sure" href="javascript:void(0)" class="sure">确定</a>
                <p class="font17">选择性别</p>
            </h1>
            <div class="scroll mebcount" data-on="0" style="padding:0px;">
                <span data-sex="MALE">男</i></span>
                <span data-sex="FAMALE">女</i></span>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery.js"></script>
<script src="/js/bestdo.js"></script>
<script>
    $(function () {


        $(".sex .count").dateControl({
            dateparent: ".dateControl",
            datecontrol: ".scroll",
            datesureBtn: ".sure",
            datecancleBtn: ".cancle",
        })

        //修改用户性别确认
        $("#modify-sex-sure").click(function () {
            var sex = $(".mebcount").find(".on").data("sex");
            var code = 400;
            $.ajax({
                type: "POST",
                url: "/user/modifyUserInfo",
                data: {
                    "key": 'sex',
                    "value": sex
                },
                success: function (resultData) {
                    console.info(resultData);
                    code = resultData.code;
                }
            });

            if(200 == code){
                window.location.reload;
            }

        })

        //修改用户昵称
        $("#monity-nick-name").click(function () {
            window.location.href = "/user/modifynickname";
        })

        // 修改姓名
        $("#monity-real-name").click(function () {
            window.location.href = "/user/modifyrealname";
        })


    })
</script>
</body>
</html>