package cn.yx.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsNews;
import cn.yx.entity.WhsSliders;
import cn.yx.mapper.WhsSlidersMapper;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午4:58:14
 * @version 1.0
 */

@Service
public class SlidersService extends AbstractService {

    @Autowired
    private WhsSlidersMapper whsSlidersMapper;

    public List<WhsSliders> list(Integer status, String timeRange, Integer pageSize, Integer currentPage) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        List<WhsSliders> rList = whsSlidersMapper.list(status, time[0], time[1], pageSize,
                pageSize * (currentPage - 1));
        rList.forEach((slider) -> {
            slider.setSrc(parseUri2Url(slider.getImgKey()));
        });
        return rList;
    }

    public int count(Integer status, String timeRange) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        return whsSlidersMapper.count(status, time[0], time[1]);
    }

    public List<WhsSliders> getNew(Integer size) {
        List<WhsSliders> rList = whsSlidersMapper.selectNew(size);
        rList.forEach((slider) -> {
            slider.setSrc(parseUri2Url(slider.getImgKey()));
        });
        return rList;
    }

    public WhsSliders upload(String title, String imgKey, String createTime, String url, Integer status) {
        WhsSliders news = new WhsSliders();
        news.setTitle(title);
        if (createTime == null) {
            news.setCreateTime(TimeUtil.getCurrentTime());
        } else {
            news.setCreateTime(TimeUtil.dayStr2Seconds(createTime));
        }
        if (StringUtils.isBlank(url)) {
            url = "#";
        }
        news.setUrl(url);
        news.setImgKey(imgKey);
        news.setStatus(status != null ? status.byteValue() : 0);
        whsSlidersMapper.insertSelective(news);
        return news;
    }

    @Override
    public int getLastId() {
        return whsSlidersMapper.getLastId();
    }

    public int update(WhsSliders com) {
        return whsSlidersMapper.updateByPrimaryKeySelective(com);
    }
}
