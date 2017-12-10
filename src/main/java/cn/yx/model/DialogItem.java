package cn.yx.model;

import java.util.ArrayList;
import java.util.List;

import cn.yx.enums.DialogItemEnum;

/**
 * @author yuxuanjiao
 * @date 2017年10月24日 上午11:55:29
 * @version 1.0
 */

public class DialogItem {

    private String id;
    private String title;
    private String type = DialogItemEnum.TEXT.getStr(); // (text、textarea、editor、radio、checkbox、select)
    private String placeholder;
    private String comment;

    private List<SelectItem> selectItems;

    private Integer minLength = 1;
    private Integer maxLength = 50;

    /**
     * 设置id,title,默认设置type,placeholder
     * @param id
     * @param title
     */
    public DialogItem(String id, String title) {
        super();
        this.id = id;
        this.title = title;
        this.placeholder = "不能为空且最大长度不超过50";
    }
    public DialogItem(String id, String title, String type) {
        super();
        this.id = id;
        this.title = title;
        this.type = type;
        this.placeholder = "不能为空且最大长度不超过50";
        if (type.equals(DialogItemEnum.TEXTAREA.getStr())) {
            this.maxLength = 1000;
            this.placeholder = "不能为空且最大长度不超过1000";
        } else if (type.equals(DialogItemEnum.IMAGE.getStr())) {
            this.placeholder = "";
        } else if (type.equals(DialogItemEnum.EDITOR.getStr())) {
            this.placeholder = "";
            this.maxLength = 15000;
        }
    }

    public DialogItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public void setLength(Integer minLength, Integer maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.placeholder = (minLength > 0 ? "不能为空且" : "") + "最大长度不超过" + maxLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void addSelectItem(Integer key, String value) {
        if(selectItems == null) {
            selectItems = new ArrayList<SelectItem>();
        }
        selectItems.add(new SelectItem(key, value));
    }
    

    class SelectItem {
        private Integer key;
        private String value;
        public SelectItem(Integer key, String value) {
            super();
            this.key = key;
            this.value = value;
        }
        public Integer getKey() {
            return key;
        }
        public void setKey(Integer key) {
            this.key = key;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }


    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }
}
