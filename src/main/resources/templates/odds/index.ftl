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
    <script language="javascript" type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=31ZZ5PhZPGyzmA5UlGYEDG29"></script>
    <script language="javascript" type="text/javascript"
            src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
</head>

<body>
<#--<div id="allmap" style="display: none;"></div>-->
<!--头部公用-->
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">场馆预订</p></div>
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
        <a href="javascript:void(0)" class="on" data-merid="-1">全部</a>
    <#--<a href="javascript:void(0)" data-merid="1020545">羽毛球</a>
    <a href="javascript:void(0)" data-merid="1020544">网球</a>
    <a href="javascript:void(0)" data-merid="1020538">游泳</a>
    <a href="javascript:void(0)" data-merid="1020551">乒乓球</a>
    <a href="javascript:void(0)" data-merid="1020552">器械健身</a>
    <a href="javascript:void(0)" data-merid="1020549">足球</a>
    <a href="javascript:void(0)" data-merid="1020550">篮球</a>
    <a href="javascript:void(0)" data-merid="1020546">综合资源</a>-->
    <#if goodsTypes?? >
        <#list goodsTypes as goodsType>
            <a href="javascript:void(0)" data-merid="${goodsType.merid}">${goodsType.sport}</a>
        </#list>
    </#if>
    <#--<#list goodsTypes as goodsType>-->
    <#-- <#if goodsType_index == 0  >
         <a href="javascript:void(0)" class="on" data-merid="${goodsType.merid}">${goodsType.sport}</a>
     <#else >-->
    <#--<a href="javascript:void(0)" data-merid="${goodsType.merid}">${goodsType.sport}</a>-->
    <#--</#if>-->
    <#--</#list>-->
    <#--</#if>-->
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
        <a href="javascript:void(0)" data-value="10000" data-distanacesrot="" class="on">不限距离</a>
        <a href="javascript:void(0)" data-value="" data-distanacesrot="asc">距离最近</a>
        <a href="javascript:void(0)" data-value="5" data-distanacesrot="">附近5km</a>
        <a href="javascript:void(0)" data-value="10" data-distanacesrot="">附近10km</a>
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
    <div id="no-result" class="empty" style="display: none">
        <div class="icon"></div>
        <p class="font14">暂无相关相关场地信息</p>
    </div>
</div>

<!--城市地位-->
<div class="position">
    <div class="positionCont">
        <div class="positionContBg box font16">
            <div class="icon"></div>
            <div id="user-position" class="txt boxflex">盐城</div>
            <div id="user-position-refresh" class="refresh"></div>
        </div>
    </div>
</div>

