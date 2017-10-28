package cn.yx.controller.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;

import cn.yx.entity.WhsArticle;
import cn.yx.enums.ApiResponseEnum;
import cn.yx.model.ApiResponse;

/**
 * @author yuxuanjiao
 * @date 2017年10月26日 下午4:10:10
 * @version 1.0
 */

@RestController
@RequestMapping("/api/articles")
public class ArticlesController extends AbstractController {

    @RequestMapping("/getTitles")
    @SuppressWarnings("unchecked")
    public JSONArray getTitles(@RequestParam(value = "parent") Integer parent) {
        return new JSONArray(articlesService.getTitles(parent));
    }

    @RequestMapping("/getContent")
    public WhsArticle getContent(@RequestParam(value = "id") Long id) {
        return articlesService.getContent(id);
    }

    @RequestMapping("/getContent/{id}")
    public ApiResponse getContentPath(@PathVariable(required = true) Long id) {
        ApiResponse resp = new ApiResponse();
        resp.setData(articlesService.getContent(id));
        return resp;
    }

    @RequestMapping(value = "/editContent", method = RequestMethod.POST)
    public ApiResponse editContent(@RequestParam(value = "id") Long id,
            @RequestParam(value = "content") String content) {
        ApiResponse api = new ApiResponse();
        if (articlesService.editContent(id, content) > 0) {
            api.setEnum(ApiResponseEnum.SUCCESS);
        } else {
            api.setEnum(ApiResponseEnum.COMMON_ERROR);
        }
        return api;
    }
}
