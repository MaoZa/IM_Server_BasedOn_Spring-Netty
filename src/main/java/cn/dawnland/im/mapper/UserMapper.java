package cn.dawnland.im.mapper;

import cn.dawnland.im.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Cap_Sub
 */
@Mapper
public interface UserMapper {

    @Select("<script>" +
            "select * from users " +
            "where 1=1 " +
            "<if test='uid != null'>and uid = #{uid} </if>" +
            "<if test='email != null'>and email = #{email} </if>" +
            "<if test='nickname != null'>and nickname = #{nickname} </if>" +
            "</script>")
    User selectByParams(@Param("uid")Long uid,
                        @Param("email")String email,
                        @Param("nickname")String nickname);

}
