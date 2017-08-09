package cn.yx.mapper;

import org.apache.ibatis.jdbc.SQL;

import cn.yx.entity.WhsHistory;

public class WhsHistorySqlProvider {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_history
     * @mbg.generated  Wed Aug 09 09:52:41 CST 2017
     */
    public String insertSelective(WhsHistory record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("whs_history");
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=TINYINT}");
        }
        if (record.getAbstr() != null) {
            sql.VALUES("abstr", "#{abstr,jdbcType=VARCHAR}");
        }
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=BIGINT}");
        }
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=LONGVARCHAR}");
        }
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_history
     * @mbg.generated  Wed Aug 09 09:52:41 CST 2017
     */
    public String updateByPrimaryKeySelective(WhsHistory record) {
        SQL sql = new SQL();
        sql.UPDATE("whs_history");
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=TINYINT}");
        }
        if (record.getAbstr() != null) {
            sql.SET("abstr = #{abstr,jdbcType=VARCHAR}");
        }
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=BIGINT}");
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
        sql.FROM("whs_history");
        if(status != null && status != -1) {
            sql.WHERE("status = #{arg0,jdbcType=TINYINT}");
        } else {
            sql.WHERE("status != -1 ");
        }
        if (limit != -1) {
            return sql + " limit " + limit + " OFFSET " + offset;
        } else {
            return sql.toString();
        }
    }

    public String countSelective(Integer status) {
        SQL sql = new SQL();
        sql.SELECT("count(*)");
        sql.FROM("whs_history");
        if(status != null && status != -1) {
            sql.WHERE("status = #{arg0,jdbcType=TINYINT}");
        } else {
            sql.WHERE("status != -1 ");
        }
        return sql.toString();
    }
}