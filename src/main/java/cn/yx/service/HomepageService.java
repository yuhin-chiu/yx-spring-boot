package cn.yx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.yx.entity.WhsHomepage;
import cn.yx.mapper.WhsHomepageMapper;

/**
 * @author yuxuanjiao
 * @date 2017年7月13日 下午3:39:58
 * @version 1.0
 */

@Service
public class HomepageService extends AbstractService {

    @Autowired
    private WhsHomepageMapper whsHomepageMapper;

    public JSONObject getBaseInfo() {
        Integer index = getLastId();
        WhsHomepage whshomepage = whsHomepageMapper.selectByPrimaryKey(index - 1);
        whshomepage.setLogoUrl(parseUri2Url(whshomepage.getLogo()));
        whshomepage.setTime(parseTimeRange2Time(whshomepage.getBeginTime(), whshomepage.getEndTime()));
        JSONObject resp = (JSONObject) JSON.toJSON(whshomepage);
        return resp;
    }

    public Integer getLastId() {
        return whsHomepageMapper.getLastId();
    }

    public Integer update(WhsHomepage homepage) {
        Integer id = whsHomepageMapper.getLastId();
        homepage.setId(id - 1);

        return whsHomepageMapper.updateByPrimaryKeySelective(homepage);
    }
}
