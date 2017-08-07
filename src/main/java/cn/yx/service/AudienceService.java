package cn.yx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsAudience;
import cn.yx.mapper.WhsAudienceMapper;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午12:53:28
 * @version 1.0
 */

@Service
public class AudienceService {

    @Autowired
    private WhsAudienceMapper auMapper;

    public int apply(WhsAudience record) {
        return auMapper.insertSelective(record);
    }

    public List<WhsAudience> listApply(Integer status, Integer pageSize, Integer currentPage) {
        return auMapper.list(status, pageSize, pageSize * (currentPage - 1));
    }

    public int countApply(Integer status) {
        return auMapper.count(status);
    }
}
