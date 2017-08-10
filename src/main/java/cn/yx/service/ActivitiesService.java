package cn.yx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsActivities;
import cn.yx.mapper.WhsActivitiesMapper;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午5:28:06
 * @version 1.0
 */

@Service
public class ActivitiesService {

    @Autowired
    private WhsActivitiesMapper whsActivitiesMapper;

    public List<WhsActivities> list(Integer status, Integer pageSize, Integer currentPage) {
        return whsActivitiesMapper.list(status, pageSize, pageSize * (currentPage - 1));
    }

    public int count(Integer status) {
        return whsActivitiesMapper.count(status);
    }

    public WhsActivities getDetail(int id) {
        return whsActivitiesMapper.selectByPrimaryKey(id);
    }

    public List<WhsActivities> getNew() {
        return whsActivitiesMapper.list(null, 20, 0);
    }

    public int update(WhsActivities com) {
        return whsActivitiesMapper.updateByPrimaryKeySelective(com);
    }

    public WhsActivities uploadActivities(String title, String content, String author, String createTime, Long browses,
            String url, Integer status) {
        WhsActivities activities = new WhsActivities();
        activities.setTitle(title);
        activities.setContent(content);
        activities.setAuthor(author);
        if (createTime == null) {
            activities.setCreateTime(TimeUtil.getCurrentTime());
        } else {
            activities.setCreateTime(TimeUtil.dayStr2Seconds(createTime));
        }
        activities.setBrowses(browses);
        activities.setUrl(url);
        activities.setStatus(status.byteValue());
        whsActivitiesMapper.insertSelective(activities);
        return activities;
    }
}
