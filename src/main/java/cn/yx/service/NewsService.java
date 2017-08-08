package cn.yx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsNews;
import cn.yx.mapper.WhsNewsMapper;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午5:26:20
 * @version 1.0
 */

@Service
public class NewsService {

    @Autowired
    private WhsNewsMapper whsNewsMapper;

    public List<WhsNews> list(Integer status, Integer parent, String timeRange, String query, Integer pageSize, Integer currentPage) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        return whsNewsMapper.list(status, parent, time[0], time[1], query, pageSize, pageSize * (currentPage - 1));
    }

    public int count(Integer status, Integer parent, String timeRange, String query) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        return whsNewsMapper.count(status, parent, time[0], time[1], query);
    }

    public WhsNews getDetail(int id) {
        return whsNewsMapper.selectByPrimaryKey(id);
    }

    public List<WhsNews> getNew() {
        return whsNewsMapper.list(null, null, -1, -1, null, 20, 1);
    }

    public int update(WhsNews com) {
        return whsNewsMapper.updateByPrimaryKeySelective(com);
    }

    public WhsNews uploadNews(String title, Byte parent, String content, String author, String createTime, Long browses,
            String url, Integer status) {
        WhsNews news = new WhsNews();
        news.setTitle(title);
        news.setParent(parent);
        news.setContent(content);
        news.setAuthor(author);
        if (createTime == null) {
            news.setCreateTime(TimeUtil.getCurrentTime());
        } else {
            news.setCreateTime(TimeUtil.dayStr2Seconds(createTime));
        }
        news.setBrowses(browses);
        news.setUrl(url);
        news.setStatus(status!=null?status.byteValue():0);
        whsNewsMapper.insertSelective(news);
        return news;
    }

}
