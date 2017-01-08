package com.saidian.web.controller;

import com.saidian.bean.ResultBean;
import com.saidian.config.HttpParams;
import com.saidian.web.Btiem.BTiemService;
import com.saidian.web.bean.GoodsType;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/1/7.
 */
@RequestMapping(value = "site")
@Controller
public class VenueBookingController {

    @Autowired
    BTiemService bTiemService;

    /**
     * 场馆列表
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "index")
    public String index(ModelMap map) throws Exception {

        //运动类型
        ResultBean goodsResultBean = bTiemService.getGoodsByCardId(HttpParams.cardId);

        //todo 运动类型为空 是否默认
        List<GoodsType> goodsTypes = goodsResultBean.getLists();

        ResultBean goodsDetailResultBean = bTiemService.getMerItemList("", "", "", "", "", HttpParams.cardId,
                "", "", "", "", "", 1, 2, 0);

        //讲接口返回jsondata 置空
        goodsDetailResultBean.setData(StringUtils.EMPTY);

        map.addAttribute("goodsTypes", goodsTypes);
        map.addAttribute("goodsDetailResultBean", goodsDetailResultBean);

        return "site/index";
    }


    /**
     * 搜索
     *
     * @param merid        商品ID
     * @param mer_item_ids 商品明细ids(多个用逗号分隔)
     * @param mer_price_id 商品价格ID
     * @param city         城市ID
     * @param q            关键字--搜索
     * @param card_type_id 卡种id（必填）
     * @param radius       距离
     * @param longitude    经度
     * @param latitude     纬度
     * @param sort         距离排序
     * @param price_sort   价格排序
     * @param page         页数
     * @param pagesize     页大小
     * @param district     区域选
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "search")
    public Object search(String merid, String mer_item_ids, String mer_price_id, String city, String q, String card_type_id,
                         String radius, String longitude, String latitude, String sort, String price_sort, Integer page,
                         Integer pagesize, Integer district) throws Exception {

        //商品列表
      /*  ResultBean goodsDetailResultBean = bTiemService.getMerItemList(merid, mer_item_ids, mer_price_id, city, q, card_type_id,
                radius, longitude, latitude, sort, price_sort, null == page ? 1 : page, null == pagesize ? 10 : pagesize, district);
*/
        ResultBean goodsDetailResultBean = bTiemService.getMerItemList(merid, "", "", "", "", HttpParams.cardId,
                "", "", "", "", "", null == page ? 1 : page, 2, 0);

        //讲接口返回jsondata 置空
        goodsDetailResultBean.setData(StringUtils.EMPTY);

        return goodsDetailResultBean;
    }


    @RequestMapping(value = "toDetail")
    public String toDetail(String mer_item_id, ModelMap map) throws Exception {

     /*   if (Strings.isNullOrEmpty(mer_item_id)) {
            map.addAttribute("detail", ResultBean.getErrorResult("商品明细ID不能为空"));
        } else {*/
        String detailResultBean = bTiemService.itemGetMerchandiseItemInfo(mer_item_id);
        JSONObject dataJsonObject = new JSONObject(detailResultBean);

        //讲接口返回jsondata 置空
        //detailResultBean.setData(StringUtils.EMPTY);
        map.addAttribute("detail", new JSONObject(dataJsonObject.getString("data")));
      /*  }*/
        return "site/detail";
    }

}
