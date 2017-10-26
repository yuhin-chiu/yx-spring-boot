package cn.yx.controller.backend;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yx.service.ArticlesService;
import cn.yx.service.HomepageService;

@Controller
@RequestMapping("/backend/others")
public class OtherController {

    @Resource
    private HomepageService homepageService;
    @Resource
    protected ArticlesService articlesService;

    @RequestMapping({ "", "/"})
    public String index() {
        return "redirect:/backend/others/article";
    }

    @RequestMapping("/list")
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
    public String help() {
        return "/other/help";
    }

    @RequestMapping("/article")
    public String article(Model model) {
        model.addAttribute("parents", articlesService.getParents());
        return "/other/article";
    }
}
