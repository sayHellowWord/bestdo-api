<div class="venuesInfo">
<#-- <#if detail.cid?number == 101 || detail.cid?number == 108 >-->
<#if stadium_services??>
    <h1 class="font15">场馆服务</h1>
<ul>
    <li class="moreinfo box font14">
        <div class="icon boxflex font12">
            <#list stadium_services as ser>
                <span class="i${ser.stadium_service_id}">${ser.name}</span>
            </#list>
        </div>
    </li>
</#if>
    <li class="moreinfo box font14">
        <span class="tit">场馆介绍：</span>
        <div class="cont boxflex">
        ${detail.venue.stadium.description}
        </div>
    </li>
</ul>
</div>
