package cn.yx.mapper;

import cn.yx.entity.WhsMedia;
import org.apache.ibatis.jdbc.SQL;

public class WhsMediaSqlProvider {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    public String insertSelective(WhsMedia record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("whs_media");
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=TINYINT}");
        }
        if (record.getImage() != null) {
            sql.VALUES("image", "#{image,jdbcType=VARCHAR}");
        }
        if (record.getPrincipal() != null) {
            sql.VALUES("principal", "#{principal,jdbcType=VARCHAR}");
        }
        if (record.getPhone() != null) {
            sql.VALUES("phone", "#{phone,jdbcType=VARCHAR}");
        }
        if (record.getTele() != null) {
            sql.VALUES("tele", "#{tele,jdbcType=VARCHAR}");
        }
        if (record.getMail() != null) {
            sql.VALUES("mail", "#{mail,jdbcType=VARCHAR}");
        }
        if (record.getAddress() != null) {
            sql.VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        if (record.getUrl() != null) {
            sql.VALUES("url", "#{url,jdbcType=LONGVARCHAR}");
        }
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    public String updateByPrimaryKeySelective(WhsMedia record) {
        SQL sql = new SQL();
        sql.UPDATE("whs_media");
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=TINYINT}");
        }
        if (record.getImage() != null) {
            sql.SET("image = #{image,jdbcType=VARCHAR}");
        }
        if (record.getPrincipal() != null) {
            sql.SET("principal = #{principal,jdbcType=VARCHAR}");
        }
        if (record.getPhone() != null) {
            sql.SET("phone = #{phone,jdbcType=VARCHAR}");
        }
        if (record.getTele() != null) {
            sql.SET("tele = #{tele,jdbcType=VARCHAR}");
        }
        if (record.getMail() != null) {
            sql.SET("mail = #{mail,jdbcType=VARCHAR}");
        }
        if (record.getAddress() != null) {
            sql.SET("address = #{address,jdbcType=VARCHAR}");
        }
        if (record.getUrl() != null) {
            sql.SET("url = #{url,jdbcType=LONGVARCHAR}");
        }
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        return sql.toString();
    }
    
    public String listSelective(Integer status, Integer limit, Integer offset) {
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.SELECT("apply_time as applyTime");
        sql.FROM("whs_media");
        sql.ORDER_BY("applyTime desc");

        if(status != null) {
            sql.WHERE("status = #{status,jdbcType=TINYINT}");
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
        sql.FROM("whs_media");
        if(status != null) {
            sql.WHERE("status = #{status,jdbcType=TINYINT}");
        }
        return sql.toString();
    }
}