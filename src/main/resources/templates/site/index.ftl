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
        <a href="javascript:void(0)" class="on">全部</a>
        <#list goodsTypes as goodsType>
            <a href="javascript:void(0)" data-merid="${goodsType.merid}">${goodsType.name}</a>
        </#list>
    </div>
</div>
<!--行政区-->
<div class="slidemenu">
    <div class="slidebg"></div>
    <div id="administrative-area" class="slidemenuCont font14">
        <a href="javascript:void(0)" class="on">全部区域</a>
        <a href="javascript:void(0)" data-value="1">亭湖区</a>
        <a href="javascript:void(0)" data-value="2">盐都区</a>
    </div>
</div>
<!--距离-->
<div class="slidemenu">
    <div class="slidebg"></div>
    <div id="distance" class="slidemenuCont font14">
        <a href="javascript:void(0)" class="on">不限距离</a>
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
        <#list goodsDetailResultBean.lists as googDetail>
            <li class="box vip" data-mer_item_id="${googDetail.mer_item_id}" data-mer_price_id="${googDetail.mer_price_id}" >
                <div class="venuesimg"><img src="${googDetail.thumb}"/></div>
                <div class="venuesdetial boxflex">
                    <h2 class="font16">${googDetail.name}</h2>
                    <div class="price font12">
                        <span class="font18">￥${googDetail.min_price!0}</span>起<i>门市价￥${googDetail.price!0}</i>
                    </div>
                    <div class="address font12">
                        <span class="d">${googDetail.geodist}</span>
                        <#list googDetail.region?split(";") as region>
                            <#if region_index == 2>
                                <span class="q">${region}</span>
                            </#if>
                            <#if region_index == 1>
                                <span class="p">${googDetail.region}</span>
                            </#if>
                        </#list>
                    <#-- <span class="q">${googDetail.region}</span>
                     <span class="p">${googDetail.region}</span>-->
                    </div>
                </div>
            </li>
        </#list>
        </ul>
        <div class="load-container font12" style="display: none;">
            <div class="loader"></div>
            数据加载中...
        </div>
    </div>

    <!--场馆列表结束-->
    <div class="empty">
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
    $(function () {

        /*筛选条件*/
        $(".chooseTab a").tabEve({
            cls: ".slidemenu",
            selected: "on",
            empty: "gray",
            typing: "slidemenu"
        });

        //筛选列表点击
        $(".slidemenu").on("click", "#sport-type > a,#administrative-area > a,#distance > a", function () {

            alert($("#sport-type").find(".on").data("merid"));

            var merid = $("#sport-type").find(".on").data("merid");
            var radius = $("#distance").find(".on").data("value");
            var longitude;
            var latitude;
            var sort;
            var price_sort;
            var page = 1;
            var pagesize = 10;
            var district = $("#administrative-area").find(".on").data("value");
            $("#googDetail-list").html('');
            venueSearch(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district);
        });

        //文章详情点击
        $("#googDetail-list").on("click", "li", function () {
            var merid = $(this).data("mer_item_id");
            var mer_price_id = $(this).data("mer_price_id");
            window.location.href = "/site/toDetail?mer_item_id=" + merid +"&mer_price_id="+mer_price_id;
        });

    });


    //场馆列表搜索
    function venueSearch(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district) {
        $(".load-container").show();
        $.ajax({
            type: "POST",
            url: "../site/search",
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
                    var source = $("#googDetail-template").html();
                    var template = Handlebars.compile(source);
                    var html = template(result.lists);
                    $(".load-container").hide();
                    $("#googDetail-list").append(html);
                }
            }
        });
    }
</script>


<script src="../js/handlebars-v4.0.5.js"></script>

<script id="googDetail-template" type="text/x-handlebars-template">
    {{#each this}}
    <li class="box vip" data-mer_item_id="{{mer_item_id}}" data-mer_price_id="{{mer_price_id}} ">
        <div class="venuesimg"><img src="{{thumb}}"/></div>
        <div class="venuesdetial boxflex">
            <h2 class="font16">{{name}}</h2>
            <div class="price font12">
                <span class="font18">￥{{min_price}}</span>起<i>门市价￥{{price}}</i>
            </div>
            <div class="address font12">
                <span class="d">{{geodist}}</span>
                <span class="q">{{region}}</span>
                <span class="p">{{region}}</span>
            </div>
        </div>
    </li>
    {{/each}}
</script>

</body>
</html>