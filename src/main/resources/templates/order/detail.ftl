<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>订单详情-待下场</title>
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
            <div class="headerC boxflex"><p class="font17">订单详情</p></div>
            <div class="headerR"><a href="tel:${order.stadium.phone}" class="tel"></a></div>
        </div>
    </div>
</div>
<div class="wrapper">
    <div class="stadius">
        <div class="stadiusCont box">

        <#if order.status?number == 0>
            <span class="icon i2"></span>
            <div class="cont boxflex">
                <h1 class="font17">待付款</h1>
                <p class="font12">超时将自动取消</p>
            </div>
        </#if>
        <#if order.status?number == 3>
            <span class="icon i1"></span>
            <div class="cont boxflex">
                <h1 class="font17">待下场</h1>
                <p class="font12">赶紧去运动吧</p>
            </div>
        </#if>
        <#if order.status?number == 5>
            <span class="icon i2"></span>
            <div class="cont boxflex">
                <h1 class="font17">确认中</h1>
                <p class="font12">百动正在努力确认中</p>
            </div>
        </#if>
        <#if order.status?number == 4>
            <span class="icon i4"></span>
            <div class="cont boxflex">
                <h1 class="font17">退订中</h1>
                <p class="font12">等待百动确认</p>
            </div>
        </#if>
        <#if order.status?number == 7>
            <span class="icon i5"></span>
            <div class="cont boxflex">
                <h1 class="font17">已取消</h1>
                <p class="font12">支付超时</p>
            </div>
        </#if>
        <#if order.status?number == -2>
            <span class="icon i6"></span>
            <div class="cont boxflex">
                <h1 class="font17">已退订</h1>
                <p class="font12">已退订成功</p>
            </div>
        </#if>
        <#if order.status?number == 2>
            <span class="icon i7"></span>
            <div class="cont boxflex">
                <h1 class="font17">已完成</h1>
                <p class="font12">订单已完成</p>
            </div>
        </#if>

        </div>
    </div>
    <div class="creatInfo orderDetail font14">

    <#--//时段性 101 网球 102 羽毛球  104 篮球   106 乒乓球 足球待定 todo-->
    <#if order.cid?number == 101 ||  order.cid?number == 102 ||  order.cid?number == 104 ||  order.cid?number == 106>
        <!-- 乒羽篮网 -->
        <h1 class="font15">${order.mer_item_name}</h1>
        <p class="box time font14">
            <i>日期：</i><span>${order.time} </span>
        </p>
        <p class="box font14"><i>时段：</i><span>
            <#list order.items as item>
            ${item.start_hour}：00~ ${item.end_hour}：00 1片<br>
            </#list>
        </span></p>
        <p class="box font14"><i>地点：</i><span>${order.stadium.address}</span></p>
    </#if>
    <#--//日期型 109 游泳  108 健身 122 台球-->
    <#if order.cid?number == 108 ||  order.cid?number == 109 ||  order.cid?number == 122>
        <div class="creatInfo">
            <h1 class="font15">${order.mer_item_name}</h1>
            <p class="box time font14"><i>日期：</i><span>${order.time} </span></p>
            <p class="box font14"><i>服务：</i><span>${order.items[0].price_info}</span></p>
            <p class="box font14"><i>地址：</i><span>${order.stadium.address}</span></p>
        </div>
    </#if>

    </div>
    <h1 class="creatTit font12">订单详情</h1>
    <ul class="blockinput blockorder">
        <li class="box font14">
            <span class="tit">订单号码：</span>
            <div class="cont boxflex">${order.oid}</div>
        </li>
        <li class="box font14">
            <span class="tit">联系人手机：</span>
            <div class="cont boxflex">${order.book_phone}</div>
        </li>
    <#if order.tpl_play_person??>
        <li class="box font14">
            <span class="tit">联系人姓名：</span>
            <div class="cont boxflex">${order.tpl_play_person}</div>
        </li>
    </#if>
    </ul>
    <h1 class="creatTit font12">结算信息</h1>
    <ul class="blockinput">
        <li class="font14">
            <span class="p1">￥${order.money}</span>
            <span class="tit2">订单总额</span>
        </li>
        <li class="font14">
            <span class="p1 p2">￥${order.money}</span>
            <span class="tit">支付金额</span>
        </li>
    </ul>
</div>



