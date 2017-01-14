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
<#--<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
            <div class="headerC boxflex">
                <p class="font17">体育赛事</p>
            </div>
        </div>
    </div>
</div>-->
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
            <div class="headerC boxflex">
                <div class="search box font14">
                    <div class="searchInput boxflex">
                        <span class="icon"></span>
                        <input id="search-keyword" type="text" class="font14" placeholder="输入场地名称或行政区" >
                    </div>
                    <a id="btn-search" href="javascript:void(0)" class="btn">搜索</a>
                </div>
            </div>
        </div>
    </div>
</div>

<#--<div class="chooseTab font13">
    <div class="chooseTabCont">
        <a href="javascript:void(0)" data-tab="0">
            行政区<span></span>
        </a>
        <a href="javascript:void(0)" data-tab="1">
            排序<span></span>
        </a>
        <a href="javascript:void(0)" data-tab="2">
            距离<span></span>
        </a>
    </div>
</div>-->
<!--区域-->
<#--<div class="slidemenu">
    <div class="slidebg"></div>
    <div class="slidemenuCont font14 nearby">
        <a href="javascript:void(0)" class="on">全部区域</a>
        &lt;#&ndash;todo 测试数据待删除&ndash;&gt;
        <a href="javascript:void(0)" data-value="1935">亭湖区</a>
        <#if regions??>
            <#list regions as regions>
                <a href="javascript:void(0)" data-value="${regions.region_id}">${regions.name}</a>
            </#list>
        </#if>
    </div>
</div>
<!--排序&ndash;&gt;
<div class="slidemenu">
    <div class="slidebg"></div>
    <div class="slidemenuCont font14 sort">
        <a href="javascript:void(0)" class="on">按时间排序</a>
    </div>
</div>
<!--距离&ndash;&gt;
<div class="slidemenu">
    <div class="slidebg"></div>
    <div class="slidemenuCont font14 distance">
        <a href="javascript:void(0)" class="on">不限距离</a>
        <a href="javascript:void(0)">距离最近</a>
        <a href="javascript:void(0)">附近5km</a>
        <a href="javascript:void(0)">附近10km</a>
    </div>
</div>-->
<!--赛事列表-->
<div class="wrapper">
    <!--赛事列表-->
    <div class="venueslist">
        <ul id="list" class="list">

        </ul>
    </div>
</div>

<!--地图图标-->
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script type="text/javascript">
    $(function () {
        // 获取搜索关键词
        var searchKeyword = $('#search-keyword').val();

        //初始化加载数据
        search(searchKeyword, 1, 15);

        //点击搜索按钮
        var btnSearch = $('#btn-search');
        btnSearch.on("click", function() {
            $("#list").html('');
            searchKeyword = $('#search-keyword').val();
            search(searchKeyword, 1, 15);
        })

        /*var xmlhttp = new XMLHttpRequest();
        if (xmlhttp.readyState < 4 || xmlhttp.status != 200) {
            $(" .loading").hide();
        }
        document.onreadystatechange = completeLoading;
        function completeLoading() {
            if (document.readyState == "complete") {
                $(".loading").hide();
            }
        }*/

        /*筛选条件*/
        $(".slidemenuCont a").on("click", function () {
            var ix = $(".slidemenuCont a").index($(this));
        })

        $(".chooseTab a").tabEve({
            cls: ".slidemenu",
            selected: "on",
            empty: "gray",
            typing: "slidemenu"
        })

        //区域
        $("div.nearby a").click(function () {
            //替换title
            $("div.chooseTabCont a.geo").html($(this).html() + '<span></span>');
            $("body").click();
            $("div.nearby").children('a').each(function (index, item) {
                $(item).removeClass('on');
            });
            $(this).addClass('on');
            scope.district = $(this).attr('data-tag');
            againReload();
        });

        //排序
        $("div.sort a").click(function () {
            //替换title
            $("div.chooseTabCont a.geo").html($(this).html() + '<span></span>');
            $("body").click();
            $("div.sort").children('a').each(function (index, item) {
                $(item).removeClass('on');
            });
            $(this).addClass('on');
            scope.district = $(this).attr('data-tag');
            againReload();
        });

        //距离
        /*$("div.distance a").click(function () {
            //替换title
            $("div.chooseTabCont a.geo").html($(this).html() + '<span></span>');
            $("body").click();
            $("div.distance").children('a').each(function (index, item) {
                $(item).removeClass('on');
            });
            $(this).addClass('on');
            scope.district = $(this).attr('data-tag');
            againReload();
        });*/

    })

    //只负责查询和追加数据，如果需要刷新页面（如查询）请执行前自己情况list数据
    function search(keyword, page, rows) {
        $.ajax({
            type: "POST",
            url: "/cms/match/list/yc",
            data: {
                "keyword": keyword,
                "page": page,
                "rows": rows
            },
            success: function (resultData) {
                console.info(resultData);
                resultHandler(resultData);
            }
        });
    }

    function resultHandler(result) {
        if (200 === result.code) {
            var source = $("#template").html();
            var template = Handlebars.compile(source);
            Handlebars.registerHelper('if_status', function(value, options) {
                if(value  == 0) {
                    return "关闭";
                } else if (value  == 1){
                    return "开启";
                }else {
                    return "";
                }
            });
            var html = template(result.data);
            $("#list").append(html);
        } else {
            alert(result.data);
        }
    }

</script>

<script src="/js/handlebars-v4.0.5.js"></script>

<script id="template" type="text/x-handlebars-template">
    {{#each this}}
    <li class="box vip">
        <div class="venuesimg"><img src="{{thumbnail}}"></div>
        <a href="/cms/match/detail?id={{id}}">
            <div class="venuesdetial boxflex">
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
            </div>
        </a>
        <div class="xiajia"><p class="font14 on">{{#if_status entryStatus}} {{entryStatus}} {{else}} {{entryStatus}} {{/if_status}}</p></div>
    </li>
    {{/each}}
</script>
</body>
</html>