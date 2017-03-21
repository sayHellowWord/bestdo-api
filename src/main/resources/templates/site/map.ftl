<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>地图</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!--忽略页面中的数字识别为电话号码-->
    <meta name="format-detection" content="telephone=no" />
    <!--忽略页面中的邮箱格式为邮箱-->
    <meta name="format-detection" content="email=no"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css" />

<body>
<style>
    .map{ width:100%;height:100%;position:relative; }
</style>
<div id="header">
    <div class="header">
        <div class="headerCont box">
            <div class="headerL"><a href="javascript:history.go(-1);" class="back"></a></div>
            <div class="headerC boxflex"><p class="font17">地图模式</p></div>
        </div>
    </div>
</div>
<span class="myposition"></span>
<div id="bdmap" class="map" style="width:100%; height:570px;top:0px;"></div>
</body>

<script src="/js/jquery.js"></script>
<script src="/js/bestdo.js"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=18682494ecab57ae8fa581c98f0d0d7a"></script>

<script>
    function genHtml(name, addr){
        return "<h1 style='width:100%;height:25px;padding-bottom:6px;border-bottom:1px solid #ddd;font-size:12px;background:#fff;'>" + name + "</h1><div style='margin-top:10px;font-size:12px'>" + addr + "</div>";
    }
    $(function(){
        var wh = $(window).height();
        $(".map").height(wh-52);

        var myPoint = null;
        var myMarker = null;
        var myInfoWindow = null;
        var infoWindowOpts = { enableMessage: false };

        var map = new BMap.Map("bdmap");
        var geoc = new BMap.Geocoder();

        var point = new BMap.Point(${longitude}, ${latitude});
        var marker = new BMap.Marker(point);

        var infoWindow = new BMap.InfoWindow('',infoWindowOpts);
        infoWindow.setContent(genHtml("${name}", "地址：${address}"));

        marker.addEventListener("click", function(){
            this.openInfoWindow(infoWindow, point);
        });
        map.addOverlay(marker);
        marker.openInfoWindow(infoWindow, point);

        map.centerAndZoom(point, 15);
        map.addControl(new BMap.NavigationControl({
            anchor:BMAP_ANCHOR_TOP_RIGHT
        }));

        var geolocation = new BMap.Geolocation();
        geolocation.getCurrentPosition(function(r){
            if(this.getStatus() == BMAP_STATUS_SUCCESS){
                myPoint = new BMap.Point(r.point.lng, r.point.lat);
                myMarker = new BMap.Marker(myPoint);

                geoc.getLocation(myPoint, function(rs){
                    var addComp = rs.addressComponents;
                    var myPosInfo = ""
                            //+ addComp.province
                            + addComp.city
                            + addComp.district
                            + addComp.street
                            + addComp.streetNumber;
                    myInfoWindow = new BMap.InfoWindow('', infoWindowOpts);
                    myInfoWindow.setContent(genHtml("我的位置", "地址："+myPosInfo));
                    myMarker.addEventListener("click", function(){
                        this.openInfoWindow(
                                myInfoWindow,
                                myPoint
                        );
                    });
                });
            }
        },{
            enableHighAccuracy: true
        });

        $("span.myposition").click(function(){
            map.addOverlay(myMarker);
            myMarker.openInfoWindow(myInfoWindow, myPoint);
            map.centerAndZoom(myPoint, 15);
        });
    });
</script>
</html>