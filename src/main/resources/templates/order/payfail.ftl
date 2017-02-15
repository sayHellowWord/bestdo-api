<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>支付失败</title>
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
            <div class="headerC boxflex"><p class="font17">支付失败</p></div>
            <div class="headerR"></div>
        </div>
    </div>
</div>
<div class="wrapper result">
    <div class="cardResult fail font18">
        <span class="icon"></span><span class="t2">支付失败</span>
    </div>
 <#--   <p class="word font14">请点击“重新支付”对订单进行支付，<span>13分12秒</span>后订单将因超时未支付被自动取消！</p>-->
    <div class="applyBtn font17">
        <a href="/order/detail?oid=${order_id}" class="viewBtn">查看订单</a>
        <a href="/" class="backHome">返回首页</a>
    </div>
</div>
</body>
</html>