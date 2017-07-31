package cn.yx.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.controller.api.AbstractController;
import cn.yx.entity.WhsActivities;
import cn.yx.entity.WhsCompany;
import cn.yx.model.ApiResponse;
import cn.yx.service.ActivitiesService;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午3:19:59
 * @version 1.0
 */
@RestController
@RequestMapping("/activities")
public class ActivitiesController extends AbstractController {

    @RequestMapping("/list")
    public ApiResponse list(@RequestParam(defaultValue = "0") Integer status,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage) {
        ApiResponse resp = new ApiResponse();
        resp.setData(activitiesService.list(status, pageSize, currentPage));
        resp.setTotal(activitiesService.count(status));
        return resp;
    }

    @RequestMapping("/detail/{id}")
    public ApiResponse detail(@PathVariable(required = true) int id) {
        ApiResponse resp = new ApiResponse();
        resp.setData(activitiesService.getDetail(id));
        return resp;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ApiResponse edit(@PathVariable Integer id, WhsActivities acti) {
        ApiResponse resp = new ApiResponse();

        resp.setData(activitiesService.update(acti));

        return resp;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponse uploadActivities(@RequestParam String title, @RequestParam String content, String author,
            String createTime, Long browses, String url, Integer status) {
        ApiResponse resp = new ApiResponse();
        resp.setData(activitiesService.uploadActivities(title, content, author, createTime, browses, url, status));
        return resp;
    }
}