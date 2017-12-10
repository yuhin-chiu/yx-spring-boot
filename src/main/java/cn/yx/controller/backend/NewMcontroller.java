package cn.yx.controller.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yx.enums.DialogItemEnum;
import cn.yx.model.DialogItem;

/**
 * @author yuxuanjiao
 * @date 2017年8月5日 下午9:21:11 
 * @version 1.0
 */
@Controller
@RequestMapping("/backend/news")
public class NewMcontroller extends DialogPageController {

    @RequestMapping({"", "/", "/list"})
    public String list(Model model) {
        setDailogItems(model);
        return "/news/list";
    }
    
    @RequestMapping("/publish")
    public String publish() {
        return "/news/publish";
    }

    /**
     * Implement Methods
     */
    private static volatile List<DialogItem> dialogItems = new ArrayList<DialogItem>();

    @Override
    protected synchronized void initDialogItems() {
        if(dialogItems.size() == 0) {
            DialogItem title = new DialogItem("title", "标题");
            DialogItem abstr = new DialogItem("abstr", "摘要", DialogItemEnum.TEXTAREA.getStr());
            DialogItem parent = new DialogItem("parent", "所属栏目", DialogItemEnum.SELECT.getStr());
            parent.addSelectItem(0, "展会动态");
            parent.addSelectItem(1, "展商新闻");//0展会动态1展商新闻2行业资讯
            parent.addSelectItem(2, "行业资讯");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.EDITOR.getStr());
            DialogItem image = new DialogItem("image", "图片", DialogItemEnum.IMAGE.getStr());
//            image.setPlaceholder("推荐尺寸380*285");

            dialogItems.add(title);
            dialogItems.add(parent);
            dialogItems.add(abstr);
            dialogItems.add(content);
            dialogItems.add(image);
        }
    }

    @Override
    protected List<DialogItem> getDailogItems() {
        return dialogItems;
    }

    @Override
    void initPresentItems() {
    }

    @Override
    List<DialogItem> getPresentItems(Integer type) {
        return null;
    }
}
