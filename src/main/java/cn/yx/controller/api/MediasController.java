package cn.yx.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.entity.WhsMedia;
import cn.yx.model.ApiResponse;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午3:19:59
 * @version 1.0
 */

@RestController
@RequestMapping("/api/medias")
public class MediasController extends AbstractController {

    @RequestMapping("/list")
    public ApiResponse list(@RequestParam(defaultValue = "-1") Integer status,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage) {
        ApiResponse resp = new ApiResponse();
        List<WhsMedia> list = mediaService.list(status, pageSize, currentPage);
        int count = mediaService.count(status);

        resp.setData(list);
        resp.setTotal(count);
        return resp;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ApiResponse edit(@PathVariable Integer id, WhsMedia media) {
        ApiResponse resp = new ApiResponse();

        resp.setData(mediaService.update(media));

        return resp;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponse uploadNews(WhsMedia media) {
        ApiResponse resp = new ApiResponse();
        resp.setData(mediaService.add(media));
        return resp;
    }

}
