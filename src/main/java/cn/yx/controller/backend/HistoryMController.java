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
 * @date 2017年8月5日 下午9:53:57
 * @version 1.0
 */

@Controller
@RequestMapping("/backend/histories")
public class HistoryMController extends DialogPageController {

    @RequestMapping({ "", "/", "/list" })
    public String list(Model model) {
        setDailogItems(model);
        return "/history/list";
    }

    @RequestMapping({ "/publish" })
    public String publish() {
        return "/history/publish";
    }

    /**
     * Implement Methods
     */
    private static volatile List<DialogItem> dialogItems = new ArrayList<DialogItem>();

    @Override
    protected synchronized void initDialogItems() {
        if (dialogItems.size() == 0) {
            DialogItem title = new DialogItem("title", "标题");
            DialogItem content = new DialogItem("content", "正文", DialogItemEnum.EDITOR.getStr());

            dialogItems.add(title);
            dialogItems.add(content);
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
