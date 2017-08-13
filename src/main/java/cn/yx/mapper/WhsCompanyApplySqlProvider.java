package cn.yx.mapper;

import org.apache.ibatis.jdbc.SQL;

import cn.yx.entity.WhsCompanyApply;

public class WhsCompanyApplySqlProvider {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company_apply
     * @mbg.generated  Sun Aug 13 17:16:41 CST 2017
     */
    public String insertSelective(WhsCompanyApply record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("whs_company_apply");
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
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
        if (record.getApplyTime() != null) {
            sql.VALUES("apply_time", "#{applyTime,jdbcType=BIGINT}");
        }
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=TINYINT}");
        }
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company_apply
     * @mbg.generated  Sun Aug 13 17:16:41 CST 2017
     */
    public String updateByPrimaryKeySelective(WhsCompanyApply record) {
        SQL sql = new SQL();
        sql.UPDATE("whs_company_apply");
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
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
        if (record.getApplyTime() != null) {
            sql.SET("apply_time = #{applyTime,jdbcType=BIGINT}");
        }
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=TINYINT}");
        }
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        return sql.toString();
    }

    public String listApplySelective(Integer status, Integer limit, Integer offset) {
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.SELECT("apply_time as applyTime");
        sql.FROM("whs_company_apply");
        if(status != null && status != -1) {
            sql.WHERE("status = #{arg0,jdbcType=TINYINT}");
        } else {
            sql.WHERE("status != -1 ");
        }
        sql.ORDER_BY("applyTime desc");

        if (limit != -1) {
            return sql + " limit " + limit + " OFFSET " + offset;
        } else {
            return sql.toString();
        }
    }

    public String countApplySelective(Integer status) {
        SQL sql = new SQL();
        sql.SELECT("count(*)");
        sql.FROM("whs_company_apply");
        if(status != null && status != -1) {
            sql.WHERE("status = #{arg0,jdbcType=TINYINT}");
        } else {
            sql.WHERE("status != -1 ");
        }        return sql.toString();
    }
}