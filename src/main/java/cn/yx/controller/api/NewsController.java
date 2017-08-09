package cn.yx.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.yx.entity.WhsNews;
import cn.yx.enums.ApiResponseEnum;
import cn.yx.model.ApiResponse;
import cn.yx.util.FileUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午3:19:59
 * @version 1.0
 */

@RestController
@RequestMapping("/api/news")
public class NewsController extends AbstractController {

    @RequestMapping("/list")
    public ApiResponse list(@RequestParam(defaultValue = "-1") Integer status,
            @RequestParam(defaultValue = "-1") Integer parent, @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "") String timeRange, String query) {
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
            @RequestParam String abstr, String author, String createTime, Long browses, Integer status,
            HttpServletRequest request) {
        ApiResponse resp = uploadFiles(request);
        String fileNames = (String)resp.getData();
        
        System.out.println(fileNames);
        String url = fileNames;
        resp.setData(newsService.uploadNews(title, target, content, abstr, author, createTime, browses, url, status));
        return resp;
    }
    
    private ApiResponse uploadFiles(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files[]");
        MultipartFile file = null;
        System.out.println(files.size());
        List<String> annexs = new ArrayList<>();
        ApiResponse temp, resp = new ApiResponse();

        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                Integer id = newsService.getLastId();
                String fileName = file.getOriginalFilename();
                temp = FileUtil.uploadFile(file, id + "/" + FileUtil.randomName(fileName));
                if (temp.getCode().compareTo(ApiResponseEnum.SUCCESS.getCode()) != 0) {
                    return temp;
                }
                annexs.add((String) temp.getData());
                
                temp = FileUtil.uploadFile(file);
                if (temp.getCode().compareTo(ApiResponseEnum.SUCCESS.getCode()) != 0) {
                    return temp;
                }
            } else {
                resp.setDescription("某些上传文件为空，请检查！");
            }
        }

        resp.setMsg("上传文件成功");
        resp.setData(StringUtils.join(annexs, ","));
        return resp;
    }

}
