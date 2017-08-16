package cn.yx.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backend/others")
public class OtherController {

    @RequestMapping({"", "/", "/sliders"})
    public String list() {
        return "/other/sliders";
    }
    
    @RequestMapping("/help")
    public String publish() {
        return "/other/help";
    }
}
