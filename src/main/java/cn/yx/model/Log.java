package cn.yx.model;

/**
 * @author yuxuanjiao
 * @date 2017年7月30日 下午5:06:02
 * @version 1.0
 */

public class Log {
    private long id;
    private long brokerId;
    private long type;
    private String exts;
    private long createTime;

    public Log(long brokerId, long type, String exts, long createTime) {
        this.brokerId = brokerId;
        this.type = type;
        this.exts = exts;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(long brokerId) {
        this.brokerId = brokerId;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getExts() {
        return exts;
    }

    public void setExts(String exts) {
        this.exts = exts;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
