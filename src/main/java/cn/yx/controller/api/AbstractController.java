package cn.yx.controller.api;

import javax.annotation.Resource;

import cn.yx.service.ActivitiesService;
import cn.yx.service.AudienceService;
import cn.yx.service.CompanyService;
import cn.yx.service.DownloadsService;
import cn.yx.service.HistoryService;
import cn.yx.service.HomepageService;
import cn.yx.service.MediaService;
import cn.yx.service.NewsService;
import cn.yx.service.SlidersService;

/**
 * @author yuxuanjiao
 * @date 2017年7月23日 下午12:19:12
 * @version 1.0
 */

public abstract class AbstractController {

    @Resource
    protected ActivitiesService activitiesService;
    @Resource
    protected HomepageService homepageService;
    @Resource
    protected SlidersService slidersService;
    @Resource
    protected NewsService newsService;
    @Resource
    protected CompanyService companyService;
    @Resource
    protected MediaService mediaService;
    @Resource
    protected AudienceService audienceService;
    @Resource
    protected HistoryService historyService;
    @Resource
    protected DownloadsService downloadsService;
}
