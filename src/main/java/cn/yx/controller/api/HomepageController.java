package cn.yx.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.yx.entity.BaseDownloads;
import cn.yx.entity.WhsActivities;
import cn.yx.entity.WhsCompany;
import cn.yx.entity.WhsHomepage;
import cn.yx.entity.WhsNews;
import cn.yx.entity.WhsSliders;
import cn.yx.model.ApiResponse;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午3:19:59
 * @version 1.0
 */
@RestController
@RequestMapping("/api/homepage")
public class HomepageController extends AbstractController {

    @RequestMapping("/all")
    public ApiResponse all() {
        // logo 时间地点等信息
        ApiResponse resp = new ApiResponse();
        JSONObject obj = homepageService.getBaseInfo();
        // 滚动窗口
        List<WhsSliders> sliders = slidersService.getNew(5);
        // 最新动态
        List<WhsNews> news = newsService.getNew();
        // 同期活动
        List<WhsActivities> activities = activitiesService.getNew();
        // downloads 只取类型为0的下载文件到首页
        List<BaseDownloads> downloads = downloadsService.getNew(null);
        // 展商推荐
        List<WhsCompany> companys = companyService.getRecomm();

        obj.put("sliders", sliders);
        obj.put("news", news);
        obj.put("activities", activities);
        obj.put("companys", companys);
        obj.put("downloads", downloads);

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

    @RequestMapping("/edit")
    public ApiResponse edit(WhsHomepage homepage, HttpServletRequest request) {
        ApiResponse resp = uploadFiles(request, homepageService, this.getClass());
        if(resp.getCode() == 200) {
            String imgKey = (String) resp.getData();
            homepage.setLogo(imgKey);
        } else {
            resp.setCode(200);
        }
        Integer obj = homepageService.update(homepage);
        resp.setData(obj);
        return resp;
    }
}
