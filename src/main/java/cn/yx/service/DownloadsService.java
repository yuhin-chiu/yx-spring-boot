package cn.yx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.yx.entity.WhsCompany;
import cn.yx.entity.WhsDownloads;
import cn.yx.mapper.WhsDownloadsMapper;

/**
 * @author yuxuanjiao
 * @date 2017年7月30日 下午7:43:23 
 * @version 1.0
 */

@Service
public class DownloadsService {

    @Resource
    WhsDownloadsMapper downMapper;
    
    public List<WhsDownloads> list(Integer status, Integer pageSize, Integer currentPage) {
        return downMapper.list(status, pageSize, pageSize * (currentPage - 1));
    }

    public int count(Integer status) {
        return downMapper.count(status);
    }
    
    public int add(WhsDownloads down) {
        return downMapper.insertSelective(down);
    }
    
    public Integer getLastId() {
        return downMapper.getLastId();
    }
}
