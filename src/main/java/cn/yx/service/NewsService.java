package cn.yx.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.config.FileConfig;
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

    private static final String BASE_FILE_URL = "/api/download?fileName=";

    @Autowired
    private WhsNewsMapper whsNewsMapper;

    @Autowired
    private FileConfig fileConfig;
    public List<WhsNews> list(Integer status, Integer parent, String timeRange, String query, Integer pageSize, Integer currentPage) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        List<WhsNews> rList = whsNewsMapper.list(status, parent, time[0], time[1], query, pageSize, pageSize * (currentPage - 1));
        rList.forEach(item -> {
            item.setUrl(parseUri2Url(item.getUrl()));
        }); 
        return rList;
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

    public WhsNews uploadNews(String title, Byte parent, String content, String abstr, String author, String createTime, Long browses,
            String url, Integer status) {
        WhsNews news = new WhsNews();
        news.setTitle(title);
        news.setParent(parent);
        news.setContent(content);
        news.setAuthor(author);
        news.setAbstr(abstr);
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
    
    public Integer getLastId() {
        return whsNewsMapper.getLastId();
    }
    
//    private String[] splitUrl(String input) {
//        if (StringUtils.isBlank(input)) {
//            return new String[0];
//        }
//        return input.replaceAll("^", BASE_FILE_URL).replaceAll(",", "," + BASE_FILE_URL).split(",");
//    }
    
    private String parseUri2Url(String input) {
        if (StringUtils.isBlank(input)) {
            return "";
        }
        return BASE_FILE_URL + input;
    }
}
