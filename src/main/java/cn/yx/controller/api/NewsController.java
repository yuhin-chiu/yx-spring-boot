package cn.yx.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.entity.WhsNews;
import cn.yx.model.ApiResponse;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午3:19:59
 * @version 1.0
 */

@RestController
@RequestMapping("/api/news")
public class NewsController extends AbstractController {

    @RequestMapping("/list")
    public ApiResponse list(Integer status, Integer parent, @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "") String timeRange,
            String query) {
        ApiResponse resp = new ApiResponse();
        List<WhsNews> list = newsService.list(status, parent, timeRange, query, pageSize, currentPage);
        int count = newsService.count(status, parent, timeRange, query);
        resp.setData(list);
        resp.setTotal(count);
        return resp;
    }

    @RequestMapping("/detail/{id}")
    public ApiResponse detail(@PathVariable(required = true) int id) {
        ApiResponse resp = new ApiResponse();
        resp.setData(newsService.getDetail(id));
        return resp;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ApiResponse edit(@PathVariable Integer id, WhsNews news) {
        ApiResponse resp = new ApiResponse();

        resp.setData(newsService.update(news));

        return resp;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponse uploadNews(@RequestParam String title, @RequestParam Byte target, @RequestParam String content,
            @RequestParam String abstr, String author, String createTime, Long browses,
            @RequestParam(defaultValue = "1", required = false) Integer status, HttpServletRequest request) {
        ApiResponse resp = uploadFiles(request, newsService, this.getClass());
        String url = (String) resp.getData();
        resp.setData(newsService.uploadNews(title, target, content, abstr, author, createTime, browses, url, status));
        return resp;
    }

    @RequestMapping("/getById")
    public WhsNews getById(@RequestParam(value = "id", required = true) int id) {
        return newsService.getDetail(id);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse insertOrUpdate(WhsNews news, HttpServletRequest request) {
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
        if (newsService.insertOrUpdate(news)) {
            return ApiResponse.successResponse();
        }
        return ApiResponse.exceptionResponse();
    }
}
