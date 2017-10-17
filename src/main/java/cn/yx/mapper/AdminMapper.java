package cn.yx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import cn.yx.model.Admin;

/**
 * @author yuxuanjiao
 * @date 2017年10月17日 下午4:28:58
 * @version 1.0
 */
@Repository
public interface AdminMapper {
    @Select("SELECT * FROM admin_user WHERE account=#{account}")
    @Results({ @Result(column = "user_name", property = "userName") })
    Admin checkUser(String account);

    @Insert("INSERT INTO admin_user(account, passwd, user_name) VALUES(#{account}, #{password}, #{userName})")
    void addUser(@Param("account") String account, @Param("password") String password,
            @Param("userName") String userName);

    @Update("UPDATE admin_user SET passwd = #{password} WHERE account = #{account}")
    int modifyUser(@Param("account") String account, @Param("password") String password);
}
