<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>十分钟健身圈</title>
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
                <div class="search box font14">
                    <div class="searchInput boxflex">
                        <span class="icon"></span>
                        <input id="search-keyword" type="text" class="font14" placeholder="输入场馆名称或行政区">
                    </div>
                    <a id="btn-search" href="javascript:void(0)" class="btn">搜索</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="chooseTab font13">
    <div class="chooseTabCont">
        <a id="" href="javascript:void(0)" class="" data-tab="0">分类<span id="" href="javascript:void(0)" class=""></span></a>
        <a id="" href="javascript:void(0)" class="" data-tab="1">行政区<span id="" href="javascript:void(0)" class=""></span></a>
    </div>
</div>
<!-- 区域 -->
<div class="slidemenu" id="firstType" data-tab="0">
    <div class="slidebg"></div>
    <div class="slidemenuCont font14 nearby">
        <a href="javascript:void(0)" data-value="" class="on">全部</a>
        <a href="javascript:void(0)" data-value="健身步道">健身步道</a>
        <a href="javascript:void(0)" data-value="晨晚练点">晨晚练点</a>
        <a href="javascript:void(0)" data-value="健身路径">健身路径</a>
        <a href="javascript:void(0)" data-value="笼式灯光运动场">笼式灯光运动场</a>

    </div>
</div>
<!-- 分类 -->
<div class="slidemenu" id="scop" data-tab="1">
    <div class="slidebg"></div>
    <div class="slidemenuCont font14 sort">
        <a href="javascript:void(0)" data-value="" class="on">全部区域</a>
    <#if regions??>
        <#list regions as regions>
            <a href="javascript:void(0)" data-value="${regions.name}">${regions.name}</a>
        </#list>
    </#if>
    </div>
</div>
<!--场馆列表-->
<div class="wrapper">
    <!--场馆列表-->
    <div class="venueslist">
        <ul id="list" class="list">

        </ul>
    <#--<div class="load-container font12">
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


<!--地图图标-->
<script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/js/bestdo.js"></script>
<script>

    var page = ${page};
    var pagesize = ${pagesize};

    var total = -1;
    var totalPage = -1;

    $(function () {

        // 获取搜索关键词
        var searchKeyword = $('#search-keyword').val();

        //初始化加载数据
        search(searchKeyword, page, pagesize);

        //点击搜索按钮
        var btnSearch = $('#btn-search');
        btnSearch.on("click", function () {
            $("#list").html('');
            searchKeyword = $('#search-keyword').val();
            page = 1;
            search(searchKeyword, page, pagesize);
        })

        /*筛选条件*/
        $(".chooseTab a").tabEve({
            cls: ".slidemenu",
            selected: "on",
            empty: "gray",
            typing: "slidemenu"
        })

        $("#firstType a").click(function () {
            $("#firstType a").removeClass("on");
            $(this).addClass("on");
            $("#list").html('');
            page = 1;
            search(searchKeyword, page, pagesize);
        });

        $("#scop a").click(function () {
            $("#scop a").removeClass("on");
            $(this).addClass("on");
            $("#list").html('');
            page = 1;
            search(searchKeyword, page, pagesize);
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
    function search(keyword, page, rows) {

        //没有结果提示隐藏
        $("#no-result").hide();

        if (totalPage != -1 && page > totalPage) {
            return;
        }

        $.ajax({
            type: "POST",
            url: "/cms/exercisehoop/list/yc",
            data: {
                "keyword": $('#search-keyword').val(),
                "firstType": $("#firstType").find(".on").data("value"),
                "scop": $("#scop").find(".on").data("value"),
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

    //页面渲染
    function resultHandler(result) {
        if (200 === result.code) {
            var source = $("#template").html();
            var template = Handlebars.compile(source);
            Handlebars.registerHelper('if_showImg', function (value, options) {
                return value.split(';')[0];
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
    <li class="box vip">
        <div class="venuesimg">
            <a href="/cms/exercisehoop/toDetail?id={{id}}">
                <img src="{{#if_showImg showImg}} {{showImg}} {{/if_showImg}}">
            </a>
        </div>
        <div class="venuesdetial boxflex">
            <a href="/cms/exercisehoop/toDetail?id={{id}}">
                <h2 class="font16">{{name}}</h2>
                <div class="address add2 font12">
                    <span class="p">{{address}}</span>
                </div>
            </a>
        </div>
    </li>
    {{/each}}
</script>
<script type="text/javascript">
    var addrs = $('#firstType').find('a'); liandong(addrs);
    var distance = $('#scop').find('a'); liandong(distance);
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
