package cn.yx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.entity.WhsAudience;
import cn.yx.entity.WhsCompany;
import cn.yx.entity.WhsCompanyApply;
import cn.yx.mapper.WhsCompanyApplyMapper;
import cn.yx.mapper.WhsCompanyMapper;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午12:15:46
 * @version 1.0
 */

@Service
public class CompanyService {

    @Autowired
    private WhsCompanyMapper comMapper;
    @Autowired
    private WhsCompanyApplyMapper comApplyMapper;

    public List<WhsCompany> getRecomm() {
        // TODO 推荐是1
        return comMapper.list(1, 20, 0);
    }

    public List<WhsCompany> list(Integer status, Integer pageSize, Integer currentPage) {
        return comMapper.list(status, pageSize, pageSize * (currentPage - 1));
    }

    public int count(Integer status) {
        return comMapper.count(status);
    }

    public int apply(WhsCompanyApply record) {
        return comApplyMapper.insertSelective(record);
    }

    public List<WhsCompanyApply> listApply(Integer pageSize, Integer currentPage) {
        return comApplyMapper.list(pageSize, pageSize * (currentPage - 1));
    }

    public int countApply() {
        return comApplyMapper.count();
    }

    public int update(WhsCompany com) {
        return comMapper.updateByPrimaryKeySelective(com);
    }

    public int add(WhsCompany com) {
        return comMapper.insertSelective(com);
    }
}
