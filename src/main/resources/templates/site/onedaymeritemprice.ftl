<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>预订-乒羽篮网</title>
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
            <div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">场次选择</p></div>
            <div class="headerR"></div>
        </div>
    </div>
</div>
<div class="wrapper">
    <!--日期选择-->
    <div class="scrolldate datesel">
        <div class="dateselfixed">
            <div style="height:3.625rem;overflow:hidden;">
                <div id="summary-day-list" class="scrolldateCont box font12">
                <#list priceAndInventorySummaryCommon as summary>
                    <#if day == summary.priceSummaray.day>
                        <span><a href="javascript:void(0)" class="on"
                                 data-day="${summary.priceSummaray.day}">${summary.formatDay}
                            <br>${summary.week}</a></span>
                    <#else >
                        <span><a href="javascript:void(0)" data-day="${summary.priceSummaray.day}">${summary.formatDay}
                            <br>${summary.week}</a></span>
                    </#if>
                </#list>
                </div>
            </div>
        </div>
    </div>
    <!--场地选择-->
    <div class="block">
        <div class="blockcont box">
            <div id="time-interval-list" class="time font12">

            </div>

            <div class="blockinfo boxflex">
                <div class="blockinfoCont">
                    <div class="blockinfoTit font12">
                        <div id="name-list" class="blockinfoTitCont box">

                        </div>
                    </div>
                    <table id="row-table" cellpadding="0" cellspacing="0">
                    <#--  <tr>
                            <td class="true"><span><i>￥</i>50</span></td>
                            <td><span></span></td>
                            <td class="true on"><span><i>￥</i>50</span></td>
                        </tr>-->
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!--选择状态提醒-->
    <div class="zm_ztjx_box">
        <ul class="zm_ztjx_con">
            <li>
                <span class="zm_yxz"></span>
                <p class="xzzt_tit">已选择</p>
            </li>
            <li>
                <span class="zm_wxz"></span>
                <p class="xzzt_tit">未选择</p>
            </li>
            <li>
                <span class="zm_bkx"></span>
                <p class="xzzt_tit">不可选</p>
            </li>
        </ul>
    </div>
    <!--预订-->
    <!--预订按钮-->
    <div id="venuesBook">
        <div class="venuesBook">
            <div class="venuesBookCont box">
                <div class="venuesBookInfo boxflex2">
                    <div class="price box2">
                        <p class="p1 font20"><span id="has-choose-money">￥0</span></p>
                    </div>
                    <div class="service box2">
                        <p class="font12">已选<span id="has-choose-num">0个</span>场次</p>
                    </div>
                </div>
                <a id="bookink-id" href="javascript:void(0)" class="venuesBookBtn boxflex font18">预订</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" language="javascript" src="/js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="/js/myproject.js"></script>
<script type="text/javascript" language="javascript" src="/js/choose.js"></script>

