package cn.yx.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuxuanjiao
 * @date 2017年8月5日 下午9:53:57 
 * @version 1.0
 */

@Controller
@RequestMapping("/backend/histories")
public class HistoryMController {

    @RequestMapping({"", "/", "/list"})
    public String list() {
        return "/history/list";
    }
}
