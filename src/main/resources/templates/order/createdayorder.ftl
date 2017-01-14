<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>创建订单</title>
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
            <div class="headerC boxflex"><p class="font17">创建订单</p></div>
            <div class="headerR"></div>
        </div>
    </div>
</div>
<div class="creatInfo">
    <h1 class="font15">${detail.name}</h1>
    <p class="box time font14"><i>日期：</i><span>${day}</span></p>
    <p class="box font14"><i>服务：</i><span>2小时不限时畅游</span></p>
    <p class="box font14"><i>地址：</i><span>${detail.venue.stadium.address}</span></p>
</div>
<ul class="blockinput">
    <li class="box member buycount font14">
        <span class="tit">购买数量</span>
        <div class="buycount boxflex">
            <a href="javascript:void(0)" class="minuse">-</a>
            <input type="text" value="1" >
            <a href="javascript:void(0)" class="plus">+</a>
        </div>
    </li>
</ul>
<ul class="blockinput">
    <li class="box font14">
        <span class="tit2">手机</span>
        <div class="cont2 boxflex">
            <a href="javascript:void(0)" class="close"></a>
            <input type="tel" placeholder="请填写联系人手机" value="${telephone??}">
        </div>
    </li>
</ul>

<h1 class="creatTit font12">结算信息</h1>
<ul class="blockinput">
    <li class="font14">
        <span class="p1">￥100</span>
        <span class="tit">订单总额</span>
    </li>
    <li class="font14">
        <span class="p1 p2">￥100</span>
        <span class="tit">支付金额</span>
    </li>
</ul>
<div class="noteword font12">
    下单后请在15分钟内进行支付，超过15分钟未支付订单将会被自动取消！
</div>
<#--
<div class="paystyle" style="display:block;">
    <div class="paystyleBg"></div>
    <div class="paystyleCont">
        <h1 class="tit font17">
            <a href="javascript:void(0)"></a>
            选择支付方式
        </h1>
        <ul class="paystylelist">
            <li class="wx on">
                <span class="icon"></span><span class="txt font16">微信</span>
            </li>
        </ul>
        <div class="payBtn font18"><a href="#">确定支付</a></div>
    </div>
</div>-->
<div class="creatBtn font17">
    <div class="btnCont"><a href="#">提交订单</a></div>
</div>
<#--<div id="layer">
    <div class="layerBg" style="z-index:9999"></div>
    <div class="layer box2">
        <div class="layerCont">
            <div class="cont font17">
                付款尚未完成，是否<br >要退出支付？
            </div>
            <div class="btn">
                <a href="javascript:void(0)" class="cancle font17">退出支付</a>
                <a href="javascript:void(0)" class="sure font17">继续支付</a>
            </div>
        </div>
    </div>
</div>-->
<script src="/js/jquery.js"></script>
<script src="/js/bestdo.js"></script>
<script>
    $(function(){
        $(".minuse").on("click",function(){
            var num = parseInt($(".buycount input").val());
            if(num>=2){
                $(".buycount input").val(num-1);
            }
        })
        $(".plus").on("click",function(){
            var num = parseInt($(".buycount input").val());
            $(".buycount input").val(num+1);
        })
    })
</script>
</body>
</html>