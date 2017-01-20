<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>场馆列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
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
            <div class="headerL"><a href="/" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">场馆预订</p></div>
            <div class="headerR"></div>
        </div>
    </div>
</div>
<div class="chooseTab www25 font13">
    <div class="chooseTabCont">
        <a href="javascript:void(0)" data-tab="0">
            运动类型<span></span>
        </a>
        <a href="javascript:void(0)" data-tab="1">
            行政区<span></span>
        </a>
        <a href="javascript:void(0)" data-tab="2">
            距离<span></span>
        </a>
    </div>
</div>
<!--运动类型-->
<div class="slidemenu">
    <div class="slidebg"></div>
    <div id="sport-type" class="slidemenuCont font14">
    <#if goodsTypes?? >
        <#list goodsTypes as goodsType>
            <#if goodsType_index == 0  >
                <a href="javascript:void(0)" class="on" data-merid="${goodsType.merid}">${goodsType.name}</a>
            <#else >
                <a href="javascript:void(0)" data-merid="${goodsType.merid}">${goodsType.name}</a>
            </#if>
        </#list>
    </#if>
    </div>
</div>
<!--行政区-->
<div class="slidemenu">
    <div class="slidebg"></div>
    <div id="administrative-area" class="slidemenuCont font14">
        <a href="javascript:void(0)" data-value="" class="on">全部区域</a>
    <#if regions??>
        <#list regions as regions>
            <a href="javascript:void(0)" data-value="${regions.region_id}">${regions.name}</a>
        </#list>
    </#if>
    </div>
</div>
<!--距离-->
<div class="slidemenu">
    <div class="slidebg"></div>
    <div id="distance" class="slidemenuCont font14">
        <a href="javascript:void(0)" data-value="10000000000000000" class="on">不限距离</a>
        <a href="javascript:void(0)" data-value="">距离最近</a>
        <a href="javascript:void(0)">优惠最多</a>
        <a href="javascript:void(0)" data-value="5000">附近5km</a>
        <a href="javascript:void(0)" data-value="10000">附近10km</a>
    </div>
</div>
<!--场馆列表-->
<div class="wrapper">
    <!--场馆列表-->
    <div class="venueslist">
        <ul id="googDetail-list" class="list">

        </ul>
    <#-- <div id="data-loading" class="load-container font12">
         <div class="loader"></div>
         数据加载中...
     </div>-->
    </div>

    <!--场馆列表结束-->
    <div id="no-result" class="empty">
        <div class="icon"></div>
        <p class="font14">暂无相关相关场地信息</p>
    </div>
</div>
<!--城市地位-->
<#--
<div class="loading">
    <div class="loadCont"></div>
    <div class="loadBg"></div>
</div>-->
<!--地图图标-->
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script>

    var longitude = '${coordinate.lng}';
    var latitude = '${coordinate.lat}';


    $(function () {

        //初始化加载
        var merid = $("#sport-type").find(".on").data("merid");
        var radius = $("#distance").find(".on").data("value");
        var sort;
        var price_sort;
        var page = 1;
        var pagesize = 10;
        var district = $("#administrative-area").find(".on").data("value");
        $("#googDetail-list").html('');

        venueSearch(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district);

        /*筛选条件*/
        $(".chooseTab a").tabEve({
            cls: ".slidemenu",
            selected: "on",
            empty: "gray",
            typing: "slidemenu"
        });

        //筛选列表点击--运动类型
        $(".slidemenu").on("click", "#sport-type > a", function () {
            $("#sport-type a").removeClass("on");
            $(this).addClass("on");
            clickSeach();
        });

        //筛选列表点击--行政区
        $(".slidemenu").on("click", "#administrative-area > a", function () {
            $("#administrative-area a").removeClass("on");
            $(this).addClass("on");
            clickSeach();
        });

        //筛选列表点击--排序
        $(".slidemenu").on("click", "#distance > a", function () {
            $("#distance a").removeClass("on");
            $(this).addClass("on");
            clickSeach();
        });

        //文章详情点击
        $("#googDetail-list").on("click", "li", function () {
            var merid = $(this).data("mer_item_id");
            var mer_price_id = $(this).data("mer_price_id");
            var cid = $(this).data("cid");
            window.location.href = "/site/toDetail?mer_item_id=" + merid + "&mer_price_id=" + mer_price_id + "&cid=" + cid;
        });


    });


    //筛选条件触发
    function clickSeach() {
        var merid = $("#sport-type").find(".on").data("merid");
        var radius = $("#distance").find(".on").data("value");
        var sort;
        var price_sort;
        var page = 1;
        var pagesize = 10;
        var district = $("#administrative-area").find(".on").data("value");
        $("#googDetail-list").html('');
        venueSearch(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district);
    }


    //场馆列表搜索
    function venueSearch(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district) {
        //$(".load-container").show();
        $.ajax({
            type: "POST",
            url: "/site/search",
            data: {
                "merid": merid,
                "radius": radius,
                "longitude": longitude,
                "latitude": latitude,
                "sort": sort,
                "price_sort": price_sort,
                "page": page,
                "pagesize": pagesize,
                "district": district
            },
            success: function (result) {
                if (200 === result.code) {
                    //  $(".load-container").hide();
                    if (result.total > 0) {
                        var source = $("#googDetail-template").html();
                        var template = Handlebars.compile(source);
                        var html = template(result.lists);
                        $(".load-container").hide();
                        $("#googDetail-list").append(html);
                    } else {
                        $("#no-result").show();
                    }
                }
            }
        });
    }
</script>


<script src="/js/handlebars-v4.0.5.js"></script>

<script id="googDetail-template" type="text/x-handlebars-template">
    {{#each this}}
    <li class="box vip" data-mer_item_id="{{mer_item_id}}" data-mer_price_id="{{mer_price_id}}" data-cid="{{cid}}">
        <div class="venuesimg"><img src="{{thumb}}"/></div>
        <div class="venuesdetial boxflex">
            <h2 class="font16">{{name}}</h2>
            <div class="price font12">
                <span class="font18">￥{{price}}</span>
            </div>
            <div class="address font12">
                <span class="d">{{geodist}}km</span>
                <span class="q">{{region}}</span>
                <span class="p">{{region}}</span>
            </div>
        </div>
    </li>
    {{/each}}
</script>

</body>
</html>