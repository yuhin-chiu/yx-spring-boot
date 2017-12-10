package cn.yx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.yx.entity.WhsActivities;
import cn.yx.entity.WhsHistory;
import cn.yx.mapper.WhsHistoryMapper;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午6:06:33
 * @version 1.0
 */

@Service
public class HistoryService {

    @Resource
    private WhsHistoryMapper hisMapper;

    public List<WhsHistory> list(Integer status, Integer pageSize, Integer currentPage) {
        List<WhsHistory> rList = hisMapper.list(status, pageSize, pageSize * (currentPage - 1));
        rList.forEach(item -> {
            item.setCreateTimeStr(TimeUtil.time2DayStr(item.getCreateTime()));
        });
        return rList;
    }

    public int count(Integer status) {
        return hisMapper.count(status);
    }

    public WhsHistory getDetail(int id) {
        return hisMapper.selectByPrimaryKey(id);
    }

    public int update(WhsHistory his) {
        return hisMapper.updateByPrimaryKeySelective(his);
    }

    public WhsHistory uploadHistory(String name, String content) {
        WhsHistory history = new WhsHistory();
        history.setContent(content);
        history.setName(name);
        history.setCreateTime(TimeUtil.getCurrentTime());
        hisMapper.insertSelective(history);
        return history;
    }

    public Boolean insertOrUpdate(WhsHistory demo) {
        if (demo.getId() == null) {
            return hisMapper.insertSelective(demo) > 0 ? true : false;
        } else {
            return hisMapper.updateByPrimaryKeySelective(demo) > 0 ? true : false;
        }
    }
}
