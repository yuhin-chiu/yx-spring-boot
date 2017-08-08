package cn.yx.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuxuanjiao
 * @date 2017年8月5日 下午9:21:11 
 * @version 1.0
 */
@Controller
@RequestMapping("/backend/news")
public class NewMcontroller {

    @RequestMapping({"", "/", "/list"})
    public String list() {
        return "/news/list";
    }
    
    @RequestMapping("/publish")
    public String publish() {
        return "/news/publish";
    }
}
