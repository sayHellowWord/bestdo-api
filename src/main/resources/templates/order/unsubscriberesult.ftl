<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>退订</title>
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
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">
                <#if code?number == 200>
                    退订成功
                <#else>
                    退订失败
                </#if>
            </p></div>
            <div class="headerR"></div>
        </div>
    </div>
</div>
<div class="wrapper result">
    <div class="cardResult activeSuccess font18">
        <span class="icon"></span><span class="t1">退订申请提交成功</span>
    </div>
    <p class="word font14"></p>
    <div class="applyBtn font17">
        <a href="/order/detail?oid=${order_id}" class="viewBtn">查看订单</a>
        <a href="/site/index" class="backHome">返回首页</a>
    </div>
</div>
</body>
</html>