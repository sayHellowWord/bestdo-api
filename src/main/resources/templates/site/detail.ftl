<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>场馆详情</title>
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
            <div class="headerC boxflex"><p class="font17">${detail.name}</p></div>
            <div class="headerR">
            <#--	<a href="javascript:void(0)" class="addFav addFavOn"></a>-->
            </div>
        </div>
    </div>
</div>
<div class="wrapper">
    <div class="detailinfo">
        <!--场馆图片-->
        <div class="imgshow">
            <div class="imglist"><img src="${detail.thumb}"></div>
            <div class="imgtxt font20"><p>${detail.name}</p></div>
        </div>

        <!--场馆信息-->
        <div class="venuesInfo">
            <ul>
                <li class="address font15"><p><a href="/site/map?mer_item_id=${detail.mer_item_id}">${detail.venue.stadium.address}</p></a></li>
                <li class="tel font15"><p><a href="tel:${detail.venue.stadium.phone}">${detail.venue.stadium.phone}</a>
                </p></li>
            </ul>
        </div>

        <!--时间预定  时段性、日期型-->
    <#--     <div class="venuesInfo">
             <ul>
                 <li class="date font15"><p>10月11日 周三 18:00</p></li>
             </ul>
         </div>-->

        <!-- 时段性  102 羽毛球  101 网球  106 乒乓球 104 篮球 -->
    <#if detail.cid?number == 101 || detail.cid?number == 102 || detail.cid?number == 104 || detail.cid?number == 106>
        <#include "detail-priceAndInventorySummaryCommon.ftl">
        <!-- 时段性 109 游泳 108 健身 113 台球-->
    <#elseif detail.cid?number == 108 || detail.cid?number == 109 || detail.cid?number == 113 >
        <#include "detail-showBookDays.ftl">
    <#--120 足球-->
    <#elseif detail.cid?number == 120 >
        <#include "detail-football.ftl">
    <#else>
     <#--   <div class="venuesInfo">
            <ul>
                <li class="date font15"><p>10月11日 周三 18:00</p></li>
            </ul>
        </div>-->
    </#if>


    <!--场地属性102 羽毛球 101 网球106 乒乓球104 篮球109 游泳108 健身   -->
    <#if detail.cid?number == 101 || detail.cid?number == 102 || detail.cid?number == 104 || detail.cid?number == 106 || detail.cid?number == 108 || detail.cid?number == 109>
        <#include "detail-field-properties.ftl">
    </#if>

        <!--场馆服务-->
    <#include "detail-venue-services.ftl">

    <!--109 游泳108 健身 113台球 -->
    <#if detail.cid?number == 108 || detail.cid?number == 109 || detail.cid?number == 113>
    <!--预订按钮-->
        <div id="venuesBook" data-info="${detail.price_info}">
           <#-- <div class="venuesBook">
                <div class="venuesBookCont box">
                    <div class="venuesBookInfo boxflex2">
                        <div class="price box2">
                            <p class="p1 font20"><span>￥350</span><i class="font12">起</i></p>
                            <P class="p2 font12">门市价￥180</P>
                        </div>
                        <div class="service box2"><p class="font12">2小时畅游</p></div>
                    </div>
                    <a href="javascript:void(0)" class="venuesBookBtn boxflex font18">预订</a>
                </div>
            </div>-->
        </div>
        </#if>
    </div>
    <!--日期控件-->
  <#--  <div class="dateControl">
        <div class="dateControlBg"></div>
        <div class="dateControlCont">
            <div class="datelist font14">
                <div class="dateline"></div>
                <h1>
                    <a href="javascript:void(0)" class="cancle">取消</a>
                    <a href="javascript:void(0)" class="sure">确定</a>
                    <p class="font17">选择teetime</p>
                </h1>
                <div class="scroll sdate perc1" data-on="2">
                    <span>11月14日<i>周一</i></span>
                    <span>11月14日<i>周一</i></span>
                    <span>11月14日<i>周一</i></span>
                    <span>11月14日<i>周一</i></span>
                    <span>11月14日<i>周一</i></span>
                    <span>11月14日<i>周一</i></span>
                </div>
                <div class="scroll stime perc" data-on="2">
                    <span>09</span>
                    <span>10</span>
                    <span>11</span>
                    <span>12</span>
                    <span>13</span>
                    <span>14</span>
                </div>
                <div class="scroll perc" data-on="0">
                    <span>00</span>
                    <span>30</span>
                </div>
            </div>
        </div>
    </div>-->
    <script src="/js/jquery.js"></script>
    <script src="/js/bestdo.js"></script>
    <script>
        $(function () {

           /* $(".addFav").click(function () {
                $(this).promptEve({
                    txt: '关注成功'
                })
            });*/

            /*$(".date p").dateControl({
                dateparent: ".dateControl",
                datecontrol: ".scroll",
                datesureBtn: ".sure",
                datecancleBtn: ".cancle",
            })*/

        })
    </script>
</body>
</html>