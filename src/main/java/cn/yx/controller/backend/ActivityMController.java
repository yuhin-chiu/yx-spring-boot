package cn.yx.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuxuanjiao
 * @date 2017年8月5日 下午9:52:48 
 * @version 1.0
 */

@Controller
@RequestMapping("/backend/activities")
public class ActivityMController {
    
    @RequestMapping({"", "/", "/list"})
    public String list() {
        return "/activity/list";
    }
    
    @RequestMapping("/publish")
    public String publish() {
        return "/activity/publish";
    }
}
