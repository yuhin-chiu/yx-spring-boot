package cn.yx.entity;

/**
 * @author yuxuanjiao
 * @date 2017年8月10日 下午8:26:33 
 * @version 1.0
 */

public class BaseDownloads {

    private Integer id;
    private String annex;
    private String annexName;
    private String url;
    private String title;
    private Integer downs;
    private Long createTime;
    private Byte type;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAnnex() {
        return annex;
    }
    public void setAnnex(String annex) {
        this.annex = annex;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getDowns() {
        return downs;
    }
    public void setDowns(Integer downs) {
        this.downs = downs;
    }
    public Long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    public Byte getType() {
        return type;
    }
    public void setType(Byte type) {
        this.type = type;
    }
    public String getAnnexName() {
        return annexName;
    }
    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }
    
}