<!--地图图标-->
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script>

    var longitude = '120.164505';
    var latitude = '33.346308';
    var cardId = '${cardId}';

    var resultTmp = "";


    var total = -1;
    var totalPage = -1;

    baiduLocation();

    $(function () {

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
            window.location.href = "/site/toDetail?mer_item_id=" + merid + "&mer_price_id=" + mer_price_id + "&cid=" + cid + "&cardId=" + cardId;
        });

        //重新定位
        $("#user-position-refresh").click(function () {
            window.location.href = window.location.href;
        });


        /*下拉加载更多*/
        $(".venueslist ul").loadmore({
            getData: function () {

                page = page + 1;

                if (totalPage != -1 && page > totalPage) {
                    return;
                }

                var merid = $("#sport-type").find(".on").data("merid");

                //点击全部 每个类型获取五次
                if (merid == -1) {
                    venueSearchOnLoad(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district);
                    return;
                }
                venueSearch(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district);
                return "";
            }
        });

    });


    //筛选条件触发
    function clickSeach() {
        var merid = $("#sport-type").find(".on").data("merid");
        var radius = $("#distance").find(".on").data("value");
        var sort = $("#distance").find(".on").data("distanacesrot");
        var price_sort;
        var page = 1;
        var pagesize = 10;
        var district = $("#administrative-area").find(".on").data("value");
        $("#googDetail-list").html('');

        if (sort.length > 1) {
            radius = 10000;
        }

        //点击全部 每个类型获取五次
        if (merid == -1) {
            venueSearchOnLoad(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district);
            return;
        }

        venueSearch(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district);
    }

    //场馆列表搜索
    function venueSearchOnLoad(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district) {
        //没有结果提示隐藏
        $("#no-result").hide();

        $("#sport-type a").each(function () {
            if ($(this).data("merid") == -1) {
                return;
            }
            $.ajax({
                type: "POST",
                url: "/odds/search",
                async: false,
                data: {
                    "merid": $(this).data("merid"),
                    "radius": radius,
                    "longitude": longitude,
                    "latitude": latitude,
                    "sort": sort,
                    "price_sort": price_sort,
                    "page": 1,
                    "pagesize": 5,
                    "district": district
                },
                success: function (result) {
                    if (200 === result.code) {
                        if (result.total > 0) {
                            var source = $("#googDetail-template").html();
                            var template = Handlebars.compile(source);
                            Handlebars.registerHelper('if_price', function (value, options) {
                                return Math.floor(value) / 100;
                            });
                            Handlebars.registerHelper('if_images', function (value, options) {
                                var str = "";
                                var splitArr = value.split(".");
                                var length = splitArr.length;
                                for (var i = 0; i < length - 1; i++) {
                                    str += splitArr[i] + ".";
                                }
                                str += "_90x90." + splitArr[length - 1];
                                return str;
                            });
                            resultTmp += template(result.lists);
                            $("#googDetail-list").append(template(result.lists));


                            if (result.total > total) {
                                total = result.total;
                                totalPage = result.totalPage;
                            }

                        }
                    }
                }
            });
        })

        if (resultTmp.length > 0) {
            $(".load-container").hide();
        } else {
            $("#no-result").show();
        }
    }


    //场馆列表搜索
    function venueSearch(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district) {
        //$(".load-container").show();
        //没有结果提示隐藏
        $("#no-result").hide();
        $.ajax({
            type: "POST",
            url: "/odds/search",
            async: false,
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

                        Handlebars.registerHelper('if_price', function (value, options) {
                            return Math.floor(value) / 100;
                        });

                        Handlebars.registerHelper('if_images', function (value, options) {
                            var str = "";
                            var splitArr = value.split(".");
                            var length = splitArr.length;
                            for (var i = 0; i < length - 1; i++) {
                                str += splitArr[i] + ".";
                            }
                            str += "_90x90." + splitArr[length - 1];
                            return str;
                        });

                        var html = template(result.lists);
                        $(".load-container").hide();
                        $("#googDetail-list").append(html);
                    } else {
                        $("#no-result").show();
                    }

                    total = result.total;
                    totalPage = result.totalPage;

                }
            }
        });
    }

    //百度定位
    function baiduLocation() {

        //安卓
        if (typeof(android) != "undefined") {
            if (typeof android.getCurrentPosition === 'undefined') {
                android.requestMap = {};
                android.requestId = 0;
                android.getCurrentPosition = function (request) {
                    var requestId = android.requestId++;
                    android.requestMap[requestId] = request;
                    android._getLocation(requestId)
                }
                android.reciveResponce = function (requestId, text) {
                    android.requestMap[requestId](JSON.parse(text));
                }
            }
        }

        if (typeof(android) != "undefined" && android.getCurrentPosition) {
            android.getCurrentPosition(showPosition);
            return
        }


        new BMap.Geolocation().getCurrentPosition(function (r) {
            if (r.accuracy != null && this.getStatus() == BMAP_STATUS_SUCCESS) {
                longitude = r.point.lng;
                latitude = r.point.lat;
                var merid = $("#sport-type").find(".on").data("merid");
                var radius = $("#distance").find(".on").data("value");
                var sort = $("#distance").find(".on").data("distanacesrot");
                var price_sort;
                var page = 1;
                var pagesize = 10;
                var district = $("#administrative-area").find(".on").data("value");
                $("#googDetail-list").html('');

                venueSearchOnLoad(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district);

                var pt = new BMap.Point(longitude, latitude);
                new BMap.Geocoder().getLocation(pt, function (rs) {
                    var addComp = rs.addressComponents;
                    $("#user-position").html(addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);
                });

            } else {
                var merid = $("#sport-type").find(".on").data("merid");
                var radius = $("#distance").find(".on").data("value");
                var sort = $("#distance").find(".on").data("distanacesrot");
                var price_sort;
                var page = 1;
                var pagesize = 10;
                var district = $("#administrative-area").find(".on").data("value");
                $("#googDetail-list").html('');
                venueSearchOnLoad(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district);
            }
        }, {enableHighAccuracy: true})
    }


    //安卓获取经纬度
    function showPosition(position) {
        if (position.code == 0) {
            longitude = position.longitude;
            latitude = position.latitude;
        }

        var merid = $("#sport-type").find(".on").data("merid");
        var radius = $("#distance").find(".on").data("value");
        var sort = $("#distance").find(".on").data("distanacesrot");
        var price_sort;
        var page = 1;
        var pagesize = 10;
        var district = $("#administrative-area").find(".on").data("value");
        $("#googDetail-list").html('');

        venueSearchOnLoad(merid, radius, longitude, latitude, sort, price_sort, page, pagesize, district);

        var pt = new BMap.Point(longitude, latitude);
        new BMap.Geocoder().getLocation(pt, function (rs) {
            var addComp = rs.addressComponents;
            $("#user-position").html(addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);
        })

    }
</script>


<script src="/js/handlebars-v4.0.5.js"></script>

<script id="googDetail-template" type="text/x-handlebars-template">
    {{#each this}}
    <li class="box vip" data-mer_item_id="{{mer_item_id}}" data-mer_price_id="{{mer_price_id}}" data-cid="{{cid}}">
        <div class="venuesimg"><img src="{{#if_images thumb}}{{thumb}}{{/if_images}} "/></div>
        <div class="venuesdetial boxflex">
            <h2 class="font16">{{name}}</h2>
            <div class="price font12">
            <#-- <span class="font18">￥{{price}}</span>-->
                <span class="font18">￥{{#if_price price}} {{price}} {{/if_price}}</span>
            </div>
            <div class="address font12">
                <span class="d"><#--{{geodist}}km-->{{geodistStr}}</span>
            <#-- <span class="q">{{region}}</span>-->
                <span class="p">{{region}}</span>
            </div>
        </div>
    </li>
    {{/each}}
</script>
<script type="text/javascript">
    var addrs = $('#administrative-area').find('a'); liandong(addrs);
    var distance = $('#distance').find('a'); liandong(distance);
    var sporttype = $('#sport-type').find('a'); liandong(sporttype);
    function liandong(c){
        for(var i = 0 ; i < c.length ; i++)
        {
            c.eq(i).on('touchend',function(){
                var v = $(this).html();
                $('.chooseTabCont .on').html(v+'<span></span>')
            })
        }
    }
</script>
</body>
</html>
