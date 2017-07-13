package cn.yx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsActivities;
import cn.yx.mapper.WhsActivitiesMapper;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午5:28:06 
 * @version 1.0
 */

@Service
public class ActivitiesService {
	
	@Autowired
	private WhsActivitiesMapper whsActivitiesMapper;
	
	public List<WhsActivities> getAll() {
		return whsActivitiesMapper.selectAll();
	}
	
	public WhsActivities getDetail(int id) {
		return whsActivitiesMapper.selectByPrimaryKey(id);
	}
}