<#--  支付状态  -->
<#if order.status?number == 0>
<div id="order-status-show" class="orderBtn font17">
   <#-- ${canRepay}-->
    <#if canRepay?number == 1>
        <div class="ordreBtnCont">
            <div class="orderBtnL">
                支付剩余<span id="count-down-re-pay-time">${diffMinutes}:${diffSeconds}</span>
            </div>
            <a id="topaybtn" href="javascript:void(0)" class="topaybtn">去支付</a>
        </div>
    <#else>
        <div class="ordreBtnCont">
            <a href="/site/toDetail?mer_item_id=${order.mer_item_id}&mer_price_id=${order.items[0].mer_price_id}&cid=${order.cid}" class="buyBtn">重新预订</a>
        </div>
    </#if>
</div>
    <#if canRepay?number == 1>
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
    <script src="/js/jquery.js"></script>
    <script>
        //倒计时
        /* var m = '${diffMinutes}';
        var s = '${diffSeconds}';
        function showtime() {
            document.getElementById('count-down-re-pay-time').innerHTML = m + ":" + s;
            s = s - 1;
            if (s == 0) {
                m = m - 1;
                s = 60
            }
            if (m <= 0 && s <= 0 ) {
                clearInterval(settime);
                document.getElementById('order-status-show').innerHTML ="<div class='ordreBtnCont'> <a href='javascript:void(0)' class='buyBtn'>重新预订</a> </div>";
                return false;
            }
        }
        var settime = setInterval(function () {
            showtime();
        }, 1000);*/
        var minute = '${diffMinutes}';
        var second = '${diffSeconds}';
        var mer_item_id = '${order.mer_item_id}';
        var mer_price_id = '${order.items[0].mer_price_id}';
        var cid = '${order.cid}';
        function lxfEndtime(){
            if(second <= 0 && minute<=0){
                document.getElementById('order-status-show').innerHTML ="<div class='ordreBtnCont'>" +
                        "<a href='/site/toDetail?mer_item_id=${order.mer_item_id}&mer_price_id=${order.items[0].mer_price_id}&cid=${order.cid}' class='buyBtn'>重新预订</a> </div>";
                return false;
            }
            if(second == 0) {
                second = 59;
            } else {
                second--;
            }
            second = second<10?('0'+second):second;

            if(second == 59 && minute>0) {
                minute--;
            }
            //minute = minute<10?('0'+minute):minute;

            document.getElementById('count-down-re-pay-time').innerHTML = minute + ":" + second;
            var t = setTimeout("lxfEndtime()",1000);
        };


        //判断是否为微信支付
        var ua = window.navigator.userAgent.toLowerCase();
        var payType = "<li class='zfb on' data-type='ALIPAYWAP'><span class='icon'></span><span class='txt font16'>支付宝</span></li>";
        if (ua.match(/MicroMessenger/i) == 'micromessenger') {
            payType = "<li class='wx on' data-type='WEIXINJSAPI'><span class='icon'></span><span class='txt font16'>微信</span></li>";
        }
        $(".paystylelist").html(payType);



        var order_id = '${order.oid}';
        var goods_id = '${order.mer_item_id}';
        var amount = '${order.order_money}';

        $(function () {

            //倒计时
            lxfEndtime();

            //去支付点击
            $("#topaybtn").click(function () {
                $("#choose-pay-type").show();
            })

            //确定支付
            $("#confirm-pay").on("click", function () {

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
                            window.setInterval(function () {
                                checkOrder(order_id);
                            }, 3000);

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
                // window.open("http://test.weixin.bestdo.com/paybank/topay?o=" + order_id + "&a=" + amount + "&s=" + sign + "&l=5");
                window.open(url + "?o=" + order_id + "&a=" + amount + "&s=" + sign + "&l=5");
                return;
            }
            window.open(url);
        }
    </script>

    </#if>
</#if>
<#if order.status?number == 3 || order.status?number == 5>
<div class="orderBtn font17">
    <a href="/order/unsubscribe?order_id=${order.oid}" class="unsubscribe">申请退订</a>
</div>
</#if>
<#if order.status?number == 7 || order.status?number == -2 || order.status?number == 2>
<div class="orderBtn font17">
    <div class="ordreBtnCont">
        <a href="javascript:void(0)" class="buyBtn">重新预订</a>
    </div>
</div>
</#if>
</body>
</html>