package cn.yx.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.yx.entity.WhsDownloads;
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
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage) {
        ApiResponse resp = new ApiResponse();

        resp.setData(downloadsService.list(status, pageSize, currentPage));
        resp.setTotal(downloadsService.count(status));
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
                temp = FileUtil.uploadFile(file, id + "/" + FileUtil.randomName(fileName));
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

}
