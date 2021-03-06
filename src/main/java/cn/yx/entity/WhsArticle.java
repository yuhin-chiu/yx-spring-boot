package cn.yx.entity;

import java.util.Date;

public class WhsArticle {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column whs_article.id
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column whs_article.parent
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    private Integer parent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column whs_article.title
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column whs_article.modify_time
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column whs_article.author
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    private String author;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column whs_article.content
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column whs_article.id
     *
     * @return the value of whs_article.id
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column whs_article.id
     *
     * @param id the value for whs_article.id
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column whs_article.parent
     *
     * @return the value of whs_article.parent
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    public Integer getParent() {
        return parent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column whs_article.parent
     *
     * @param parent the value for whs_article.parent
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column whs_article.title
     *
     * @return the value of whs_article.title
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column whs_article.title
     *
     * @param title the value for whs_article.title
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column whs_article.modify_time
     *
     * @return the value of whs_article.modify_time
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column whs_article.modify_time
     *
     * @param modifyTime the value for whs_article.modify_time
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column whs_article.author
     *
     * @return the value of whs_article.author
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    public String getAuthor() {
        return author;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column whs_article.author
     *
     * @param author the value for whs_article.author
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column whs_article.content
     *
     * @return the value of whs_article.content
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column whs_article.content
     *
     * @param content the value for whs_article.content
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    public WhsArticle() {}
    public WhsArticle(Integer parent, String title) {
        this.parent = parent;
        this.title = title;
    }
}