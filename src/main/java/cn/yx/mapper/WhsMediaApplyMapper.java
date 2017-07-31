package cn.yx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import cn.yx.entity.WhsMediaApply;

public interface WhsMediaApplyMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media_apply
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Delete({ "delete from whs_media_apply", "where id = #{id,jdbcType=INTEGER}" })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media_apply
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Insert({ "insert into whs_media_apply (id, name, ", "phone, principal, ", "tele, mail, address, ", "apply_time)",
            "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{phone,jdbcType=VARCHAR}, #{principal,jdbcType=VARCHAR}, ",
            "#{tele,jdbcType=VARCHAR}, #{mail,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
            "#{applyTime,jdbcType=BIGINT})" })
    int insert(WhsMediaApply record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media_apply
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @InsertProvider(type = WhsMediaApplySqlProvider.class, method = "insertSelective")
    int insertSelective(WhsMediaApply record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media_apply
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Select({ "select", "id, name, phone, principal, tele, mail, address, apply_time", "from whs_media_apply",
            "where id = #{id,jdbcType=INTEGER}" })
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "principal", property = "principal", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tele", property = "tele", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mail", property = "mail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR),
            @Result(column = "apply_time", property = "applyTime", jdbcType = JdbcType.BIGINT) })
    WhsMediaApply selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media_apply
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @UpdateProvider(type = WhsMediaApplySqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WhsMediaApply record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_media_apply
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Update({ "update whs_media_apply", "set name = #{name,jdbcType=VARCHAR},", "phone = #{phone,jdbcType=VARCHAR},",
            "principal = #{principal,jdbcType=VARCHAR},", "tele = #{tele,jdbcType=VARCHAR},",
            "mail = #{mail,jdbcType=VARCHAR},", "address = #{address,jdbcType=VARCHAR},",
            "apply_time = #{applyTime,jdbcType=BIGINT}", "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKey(WhsMediaApply record);

    @SelectProvider(type = WhsMediaApplySqlProvider.class, method = "listApplySelective")
    List<WhsMediaApply> list(Integer limit, Integer offset);
    
    @SelectProvider(type = WhsMediaApplySqlProvider.class, method = "countApplySelective")
    int count();
}