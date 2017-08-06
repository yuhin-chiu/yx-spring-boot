package cn.yx.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuxuanjiao
 * @date 2017年8月5日 下午3:29:21 
 * @version 1.0
 */

@Controller
@RequestMapping("/backend/applies")
public class ApplyMController {
    
    @RequestMapping({"", "/", "/company"}) 
    public String index() {
        return "/apply/company";
    }
    
    @RequestMapping("/audience")
    public String audience() {
        return "/apply/audience";
    }
    @RequestMapping("/media")
    public String media() {
        return "/apply/media";
    }
}
