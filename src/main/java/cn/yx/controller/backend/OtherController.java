package cn.yx.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yx.service.HomepageService;

@Controller
@RequestMapping("/backend/others")
public class OtherController {

	@Autowired
    private HomepageService homepageService;

    @RequestMapping({"", "/", "/list"})
    public String list() {
        return "/other/list";
    }
    
    @RequestMapping("/homepage")
    public String home(Model model) {
    	
    	model.addAttribute("introduction", homepageService.getBaseInfo().getString("introduction"));
        return "/other/homepage";
    }
    
    @RequestMapping("/sliders")
    public String sliders() {
        return "/other/sliders";
    }
    
    @RequestMapping("/help")
    public String publish() {
        return "/other/help";
    }
}
