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
@RequestMapping("/backend/medias")
public class MediaMController extends DialogPageController {

    @RequestMapping({ "", "/", "/list" })
    public String list(Model model) {
        setDailogItems(model);
        return "/media/list";
    }

    /**
     * Implement Methods
     */
    private static volatile List<DialogItem> dialogItems = new ArrayList<DialogItem>();

    @Override
    protected synchronized void initDialogItems() {
        if (dialogItems.size() == 0) {
            DialogItem title = new DialogItem("name", "媒体名");
            DialogItem url = new DialogItem("url", "跳转链接");
            url.setPlaceholder("请输入http或https开头的链接");
            DialogItem image = new DialogItem("image2", "展示图片", DialogItemEnum.IMAGE.getStr());
            image.setPlaceholder("以下为可选字段");
            DialogItem principal = new DialogItem("principal", "负责人", DialogItemEnum.TEXT.getStr());
            principal.setMinLength(0);
            DialogItem phone = new DialogItem("phone", "负责人手机", DialogItemEnum.TEXT.getStr());
            phone.setMinLength(0);
            DialogItem address = new DialogItem("address", "公司地址", DialogItemEnum.TEXT.getStr());
            address.setMinLength(0);

            DialogItem tele = new DialogItem("tele", "办公电话", DialogItemEnum.TEXT.getStr());
            tele.setMinLength(0);

            DialogItem mail = new DialogItem("mail", "邮箱", DialogItemEnum.TEXT.getStr());
            mail.setMinLength(0);

            dialogItems.add(title);
            dialogItems.add(image);
            dialogItems.add(url);
            dialogItems.add(principal);
            dialogItems.add(phone);
            dialogItems.add(address);
            dialogItems.add(tele);
            dialogItems.add(mail);
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
