<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>location</title>
    <script language="javascript" type="text/javascript" src="/js/jquery.js"></script>
    <script language="javascript" type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=31ZZ5PhZPGyzmA5UlGYEDG29"></script>
    <script language="javascript" type="text/javascript"
            src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
    <script>
        new BMap.Geolocation().getCurrentPosition(function (r) {
            console.info(r);
            if (r.accuracy != null) {
                if (this.getStatus() == BMAP_STATUS_SUCCESS) {
                    currentLat = r.point.lat;
                    currentLon = r.point.lng;
                    alert("currentLat" + currentLat + "  currentLon:" + currentLon);
                    var pt = new BMap.Point(currentLon, currentLat);
                    new BMap.Geocoder().getLocation(pt, function (rs) {
                        var addComp = rs.addressComponents;
                        var city = addComp.city;
                        var addComp = rs.addressComponents;
                        var texts = addComp.district + "-" + addComp.street + "-" + addComp.streetNumber;
                        alert(r.accuracy + ',' + rs.address);
                        alert('texts:' + texts);
                    })
                }
            } else {
                alert('禁止了定位');
            }
        })
    </script>
</head>
<body>
</body>
</html>