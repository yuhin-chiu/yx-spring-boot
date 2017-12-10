package cn.yx.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.entity.WhsHistory;
import cn.yx.entity.WhsNews;
import cn.yx.model.ApiResponse;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午6:05:09
 * @version 1.0
 */

@RestController
@RequestMapping("/api/history")
public class HistoryController extends AbstractController {

    @RequestMapping("/list")
    public ApiResponse list(@RequestParam(defaultValue = "1") Integer status,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage) {
        ApiResponse resp = new ApiResponse();
        List list = historyService.list(status, pageSize, currentPage);
        int count = historyService.count(status);

        resp.setData(list);
        resp.setTotal(count);
        return resp;
    }
    
    @RequestMapping("/listAll")
    public ApiResponse listAll(@RequestParam(defaultValue = "-1") Integer status,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage) {
        ApiResponse resp = new ApiResponse();
        List list = historyService.list(status, pageSize, currentPage);
        int count = historyService.count(status);

        resp.setData(list);
        resp.setTotal(count);
        return resp;
    }

    @RequestMapping("/detail/{id}")
    public ApiResponse detail(@PathVariable int id) {
        ApiResponse resp = new ApiResponse();
        resp.setData(historyService.getDetail(id));
        return resp;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ApiResponse edit(@PathVariable Integer id, WhsHistory history) {
        ApiResponse resp = new ApiResponse();

        resp.setData(historyService.update(history));

        return resp;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponse uploadHistory(@RequestParam String name, @RequestParam String content) {
        ApiResponse resp = new ApiResponse();
        resp.setData(historyService.uploadHistory(name, content));
        return resp;
    }
    @RequestMapping("/getById")
    public WhsHistory getById(@RequestParam(value = "id", required = true) int id) {
        return historyService.getDetail(id);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse insertOrUpdate(WhsHistory news, HttpServletRequest request) {
        if (historyService.insertOrUpdate(news)) {
            return ApiResponse.successResponse();
        }
        return ApiResponse.exceptionResponse();
    }
}
