package cn.yx.controller.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.entity.WhsCompany;
import cn.yx.model.ApiResponse;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午7:20:06
 * @version 1.0
 */

@RestController
@RequestMapping("/company")
public class CompanyController extends AbstractController {

    @RequestMapping("/list")
    public ApiResponse list(@RequestParam(defaultValue = "0") Integer status,
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
}
