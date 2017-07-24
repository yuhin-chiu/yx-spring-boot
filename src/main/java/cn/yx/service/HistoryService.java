package cn.yx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import cn.yx.entity.WhsHistory;
import cn.yx.entity.WhsNews;
import cn.yx.mapper.WhsHistoryMapper;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午6:06:33
 * @version 1.0
 */

@Service
public class HistoryService {

    @Resource
    private WhsHistoryMapper hisMapper;

    public List<WhsHistory> list(Integer pageSize, Integer currentPage) {
        return hisMapper.list(pageSize, pageSize * (currentPage - 1));
    }

    public int count() {
        return hisMapper.count();
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
        hisMapper.insertSelective(history);
        return history;
    }
}
