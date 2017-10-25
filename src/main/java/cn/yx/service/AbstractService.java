package cn.yx.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        return fileConfig.getUrl() + FileConstant.IMAGE_URL + input;
    }

    protected String parseUri2DownloadUrl(String input, String originName) {
        if (StringUtils.isBlank(input)) {
            return "";
        }
        return fileConfig.getUrl() + FileConstant.DOWNLOAD_URL + input + "&originName=" + originName;
    }

    protected String parseId2DownloadUrl(Integer id, String originName) {
        return fileConfig.getUrl() + FileConstant.DOWNLOAD_URL_ID + id + "&originName=" + originName;
    }

    protected static String parseTimeRange2Time(Date beginTime, Date endTime) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(beginTime);
        cal2.setTime(endTime);
        boolean isSameMonth = cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat format2 = null;
        if (isSameMonth) {
            format2 = new SimpleDateFormat("dd日");
        } else {
            format2 = new SimpleDateFormat("MM月dd日");
        }

        String time = format1.format(beginTime) + "~" + format2.format(endTime);
        return time;
    }
}
