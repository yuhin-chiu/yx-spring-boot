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

import cn.yx.entity.WhsCompany;

public interface WhsCompanyMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @Delete({ "delete from whs_company", "where id = #{id,jdbcType=INTEGER}" })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @Insert({ "insert into whs_company (id, name, ", "type, image, principal, ", "phone, tele, mail, ",
            "status, address, ", "url)", "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
            "#{type,jdbcType=TINYINT}, #{image,jdbcType=VARCHAR}, #{principal,jdbcType=VARCHAR}, ",
            "#{phone,jdbcType=VARCHAR}, #{tele,jdbcType=VARCHAR}, #{mail,jdbcType=VARCHAR}, ",
            "#{status,jdbcType=TINYINT}, #{address,jdbcType=VARCHAR}, ", "#{url,jdbcType=LONGVARCHAR})" })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(WhsCompany record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @InsertProvider(type = WhsCompanySqlProvider.class, method = "insertSelective")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertSelective(WhsCompany record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @Select({ "select", "id, name, type, image, principal, phone, tele, mail, status, address, url", "from whs_company",
            "where id = #{id,jdbcType=INTEGER}" })
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "type", property = "type", jdbcType = JdbcType.TINYINT),
            @Result(column = "image", property = "image", jdbcType = JdbcType.VARCHAR),
            @Result(column = "principal", property = "principal", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tele", property = "tele", jdbcType = JdbcType.VARCHAR),
            @Result(column = "mail", property = "mail", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.TINYINT),
            @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR),
            @Result(column = "url", property = "url", jdbcType = JdbcType.LONGVARCHAR) })
    WhsCompany selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @UpdateProvider(type = WhsCompanySqlProvider.class, method = "updateByPrimaryKeySelective")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int updateByPrimaryKeySelective(WhsCompany record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @Update({ "update whs_company", "set name = #{name,jdbcType=VARCHAR},", "type = #{type,jdbcType=TINYINT},",
            "image = #{image,jdbcType=VARCHAR},", "principal = #{principal,jdbcType=VARCHAR},",
            "phone = #{phone,jdbcType=VARCHAR},", "tele = #{tele,jdbcType=VARCHAR},",
            "mail = #{mail,jdbcType=VARCHAR},", "status = #{status,jdbcType=TINYINT},",
            "address = #{address,jdbcType=VARCHAR},", "url = #{url,jdbcType=LONGVARCHAR}",
            "where id = #{id,jdbcType=INTEGER}" })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int updateByPrimaryKeyWithBLOBs(WhsCompany record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_company
     * @mbg.generated  Sun Jul 23 15:42:13 CST 2017
     */
    @Update({ "update whs_company", "set name = #{name,jdbcType=VARCHAR},", "type = #{type,jdbcType=TINYINT},",
            "image = #{image,jdbcType=VARCHAR},", "principal = #{principal,jdbcType=VARCHAR},",
            "phone = #{phone,jdbcType=VARCHAR},", "tele = #{tele,jdbcType=VARCHAR},",
            "mail = #{mail,jdbcType=VARCHAR},", "status = #{status,jdbcType=TINYINT},",
            "address = #{address,jdbcType=VARCHAR}", "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKey(WhsCompany record);
    
    @SelectProvider(type = WhsCompanySqlProvider.class, method = "listSelective")
    List<WhsCompany> list(Integer status, Integer limit, Integer offset);
    
    @SelectProvider(type = WhsCompanySqlProvider.class, method = "countSelective")
    int count(Integer status);
}