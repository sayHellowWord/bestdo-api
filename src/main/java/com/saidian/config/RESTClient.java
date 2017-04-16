package com.saidian.config;

import com.google.common.base.Strings;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wubo on 2016/12/27.
 */
@Component
public class RESTClient {

    private RestTemplate restTemplate;

    public RESTClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * 体育赛事列表
     *
     * @param page
     * @param rows
     * @return
     */
    public String matchList(String itemCode, String regionName, String entryStatus, Integer page, Integer rows) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "match/list?itemCode={itemCode}&regionName={regionName}" +
                        "&entryStatus={entryStatus}&" +
                        "page={page}&rows={rows}",
                null, String.class, itemCode, regionName, entryStatus, page, rows);
        return result;
    }

    /**
     * 体育赛事列表总数
     *
     * @return
     */
    public String matchListTotal(String itemCode, String regionName, String entryStatus) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "match/list?itemCode={itemCode}&regionName={regionName}" +
                        "&entryStatus={entryStatus}",
                null, String.class, itemCode, regionName, entryStatus);
        return result;
    }


    /**
     * 体育赛事详情
     *
     * @param id
     * @return
     */
    public String matchDetail(String id) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "match/detail?id={id}",
                null, String.class, id);
        return result;
    }

    /**
     * 赛事动态
     *
     * @param matchId
     * @param page
     * @param rows
     * @return
     */
    public String matchDynamicList(String matchId, Integer page, Integer rows) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "matchDynamic/list?matchId={matchId}&page={page}&rows={rows}",
                null, String.class, matchId, page, rows);
        return result;
    }

    /**
     * 动态详情
     *
     * @param id
     * @return
     */
    public String matchDynamicDetail(String id) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "matchDynamic/detail?id={id}",
                null, String.class, id);
        return result;
    }

    /**
     * 体育培训
     *
     * @param name
     * @param project
     * @param district
     * @param signState
     * @param shelves
     * @param state
     * @param page
     * @param rows
     * @return
     */
    public String ycTrainhList(String name, String project, String district, String signState, String shelves,
                               String state, Integer page, Integer rows, String time_sort) {
     /*   String result = restTemplate.postForObject(HttpParams.CMS_URL + "train/listTrainWX?name={name}&project={project}" +
                        "&district={district}&signState={signState}&shelves={shelves}&state={state}&page={page}" +
                        "&rows={rows}&time_sort={time_sort}",
                null, String.class, name, project, district, signState, shelves, state, page, rows, time_sort);*/
        String url = "train/listTrainWX?page={page}&rows={rows}&time_sort={time_sort}";
        if (!Strings.isNullOrEmpty(district))
            url += "&district={district}";

        String result = restTemplate.postForObject(HttpParams.CMS_URL + url, null, String.class, page, rows, time_sort, district);

        return result;
    }

    //体育培训详情
    public String trainDetail(String id) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "train/findTrain?id={id)}",
                null, String.class, id);
        return result;
    }

    //教练列表
    public String coachList(String trainId, String name, String project, String rank, String state, Integer page, Integer rows) {
       /* String result = restTemplate.postForObject(HttpParams.CMS_URL + "train/listCoachWX?trainId={trainId}&name={name}&project={project}" +
                        "&rank={rank}&state={state}&page={page}&rows={rows}", null, String.class,
                trainId, name, project, rank, state, page, rows);*/
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "train/listCoachWX?trainId={trainId}&name={name}" +
                        "&state={state}&page={page}&rows={rows}", null, String.class,
                trainId, name, state, page, rows);

        return result;
    }

    //获取教练详情
    public String coachDetail(String id) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "train/findCoach?id={id}", null, String.class, id);
        return result;
    }

    //十分钟健身圈列表
    public String excrciseHoodList(String firstType, String scop, String keyword, Integer page, Integer rows) {
        String url = "tenminsite/front_list?page={page}&rows={rows}";
        Map params = new HashMap();
        params.put("page",page);
        params.put("rows",rows);
        if (!Strings.isNullOrEmpty(firstType)) {
            url += "&firstType={firstType}";
            params.put("firstType",firstType);
        }
        if (!Strings.isNullOrEmpty(scop)) {
            url += "&scop={scop}";
            params.put("scop",scop);
        }
        if (!Strings.isNullOrEmpty(keyword)) {
            url += "&name={keyword}";
            params.put("keyword",keyword);
        }
        String result = restTemplate.postForObject(HttpParams.CMS_URL + url,
                null, String.class,params);
      /*  String result = restTemplate.postForObject(HttpParams.CMS_URL + url,
                null, String.class, page, rows,params);*/
        return result;
    }


    //十分钟健身圈列表_total
    public String excrciseHoodListTotal(String keyword, Integer page, Integer rows) {
        String url = "tenminsite/front_count?page={page}&rows={rows}";
        if (!Strings.isNullOrEmpty(keyword))
            url += "&name={keyword}&";
        String result = restTemplate.postForObject(HttpParams.CMS_URL + url,
                null, String.class, page, rows, keyword);
        return result;
    }

    //十分钟健身圈详情
    public String excrciseHoodDetail(String id) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "tenminsite/front_findById?id={id)}",
                null, String.class, id);
        return result;
    }

    //组织列表
    public String organizationList(String name, String district, String state, Integer page, Integer rows) {
        String url = "orgn/listOrganizationWX?name={name}&state={state}&page={page}&rows={rows}";
        if (!Strings.isNullOrEmpty(district))
            url += "&district={district}";
        String result = restTemplate.postForObject(HttpParams.CMS_URL + url,
                null, String.class, name, state, page, rows, district);
        return result;
    }

    //查找组织
    public String findOrganization(String id) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "orgn/findOrganization?id={id}",
                null, String.class, id);
        return result;
    }

    //查找组织动态
    public String findOrganizationDynamic(String oId, Integer page, Integer rows) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "orgn/listDynamicWX?oId={oId}&page={page}&rows={rows}",
                null, String.class, oId, page, rows);
        return result;
    }


    //查找组织冬天
    public String findDynamic(String id) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "orgn/findDynamic?id={id}",
                null, String.class, id);
        return result;
    }

    //健身指导-监测站列表
    public String guidanceList(Integer page, Integer rows) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "phy/front_list?page={page}&rows={rows}",
                null, String.class, page, rows);
        return result;
    }

    //根据id获取健身指导
    public String guidance(String id) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "phy/front_findById?id={id}",
                null, String.class, id);
        return result;
    }


    //体育信息列表
    public String informationiList(String title, String label, String state, Integer page, Integer rows, String startDate, String endDate) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "news/listWX?page={page}&rows={rows}",
                null, String.class, page, rows);
        return result;
    }

    //体育信息列表总数
    public String informationiListTotal(String title, String label, String state, Integer page, Integer rows, String startDate, String endDate) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "news/listWX",
                null, String.class);
        return result;
    }


    //体育信息详情
    public String informationiDetail(String id) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "news/findById?&id={id}", null, String.class, id);
        return result;
    }

}
