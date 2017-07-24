package cn.yx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsCompany;
import cn.yx.entity.WhsMedia;
import cn.yx.entity.WhsMediaApply;
import cn.yx.mapper.WhsMediaApplyMapper;
import cn.yx.mapper.WhsMediaMapper;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午12:33:17 
 * @version 1.0
 */

@Service
public class MediaService {

    @Autowired
    private WhsMediaMapper mediaMapper;
    @Autowired
    private WhsMediaApplyMapper mediaApplyMapper;
    
    public List<WhsMedia> getRecomm() {
        return list(1, 20, 0);
    }
    
    public List<WhsMedia> list(Integer status, Integer limit, Integer offset) {
        return mediaMapper.list(status, limit, offset);
    }
    
    public int count(Integer status) {
        return mediaMapper.count(status);
    }
    
    public int apply(WhsMediaApply record) {
        return mediaApplyMapper.insertSelective(record);
    }
    
    public List<WhsMediaApply> listApply(Integer pageSize, Integer currentPage) {
        
        return mediaApplyMapper.list(pageSize, pageSize * (currentPage - 1));
    }
    
    public int countApply() {
        return mediaApplyMapper.count();
    }
}
