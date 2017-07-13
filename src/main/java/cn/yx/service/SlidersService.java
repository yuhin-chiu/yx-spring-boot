package cn.yx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsSliders;
import cn.yx.mapper.WhsSlidersMapper;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午4:58:14 
 * @version 1.0
 */

@Service
public class SlidersService {

	@Autowired
	private WhsSlidersMapper whsSlidersMapper;
	
	public List<WhsSliders> getAll() {
		return whsSlidersMapper.selectAll();
	}
	
}
