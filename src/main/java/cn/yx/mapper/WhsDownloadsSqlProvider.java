package cn.yx.mapper;

import org.apache.ibatis.jdbc.SQL;

import cn.yx.entity.WhsDownloads;

public class WhsDownloadsSqlProvider {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_downloads
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    public String insertSelective(WhsDownloads record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("whs_downloads");
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        if (record.getBrowses() != null) {
            sql.VALUES("browses", "#{browses,jdbcType=BIGINT}");
        }
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        if (record.getAbstr() != null) {
            sql.VALUES("abstr", "#{abstr,jdbcType=VARCHAR}");
        }
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=TINYINT}");
        }
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=BIGINT}");
        }
        if (record.getAnnex() != null) {
            sql.VALUES("annex", "#{annex,jdbcType=VARCHAR}");
        }
        if (record.getAnnexName() != null) {
            sql.VALUES("annex_name", "#{annexName,jdbcType=VARCHAR}");
        }
        if (record.getDowns() != null) {
            sql.VALUES("downs", "#{downs,jdbcType=INTEGER}");
        }
        if (record.getAuthor() != null) {
            sql.VALUES("author", "#{author,jdbcType=VARCHAR}");
        }
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=TINYINT}");
        }
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=LONGVARCHAR}");
        }
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_downloads
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    public String updateByPrimaryKeySelective(WhsDownloads record) {
        SQL sql = new SQL();
        sql.UPDATE("whs_downloads");
        if (record.getBrowses() != null) {
            sql.SET("browses = #{browses,jdbcType=BIGINT}");
        }
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        if (record.getAbstr() != null) {
            sql.SET("abstr = #{abstr,jdbcType=VARCHAR}");
        }
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=TINYINT}");
        }
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=BIGINT}");
        }
        if (record.getAnnex() != null) {
            sql.SET("annex = #{annex,jdbcType=VARCHAR}");
        }
        if (record.getAnnexName() != null) {
            sql.SET("annex_name = #{annexName,jdbcType=VARCHAR}");
        }
        if (record.getDowns() != null) {
            sql.SET("downs = #{downs,jdbcType=INTEGER}");
        }
        if (record.getAuthor() != null) {
            sql.SET("author = #{author,jdbcType=VARCHAR}");
        }
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=TINYINT}");
        }
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=LONGVARCHAR}");
        }
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        return sql.toString();
    }

    public String listSelective(Integer status, Byte type, Integer limit, Integer offset) {
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.SELECT("annex_name as annexName, create_time as createTime");
        sql.FROM("whs_downloads");
        sql.ORDER_BY("create_time desc");
        
        if(status != null && status != -1) {
            sql.WHERE("status = #{arg0,jdbcType=TINYINT}");
        } else {
            sql.WHERE("status != -1 ");
        }
        if(type != null && type != -1) {
            sql.WHERE("type = #{arg1,jdbcType=TINYINT}");
        } else {
            sql.WHERE("type != -1 ");
        }
        if (limit != null && limit != -1) {
            return sql + " limit " + limit + " OFFSET " + offset;
        } else {
            return sql.toString();
        }
    }

    public String countSelective(Integer status, Byte type) {
        SQL sql = new SQL();
        sql.SELECT("count(*)");
        sql.FROM("whs_downloads");
        
        if(status != null && status != -1) {
            sql.WHERE("status = #{arg0,jdbcType=TINYINT}");
        } else {
            sql.WHERE("status != -1 ");
        }
        if(type != null && type != -1) {
            sql.WHERE("type = #{arg1,jdbcType=TINYINT}");
        } else {
            sql.WHERE("type != -1 ");
        }
        
        return sql.toString();
    }
}