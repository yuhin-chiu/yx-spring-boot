package cn.yx.mapper;

import cn.yx.entity.WhsDownloads;

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

public interface WhsDownloadsMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_downloads
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Delete({ "delete from whs_downloads", "where id = #{id,jdbcType=INTEGER}" })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_downloads
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({ "insert into whs_downloads (id, browses, ", "title, status, create_time, ", "annex, annex_name, ",
            "downs, author, content)", "values (#{id,jdbcType=INTEGER}, #{browses,jdbcType=BIGINT}, ",
            "#{title,jdbcType=VARCHAR}, #{status,jdbcType=TINYINT}, #{createTime,jdbcType=BIGINT}, ",
            "#{annex,jdbcType=VARCHAR}, #{annexName,jdbcType=VARCHAR}, ",
            "#{downs,jdbcType=INTEGER}, #{author,jdbcType=VARCHAR}, #{content,jdbcType=LONGVARCHAR})" })
    int insert(WhsDownloads record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_downloads
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = WhsDownloadsSqlProvider.class, method = "insertSelective")
    int insertSelective(WhsDownloads record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_downloads
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Select({ "select", "id, browses, title, status, create_time, annex, annex_name, downs, author, content",
            "from whs_downloads", "where id = #{id,jdbcType=INTEGER}" })
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "browses", property = "browses", jdbcType = JdbcType.BIGINT),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "status", property = "status", jdbcType = JdbcType.TINYINT),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.BIGINT),
            @Result(column = "annex", property = "annex", jdbcType = JdbcType.VARCHAR),
            @Result(column = "annex_name", property = "annexName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "downs", property = "downs", jdbcType = JdbcType.INTEGER),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.LONGVARCHAR) })
    WhsDownloads selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_downloads
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @UpdateProvider(type = WhsDownloadsSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WhsDownloads record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_downloads
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Update({ "update whs_downloads", "set browses = #{browses,jdbcType=BIGINT},", "title = #{title,jdbcType=VARCHAR},",
            "status = #{status,jdbcType=TINYINT},", "create_time = #{createTime,jdbcType=BIGINT},",
            "annex = #{annex,jdbcType=VARCHAR},", "annex_name = #{annexName,jdbcType=VARCHAR},",
            "downs = #{downs,jdbcType=INTEGER},", "author = #{author,jdbcType=VARCHAR},",
            "content = #{content,jdbcType=LONGVARCHAR}", "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKeyWithBLOBs(WhsDownloads record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table whs_downloads
     * @mbg.generated  Sun Jul 30 19:58:31 CST 2017
     */
    @Update({ "update whs_downloads", "set browses = #{browses,jdbcType=BIGINT},", "title = #{title,jdbcType=VARCHAR},",
            "status = #{status,jdbcType=TINYINT},", "create_time = #{createTime,jdbcType=BIGINT},",
            "annex = #{annex,jdbcType=VARCHAR},", "annex_name = #{annexName,jdbcType=VARCHAR},",
            "downs = #{downs,jdbcType=INTEGER},", "author = #{author,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKey(WhsDownloads record);
    
    @SelectProvider(type = WhsDownloadsSqlProvider.class, method = "listSelective")
    List<WhsDownloads> list(Integer status, Integer limit, Integer offset);
    
    @SelectProvider(type = WhsDownloadsSqlProvider.class, method = "countSelective")
    int count(Integer status);
    
    @Select("SELECT max(id) + 1 FROM whs_downloads")
    Integer getLastId();
}