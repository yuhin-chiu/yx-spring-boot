package cn.yx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsAudience;
import cn.yx.entity.WhsMedia;
import cn.yx.mapper.WhsAudienceMapper;
import cn.yx.util.TimeUtil;

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

    public List<WhsAudience> listApply(Integer status, String timeRange, Integer pageSize, Integer currentPage) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        return auMapper.list(status, time[0], time[1], pageSize, pageSize * (currentPage - 1));
    }

    public int countApply(Integer status, String timeRange) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        return auMapper.count(status, time[0], time[1]);
    }
    public int update(WhsAudience record) {
        return auMapper.updateByPrimaryKeySelective(record);
    }
}
