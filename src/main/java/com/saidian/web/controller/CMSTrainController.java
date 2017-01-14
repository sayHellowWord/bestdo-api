package com.saidian.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.saidian.bean.Result;
import com.saidian.config.RESTClient;
import com.saidian.web.bean.cms.Coach;
import com.saidian.web.bean.cms.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/11.
 */
@RequestMapping(value = "/cms/train")
@Controller
public class CMSTrainController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    RESTClient restClient;


    @RequestMapping("/list")
    public String list(ModelMap modelMap) {
        return "/train/list";
    }

    @RequestMapping("/toDetail")
    public String toDetail(String id, ModelMap modelMap) {
        String result = restClient.trainDetail(id);
        Train train = null;
        try {
            train = objectMapper.readValue(result, Train.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取教练 TODO
        List<Coach> coaches = new ArrayList<Coach>();
        Coach coach = new Coach();
        coach.setId(1l);
        coach.setName("老王");
        coach.setGender((byte) 1);
        coach.setBirthday(new Date());
        coach.setEducationalBg("中");
        coach.setExperience("3年");
        coach.setIcon("http://ojhwcmd4b.bkt.clouddn.com/1484126439624/84Z58PICuRE_1024.jpg");
        coach.setProject("网球");
        coach.setRank("高级");
        coach.setSiteName("网球中心;网球馆;");
        coach.setPhotoIcon("http://ojhwcmd4b.bkt.clouddn.com/1484126442142/2.jpg");
        coach.setCreateDate(new Date());
        coach.setUpdateDate(new Date());
        coaches.add(coach);
        coaches.add(coach);

        modelMap.addAttribute("train", train);
        modelMap.addAttribute("coaches", coaches);

        return "/train/detail";
    }

    /**
     * 体育培训列表 接口
     *
     * @param keyword
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */

    @RequestMapping("/list/yc")
    @ResponseBody
    public Result ycTrainList(String name, String project, String district, String signState, String shelves, String state, Integer page, Integer rows, String time_sort) throws Exception {
        String resultStr = restClient.ycTrainhList(name, project, district, signState, shelves, state,
                null == page ? 1 : page, null == rows ? 10 : rows, Strings.isNullOrEmpty(time_sort) ? "asc" : time_sort);
        Result result = new Result();
        List<Train> trains = null;
        try {
            trains = objectMapper.readValue(resultStr, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.setData(trains);
        result.setCode(200);
        return result;
    }

    @RequestMapping("/coach/list")
    @ResponseBody
    public Object coachList(String name, String project, String rank, String state, Integer page, Integer rows) throws Exception {
        String resultStr = restClient.coachList(name, project, rank, state, null == page ? 1 : page, null == rows ? 10 : rows);
        List<Coach> coaches = null;
        try {
            coaches = objectMapper.readValue(resultStr, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Result result = new Result();
        result.setData(coaches);
        result.setCode(200);
        return result;
    }

    @RequestMapping("/coach/toDetail")
    public String list(String id, ModelMap modelMap) {
        String result = restClient.coachDetail(id);
        Coach coache = null;
        try {
            coache = objectMapper.readValue(result, Coach.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("coach", coache);
        return "/train/coachdetail";
    }

}
