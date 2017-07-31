package cn.yx.service;

import java.util.List;

import cn.yx.entity.WhsDownloads;

/**
 * @author yuxuanjiao
 * @date 2017年7月30日 下午8:13:53 
 * @version 1.0
 */

public interface PagingServiceInterdace<E> {

    public List<E> list(Integer status, Integer pageSize, Integer currentPage);

    public int count(Integer status);
    
    public Integer getLastId();
}
