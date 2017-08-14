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

import cn.yx.entity.WhsCompany;
import cn.yx.enums.ApiResponseEnum;
import cn.yx.model.ApiResponse;
import cn.yx.util.FileUtil;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午7:20:06
 * @version 1.0
 */

@RestController
@RequestMapping("/api/company")
public class CompanyController extends AbstractController {

    @RequestMapping("/list")
    public ApiResponse list(@RequestParam(defaultValue = "-1") Integer status,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "1") Integer currentPage) {
        ApiResponse resp = new ApiResponse();

        resp.setData(companyService.list(status, pageSize, currentPage));
        resp.setTotal(companyService.count(status));
        return resp;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ApiResponse edit(@PathVariable Integer id, WhsCompany company) {
        ApiResponse resp = new ApiResponse();

        resp.setData(companyService.update(company));

        return resp;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponse add(WhsCompany company) {
        ApiResponse resp = new ApiResponse();

        resp.setData(companyService.add(company));

        return resp;
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ApiResponse upload(WhsCompany company, HttpServletRequest request) {
        ApiResponse temp = null, resp = new ApiResponse();
        List<String> annexs = new ArrayList<>();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files[]");
        if(files.size() == 0) {
            resp.setCode(ApiResponseEnum.FILE_SAVE_EMPTY.getCode());
            return resp;
        }
        MultipartFile file = files.get(0);
        String fileName = file.getOriginalFilename();
        if (!file.isEmpty()) {
            Integer id = companyService.getLastId();
            temp = FileUtil.uploadFile(file, "company/" + id + "/" + FileUtil.randomName(fileName), this.getClass());
            if (temp.getCode().compareTo(ApiResponseEnum.SUCCESS.getCode()) != 0) {
                return temp;
            }
            annexs.add((String) temp.getData());
            company.setId(id);
        } else {
            resp.setEnum(ApiResponseEnum.FILE_SAVE_EMPTY);
            return resp;
        }
        
        company.setImage(StringUtils.join(annexs, ","));
        company.setCreateTime(TimeUtil.getCurrentTime());
        resp.setData(companyService.add(company));

        return resp;
    }
}
