package cn.yx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.entity.WhsActivities;
import cn.yx.service.ActivitiesService;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午3:19:59 
 * @version 1.0
 */
@RestController
@RequestMapping("/activities")
public class ActivitiesController {
	
	@Autowired
	private ActivitiesService activitiesService;
	
	@RequestMapping("/list")
	public List<WhsActivities> list() {
		return activitiesService.getAll();
	}
	
	@RequestMapping("/detail/{id}")
	public WhsActivities detail(@PathVariable(required = true) int id) {
		return activitiesService.getDetail(id);
	}  
}
