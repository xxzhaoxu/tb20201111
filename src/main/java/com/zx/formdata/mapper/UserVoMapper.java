package com.zx.formdata.mapper;

import com.zx.formdata.entity.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserVoMapper {
    int deleteByPrimaryKey(Integer uid);

    int insertSelective(UserVo record);

    UserVo selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UserVo record);

    UserVo selectUserByAccount(@Param("account")String account);

    List<UserVo> selectUserList(@Param("start")Integer start, @Param("end")Integer end, @Param("userName")String userName,@Param("phone")String phone);

    Integer selectUserCount( @Param("userName")String userName,@Param("phone")String phone);

    /**
     * 判断账号有没有被使用
     * @param uid
     * @param account
     * @return
     */
    UserVo hasOtherAccount(@Param("uid")Integer uid,@Param("account")String account);

}