package cn.yx.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.yx.entity.WhsDownloads;
import cn.yx.entity.WhsNews;
import cn.yx.enums.ApiResponseEnum;
import cn.yx.model.ApiResponse;
import cn.yx.util.FileUtil;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月30日 下午8:25:24
 * @version 1.0
 */

@RestController
@RequestMapping("/api/downloads")
public class DownloadsController extends AbstractController {

    @RequestMapping(value = "/list")
    @ResponseBody
    public ApiResponse listDownloads(@RequestParam(defaultValue = "-1") Integer status,
            @RequestParam(defaultValue = "-1") Integer type,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage) {
        ApiResponse resp = new ApiResponse();

        resp.setData(downloadsService.list(status, type, pageSize, currentPage));
        resp.setTotal(downloadsService.count(status, type));
        return resp;
    }
    
    @RequestMapping(value = "/listFiles")
    @ResponseBody
    public ApiResponse listFiles() {
        ApiResponse resp = new ApiResponse();
        resp.setData(downloadsService.getNew(0));
        return resp;
    }
    @RequestMapping(value = "/listHotels")
    @ResponseBody
    public ApiResponse listHotels() {
        ApiResponse resp = new ApiResponse();
        resp.setData(downloadsService.getNew(1));
        return resp;
    }
    
    @RequestMapping(value = "/listInfos")
    @ResponseBody
    public ApiResponse listInfos() {
        ApiResponse resp = new ApiResponse();
        resp.setData(downloadsService.getNew(2));
        return resp;
    }

    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse publishDownloads(WhsDownloads down, HttpServletRequest request) {
        ApiResponse temp, resp = new ApiResponse();
        List<String> annexs = new ArrayList<>();
        List<String> annexNames = new ArrayList<>();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Integer id = downloadsService.getLastId();
                String fileName = file.getOriginalFilename();
                temp = FileUtil.uploadFile(file, "publish/" + id + "/" + FileUtil.randomName(fileName), this.getClass());
                if (temp.getCode().compareTo(ApiResponseEnum.SUCCESS.getCode()) != 0) {
                    return temp;
                }
                annexs.add((String) temp.getData());
                annexNames.add(fileName);
                down.setId(id);
            }
        }

        // down.setAuthor(AdminInfoHolder.getAdmin().getUserName());
        down.setAnnex(StringUtils.join(annexs, ","));
        down.setAnnexName(StringUtils.join(annexNames, ","));
        down.setCreateTime(TimeUtil.getCurrentTime());
        downloadsService.add(down);

        resp.setData(down);
        return resp;
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse upload(WhsDownloads down, @RequestParam(defaultValue = "0")Integer target, HttpServletRequest request) {
        ApiResponse temp = null, resp = new ApiResponse();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files[]");
        if(files.size() == 0) {
            resp.setCode(ApiResponseEnum.FILE_SAVE_EMPTY.getCode());
            return resp;
        }
        MultipartFile file = files.get(0);
        String fileName = file.getOriginalFilename();
        if (!file.isEmpty()) {
            Integer id = downloadsService.getLastId();
            temp = FileUtil.uploadFile(file, "upload/" + id + "/" + FileUtil.randomName(fileName), this.getClass());
            if (temp.getCode().compareTo(ApiResponseEnum.SUCCESS.getCode()) != 0) {
                return temp;
            }
            down.setId(id);
        } else {
            resp.setEnum(ApiResponseEnum.FILE_SAVE_EMPTY);
            return resp;
        }

        down.setType(target.byteValue());
        down.setAnnex((String)temp.getData());
        if(StringUtils.isBlank(down.getTitle())) {
            down.setTitle(fileName.substring(0, fileName.lastIndexOf(".")));
        }
        down.setAnnexName(fileName.substring(fileName.lastIndexOf(".")));
        down.setCreateTime(TimeUtil.getCurrentTime());
        downloadsService.add(down);

        resp.setData(down);
        return resp;
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ApiResponse edit(@PathVariable Integer id, WhsDownloads down) {
        ApiResponse resp = new ApiResponse();

        resp.setData(downloadsService.update(down));

        return resp;
    }

    @RequestMapping("/getById")
    public WhsDownloads getById(@RequestParam(value = "id", required = true) int id) {
        return downloadsService.getById(id);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse insertOrUpdate(WhsDownloads news, HttpServletRequest request) {
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
        if (downloadsService.insertOrUpdate(news)) {
            return ApiResponse.successResponse();
        }
        return ApiResponse.exceptionResponse();
    }
}
