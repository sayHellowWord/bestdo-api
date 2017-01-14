<div class="scrolldate">
    <div id="showBookDays-list" class="scrolldateCont box font12">

    </div>
</div>
<script src="/js/jquery.js"></script>
<script>
    $(function () {
        var mer_item_id = '${mer_item_id}';
        var mer_price_id = '${mer_price_id}';
        var cid = '${cid}';

        //加载订场日期数据
        loadBookDays(mer_item_id);

        //订场日期选中
        $(".scrolldate").on("click", "#showBookDays-list span", function () {
            //下面所有a移除 class="on"
            $("#showBookDays-list a").removeClass("on");
            $(this).find("a").addClass("on");
            var date = $(this).data("day");
            predeterminedPriceInformation(mer_item_id, mer_price_id, date);
        })


        //预定点击
        $("body").on("click", ".venuesBookBtn", function () {
            var book_day = $("#showBookDays-list .on").data("day");
            window.location.href = "/order/createOrder?mer_item_id=" + mer_item_id + "&book_day=" + book_day + "&cid=" + cid;
        })

    })

    //获取此场馆可预订的日期
    function loadBookDays(mer_item_id) {
        $.ajax({
            type: "POST",
            url: "/siteinfo/showBookDays",
            data: {
                "mer_item_id": mer_item_id
            },
            success: function (result) {
                if (200 === result.code) {
                    var source = $("#template").html();
                    var template = Handlebars.compile(source);
                    Handlebars.registerHelper('if_status', function (value, options) {
                        if (value == 0) {
                            return "gray";
                        } else if (value == 1) {
                            return "";
                        } else {
                            return "";
                        }
                    });
                    var html = template(result.lists);
                    $("#showBookDays-list").append(html);

                    //第一个元素点击，相当于第一个选中
                    $("#showBookDays-list span:first").click();
                } else {
                    alert(result.msg);
                }
            }
        })

    }

    //获取一天商品明细的价格和库存信息（日期、时段、小时）
    function predeterminedPriceInformation(mer_item_id, mer_price_id, date) {
        $.ajax({
            type: "POST",
            url: "/siteinfo/getOneDayItemPrice",
            data: {
                "mer_item_id": mer_item_id,
                "mer_price_id": mer_price_id,
                "date": date
            },
            success: function (result) {
                if (200 === result.code) {
                    var source = $("#price-template").html();
                    var template = Handlebars.compile(source);
                    Handlebars.registerHelper('if_destine', function (value, options) {
                        if (value == 0) {
                            return "gray";
                        } else if (value == 1) {
                            return "";
                        } else {
                            return "";
                        }
                    });
                    var html = template(result.object);
                    $("#venuesBook").append(html);

                } else {
                    alert(result.msg);
                }
            }
        })

    }


</script>

<script src="/js/handlebars-v4.0.5.js"></script>

<script id="template" type="text/x-handlebars-template">
    {{#each this}}
    <span data-day="{{day}}"><a href="javascript:void(0)" data-day="{{day}}"
                                class="{{#if_status entryStatus}} {{entryStatus}} {{/if_status}}">{{formatDay}}<br>{{week}}</a></span>
    {{/each}}
</script>

<script id="price-template" type="text/x-handlebars-template">
    <div class="venuesBook">
        <div class="venuesBookCont box">
            <div class="venuesBookInfo boxflex2">
                <div class="price box2">
                    {{#if destine}}
                    {{#each priceInfos}}
                    <p class="p1 font20"><span>{{prepay_price}}</span><i class="font12">起</i></p>
                    {{/each}}
                    {{else}}
                    <div class="service box2"><p class="font12">不可预订</p></div>
                    {{/if}}
                </div>
                <div class="service box2"><p class="font12">2小时畅游</p></div>
            </div>
            {{#if destine}}
            <a href="javascript:void(0)" class="venuesBookBtn boxflex font18">预订</a>
        <#--<a href="{url action='orders/base/createOrder'}?mer_item_id={$mer_item_id}&mer_id={$mer_item['merid']}
        &book_day={$choose_day}&cid={$mer_item['cid']}" class="venuesBookBtn boxflex font18">预订</a>-->
            {{/if}}
        </div>
    </div>
</script>