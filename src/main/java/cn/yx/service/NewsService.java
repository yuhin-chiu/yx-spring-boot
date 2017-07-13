package cn.yx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsActivities;
import cn.yx.entity.WhsNews;
import cn.yx.mapper.WhsNewsMapper;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午5:26:20 
 * @version 1.0
 */

@Service
public class NewsService {

	@Autowired
	private WhsNewsMapper whsNewsMapper;
	
	public List<WhsNews> getAll() {
		return whsNewsMapper.selectAll();
	}
	
	public WhsNews getDetail(int id) {
		return whsNewsMapper.selectByPrimaryKey(id);
	}
}
