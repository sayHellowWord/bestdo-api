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
</div>
<div class="chooseTab font13">
    <div class="chooseTabCont">
        <a href="javascript:void(0)" data-tab="0">
            行政区<span></span>
        </a>
        <a href="javascript:void(0)" data-tab="1">
            排序<span></span>
        </a>
    </div>
</div>
<!--区域-->
<div class="slidemenu" data-tab="0">
    <div class="slidebg"></div>
    <div class="slidemenuCont font14 nearby">
        <a href="javascript:void(0)" data-value="" class="on">全部区域</a>
    <#if regions??>
        <#list regions as regions>
            <a href="javascript:void(0)" data-value="${regions.region_id}">${regions.name}</a>
        </#list>
    </#if>
    </div>
</div>
<!--排序-->
<div class="slidemenu" data-tab="1">
    <div class="slidebg"></div>
    <div class="slidemenuCont font14 sort">
        <a href="javascript:void(0)" data-value="asc" class="on">按时间排序</a>
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
        <p class="font14">暂无相关相关场地信息</p>
    </div>

</div>

<!--地图图标-->
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script>
    $(function () {

        // 获取行政区字段
        var area = $('.nearby .on').data('value');
        // 获取排序字段
        var order = '';
        // var order = $('.sort .on').data('value');

        // 初始化加载数据
        search(area, order, 1, 15);

        /*var xmlhttp=new XMLHttpRequest();
        if (xmlhttp.readyState <4 || xmlhttp.status != 200){
            $(".loading").hide();
        }
        document.onreadystatechange = completeLoading;
        function completeLoading() {
            if (document.readyState == "complete") {
                $(".loading").hide();
            }
        }*/
        /*筛选条件*/
        /* $(".slidemenuCont a").on("click",function(){
             var ix = $(".slidemenuCont a").index($(this));
         })*/

        $(".chooseTab a").tabEve({
            cls: ".slidemenu",
            selected: "on",
            empty: "gray",
            typing: "slidemenu"
        })

        //区域
        $("div.nearby a").click(function () {

            //替换title
            //$("div.chooseTabCont a.geo").html($(this).html() + '<span></span>');

            var ind = $(this).parent().parent().data('tab');
            var titleTxt = '';
            if ($(this).text() === '全部区域') {
                titleTxt = '行政区';
            } else {
                titleTxt = $(this).text();
            }
            $('div.chooseTabCont a').eq(ind).html(titleTxt + '<span></span>');
            $("body").click();

            $("div.nearby").children('a').each(function (index, item) {
                $(item).removeClass('on');
            });

            $(this).addClass('on');

            area = $('.nearby .on').data('value');
            order = $('.sort .on').data('value');
            $('#list').html('');
            search(area, order, 1, 15);

            //scope.district = $(this).attr('data-tag');

            //againReload();
        });

        //排序
        $("div.sort a").click(function () {

            //替换title
            //$("div.chooseTabCont a.geo").html($(this).html() + '<span></span>');

            var ind = $(this).parent().parent().data('tab');
            var titleTxt = '';
            if ($(this).text() === '按时间排序') {
                titleTxt = '排序';
            } else {
                titleTxt = $(this).text();
            }
            $('div.chooseTabCont a').eq(ind).html(titleTxt + '<span></span>');

            $("body").click();

            $("div.sort").children('a').each(function (index, item) {
                $(item).removeClass('on');
            });

            $(this).addClass('on');

            area = $('.nearby .on').data('value');
            order = $('.sort .on').data('value');
            $('#list').html('');
            search(area, order, 1, 15);

            //scope.district = $(this).attr('data-tag');

            //againReload();
        });

    })

    //只负责查询和追加数据，如果需要刷新页面（如查询）请执行前自己情况list数据
    function search(area, order, page, rows) {

        //没有结果提示隐藏
        $("#no-result").hide();

        $.ajax({
            type: "POST",
            url: "/cms/match/list/yc",
            data: {
                "district": area,
                "time_sort": order,
                "page": page,
                "rows": rows
            },
            success: function (resultData) {
                resultHandler(resultData);
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
                <img src="{{#if_showImg thumbnail}} {{thumbnail}} {{/if_showImg}}" onerror="this.src='/images/default.jpg'" >
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
</body>
</html>