<script>
    $(function () {

        var mer_item_id = '${mer_item_id}';
        var mer_price_id = '${mer_price_id}';
        var day = $("#summary-day-list .on").data("day");
        var cid = '${cid}';

        //页面初始化
        loadData(mer_item_id, mer_price_id, day);

        //某天点击
        $('body').on("click", "#summary-day-list a", function () {
            $("#summary-day-list .on").removeClass("on");
            $(this).addClass("on");
            var day = $(this).data("day");
            loadData(mer_item_id, mer_price_id, day);
        })


        /*<td class="true"><span><i>￥</i>50</span></td> <td><span></span></td> <td class="true on"><span><i>￥</i>50</span></td>*/
        //时间段选择
        $("#row-table").on("click", ".true", function () {

            //是否已选中
            if ($(this).hasClass("on")) {
                $(this).removeClass("on");
                countChoose();
            } else {
                //选中个数是否超过5个
                if ($("#row-table").find(".on").length > 4) {
                    alert("每次最多只能预定5片场地");
                    return;
                } else {
                    $(this).addClass("on");
                    countChoose();
                }
            }
        })

        $("#venuesBook").on("click", "#bookink-id", function () {

            if ($("#row-table").find(".on").length < 1) {
                alert("请选择要预定的场地");
                return;
            }
            var book_day = $("#summary-day-list .on").data("day");
            /* <td class="true"  data-money="{{prepay_price}}" data-piece_id="{{piece_id}}" data-start_hour="{{priceInfo.start_hour}}"  data-end_hour="{{priceInfo.end_hour}}">
 */
            //组织选中的片场信息
            var timeStr = '';
            $("#row-table").find(".on").each(function () {
                var money = $(this).data("money");
                var piece_id = $(this).data("piece_id");
                var start_hour = $(this).data("start_hour");
                var end_hour = $(this).data("end_hour");
                timeStr = timeStr + money + "," + piece_id + "," + start_hour + "," + end_hour + ";";
            });

            var totalMoney = $("#has-choose-money").text().replace("￥", "");


            // window.location.href = "/order/createOrder?mer_item_id=" + mer_item_id + "&book_day=" + book_day + "&cid=" + cid;
            window.location.href = "/order/createOrder?mer_item_id=" + mer_item_id + "&mer_price_id=" + mer_price_id +
                    "&book_day=" + book_day + "&cid=" + cid + "&timeStr=" + timeStr + "&totalMoney=" + totalMoney;
        })


    })

    function countChoose() {
        $("#has-choose-num").html($("#row-table").find(".on").length + "个");
        var money = 0;
        $("#row-table").find(".on").each(function () {
            var thisMoney = $(this).text().replace("￥", "");
            money = money + thisMoney * 1;
        })
        $("#has-choose-money").html("￥" + money);
    }


    //加载数据
    function loadData(mer_item_id, mer_price_id, day) {

        //TODO  此处应该是加载动画
        $("#time-interval-list").html('');
        $("#name-list").html('');
        $("#row-table").html('');

        //订单计数初始化
        $("#has-choose-num").html("0个");
        $("#has-choose-money").html("￥0");

        $.ajax({
            type: "POST",
            url: "/site/getOneDayItemPriceForTimeinterval",
            data: {
                "mer_item_id": mer_item_id,
                "mer_price_id": mer_price_id,
                "day": day
            },
            success: function (resultData) {
                console.info(resultData);
                drawTimeInterval(resultData.timeList);
                drawName(resultData.nameList);
                drawRow(resultData.rows);
            }
        });
    }

    //时段绘制
    function drawTimeInterval(data) {
        var source = $("#time-interval-template").html();
        var template = Handlebars.compile(source);
        var html = template(data);
        $("#time-interval-list").html(html);
    }

    //片场名称绘制
    function drawName(data) {
        var source = $("#name-template").html();
        var template = Handlebars.compile(source);
        var html = template(data);
        $("#name-list").html(html);
    }

    //绘制方格
    function drawRow(data) {
        var source = $("#row-template").html();
        var template = Handlebars.compile(source);
        var html = template(data);
        $("#row-table").html(html);
    }

</script>

<script src="/js/handlebars-v4.0.5.js"></script>
<script id="time-interval-template" type="text/x-handlebars-template">
    {{#each this}}
    <div>{{this}}</div>
    {{/each}}
</script>
<script id="name-template" type="text/x-handlebars-template">
    {{#each this}}
    <span>{{this}}</span>
    {{/each}}
</script>
<script id="row-template" type="text/x-handlebars-template">
    {{#each this}}
    <tr>
        {{#each row}}
        {{#if canbook}}
        <td class="true" data-money="{{prepay_price}}" data-piece_id="{{piece_id}}"
            data-start_hour="{{priceInfo.start_hour}}" data-end_hour="{{priceInfo.end_hour}}">
            <span><i>￥</i>{{prepay_price}}</span>
        </td>
        {{else}}
        <td><span></span></td>
        {{/if}}
        {{/each}}
    </tr>
    {{/each}}
</script>
</body>
</html>