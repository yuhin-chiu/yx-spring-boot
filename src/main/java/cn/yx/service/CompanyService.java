package cn.yx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsCompany;
import cn.yx.entity.WhsCompanyApply;
import cn.yx.mapper.WhsCompanyApplyMapper;
import cn.yx.mapper.WhsCompanyMapper;
import cn.yx.util.FileUtil;
import cn.yx.util.TimeUtil;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午12:15:46
 * @version 1.0
 */

@Service
public class CompanyService extends AbstractService {

    @Autowired
    private WhsCompanyMapper comMapper;
    @Autowired
    private WhsCompanyApplyMapper comApplyMapper;

    public List<WhsCompany> getRecomm() {
        // TODO 推荐是1
        return this.list(1, 20, 1);
    }

    public List<WhsCompany> list(Integer status, Integer pageSize, Integer currentPage) {
        List<WhsCompany> rList = comMapper.list(status, pageSize, pageSize * (currentPage - 1));
        rList.forEach((item) -> {
            item.setImageUrl(parseUri2Url(item.getImage()));
            item.setCreateTimeStr(TimeUtil.time2DayStr(item.getCreateTime()));
        });
        return rList;
    }

    public int count(Integer status) {
        return comMapper.count(status);
    }

    public int apply(WhsCompanyApply record) {
        return comApplyMapper.insertSelective(record);
    }

    public List<WhsCompanyApply> listApply(Integer status, String timeRange, Integer pageSize, Integer currentPage) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        return comApplyMapper.list(status, time[0], time[1], pageSize, pageSize * (currentPage - 1));
    }

    public int countApply(Integer status, String timeRange) {
        long[] time = TimeUtil.splitTimeRange(timeRange);
        return comApplyMapper.count(status, time[0], time[1]);
    }

    public int updateApply(WhsCompanyApply com) {
        return comApplyMapper.updateByPrimaryKeySelective(com);
    }

    public int update(WhsCompany com) {
        return comMapper.updateByPrimaryKeySelective(com);
    }

    public int add(WhsCompany com) {
        return comMapper.insertSelective(com);
    }

    @Override
    public int getLastId() {
        return comMapper.getLastId();
    }
}
