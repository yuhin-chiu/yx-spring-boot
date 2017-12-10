package cn.yx.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.yx.enums.ApiResponseEnum;
import cn.yx.model.ApiResponse;
import cn.yx.service.AbstractService;
import cn.yx.service.ActivitiesService;
import cn.yx.service.AdminService;
import cn.yx.service.ArticlesService;
import cn.yx.service.AudienceService;
import cn.yx.service.CompanyService;
import cn.yx.service.DownloadsService;
import cn.yx.service.HistoryService;
import cn.yx.service.HomepageService;
import cn.yx.service.MediaService;
import cn.yx.service.NewsService;
import cn.yx.service.SlidersService;
import cn.yx.util.FileUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午12:19:12
 * @version 1.0
 */
public abstract class AbstractController {

    @Resource
    protected ActivitiesService activitiesService;
    @Resource
    protected HomepageService homepageService;
    @Resource
    protected SlidersService slidersService;
    @Resource
    protected NewsService newsService;
    @Resource
    protected CompanyService companyService;
    @Resource
    protected MediaService mediaService;
    @Resource
    protected AudienceService audienceService;
    @Resource
    protected HistoryService historyService;
    @Resource
    protected DownloadsService downloadsService;
    @Resource
    protected AdminService adminService;
    @Resource
    protected ArticlesService articlesService;
    
    protected ApiResponse uploadFiles(HttpServletRequest request, AbstractService service, Class clzss) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files[]");
        MultipartFile file = null;
        List<String> annexs = new ArrayList<>();
        ApiResponse temp, resp = new ApiResponse();

        if(files == null || files.size() == 0) {
            resp.setCode(-1);
            return resp;
        }
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                Integer id = service.getLastId(); // 有问题
                String fileName = file.getOriginalFilename();
                temp = FileUtil.uploadFile(file, clzss.getSimpleName() + "/" + id + "/" + FileUtil.randomName(fileName), clzss);
                if (temp.getCode().compareTo(ApiResponseEnum.SUCCESS.getCode()) != 0) {
                    return temp;
                }
                
                annexs.add((String) temp.getData());
            } else {
                resp.setDescription("某些上传文件为空，请检查！");
            }
        }

        resp.setMsg("上传文件成功");
        resp.setData(StringUtils.join(annexs, ","));
        return resp;
    }

    protected ApiResponse uploadFile(HttpServletRequest request, Class<?> clzss, String key) {
        MultipartFile file = null;
        String annex = null;
        ApiResponse temp = null;

        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            file = multiRequest.getFile(key);
        }  catch (Exception e) {
            return null;
        }
        if (file == null) {
            return null;
        }

        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            temp = FileUtil.uploadFile(file, clzss.getSimpleName() + "/" + FileUtil.randomName(fileName), clzss);
            if (temp.getCode().compareTo(ApiResponseEnum.SUCCESS.getCode()) != 0) {
                return temp;
            }
            annex = (String) temp.getData();
        } else {
            return ApiResponse.fileSaveEmpty();
        }

        return ApiResponse.successResponse().setData(annex);
    }
}
