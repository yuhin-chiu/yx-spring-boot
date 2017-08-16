package cn.yx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsActivities;
import cn.yx.entity.WhsNews;
import cn.yx.mapper.WhsActivitiesMapper;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午5:28:06
 * @version 1.0
 */

@Service
public class ActivitiesService extends AbstractService {

    @Autowired
    private WhsActivitiesMapper whsActivitiesMapper;

    /**
     * currentPage从1开始
     * @param status
     * @param timeRange
     * @param query
     * @param pageSize
     * @param currentPage
     * @return
     */
    public List<WhsActivities> list(Integer status, String timeRange, String query, Integer pageSize, Integer currentPage) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        List<WhsActivities> rList = whsActivitiesMapper.list(status, time[0], time[1], query, pageSize,
                pageSize * (currentPage - 1));
        rList.forEach(item -> {
            item.setUrl(parseUri2Url(item.getUrl()));
            item.setCreateTimeStr(TimeUtil.time2DayStr(item.getCreateTime()));
        });
        return rList;
    }

    public int count(Integer status, String timeRange, String query) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        return whsActivitiesMapper.count(status, time[0], time[1], query);
    }

    public WhsActivities getDetail(int id) {
    	WhsActivities rAct = whsActivitiesMapper.selectByPrimaryKey(id);
    	rAct.setCreateTimeStr(TimeUtil.time2DayStr(rAct.getCreateTime()));
        return rAct;
    }

    public List<WhsActivities> getNew() {
        return list(1, "", null, 20, 1);// currentPage应该从1开始
    }

    public int update(WhsActivities com) {
        return whsActivitiesMapper.updateByPrimaryKeySelective(com);
    }

    public WhsActivities uploadActivities(String title, String content, String abstr, String author, String createTime, Long browses,
            String url, Integer status) {
        WhsActivities activities = new WhsActivities();
        activities.setTitle(title);
        activities.setContent(content);
        activities.setAuthor(author);
        activities.setAbstr(abstr);
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
