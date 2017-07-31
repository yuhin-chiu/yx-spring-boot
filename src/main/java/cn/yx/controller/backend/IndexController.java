package cn.yx.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yx.controller.api.AbstractController;

/**
 * @author yuxuanjiao
 * @date 2017年7月30日 下午4:45:50 
 * @version 1.0
 */

@Controller
@RequestMapping("/backend")
public class IndexController extends AbstractController {

    @RequestMapping({"", "/", "/index"})
    public String backendIndex() {
        
        return "/backend/index";
    }
}
