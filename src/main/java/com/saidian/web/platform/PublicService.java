package com.saidian.web.platform;

import com.saidian.bean.ResultBean;
import com.saidian.config.AccessServices;
import com.saidian.utils.HttpResultUtil;
import com.saidian.utils.HttpUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/1/13.
 */
@Service
public class PublicService {


    //获取下级地区
    private String REGION_GET_CHILDREN = "region/api/getChildren";

    //获取level级别的所有地区
    private String REGION_GET_REGIONS_BY_LEVEL = "region/api/getRegionsByLevel";

    private String REGION_CITY_LND_ANDLAT = "region/api/getCityLngAndLat";


    /**
     * 获取下级地区
     *
     * @param region_id
     * @return
     * @throws Exception
     */
    public ResultBean regionGetChildren(String region_id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("region_id", region_id);
        String result = HttpUtil.doPost(AccessServices.PUBLIC_SERVICE_URL + REGION_GET_CHILDREN, jsonObject.toString(), AccessServices.PUBLIC_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);
    }

    /**
     * 获取level级别的所有地区
     *
     * @param level
     * @return
     * @throws Exception
     */
    public ResultBean getRegionsByLevel(String level) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("level", level);
        String result = HttpUtil.doPost(AccessServices.PUBLIC_SERVICE_URL + REGION_GET_REGIONS_BY_LEVEL, jsonObject.toString(), AccessServices.PUBLIC_SERVICE_KEY);
        return HttpResultUtil.result2Bean(result);
    }

    /**
     * 城市经纬度服务
     *
     * @param city_id
     * @return
     * @throws Exception
     */
    public ResultBean getCityLngAndLat(String city_id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("city_id", city_id);
        String result = HttpUtil.doPost(AccessServices.PUBLIC_SERVICE_URL + REGION_CITY_LND_ANDLAT, jsonObject.toString(), AccessServices.PUBLIC_SERVICE_KEY);
        ResultBean resultBean = HttpResultUtil.result2Bean(result);
        return resultBean;
    }

}
