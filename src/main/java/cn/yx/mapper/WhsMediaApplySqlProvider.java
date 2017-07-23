package cn.yx.mapper;

import org.apache.ibatis.jdbc.SQL;

import cn.yx.entity.WhsMediaApply;

public class WhsMediaApplySqlProvider {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to
     * the database table whs_media_apply
     * 
     * @mbg.generated Sun Jul 23 15:42:13 CST 2017
     */
    public String insertSelective(WhsMediaApply record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("whs_media_apply");
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        if (record.getPhone() != null) {
            sql.VALUES("phone", "#{phone,jdbcType=VARCHAR}");
        }
        if (record.getPrincipal() != null) {
            sql.VALUES("principal", "#{principal,jdbcType=VARCHAR}");
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
        if (record.getApplyTime() != null) {
            sql.VALUES("apply_time", "#{applyTime,jdbcType=BIGINT}");
        }
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to
     * the database table whs_media_apply
     * 
     * @mbg.generated Sun Jul 23 15:42:13 CST 2017
     */
    public String updateByPrimaryKeySelective(WhsMediaApply record) {
        SQL sql = new SQL();
        sql.UPDATE("whs_media_apply");
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        if (record.getPhone() != null) {
            sql.SET("phone = #{phone,jdbcType=VARCHAR}");
        }
        if (record.getPrincipal() != null) {
            sql.SET("principal = #{principal,jdbcType=VARCHAR}");
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
        if (record.getApplyTime() != null) {
            sql.SET("apply_time = #{applyTime,jdbcType=BIGINT}");
        }
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        return sql.toString();
    }

    public String listApplySelective(Integer limit, Integer offset) {
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.SELECT("apply_time as applyTime");
        sql.FROM("whs_media_apply");
        sql.ORDER_BY("applyTime desc");

        if (limit != -1) {
            return sql + " limit " + limit + " OFFSET " + offset;
        } else {
            return sql.toString();
        }
    }

    public String countApplySelective() {
        SQL sql = new SQL();
        sql.SELECT("count(*)");
        sql.FROM("whs_media_apply");
        return sql.toString();
    }
}