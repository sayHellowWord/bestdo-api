<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>申请退订</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no" />
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>


<body>
<!--头部公用-->
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:void(0)" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">申请退订</p></div>
            <div class="headerR"></div>
        </div>
    </div>
</div>
<div class="wrapper">
    <div class="reason">
        <ul class="blockinput">
            <li class="box font14">
                <span class="tit">退订原因</span>
                <div class="cont boxflex">
                    <div class="reasontxt">选择退订原因</div>
                </div>
            </li>
        </ul>
        <h1 class="creatTit font12">退订原因</h1>
        <div class="textarea">
            <textarea id="unsubscribe-reason" placeholder="说明退订原因"></textarea>
        </div>
        <input id="unsubscribe-order-id"  type="hidden" value="${order_id}">
        <div id="unsubscribe-submit" class="applyBtn font17"><a href="javascript:void(0)">申请退订</a></div>
    </div>
</div>
<!--退订原因-->
<div class="dateControl">
    <div class="dateControlBg"></div>
    <div class="dateControlCont">
        <div class="datelist font14">
            <div class="dateline"></div>
            <h1>
                <a href="javascript:void(0)" class="cancle">取消</a>
                <a href="javascript:void(0)" class="sure">确定</a>
                <p class="font17">退订原因</p>
            </h1>
            <div class="scroll mebcount" data-on="0">
                <span>天气原因</span>
                <span>个人原因</span>
                <span>其他</span>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery.js"></script>
<script src="/js/bestdo.js"></script>
<script>
    $(function(){
        $(".reasontxt").dateControl({
            dateparent:".dateControl",
            datecontrol:".scroll",
            datesureBtn:".sure",
            datecancleBtn:".cancle",
        })

        //申请退订提交
        $("#unsubscribe-submit").click(function () {

            var reason = $("#unsubscribe-reason").val();

            if(reason.length < 1){
                alert("请录入退订原因!")
                return;
            }

            var order_id = $("#unsubscribe-order-id").val();

            $.ajax({
                type: "POST",
                url: "/order/submitunsubscribe",
                data: {
                    "order_id": order_id,
                    "reason": reason
                },
                success: function (result) {
                   /* if (200 === result.code) {
                        console.info(result);
                        alert("退订成功");

                    } else {
                        alert("获取订单状态出错")
                    }*/
                    toResultPage(order_id, result.code);
                }
            });
        })
    })

    //跳转结果页
    function toResultPage(order_id,code) {
        window.location.href="/order/unsubscriberesult?order_id="+order_id+"&code="+code;
    }
    
    
    
</script>
</body>
</html>