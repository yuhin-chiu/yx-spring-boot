package cn.yx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.entity.WhsAudience;
import cn.yx.entity.WhsCompanyApply;
import cn.yx.entity.WhsMediaApply;
import cn.yx.enums.ApiResponseEnum;
import cn.yx.model.ApiResponse;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午12:51:15
 * @version 1.0
 */

@RestController
@RequestMapping("/apply")
public class ApplyController extends AbstractController {

    @RequestMapping(value = "/audience", method = RequestMethod.POST)
    public ApiResponse audience(@RequestParam String company, @RequestParam String name, @RequestParam String phone,
            String tele, String mail) {
        ApiResponse res = new ApiResponse();
        WhsAudience record = new WhsAudience();
        
        record.setCompany(company);
        record.setName(name);
        record.setPhone(phone);
        record.setMail(mail);
        record.setTele(tele);
        record.setApplyTime(TimeUtil.getCurrentTime());
        
        Integer rInt = audienceService.apply(record);
        if(rInt> 0) {
            res.setEnum(ApiResponseEnum.SUCCESS);
        } else {
            res.setEnum(ApiResponseEnum.INTERNAL_ERROR);
        }
        res.setData(rInt);
        return res; 
    }
    
    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public ApiResponse company(@RequestParam String name, @RequestParam String principal, @RequestParam String phone,
            String tele, String mail, String address) {
        //TODO
        ApiResponse res = new ApiResponse();
        WhsCompanyApply record = new WhsCompanyApply();
        
        record.setName(name);
        record.setPrincipal(principal);
        record.setPhone(phone);
        record.setMail(mail);
        record.setTele(tele);
        record.setAddress(address);
        record.setApplyTime(TimeUtil.getCurrentTime());
        
        Integer rInt = companyService.apply(record);
        if(rInt> 0) {
            res.setEnum(ApiResponseEnum.SUCCESS);
        } else {
            res.setEnum(ApiResponseEnum.INTERNAL_ERROR);
        }
        res.setData(rInt);
        return res; 
    }
    
    @RequestMapping(value="/media", method = RequestMethod.POST)
    public ApiResponse media(@RequestParam String name, @RequestParam String principal, @RequestParam String phone,
            String tele, String mail) {
        ApiResponse res = new ApiResponse();
        WhsMediaApply record = new WhsMediaApply();
        
        record.setName(name);
        record.setPrincipal(principal);
        record.setPhone(phone);
        record.setMail(mail);
        record.setTele(tele);
        record.setApplyTime(TimeUtil.getCurrentTime());
        
        Integer rInt = mediaService.apply(record);
        if(rInt> 0) {
            res.setEnum(ApiResponseEnum.SUCCESS);
        } else {
            res.setEnum(ApiResponseEnum.INTERNAL_ERROR);
        }
        res.setData(rInt);
        return res; 
    }
    
    @RequestMapping("/media/list")
    public ApiResponse mediaList(Integer pageSize, Integer currentPage) {
        ApiResponse res = new ApiResponse();
        pageSize = (pageSize == null)? 20 : pageSize;
        currentPage = (currentPage ==null)? 1 : currentPage;
        
        List<WhsMediaApply> list = mediaService.listApply(pageSize, currentPage);
        
        res.setData(list);
        res.setDescription("默认返回第一页，每页20行");
        return res; 
    }
    
    @RequestMapping("/audience/list")
    public ApiResponse audienceList(Integer pageSize, Integer currentPage) {
        ApiResponse res = new ApiResponse();
        pageSize = (pageSize == null)? 20 : pageSize;
        currentPage = (currentPage ==null)? 1 : currentPage;
        
        List<WhsAudience> list = audienceService.listApply(pageSize, currentPage);
        //total
        
        
        res.setData(list);
        res.setDescription("默认返回第一页，每页20行");
        return res; 
    }
    
    @RequestMapping("/company/list")
    public ApiResponse companyList(Integer pageSize, Integer currentPage) {
        ApiResponse res = new ApiResponse();
        pageSize = (pageSize == null)? 20 : pageSize;
        currentPage = (currentPage ==null)? 1 : currentPage;
        
        List<WhsCompanyApply> list = companyService.listApply(pageSize, currentPage);
        
        res.setData(list);
        res.setDescription("默认返回第一页，每页20行");
        return res; 
    }
}
