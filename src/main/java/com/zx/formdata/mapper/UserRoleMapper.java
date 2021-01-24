package com.zx.formdata.mapper;

import com.zx.formdata.entity.UserRole;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer urid);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer urid);

    UserRole selectbyUid(@Param("uid")Integer uid);

    UserRole selectByUidAndRid(@Param("uid")Integer uid,@Param("rid")Integer rid);

    int updateByPrimaryKeySelective(UserRole record);
}