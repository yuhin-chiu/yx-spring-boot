package cn.yx.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.entity.WhsActivities;
import cn.yx.model.ApiResponse;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午3:19:59
 * @version 1.0
 */
@RestController
@RequestMapping("/api/activities")
public class ActivitiesController extends AbstractController {

    @RequestMapping("/list")
    public ApiResponse list(Integer status, @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "") String timeRange,
            String query) {
        ApiResponse resp = new ApiResponse();
        resp.setData(activitiesService.list(status, timeRange, query, pageSize, currentPage));
        resp.setTotal(activitiesService.count(status, timeRange, query));
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
    public ApiResponse uploadActivities(@RequestParam String title, @RequestParam String content, @RequestParam String abstr, String author,
            String createTime, Long browses, Integer status, HttpServletRequest request) {
        ApiResponse resp = uploadFiles(request, activitiesService, this.getClass());
        String url = (String)resp.getData();
        resp.setData(activitiesService.uploadActivities(title, content, abstr, author, createTime, browses, url, status));
        return resp;
    }
    @RequestMapping("/getById")
    public WhsActivities getById(@RequestParam(value = "id", required = true) int id) {
        return activitiesService.getDetail(id);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse insertOrUpdate(WhsActivities news, HttpServletRequest request) {
        ApiResponse temp = this.uploadFile(request, this.getClass(), "image");

        if (news.getId() == null) {
            if (temp == null) {
                return ApiResponse.fileSaveEmpty();
            } else if (temp.isSuccess()) {
                String imgKey = (String) temp.getData();
                news.setUrl(imgKey);
            } else {
                return temp;
            }
        } else if (news.getId() != null) {
            if (temp != null && temp.isSuccess()) {
                String imgKey = (String) temp.getData();
                news.setUrl(imgKey);
            }
        }
        if (activitiesService.insertOrUpdate(news)) {
            return ApiResponse.successResponse();
        }
        return ApiResponse.exceptionResponse();
    }
}
