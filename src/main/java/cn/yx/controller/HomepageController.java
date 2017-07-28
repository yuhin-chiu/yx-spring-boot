package cn.yx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.yx.entity.WhsActivities;
import cn.yx.entity.WhsCompany;
import cn.yx.entity.WhsMedia;
import cn.yx.entity.WhsNews;
import cn.yx.entity.WhsSliders;
import cn.yx.enums.ApiResponseEnum;
import cn.yx.model.ApiResponse;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午3:19:59
 * @version 1.0
 */
@RestController
@RequestMapping("/homepage")
public class HomepageController extends AbstractController {

    @RequestMapping("/all")
    public ApiResponse all() {
        // logo 时间地点等信息
        ApiResponse resp = new ApiResponse();
        JSONObject obj = homepageService.getBaseInfo();
        // 滚动窗口
        List<WhsSliders> sliders = slidersService.getNew();
        // 最新动态
        List<WhsNews> news = newsService.getNew();
        // 同期活动
        List<WhsActivities> activities = activitiesService.getNew();
        // TODO downloads
        // 展商推荐
        List<WhsCompany> companys = companyService.getRecomm();
        // 合作媒体
        List<WhsMedia> medias = mediaService.getRecomm();

        obj.put("sliders", sliders);
        obj.put("news", news);
        obj.put("activities", activities);
        obj.put("companys", companys);
        obj.put("medias", medias);

        resp.setData(obj);

        return resp;
    }

    @RequestMapping("/base")
    public ApiResponse base() {
        ApiResponse resp = new ApiResponse();
        JSONObject obj = homepageService.getBaseInfo();
        resp.setData(obj);
        return resp;
    }

}
