package cn.yx.mapper;

import cn.yx.entity.WhsSliders;
import org.apache.ibatis.jdbc.SQL;

public class WhsSlidersSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table whs_sliders
     *
     * @mbg.generated Thu Jul 13 16:24:58 CST 2017
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
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table whs_sliders
     *
     * @mbg.generated Thu Jul 13 16:24:58 CST 2017
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
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}