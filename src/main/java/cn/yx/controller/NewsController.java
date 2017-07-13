package cn.yx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yx.entity.WhsNews;
import cn.yx.service.NewsService;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午3:19:59 
 * @version 1.0
 */
@RestController
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("/list")
	public List<WhsNews> list() {
		return newsService.getAll();
	}
	
	@RequestMapping("/detail/{id}")
	public WhsNews detail(@PathVariable(required = true) int id) {
		return newsService.getDetail(id);
	}  
}
