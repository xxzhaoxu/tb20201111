package com.zx.formdata.mapper;

import com.zx.formdata.entity.RolePermissionVo;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionVoMapper {
    int deleteByPrimaryKey(Integer rpid);

    int insertSelective(RolePermissionVo record);

    RolePermissionVo selectByPrimaryKey(Integer rpid);

    int updateByPrimaryKeySelective(RolePermissionVo record);

    RolePermissionVo selectRolePermissionVoByRidAndPid(@Param("rid")Integer rid,@Param("pid")Integer pid);

    int delectByRid(@Param("rid")Integer rid);

}