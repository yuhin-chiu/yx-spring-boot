package cn.yx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import cn.yx.entity.WhsAudience;

public interface WhsAudienceMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_audience
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    @Delete({ "delete from whs_audience", "where id = #{id,jdbcType=INTEGER}" })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_audience
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({ "insert into whs_audience (id, name, ", "type, image, phone, ", "company, tele, mail, ",
            "address, status, ", "apply_time)", "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{type,jdbcType=TINYINT}, #{image,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
            "#{company,jdbcType=VARCHAR}, #{tele,jdbcType=VARCHAR}, #{mail,jdbcType=VARCHAR}, ",
            "#{address,jdbcType=VARCHAR}, #{status,jdbcType=TINYINT}, ", "#{applyTime,jdbcType=BIGINT})" })
    int insert(WhsAudience record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_audience
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = WhsAudienceSqlProvider.class, method = "insertSelective")
    int insertSelective(WhsAudience record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_audience
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    @Select({ "select", "id, name, type, image, phone, company, tele, mail, address, status, apply_time",
            "from whs_audience", "where id = #{id,jdbcType=INTEGER}" })
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.TINYINT),
            @Result(column = "image", property = "image", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "company", property = "company", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tele", property = "tele", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mail", property = "mail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.TINYINT),
            @Result(column = "apply_time", property = "applyTime", jdbcType = JdbcType.BIGINT) })
    WhsAudience selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_audience
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    @UpdateProvider(type = WhsAudienceSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WhsAudience record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_audience
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    @Update({ "update whs_audience", "set name = #{name,jdbcType=VARCHAR},", "type = #{type,jdbcType=TINYINT},",
            "image = #{image,jdbcType=VARCHAR},", "phone = #{phone,jdbcType=VARCHAR},",
            "company = #{company,jdbcType=VARCHAR},", "tele = #{tele,jdbcType=VARCHAR},",
            "mail = #{mail,jdbcType=VARCHAR},", "address = #{address,jdbcType=VARCHAR},",
            "status = #{status,jdbcType=TINYINT},", "apply_time = #{applyTime,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKey(WhsAudience record);

    @SelectProvider(type = WhsAudienceSqlProvider.class, method = "listApplySelective")
    List<WhsAudience> list(Integer status, long beginTime, long endTime, Integer limit, Integer offset);
    
    @SelectProvider(type = WhsAudienceSqlProvider.class, method = "countApplySelective")
    int count(Integer status, long beginTime, long endTime);
}