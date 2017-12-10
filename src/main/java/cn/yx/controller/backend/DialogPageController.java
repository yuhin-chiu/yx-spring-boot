package cn.yx.controller.backend;

import java.util.List;

import org.springframework.ui.Model;

import cn.yx.model.DialogItem;

/**
 * @author yuxuanjiao
 * @date 2017年10月24日 下午2:59:58 
 * @version 1.0
 */

public abstract class DialogPageController {

    /**
     * 设置每个页面不同的编辑框项目
     */
    abstract void initDialogItems();
    
    /**
     * 获取到DialogItems单例
     */
    abstract List<DialogItem> getDailogItems();
    
    protected void setDailogItems(Model model) {
        initDialogItems();
        model.addAttribute("dialogItems", getDailogItems());
    }

    abstract void initPresentItems();
    
    /**
     * 获取到PresentItems单例
     */
    abstract List<DialogItem> getPresentItems(Integer type);
    
    protected void setPresentItems(Model model, Integer type) {
        initPresentItems();
        model.addAttribute("presentItems", getPresentItems(type));
    }
}
