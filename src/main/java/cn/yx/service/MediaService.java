package cn.yx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsMedia;
import cn.yx.entity.WhsMediaApply;
import cn.yx.mapper.WhsMediaApplyMapper;
import cn.yx.mapper.WhsMediaMapper;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午12:33:17
 * @version 1.0
 */

@Service
public class MediaService extends AbstractService {

    @Autowired
    private WhsMediaMapper mediaMapper;
    @Autowired
    private WhsMediaApplyMapper mediaApplyMapper;

    public List<WhsMedia> getRecomm() {
        return this.list(1, 20, 1);
    }

    public List<WhsMedia> list(Integer status, Integer pageSize, Integer currentPage) {
        List<WhsMedia> rList = mediaMapper.list(status, pageSize, pageSize * (currentPage - 1));
        rList.forEach(item -> {
            item.setImage(parseUri2Url(item.getImage()));
        });
        return rList;
    }

    public int count(Integer status) {
        return mediaMapper.count(status);
    }

    public int apply(WhsMediaApply record) {
        return mediaApplyMapper.insertSelective(record);
    }

    public List<WhsMediaApply> listApply(Integer status, String timeRange, Integer pageSize, Integer currentPage) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        return mediaApplyMapper.list(status, time[0], time[1], pageSize, pageSize * (currentPage - 1));
    }

    public int countApply(Integer status, String timeRange) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        return mediaApplyMapper.count(status, time[0], time[1]);
    }

    public int update(WhsMedia media) {
        return mediaMapper.updateByPrimaryKeySelective(media);
    }

    public int updateApply(WhsMediaApply media) {
        return mediaApplyMapper.updateByPrimaryKeySelective(media);
    }

    public int add(WhsMedia media) {
        return mediaMapper.insertSelective(media);
    }

    public WhsMedia getDetail(int id) {
        WhsMedia rAct = mediaMapper.selectByPrimaryKey(id);
        return rAct;
    }

    public Boolean insertOrUpdate(WhsMedia demo) {
        if (demo.getId() == null) {
            return mediaMapper.insertSelective(demo) > 0 ? true : false;
        } else {
            return mediaMapper.updateByPrimaryKeySelective(demo) > 0 ? true : false;
        }
    }
}
