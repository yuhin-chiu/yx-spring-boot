package cn.yx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import cn.yx.entity.WhsArticle;

public interface WhsArticleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table whs_article
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    @Delete({
        "delete from whs_article",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table whs_article
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    @Insert({
        "insert into whs_article (id, parent, ",
        "title, modify_time, ",
        "author, content)",
        "values (#{id,jdbcType=BIGINT}, #{parent,jdbcType=INTEGER}, ",
        "#{title,jdbcType=VARCHAR}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{author,jdbcType=VARCHAR}, #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(WhsArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table whs_article
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    @InsertProvider(type=WhsArticleSqlProvider.class, method="insertSelective")
    int insertSelective(WhsArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table whs_article
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    @Select({
        "select",
        "id, parent, title, modify_time, author, content",
        "from whs_article",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="parent", property="parent", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="modify_time", property="modifyTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    WhsArticle selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table whs_article
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    @UpdateProvider(type=WhsArticleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(WhsArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table whs_article
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    @Update({
        "update whs_article",
        "set parent = #{parent,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "author = #{author,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(WhsArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table whs_article
     *
     * @mbg.generated Wed Oct 25 16:59:29 CST 2017
     */
    @Update({
        "update whs_article",
        "set parent = #{parent,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "author = #{author,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(WhsArticle record);

    @Select({
        "select id, title",
        "from whs_article",
        "where parent = #{parent,jdbcType=INTEGER}"
    })
    List<WhsArticle> getTitles(Integer parent);
}