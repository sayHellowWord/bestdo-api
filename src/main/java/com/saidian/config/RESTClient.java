package com.saidian.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
     * @param keyword
     * @param page
     * @param rows
     * @return
     */
    public String matchList(String keyword, Integer page, Integer rows) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "match/list?keyword={keyword}&page={page}&rows={rows}",
                null, String.class, keyword, page, rows);
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
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "train/listTrainWX?name={name}&project={project}" +
                        "&district={district}&signState={signState}&shelves={shelves}&state={state}&page={page}" +
                        "&rows={rows}&time_sort={time_sort}",
                null, String.class, name, project, district, signState, shelves, state, page, rows, time_sort);
        return result;
    }

    //体育培训详情
    public String trainDetail(String id) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "train/findTrain?id={id)}",
                null, String.class, id);
        return result;
    }

    //教练列表
    public String coachList(String name, String project, String rank, String state, Integer page, Integer rows) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "train/listCoachWX?name={name}&project={project}" +
                        "&rank={rank}&state={state}&page={page}&rows={rows}", null, String.class,
                name, project, rank, state, page, rows);
        return result;
    }

    //获取教练详情
    public String coachDetail(String id) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "train/findCoach?id={id}", null, String.class, id);
        return result;
    }

    //十分钟健身圈列表
    public String excrciseHoodList(String keyword, Integer page, Integer rows) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "tenminsite/front_list?keyword={keyword}&page={page}&rows={rows}",
                null, String.class, keyword, page, rows);
        return result;
    }

    //十分钟健身圈详情
    public String excrciseHoodDetail(String id) {
        String result = restTemplate.postForObject(HttpParams.CMS_URL + "tenminsite/front_findById?id={id)}",
                null, String.class, id);
        return result;
    }

}
