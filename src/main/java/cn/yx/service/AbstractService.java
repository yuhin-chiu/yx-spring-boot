package cn.yx.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yx.config.FileConfig;
import cn.yx.constant.FileConstant;

/**
 * @author yuxuanjiao
 * @date 2017年8月10日 下午8:37:41 
 * @version 1.0
 */
@Service
public class AbstractService {
    
    @Autowired
    private FileConfig fileConfig;
    
    protected String parseUri2Url(String input) {
        if (StringUtils.isBlank(input)) {
            return "";
        }
        return fileConfig.getUrl() + FileConstant.DOWNLOAD_URL + input;
    }
}
