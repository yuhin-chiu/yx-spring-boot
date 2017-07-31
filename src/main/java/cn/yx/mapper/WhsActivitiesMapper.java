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

import cn.yx.entity.WhsActivities;

public interface WhsActivitiesMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_activities
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Delete({ "delete from whs_activities", "where id = #{id,jdbcType=INTEGER}" })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_activities
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({ "insert into whs_activities (id, title, ", "url, status, create_time, ", "author, browses, ", "content)",
            "values (#{id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
            "#{url,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, #{createTime,jdbcType=BIGINT}, ",
            "#{author,jdbcType=VARCHAR}, #{browses,jdbcType=BIGINT}, ", "#{content,jdbcType=LONGVARCHAR})" })
    int insert(WhsActivities record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_activities
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = WhsActivitiesSqlProvider.class, method = "insertSelective")
    int insertSelective(WhsActivities record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_activities
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Select({ "select", "id, title, url, status, create_time, author, browses, content", "from whs_activities",
            "where id = #{id,jdbcType=INTEGER}" })
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "url", property = "url", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "browses", property = "browses", jdbcType = JdbcType.BIGINT),
            @Result(column = "content", property = "content", jdbcType = JdbcType.LONGVARCHAR) })
    WhsActivities selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_activities
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @UpdateProvider(type = WhsActivitiesSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WhsActivities record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_activities
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Update({ "update whs_activities", "set title = #{title,jdbcType=VARCHAR},", "url = #{url,jdbcType=VARCHAR},",
            "status = #{status,jdbcType=INTEGER},", "create_time = #{createTime,jdbcType=BIGINT},",
            "author = #{author,jdbcType=VARCHAR},", "browses = #{browses,jdbcType=BIGINT},",
            "content = #{content,jdbcType=LONGVARCHAR}", "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKeyWithBLOBs(WhsActivities record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_activities
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Update({ "update whs_activities", "set title = #{title,jdbcType=VARCHAR},", "url = #{url,jdbcType=VARCHAR},",
            "status = #{status,jdbcType=INTEGER},", "create_time = #{createTime,jdbcType=BIGINT},",
            "author = #{author,jdbcType=VARCHAR},", "browses = #{browses,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKey(WhsActivities record);

    @SelectProvider(type = WhsActivitiesSqlProvider.class, method = "listSelective")
    List<WhsActivities> list(Integer status, Integer limit, Integer offset);
    
    @SelectProvider(type = WhsActivitiesSqlProvider.class, method = "countSelective")
    int count(Integer status);
}