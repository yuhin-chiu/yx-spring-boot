package cn.yx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.yx.entity.BaseDownloads;
import cn.yx.entity.WhsDownloads;
import cn.yx.entity.WhsNews;
import cn.yx.mapper.WhsDownloadsMapper;

/**
 * @author yuxuanjiao
 * @date 2017年7月30日 下午7:43:23
 * @version 1.0
 */

@Service
public class DownloadsService extends AbstractService {

    @Resource
    WhsDownloadsMapper downMapper;

    public List<BaseDownloads> getNew(Integer type) {
        Byte b = type != null ? type.byteValue(): -1;
        List<BaseDownloads> rList = downMapper.getNew(b); // 只获取下载类型问0的下载文件
        rList.forEach(item -> {
            item.setUrl(parseId2DownloadUrl(item.getId(), item.getTitle() + item.getAnnexName()));
        });
        return rList;
    }

    public List<WhsDownloads> list(Integer status, Integer type, Integer pageSize, Integer currentPage) {
        List<WhsDownloads> rList  = downMapper.list(status, type.byteValue(), pageSize, pageSize * (currentPage - 1));
        rList.forEach(item -> {
            item.setUrl(parseUri2DownloadUrl(item.getAnnex(), item.getTitle() + item.getAnnexName()));
        });
        return rList;
    }

    public int count(Integer status, Integer type) {
        Byte b = type != null ? type.byteValue(): -1;
        return downMapper.count(status, b);
    }

    public int add(WhsDownloads down) {
        return downMapper.insertSelective(down);
    }

    public Integer getLastId() {
        return downMapper.getLastId();
    }
    
    public WhsDownloads getById(Integer id) {
        return downMapper.selectByPrimaryKey(id);
    }
    
    public Integer increase(int id, int change) {
        return downMapper.increase(id, change);
    }
    
    public Integer update(WhsDownloads down) {
        return downMapper.updateByPrimaryKeySelective(down);
    }
}
