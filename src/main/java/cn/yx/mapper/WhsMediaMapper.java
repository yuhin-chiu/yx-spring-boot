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

import cn.yx.entity.WhsMedia;

public interface WhsMediaMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media
     * @mbg.generated  Mon Aug 07 21:32:02 CST 2017
     */
    @Delete({ "delete from whs_media", "where id = #{id,jdbcType=INTEGER}" })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media
     * @mbg.generated  Mon Aug 07 21:32:02 CST 2017
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({ "insert into whs_media (id, name, ", "type, image, principal, ", "phone, tele, mail, ",
            "address, status, ", "apply_time, url)", "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{type,jdbcType=TINYINT}, #{image,jdbcType=VARCHAR}, #{principal,jdbcType=VARCHAR}, ",
            "#{phone,jdbcType=VARCHAR}, #{tele,jdbcType=VARCHAR}, #{mail,jdbcType=VARCHAR}, ",
            "#{address,jdbcType=VARCHAR}, #{status,jdbcType=TINYINT}, ",
            "#{applyTime,jdbcType=INTEGER}, #{url,jdbcType=LONGVARCHAR})" })
    int insert(WhsMedia record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media
     * @mbg.generated  Mon Aug 07 21:32:02 CST 2017
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = WhsMediaSqlProvider.class, method = "insertSelective")
    int insertSelective(WhsMedia record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media
     * @mbg.generated  Mon Aug 07 21:32:02 CST 2017
     */
    @Select({ "select", "id, name, type, image, principal, phone, tele, mail, address, status, apply_time, ", "url",
            "from whs_media", "where id = #{id,jdbcType=INTEGER}" })
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.TINYINT),
            @Result(column = "image", property = "image", jdbcType = JdbcType.VARCHAR),
            @Result(column = "principal", property = "principal", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tele", property = "tele", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mail", property = "mail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.TINYINT),
            @Result(column = "apply_time", property = "applyTime", jdbcType = JdbcType.INTEGER),
            @Result(column = "url", property = "url", jdbcType = JdbcType.LONGVARCHAR) })
    WhsMedia selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media
     * @mbg.generated  Mon Aug 07 21:32:02 CST 2017
     */
    @UpdateProvider(type = WhsMediaSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WhsMedia record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media
     * @mbg.generated  Mon Aug 07 21:32:02 CST 2017
     */
    @Update({ "update whs_media", "set name = #{name,jdbcType=VARCHAR},", "type = #{type,jdbcType=TINYINT},",
            "image = #{image,jdbcType=VARCHAR},", "principal = #{principal,jdbcType=VARCHAR},",
            "phone = #{phone,jdbcType=VARCHAR},", "tele = #{tele,jdbcType=VARCHAR},",
            "mail = #{mail,jdbcType=VARCHAR},", "address = #{address,jdbcType=VARCHAR},",
            "status = #{status,jdbcType=TINYINT},", "apply_time = #{applyTime,jdbcType=INTEGER},",
            "url = #{url,jdbcType=LONGVARCHAR}", "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKeyWithBLOBs(WhsMedia record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media
     * @mbg.generated  Mon Aug 07 21:32:02 CST 2017
     */
    @Update({ "update whs_media", "set name = #{name,jdbcType=VARCHAR},", "type = #{type,jdbcType=TINYINT},",
            "image = #{image,jdbcType=VARCHAR},", "principal = #{principal,jdbcType=VARCHAR},",
            "phone = #{phone,jdbcType=VARCHAR},", "tele = #{tele,jdbcType=VARCHAR},",
            "mail = #{mail,jdbcType=VARCHAR},", "address = #{address,jdbcType=VARCHAR},",
            "status = #{status,jdbcType=TINYINT},", "apply_time = #{applyTime,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKey(WhsMedia record);

    @SelectProvider(type = WhsMediaSqlProvider.class, method = "listSelective")
    List<WhsMedia> list(Integer status, Integer limit, Integer offset);
    
    @SelectProvider(type = WhsMediaSqlProvider.class, method = "countSelective")
    int count(Integer status);
}