package cn.yx.mapper;

import org.apache.ibatis.jdbc.SQL;

import cn.yx.entity.WhsSliders;

public class WhsSlidersSqlProvider {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_sliders
     * @mbg.generated  Wed Aug 09 09:52:41 CST 2017
     */
    public String insertSelective(WhsSliders record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("whs_sliders");
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        if (record.getImgKey() != null) {
            sql.VALUES("img_key", "#{imgKey,jdbcType=VARCHAR}");
        }
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        if (record.getUrl() != null) {
            sql.VALUES("url", "#{url,jdbcType=VARCHAR}");
        }
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=TINYINT}");
        }
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=BIGINT}");
        }
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_sliders
     * @mbg.generated  Wed Aug 09 09:52:41 CST 2017
     */
    public String updateByPrimaryKeySelective(WhsSliders record) {
        SQL sql = new SQL();
        sql.UPDATE("whs_sliders");
        if (record.getImgKey() != null) {
            sql.SET("img_key = #{imgKey,jdbcType=VARCHAR}");
        }
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        if (record.getUrl() != null) {
            sql.SET("url = #{url,jdbcType=VARCHAR}");
        }
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=TINYINT}");
        }
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=BIGINT}");
        }
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        return sql.toString();
    }
}