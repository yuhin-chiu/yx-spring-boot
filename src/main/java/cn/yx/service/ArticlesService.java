package cn.yx.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.yx.entity.WhsArticle;
import cn.yx.mapper.WhsArticleMapper;

/**
 * @author yuxuanjiao
 * @date 2017年10月26日 下午4:13:01
 * @version 1.0
 */

@Service
@SuppressWarnings("unchecked")
public class ArticlesService {

    @Resource
    private WhsArticleMapper articleMapper;

    public List getTitles(Integer parent) {
        return articleMapper.getTitles(parent);
    }
    
    public WhsArticle getContent(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }
    
    public int editContent(Long id ,String content) {
        WhsArticle record = new WhsArticle();
        record.setId(id);
        record.setContent(content);
        return articleMapper.updateByPrimaryKeySelective(record);
    }

    public List<WhsArticle> getParents() {
        List<WhsArticle> list = new ArrayList<WhsArticle>();
        list.add(new WhsArticle(0, "关于展会"));
        list.add(new WhsArticle(1, "展商"));
        list.add(new WhsArticle(2, "观众"));
        list.add(new WhsArticle(3, "关于我们"));
        return list;
    }
}
