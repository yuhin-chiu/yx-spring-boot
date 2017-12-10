package cn.yx.enums;

/**
 * @author yuxuanjiao
 * @date 2017年10月24日 下午3:31:54
 * @version 1.0
 */

public enum DialogItemEnum {

    TEXT(1, "text"), TEXTAREA(2, "textarea"), EDITOR(3, "editor"), SELECT(4, "select"), IMAGE(5, "image"), HIDDEN(6, "hidden");

    private Integer code;
    private String str;

    DialogItemEnum(Integer code, String str) {
        this.code = code;
        this.str = str;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
