<div class="venuesInfo">
    <h1 class="font15">场地属性</h1>
    <ul>
        <li class="other font14">
        <#if detail.cid?number == 101>
            <div><span>场地环境：</span><span><#if detail.venue.position?number == 1>室内<#else>室外</#if></span></div>
            <div><span>场地类型：</span><span><#if detail.venue.venue_type?number == 1>普通<#elseif  detail.venue.venue_type == '2'>品牌<#elseif  detail.venue.venue_type == '3'>高校<#elseif  detail.venue.venue_type == '4'>专业</#if></span></div>
            <div><span>场地材质：</span><span><#if detail.venue.venue_material?number == 1>普通地板<#elseif  detail.venue.venue_material == '2'>木质<#elseif  detail.venue.venue_material == '3'>塑胶<#elseif  detail.venue.venue_material == '4'>专业</#if></span></div>
            <div><span>灯光效果：</span><span><#if detail.venue.light?number == 1>顶灯<#else>侧灯</#if></span></div>
            <#if detail.venue.is_single?number == 1><div><span>单打：</span><span>有</span></div></#if>
            <#if detail.venue.is_double?number == 1><div><span>双打：</span><span>有</span></div></#if>
            <#if detail.venue.height?number gt 0 ><div><span>场地高度：</span><span>${detail.venue.height}米</span></div></#if>
        <#elseif detail.cid?number == 102>
            <div><span>场地环境：</span><span><#if detail.venue.position?number == 1>室内<#else>室外</#if></span></div>
            <div><span>场地类型：</span><span><#if detail.venue.venue_type?number == 1>普通<#elseif  detail.venue.venue_type == '2'>品牌<#elseif  detail.venue.venue_type == '3'>高校<#elseif  detail.venue.venue_type == '4'>专业</#if></span></div>
            <div><span>场地材质：</span><span><#if detail.venue.venue_material?number == 1>普通地板<#elseif  detail.venue.venue_material == '2'>木质<#elseif  detail.venue.venue_material == '3'>塑胶<#elseif  detail.venue.venue_material == '4'>专业</#if></span></div>
            <div><span>灯光效果：</span><span><#if detail.venue.light?number == 1>顶灯<#else>侧灯</#if></span></div>
            <#if detail.venue.height?number gt 0 ><div><span>场地高度：</span><span>${detail.venue.height}米</span></div></#if>
        <#elseif detail.cid?number == 104>
            <div><span>场地环境：</span><span><#if detail.venue.position?number == 1>室内<#else>室外</#if></span></div>
            <div><span>场地类型：</span><span><#if detail.venue.venue_type?number == 1>普通<#elseif  detail.venue.venue_type == '2'>品牌<#elseif  detail.venue.venue_type == '3'>高校<#elseif  detail.venue.venue_type == '4'>专业</#if></span></div>
            <div><span>灯光照明 ：</span><span><#if detail.venue.light?number == 1>有<#else>否</#if></span></div>
            <#if detail.venue.venue_spec??><div><span>场地规格：</span><span><#if detail.venue.venue_spec?number == 1>半场<#else>全场</#if></span></div></#if>
            <#if detail.venue.is_coach??><div><span>教练：</span><span><#if detail.venue.is_coach?number == 1>有<#else>否</#if></span></div></#if>
        <#elseif detail.cid?number == 106>
            <div><span>场地环境：</span><span><#if detail.venue.position?number == 1>室内<#else>室外</#if></span></div>
            <div><span>场地类型：</span><span><#if detail.venue.venue_type?number == 1>普通<#elseif  detail.venue.venue_type == '2'>品牌<#elseif  detail.venue.venue_type == '3'>高校<#elseif  detail.venue.venue_type == '4'>专业</#if></span></div>
            <div><span>灯光照明 ：</span><span><#if detail.venue.light?number == 1>有<#else>否</#if></span></div>
            <#if detail.venue.is_baffle??><div><span>挡板：</span><span><#if detail.venue.is_baffle?number == 1>有<#else>否</#if></span></div></#if>
            <#if detail.venue.table_quality??><div><span>球桌品质：</span><span><#if detail.venue.table_quality?number == 1>赛事专用<#else>普通球桌</#if></span></div></#if>
        <#elseif detail.cid?number == 108>
            <div><span>场地类型：</span><span><#if detail.venue.venue_type?number == 1>普通<#elseif  detail.venue.venue_type == '2'>品牌<#elseif  detail.venue.venue_type == '3'>高校<#elseif  detail.venue.venue_type == '4'>专业</#if></span></div>
            <#if detail.venue.aerobics_zone?number == 1><div><span>形体房：</span><span>有</span></div></#if>
            <#if detail.venue.shape_zone?number == 1><div><span>形体房：</span><span>有</span></div></#if>
            <#if detail.venue.spinning?number == 1><div><span>动感单车室：</span><span>有</span></div></#if>
            <#if detail.venue.yoga?number == 1><div><span>瑜伽：</span><span>有</span></div></#if>
            <#if detail.venue.pilates?number == 1><div><span>普拉提：</span><span>有</span></div></#if>
            <#if detail.venue.swimming_pool?number == 1><div><span>游泳池：</span><span>有</span></div></#if>
        <#elseif detail.cid?number == 109>
            <div><span>场地环境：</span><span><#if detail.venue.position?number == 1>室内<#else>室外</#if></span></div>
            <div><span>场地类型：</span><span><#if detail.venue.venue_type?number == 1>普通<#elseif  detail.venue.venue_type == '2'>品牌<#elseif  detail.venue.venue_type == '3'>高校<#elseif  detail.venue.venue_type == '4'>专业</#if></span></div>
            <div><span>泳道数量：</span><span>${detail.venue.lanes_number}</span></div>
            <div><span>泳道长度：</span><span><#if detail.venue.lanes_length?number == 1 >25米<#elseif detail.venue.lanes_length?number == 2 >50米<#elseif detail.venue.lanes_length?number == 3 >异形<#else>无</#if></span></div>
            <#if detail.venue.profundal_zone?number gt 0 || detail.venue.shallow_zone?number gt 0><div><span>泳池深度(深/浅)：</span><span>${ detail.venue.profundal_zone}/ ${ detail.venue.shallow_zone}</span></div></#if>
            <#if detail.venue.children_zone?number == 1><div><span>儿童区：</span><span>有</span></div></#if>
            <#if detail.venue.diving_platform?number == 1><div><span>跳台：</span><span>有</span></div></#if>
            <#if detail.venue.fitness_facility?number == 1><div><span>健身实施：</span><span>有</span></div></#if>
            <#if detail.venue.thermostatic_swimming_pool?number == 1><div><span>恒温泳池：</span><span>有</span></div></#if>
        </#if>
        </li>
    </ul>
</div>