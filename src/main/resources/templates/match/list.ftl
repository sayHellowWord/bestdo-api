<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>体育赛事</title>
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
                <p class="font17">体育赛事</p>
            </div>
        </div>
    </div>
</div>
<div class="chooseTab www25 font13">
    <div class="chooseTabCont">
        <a href="javascript:void(0)" data-tab="0">运动类型<span></span></a>
        <a href="javascript:void(0)" data-tab="1">行政区<span></span></a>
        <a href="javascript:void(0)" data-tab="2" class="">报名状态<span></span></a>
    </div>
</div>
<!-- 运动类型 -->
<div class="slidemenu">
    <div class="slidebg"></div>
    <div id="itemCode" class="slidemenuCont font14">
        <a href="javascript:void(0)" class="on" data-value="">全部</a>
        <a href="javascript:void(0)" data-value="jianshen">健身</a>
        <a href="javascript:void(0)" data-value="lanqiu">篮球</a>
        <a href="javascript:void(0)" data-value="pingpangqiu">乒乓球</a>
        <a href="javascript:void(0)" data-value="wangqiu">网球</a>
        <a href="javascript:void(0)" data-value="youyong">游泳</a>
        <a href="javascript:void(0)" data-value="yumaoqiu">羽毛球</a>
        <a href="javascript:void(0)" data-value="zonghelei">综合类</a>
        <a href="javascript:void(0)" data-value="zuqiu">足球</a>
        <a href="javascript:void(0)" data-value="qita">其他</a>
    </div>
</div>
<!-- 行政区 -->
<div class="slidemenu">
    <div class="slidebg"></div>
    <div id="regionName" class="slidemenuCont font14">
        <a href="javascript:void(0)" data-value="" class="on">全部区域</a>
    <#if regions??>
        <#list regions as regions>
            <a href="javascript:void(0)" data-value="${regions.name}">${regions.name}</a>
        </#list>
    </#if>
    </div>
</div>
<!-- 距离 -->
<div class="slidemenu">
    <div class="slidebg"></div>
    <div id="entryStatus" class="slidemenuCont font14">
        <a href="javascript:void(0)" data-value="" class="on">全部</a>
        <a href="javascript:void(0)" data-value="1" >开启</a>
        <a href="javascript:void(0)" data-value="0" >关闭</a>
    </div>
</div>

<!--赛事列表-->
<div class="wrapper">
    <!--赛事列表-->
    <div class="venueslist">
        <ul id="list" class="list">

        </ul>
    </div>

    <!--场馆列表结束-->
    <div id="no-result" class="empty">
        <div class="icon"></div>
        <p class="font14">暂无相关相关赛事信息</p>
    </div>

</div>

<!--地图图标-->
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script>


    var page = ${page};
    var pagesize = ${pagesize};

    var total = -1;
    var totalPage = -1;

    $(function () {

        // 获取行政区字段
        var area = $('.nearby .on').data('value');
        // 获取排序字段
        var order = '';

        // 初始化加载数据
        search(area, order, page, pagesize);

        $(".chooseTab a").tabEve({
            cls: ".slidemenu",
            selected: "on",
            empty: "gray",
            typing: "slidemenu"
        })

        //筛选
        $("#itemCode a").click(function () {
            $("#itemCode a").removeClass("on");
            $(this).addClass("on");
            $("#list").html('');
            search(area, order,page, pagesize);
        });

        $("#regionName a").click(function () {
            $("#regionName a").removeClass("on");
            $(this).addClass("on");
            $("#list").html('');
            search(area, order,page, pagesize);
        });

        $("#entryStatus a").click(function () {
            $("#entryStatus a").removeClass("on");
            $(this).addClass("on");
            $("#list").html('');
            search(area, order,page, pagesize);
        });


        /*下拉加载更多*/
        $(".venueslist ul").loadmore({
            getData: function () {
                page = page + 1;
                search(searchKeyword, page, pagesize);
                return "";
            }
        });

    })

    //只负责查询和追加数据，如果需要刷新页面（如查询）请执行前自己情况list数据
    function search(area, order, page, rows) {

        //没有结果提示隐藏
        $("#no-result").hide();

        if (totalPage != -1 && page > totalPage) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "/cms/match/list/yc",
            data: {
                "itemCode": $("#itemCode").find(".on").data("value"),
                "regionName": $("#regionName").find(".on").data("value"),
                "entryStatus": $("#entryStatus").find(".on").data("value"),
                "page": page,
                "rows": rows
            },
            success: function (resultData) {
                resultHandler(resultData);
                total = resultData.total;
                totalPage = Math.ceil(total / pagesize);
            }
        });
    }

    function resultHandler(result) {
        if (200 === result.code) {
            var source = $("#template").html();
            var template = Handlebars.compile(source);
            Handlebars.registerHelper('if_showImg', function (value, options) {
                return value.split(';')[0];
            });
            Handlebars.registerHelper('if_status', function (value, options) {
                if (value == 0) {
                    return "关闭";
                } else if (value == 1) {
                    return "开启";
                } else {
                    return "";
                }
            });

            //当前未分页所以这样做 TODO
            if (result.data.length > 0) {
                var html = template(result.data);
                $("#list").append(html);
            } else {
                $("#no-result").show();
            }

        } else {
            alert(result.data);
        }
    }

</script>

<script src="/js/handlebars-v4.0.5.js"></script>

<script id="template" type="text/x-handlebars-template">
    {{#each this}}
    <li class="box vip vip2">
        <div class="venuesimg">
            <a href="/cms/match/detail?id={{id}}">
                <img src="{{#if_showImg thumbnail}} {{thumbnail}} {{/if_showImg}}"
                     onerror="this.src='/images/default.jpg'">
            </a>
        </div>

        <div class="venuesdetial boxflex">
            <a href="/cms/match/detail?id={{id}}">
                <h2 class="font16">{{name}} </h2>
                <div class="address add3 font12">
                    <span class="p">报名时间：{{startDate}}-{{endDate}}</span>
                </div>
                <div class="address add3 font12">
                    <span class="p">比赛时间：{{matchDate}}</span>
                </div>
                <div class="address add3 font12">
                    <span class="p">赛事地点：{{address}}</span>
                </div>
            </a>
        </div>
        <div class="xiajia"><p class="font14 on">{{#if_status entryStatus}} {{entryStatus}} {{else}} {{entryStatus}}
            {{/if_status}}</p></div>
    </li>
    {{/each}}
</script>
<script type="text/javascript">
    var addrs = $('#itemCode').find('a');
    liandong(addrs);
    var distance = $('#regionName').find('a');
    liandong(distance);
    var sporttype = $('#entryStatus').find('a');
    liandong(sporttype);
    function liandong(c) {
        for (var i = 0; i < c.length; i++) {
            c.eq(i).on('touchend', function () {
                var v = $(this).html();
                $('.chooseTabCont .on').html(v + '<span></span>')
            })
        }
    }
</script>
</body>
</html>
