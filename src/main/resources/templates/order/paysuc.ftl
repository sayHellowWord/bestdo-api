<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>支付成功</title>
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
            <div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">支付成功</p></div>
            <div class="headerR"></div>
        </div>
    </div>
</div>
<div class="wrapper result">
    <div class="cardResult activeSuccess font18">
        <span class="icon"></span><span class="t1">支付成功</span>
    </div>
    <p class="word font14">您的订单已支付成功，百动正在努力为您确认中，其预订结果将会以短信的形式通知您！</p>
    <div class="applyBtn font17">
        <a href="/order/detail?oid=${order_id}" class="viewBtn">查看订单</a>
        <a href="/site/index" class="backHome">返回首页</a>
    </div>
</div>
</body>
</html>