package cn.yx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.yx.entity.WhsActivities;
import cn.yx.entity.WhsNews;
import cn.yx.entity.WhsSliders;
import cn.yx.service.ActivitiesService;
import cn.yx.service.HomepageService;
import cn.yx.service.NewsService;
import cn.yx.service.SlidersService;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午3:19:59 
 * @version 1.0
 */
@RestController
@RequestMapping("/homepage")
public class HomepageController {
	
	@Autowired
	private HomepageService homepageService;
	@Autowired
	private SlidersService slidersService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private ActivitiesService activitiesService;
	
	@RequestMapping("/base")
	public JSONObject base() {
		JSONObject resp = homepageService.getBaseInfo();
		List<WhsSliders> sliders = slidersService.getAll();
		List<WhsNews> news = newsService.getAll();
		List<WhsActivities> activities = activitiesService.getAll();
		
		resp.put("sliders", sliders);
		resp.put("news", news);
		resp.put("activities", activities);
			
		return resp;
	}
}
