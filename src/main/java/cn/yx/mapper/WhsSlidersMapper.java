package cn.yx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import cn.yx.entity.WhsSliders;

public interface WhsSlidersMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_sliders
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    @Delete({ "delete from whs_sliders", "where id = #{id,jdbcType=INTEGER}" })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_sliders
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({ "insert into whs_sliders (id, img_key, ", "title, url, status, ", "create_time)",
            "values (#{id,jdbcType=INTEGER}, #{imgKey,jdbcType=VARCHAR}, ",
            "#{title,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, #{status,jdbcType=TINYINT}, ",
            "#{createTime,jdbcType=BIGINT})" })
    int insert(WhsSliders record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_sliders
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = WhsSlidersSqlProvider.class, method = "insertSelective")
    int insertSelective(WhsSliders record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_sliders
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    @Select({ "select", "id, img_key, title, url, status, create_time", "from whs_sliders",
            "where id = #{id,jdbcType=INTEGER}" })
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "img_key", property = "imgKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "url", property = "url", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.TINYINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT) })
    WhsSliders selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_sliders
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    @UpdateProvider(type = WhsSlidersSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WhsSliders record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_sliders
     * @mbg.generated  Mon Aug 14 20:03:18 CST 2017
     */
    @Update({ "update whs_sliders", "set img_key = #{imgKey,jdbcType=VARCHAR},", "title = #{title,jdbcType=VARCHAR},",
            "url = #{url,jdbcType=VARCHAR},", "status = #{status,jdbcType=TINYINT},",
            "create_time = #{createTime,jdbcType=BIGINT}", "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKey(WhsSliders record);

    @Select({
        "select",
        "id, img_key, title, url, status, create_time",
        "from whs_sliders",
        "where status = 0"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="img_key", property="imgKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.INTEGER)
    })
    List<WhsSliders> selectAll();
    
    @Select({
        "select",
        "id, img_key, title, url, status, create_time",
        "from whs_sliders",
        "where status = 0",
        "order by create_time desc",
        "limit 10"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="img_key", property="imgKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.INTEGER)
    })
    List<WhsSliders> selectNew();
    
    @Select({
        "select",
        "id, img_key, title, url, status, create_time",
        "from whs_sliders"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="img_key", property="imgKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.INTEGER)
    })
    List<WhsSliders> selectAllWithoutStatus();
}