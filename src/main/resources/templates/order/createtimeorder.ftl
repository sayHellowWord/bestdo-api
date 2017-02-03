<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>创建订单</title>
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
    <div class="header fixed">
        <div class="header box">
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">创建订单</p></div>
            <div class="headerR"></div>
        </div>
    </div>
</div>
<div class="creatInfo">
    <h1 class="font15">${detail.name}</h1>
    <p class="box time font14"><i>日期：</i><span>${day}</span></p>
    <p class="box font14"><i>时段：</i><span>
        <#if timeArr ??>
            <#list timeArr as time>
            ${time.start_hour}:00~${time.end_hour}:00 1片<br>
            </#list>
        </#if>
        <#--  9:00~10:00 1片<br >
          9:00~10:00 1片<br >-->
    </span></p>
    <p class="box font14"><i>地址：</i><span>${detail.venue.stadium.address}</span></p>
</div>

<ul class="blockinput">
    <li class="box font14">
        <span class="tit2">手机</span>
        <div class="cont2 input boxflex">
            <a href="javascript:void(0)" class="close"></a>
            <input id="telphone-id" type="tel" placeholder="请填写联系人手机" value="${telephone!}">
        </div>
    </li>
</ul>
<#--<ul class="blockinput">
    <li class="font14">
        <span class="count pagecount">1张可用</span>
        <span class="tit">使用卡券</span>
    </li>
</ul>-->
<h1 class="creatTit font12">结算信息</h1>
<ul class="blockinput">
    <li class="font14">
        <span class="p1">￥${((totalMoney?number)/100)?string("#.##")}</span>
        <span class="tit">订单总额</span>
    </li>
<#--    <li class="font14">
        <span class="p1 p3">￥100</span>
        <span class="tit3">会员专享价</span>
    </li>
    <li class="font14">
        <span class="p1">￥0</span>
        <span class="tit">抵扣</span>
    </li>-->
    <li class="font14">
        <span id="pay-total" class="p1 p2" data-money="${totalMoney}" >￥${((totalMoney?number)/100)?string("#.##")}</span>
        <span class="tit">支付金额</span>
    </li>
</ul>
<div class="noteword font12">
    下单后请在15分钟内进行支付，超过15分钟未支付订单将会被自动取消！
</div>


<div id="choose-pay-type" class="paystyle" style="display:none;">
    <div class="paystyleBg"></div>
    <div class="paystyleCont">
        <h1 class="tit font17">
            <a href="javascript:void(0)"></a>
            选择支付方式
        </h1>
        <ul class="paystylelist">

        </ul>
        <input id="pay_order_id" type="hidden">
        <input id="pay_goods_id" type="hidden">
        <input id="pay_amount" type="hidden">
        <div id="confirm-pay" class="payBtn font18"><a href="#">确定支付</a></div>
    </div>
</div>

<div id="submit-div" class="creatBtn font17">
    <div id="submit-order" class="btnCont"><a href="#">提交订单</a></div>
</div>
</body>
<script src="/js/jquery.js"></script>
<script src="/js/bestdo.js"></script>
<script>
    //判断是否为微信支付
    var ua = window.navigator.userAgent.toLowerCase();
    var payType = "<li class='zfb on' data-type='ALIPAYWAP'><span class='icon'></span><span class='txt font16'>支付宝</span></li>";
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        payType = "<li class='wx on' data-type='WEIXINJSAPI'><span class='icon'></span><span class='txt font16'>微信</span></li>";
    }
    $(".paystylelist").html(payType);

    $(function () {
        var mer_item_id = '${mer_item_id}';
        var mer_price_id = '${mer_price_id}';
        var cid = '${cid}';
        var book_day = '${book_day}';

        //提交订单
        $("#submit-order").on("click", function () {

            //获取电话
            var telphone = $("#telphone-id").val();
            if (telphone.length < 1) {
                alert("请输入手机号!!");
                return;
            }
            var partten = /^1[3,5,8]\d{9}$/;
            if (!partten.test(telphone)) {
                alert('手机号码格式不正确!');
                return;
            }

//            var order_money = $("#pay-total").text().replace("￥", "");
            var order_money = $("#pay-total").data("money");

            $.ajax({
                type: "POST",
                async: false,
                url: "/order/submitOrder",
                data: {
                    "cid": cid,
                    "mer_item_id": mer_item_id,
                    "mer_price_id": mer_price_id,
                    "book_day": book_day,
                    "book_phone": telphone,
                    "order_money": order_money,
                    "timeStr": '${timeStr}'
                },
                success: function (result) {
                    if (200 == result.code) {
                        alert("订单提交成功");
                        $("#choose-pay-type").show();
                        $("#submit-div").hide();
                        $("#pay_order_id").val(result.object.oid);
                        $("#pay_goods_id").val(result.object.mer_item_id);
                        $("#pay_amount").val(result.object.pay_money);
                    } else {
                        alert("订单提交失败:" + result.msg);
                    }
                }
            });


        })

        //确定支付
        $("#confirm-pay").on("click", function () {

            var order_id = $("#pay_order_id").val();
            var goods_id = $("#pay_goods_id").val();
            var amount = $("#pay_amount").val();

            var code = -1;
            var url = '';

            var sign = '';

            $.ajax({
                type: "POST",
                async: false,
                url: "/pay/payBank",
                data: {
                    "order_id": order_id,
                    "goods_id": goods_id,
                    "amount": amount,
                    "channel_id": $(".paystylelist").find(".on").data("type")
                },
                success: function (result) {
                    if (200 == result.code) {
                        code = result.code;
                        url = result.data;
                        sign = result.object;
                        //循环执行，每隔3秒钟执行一次 showalert（）
                      /*  window.setInterval(function () {
                            checkOrder(order_id);
                        }, 3000);
*/
                    } else {
                        alert("创建订单失败")
                    }
                }
            });
            if (200 == code) {
                openPaypage(url, order_id, amount, sign);
            }
        })

    })

    //检查订单状态
    function checkOrder(order_id) {
        var status = 0;
        $.ajax({
            type: "POST",
            async: false,
            url: "/order/searchDeail",
            data: {
                "oid": order_id
            },
            success: function (result) {
                if (200 == result.code) {
                    status = result.object.status;
                }
            }
        });
        if (-1 == status) { //已取消》订单超时
            toTimeOutPage(order_id)
        } else if (1 == status) { //已支付
            toSucPage(order_id)
        } else if (3 == status) { //待下场
            toSucPage(order_id)
        }
    }

    //打开支付页面
    function openPaypage(url, order_id, amount, sign) {
        console.info(url);
        if (ua.match(/MicroMessenger/i) == 'micromessenger') {
          //  window.open("http://test.weixin.bestdo.com/paybank/topay?o=" + order_id + "&a=" + amount + "&s=" + sign + "&l=5");
            window.open(url + "?o=" + order_id + "&a=" + amount + "&s=" + sign + "&l=5");
            return;
        }
        window.open(url);
    }


    //跳转支付成功页
    function toSucPage(order_id) {
        window.location.href = "/pay/paySucPage";
    }

    //跳转支付超时页
    function toTimeOutPage(order_id) {
        window.location.href = "/pay/payTimeOutPage";
    }
</script>
</html>