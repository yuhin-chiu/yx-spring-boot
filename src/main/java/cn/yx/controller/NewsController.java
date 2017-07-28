package cn.yx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.entity.WhsNews;
import cn.yx.model.ApiResponse;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午3:19:59
 * @version 1.0
 */

@RestController
@RequestMapping("/news")
public class NewsController extends AbstractController {

    @RequestMapping("/list")
    public ApiResponse list(Integer status, Integer parent, Integer pageSize, Integer currentPage) {
        ApiResponse resp = new ApiResponse();
        pageSize = (pageSize == null) ? 20 : pageSize;
        currentPage = (currentPage == null) ? 1 : currentPage;
        List list = newsService.list(status, parent, pageSize, currentPage);
        int count = newsService.count(status, parent);

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
    public ApiResponse uploadNews(@RequestParam String title, @RequestParam Byte parent, @RequestParam String content,
            String author, String createTime, Long browses, String url, Integer status) {
        ApiResponse resp = new ApiResponse();
        resp.setData(newsService.uploadNews(title, parent, content, author, createTime, browses, url, status));
        return resp;
    }

}
