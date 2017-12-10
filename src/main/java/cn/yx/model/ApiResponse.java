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

    public ApiResponse setData(Object data) {
        this.data = data;
        return this;
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

    /**
     * 返回参数为空
     * 
     * @return
     */
    public static ApiResponse paramIsNullResponse() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.BAD_REQUEST.getCode());
        response.setMsg(ApiResponseEnum.BAD_REQUEST.getContent());
        return response;
    }

    /**
     * 返回成功
     * 
     * @return
     */
    public static ApiResponse successResponse() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.SUCCESS.getCode());
        response.setMsg(ApiResponseEnum.SUCCESS.getContent());
        return response;
    }

    /**
     * 返回异常
     * 
     * @return
     */
    public static ApiResponse exceptionResponse() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.INTERNAL_ERROR.getCode());
        response.setMsg(ApiResponseEnum.INTERNAL_ERROR.getContent());
        return response;
    }

    public static ApiResponse unLoginResponse() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.NON_LOGIN_IN.getCode());
        response.setMsg(ApiResponseEnum.NON_LOGIN_IN.getContent());
        return response;
    }

    public static ApiResponse fileSaveError() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.FILE_SAVE_FAILED.getCode());
        response.setMsg(ApiResponseEnum.FILE_SAVE_FAILED.getContent());
        return response;
    }

    public static ApiResponse fileSaveEmpty() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.FILE_SAVE_EMPTY.getCode());
        response.setMsg(ApiResponseEnum.FILE_SAVE_EMPTY.getContent());
        return response;
    }

    public static ApiResponse noMapping() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.PAGE_NOT_FOUND.getCode());
        response.setMsg(ApiResponseEnum.PAGE_NOT_FOUND.getContent());
        return response;
    }

    public static ApiResponse otherError() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.COMMON_ERROR.getCode());
        response.setMsg(ApiResponseEnum.COMMON_ERROR.getContent());
        return response;
    }

    public Boolean isSuccess() {
        if (this.getCode() != null && this.getCode().equals(ApiResponseEnum.SUCCESS.getCode())) {
            return true;
        }
        return false;
    }
}
