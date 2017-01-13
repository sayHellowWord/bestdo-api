<div class="scrolldate">
    <div id="showBookDays-list" class="scrolldateCont box font12">

    </div>
</div>
<script src="/js/jquery.js"></script>
<script>
    $(function () {
        var mer_item_id = '${mer_item_id}';
        var mer_price_id = '${mer_price_id}';

        //加载订场日期数据
        loadBookDays(mer_item_id);

        //订场日期选中
        $(".scrolldate").on("click","#showBookDays-list span",function () {
            //下面所有a移除 class="on"
            $("#showBookDays-list a").removeClass("on");
            $(this).find("a").addClass("on");
            var date = $(this).data("day");
            predeterminedPriceInformation(mer_item_id,mer_price_id,date);
        })

    })

    //获取此场馆可预订的日期
    function loadBookDays(mer_item_id){
        $.ajax({
            type: "POST",
            url: "/site/showBookDays",
            data: {
                "mer_item_id": mer_item_id
            },
            success: function (result) {
                if (200 === result.code) {
                    var source = $("#template").html();
                    var template = Handlebars.compile(source);
                    Handlebars.registerHelper('if_status', function(value, options) {
                        if(value  == 0) {
                            return "gray";
                        } else if (value  == 1){
                            return "";
                        }else {
                            return "";
                        }
                    });
                    var html = template(result.lists);
                    $("#showBookDays-list").append(html);

                    //第一个元素点击，相当于第一个选中
                    $("#showBookDays-list span:first").click();
                }else{
                    alert(result.msg);
                }
            }
        })

    }

    //获取一天商品明细的价格和库存信息（日期、时段、小时）
    function predeterminedPriceInformation (mer_item_id,mer_price_id,date){
        $.ajax({
            type: "POST",
            url: "/site/getOneDayItemPrice",
            data: {
                "mer_item_id": mer_item_id,
                "mer_price_id": mer_price_id,
                "date": date
            },
            success: function (result) {
                if (200 === result.code) {

                }else{
                    alert(result.msg);
                }
            }
        })

    }
</script>

<script src="/js/handlebars-v4.0.5.js"></script>

<script id="template" type="text/x-handlebars-template">
    {{#each this}}
        <span data-day="{{day}}"><a href="javascript:void(0)" class="{{#if_status entryStatus}} {{entryStatus}} {{/if_status}}">{{formatDay}}<br>{{week}}</a></span>
    {{/each}}
</script>

<script id="bookday-template" type="text/x-handlebars-template">
    <div class="venuesBook">
        <div class="venuesBookCont box">
            <div class="venuesBookInfo boxflex2">
                <div class="price box2">
                    <p class="p1 font20"><span>￥350</span><i class="font12">起</i></p>
                    <P class="p2 font12">门市价￥180</P>
                </div>
                <div class="service box2">
                    <p class="font12">18洞72杆</p>
                    <p class="font12">(价格包含)</p>
                </div>
            </div>
            <a href="javascript:void(0)" class="venuesBookBtn boxflex font18">预订</a>
        </div>
    </div>
</script>