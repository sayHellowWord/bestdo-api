<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>支付超时</title>
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
            <div class="headerC boxflex"><p class="font17">支付超时</p></div>
            <div class="headerR"></div>
        </div>
    </div>
</div>
<div class="wrapper result">
    <div class="cardResult fail font18">
        <span class="icon"></span><span class="t2">支付超时</span>
    </div>
    <p class="word font14">该订单因超时未支付已被自动取消，不可进行支付！</p>
    <div class="applyBtn font17">
        <a href="/order/detail?oid=${order_id}" class="viewBtn">查看订单</a>
        <a href="/site/index" class="backHome">返回首页</a>
    </div>
</div>
</body>
</html>