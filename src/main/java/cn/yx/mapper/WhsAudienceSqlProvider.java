package cn.yx.mapper;

import org.apache.ibatis.jdbc.SQL;

import cn.yx.entity.WhsAudience;

public class WhsAudienceSqlProvider {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_audience
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    public String insertSelective(WhsAudience record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("whs_audience");
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
        if (record.getPhone() != null) {
            sql.VALUES("phone", "#{phone,jdbcType=VARCHAR}");
        }
        if (record.getCompany() != null) {
            sql.VALUES("company", "#{company,jdbcType=VARCHAR}");
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
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=TINYINT}");
        }
        if (record.getApplyTime() != null) {
            sql.VALUES("apply_time", "#{applyTime,jdbcType=BIGINT}");
        }
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_audience
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    public String updateByPrimaryKeySelective(WhsAudience record) {
        SQL sql = new SQL();
        sql.UPDATE("whs_audience");
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=TINYINT}");
        }
        if (record.getImage() != null) {
            sql.SET("image = #{image,jdbcType=VARCHAR}");
        }
        if (record.getPhone() != null) {
            sql.SET("phone = #{phone,jdbcType=VARCHAR}");
        }
        if (record.getCompany() != null) {
            sql.SET("company = #{company,jdbcType=VARCHAR}");
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
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=TINYINT}");
        }
        if (record.getApplyTime() != null) {
            sql.SET("apply_time = #{applyTime,jdbcType=BIGINT}");
        }
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        return sql.toString();
    }

    public String listApplySelective(Integer status, long beginTime, long endTime, Integer limit, Integer offset) {
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.SELECT("apply_time as applyTime");
        sql.FROM("whs_audience");
        sql.ORDER_BY("applyTime desc");
        if(status != null && status != -1) {
            sql.WHERE("status = #{arg0,jdbcType=TINYINT}");
        } else {
            sql.WHERE("status != -1 ");
        }
        if(beginTime != -1) {
            sql.WHERE("apply_time >= #{arg1,jdbcType=BIGINT} AND #{arg2,jdbcType=BIGINT}");
        }

        if (limit != -1) {
            return sql + " limit " + limit + " OFFSET " + offset;
        } else {
            return sql.toString();
        }
    }

    public String countApplySelective(Integer status, long beginTime, long endTime) {
        SQL sql = new SQL();
        sql.SELECT("count(*)");
        sql.FROM("whs_audience");
        if(status != null && status != -1) {
            sql.WHERE("status = #{arg0,jdbcType=TINYINT}");
        } else {
            sql.WHERE("status != -1 ");
        }
        if(beginTime != -1) {
            sql.WHERE("apply_time >= #{arg1,jdbcType=BIGINT} AND #{arg2,jdbcType=BIGINT}");
        }
        return sql.toString();
    }
}