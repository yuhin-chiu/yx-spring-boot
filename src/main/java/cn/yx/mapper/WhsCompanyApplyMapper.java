package cn.yx.mapper;

import cn.yx.entity.WhsCompanyApply;
import cn.yx.entity.WhsMediaApply;

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

public interface WhsCompanyApplyMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company_apply
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @Delete({ "delete from whs_company_apply", "where id = #{id,jdbcType=INTEGER}" })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company_apply
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @Insert({ "insert into whs_company_apply (id, name, ", "principal, phone, ", "tele, mail, address, ", "apply_time)",
            "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{principal,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
            "#{tele,jdbcType=VARCHAR}, #{mail,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
            "#{applyTime,jdbcType=BIGINT})" })
    int insert(WhsCompanyApply record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company_apply
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @InsertProvider(type = WhsCompanyApplySqlProvider.class, method = "insertSelective")
    int insertSelective(WhsCompanyApply record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company_apply
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @Select({ "select", "id, name, principal, phone, tele, mail, address, apply_time", "from whs_company_apply",
            "where id = #{id,jdbcType=INTEGER}" })
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "principal", property = "principal", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tele", property = "tele", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mail", property = "mail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR),
            @Result(column = "apply_time", property = "applyTime", jdbcType = JdbcType.BIGINT) })
    WhsCompanyApply selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company_apply
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @UpdateProvider(type = WhsCompanyApplySqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WhsCompanyApply record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company_apply
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @Update({ "update whs_company_apply", "set name = #{name,jdbcType=VARCHAR},",
            "principal = #{principal,jdbcType=VARCHAR},", "phone = #{phone,jdbcType=VARCHAR},",
            "tele = #{tele,jdbcType=VARCHAR},", "mail = #{mail,jdbcType=VARCHAR},",
            "address = #{address,jdbcType=VARCHAR},", "apply_time = #{applyTime,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKey(WhsCompanyApply record);

    @SelectProvider(type = WhsCompanyApplySqlProvider.class, method = "listApplySelective")
    List<WhsCompanyApply> list(Integer limit, Integer offset);
    
    @SelectProvider(type = WhsCompanyApplySqlProvider.class, method = "countApplySelective")
    int count();
}