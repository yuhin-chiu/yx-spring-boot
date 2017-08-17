package cn.yx.controller.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.entity.WhsSliders;
import cn.yx.model.ApiResponse;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年8月17日 上午9:59:05 
 * @version 1.0
 */
@RestController
@RequestMapping("/api/sliders")
public class SliderController extends AbstractController {

    @RequestMapping("/list")
    public ApiResponse list(Integer status, @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "") String timeRange) {
        ApiResponse resp = new ApiResponse();
        List<WhsSliders> list = slidersService.list(status, timeRange, pageSize, currentPage);
        int count = slidersService.count(status, timeRange);
        resp.setData(list);
        resp.setTotal(count);
        return resp;
    }

//    @RequestMapping("/detail/{id}")
//    public ApiResponse detail(@PathVariable(required = true) int id) {
//        ApiResponse resp = new ApiResponse();
//        resp.setData(slidersService.getDetail(id));
//        return resp;
//    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ApiResponse edit(@PathVariable Integer id, WhsSliders sliders) {
        ApiResponse resp = new ApiResponse();

        if(sliders.getStatus() == 1) {
        	sliders.setCreateTime(TimeUtil.getCurrentTime());
        }
        resp.setData(slidersService.update(sliders));

        return resp;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponse uploadNews(@RequestParam("title") String title, String createTime,
            @RequestParam(name = "url", defaultValue = "", required = true) String url,
            @RequestParam(defaultValue = "1", required = false) Integer status, HttpServletRequest request) {
        ApiResponse resp = uploadFiles(request, this.getClass());
        String imgKey = (String) resp.getData();
        resp.setData(slidersService.upload(title, imgKey, createTime, url, status));
        return resp;
    }
    
    @RequestMapping("/new")
    public ApiResponse getNew(@RequestParam(name="size", defaultValue = "3") Integer size) {
        ApiResponse resp = new ApiResponse();

        resp.setData(slidersService.getNew(size));
        resp.setTotal(size);
        return resp;
    }
}
