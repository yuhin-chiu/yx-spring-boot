package cn.yx.mapper;

import cn.yx.entity.WhsActivities;
import org.apache.ibatis.jdbc.SQL;

public class WhsActivitiesSqlProvider {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to
     * the database table whs_activities
     * 
     * @mbg.generated Sun Jul 23 15:42:13 CST 2017
     */
    public String insertSelective(WhsActivities record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("whs_activities");
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
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
            sql.VALUES("create_time", "#{createTime,jdbcType=BIGINT}");
        }
        if (record.getAuthor() != null) {
            sql.VALUES("author", "#{author,jdbcType=VARCHAR}");
        }
        if (record.getBrowses() != null) {
            sql.VALUES("browses", "#{browses,jdbcType=BIGINT}");
        }
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=LONGVARCHAR}");
        }
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to
     * the database table whs_activities
     * 
     * @mbg.generated Sun Jul 23 15:42:13 CST 2017
     */
    public String updateByPrimaryKeySelective(WhsActivities record) {
        SQL sql = new SQL();
        sql.UPDATE("whs_activities");
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
            sql.SET("create_time = #{createTime,jdbcType=BIGINT}");
        }
        if (record.getAuthor() != null) {
            sql.SET("author = #{author,jdbcType=VARCHAR}");
        }
        if (record.getBrowses() != null) {
            sql.SET("browses = #{browses,jdbcType=BIGINT}");
        }
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=LONGVARCHAR}");
        }
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        return sql.toString();
    }

    public String listSelective(Integer status, Integer limit, Integer offset) {
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.SELECT("create_time as createTime");
        sql.FROM("whs_activities");

        if (status != null && status >= 0) {
            sql.WHERE("status = #{status,jdbcType=TINYINT}");
        }
        sql.ORDER_BY("createTime desc");

        if (limit != -1) {
            return sql + " limit " + limit + " OFFSET " + offset;
        } else {
            return sql.toString();
        }
    }

    public String countSelective(Integer status) {
        SQL sql = new SQL();
        sql.SELECT("count(*)");
        sql.FROM("whs_activities");

        if (status != null && status >= 0) {
            sql.WHERE("status = #{status,jdbcType=TINYINT}");
        }

        return sql.toString();
    }
}