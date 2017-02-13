<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>订单列表</title>
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
            <div class="headerC boxflex">
                <p class="font17">全部订单</p>
            </div>
            <div class="headerR"><a href="#" class="txt font14" data-tab="0">运动类型</a></div>
        </div>
    </div>
</div>
<!--运动类型筛选-->
<div class="slidemenu sportsel" style="top:4.4rem;">
    <div class="slidebg"></div>
    <div id="div-order-type" class="slidemenuCont font14">
        <a data-cid="" href="javascript:void(0)" class="i0 on"><span>全部订单</a>
        <a data-cid="109" href="javascript:void(0)" class="i3"><span>游泳</span></a>
        <a data-cid="108" href="javascript:void(0)" class="i4"><span>健身</span></a>
        <a data-cid="102" href="javascript:void(0)" class="i5"><span>羽毛球</span></a>
        <a data-cid="101" href="javascript:void(0)" class="i6"><span>网球</span></a>
        <a data-cid="104" href="javascript:void(0)" class="i7"><span>篮球</span></a>
        <a data-cid="113" href="javascript:void(0)" class="i8"><span>台球</span></a>
        <a data-cid="120" href="javascript:void(0)" class="i1"><span>足球</span></a>
        <a data-cid="106" href="javascript:void(0)" class="i2"><span>乒乓球</span></a>
    </div>
</div>
<div class="wrapper">
    <!--订单状态-->
    <div id="orderStadius">
        <div class="orderStadius">
            <div class="orderStadiusCont font14">
                <ul id="ul-order-status">

                <#if status?number == 9 >
                    <li data-status="9" class="on">全部订单</li>
                <#else >
                    <li data-status="9">全部订单</li>
                </#if>

                <#if status?number == 0 >
                    <li data-status="0" class="on">待付款</li>
                <#else >
                    <li data-status="0">待付款</li>
                </#if>

                <#if status?number == 5 >
                    <li data-status="5" class="on">确认中</li>
                <#else >
                    <li data-status="5">确认中</li>
                </#if>

                <#if status?number == 3 >
                    <li data-status="3" class="on">待下场</li>
                <#else >
                    <li data-status="3">待下场</li>
                </#if>

                <#if status?number == 7 >
                    <li data-status="7" class="on">已结束</li>
                <#else >
                    <li data-status="7">已结束</li>
                </#if>

                </ul>
            </div>
        </div>
    </div>
    <!--订单列表-->
    <div class="orderList">
        <ul id="ul-order-list">

        </ul>
    </div>
    <!--无订单样式-->
    <div id="no-result-show" class="empty noOrder font14">
        <div class="icon"></div>
        <p>暂无订单信息</p>
        <a href="/site/index">立即去下单</a>
    </div>
</div>
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script>
    $(function () {

        //页面加载完后执行
        window.onload = function () {
            var status = $("#ul-order-status").find(".on").data("status");
            var cid = $("#div-order-type").find(".on").data("cid");
            var page = 1;
            var pagesize = 10;
            search(status, cid, page, pagesize, 1);
        }


        $(".slidemenuCont a").on("click", function () {
            var ix = $(".slidemenuCont a").index($(this));
            var status = $("#ul-order-status").find(".on").data("status");
            var cid = $("#div-order-type").find(".on").data("cid");
            var page = 1;
            var pagesize = 10;
            $("#ul-order-list").html('');
            search(status, cid, page, pagesize, 1);
        })
        $(".headerR .txt").tabEve({
            cls: ".slidemenu",
            selected: "on",
            empty: "gray",
            typing: "slidemenu"
        })
        $(".slidemenu a").tabEve({
            cls: ".slidemenu",
            selected: "on",
            typing: "radio"
        })
        /*下拉加载更多*/
      /*  var i = 0;
        $(".orderList ul").loadmore({
            getData: function () {
                i++;
                var str = "<li>" + i + "</li>";
                return str;
            }
        });*/

        //订单状态点击
        $(".orderStadiusCont ul li").click(function () {
            $(".orderStadiusCont ul li").removeClass("on")
            $(this).addClass("on");
            var status = $("#ul-order-status").find(".on").data("status");
            var cid = $("#div-order-type").find(".on").data("cid");
            var page = 1;
            var pagesize = 10;
            $("#ul-order-list").html('');
            search(status, cid, page, pagesize, 1);
        })


    })

    //只负责append 如果需要重新加载内容 方法执行前清空ul内容
    //搜索 noResultSow 1 没有结果的话显示，立即下单
    function search(status, cid, page, pagesize, noResultSow) {

        $("#no-result-show").hide();

        $.ajax({
            type: "POST",
            url: "/order/orderListsSearch",
            async: false,
            data: {
                "status": status,
                "cid": cid,
                "page": page,
                "pagesize": pagesize
            },
            success: function (result) {
                if (200 === result.code) {
                    if (result.object.orders.length < 1) {
                        if (1 == noResultSow) {
                            $("#no-result-show").show();
                        }
                    } else {
                        var source = $("#template").html();
                        var template = Handlebars.compile(source);
                        var html = template(result.object.orders);
                        $("#ul-order-list").append(html);
                    }
                } else {
                    alert("获取订单状态出错")
                }
            }
        });
    }

</script>

<script src="/js/handlebars-v4.0.5.js"></script>
<script id="template" type="text/x-handlebars-template">
    {{#each this}}
    <li>
        <a href="/order/detail?oid={{oid}}">
            <div class="lcont box">
                <div class="imgwrap"><img src="{{stadium.thumb}}"></div>
                <div class="imginfo boxflex font12">
                    <h1 class="font14">{{mer_item_name}}</h1>
                    <p>{{time}}</p>
                    <p>数量{{book_number}}<span>{{money}}</span></p>
                </div>
                {{#if status}}
                <div class="otherinfo"><p class="font13">{{statusName}}</p></div>
                {{else}}
                <div class="otherinfo font12">
                    <#--<div class="timeout">剩余：<span>{{remain_time}}</span></div>-->
                    <a href="/order/detail?oid={{oid}}" class="topaybtn">去支付</a>
                </div>
                {{/if}}
            </div>
        </a>
    </li>
    {{/each}}
</script>
</body>
</html>