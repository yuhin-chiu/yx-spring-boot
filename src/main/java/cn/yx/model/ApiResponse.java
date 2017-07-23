package cn.yx.model;

import cn.yx.enums.ApiResponseEnum;

public class ApiResponse {
    private Integer code = 200;
    private String msg = "success";
    private Object data;
    private String description;
    private Integer total = 0;

    public void setEnum(ApiResponseEnum en) {
        this.code = en.getCode();
        this.msg = en.getContent();
    }
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
    
}
