package com.zx.formdata.mapper;

import com.zx.formdata.entity.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleVoMapper {
    int deleteByPrimaryKey(Integer rid);

    int insertSelective(RoleVo record);

    RoleVo selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(RoleVo record);

    List<RoleVo> selectRoleList(@Param("start")Integer start,@Param("end")Integer end,@Param("roleName")String roleName);

    Integer selectRoleCount(@Param("roleName")String roleName);

    RoleVo selectRoleByRoleName(@Param("roleName")String roleName);

    RoleVo selectRolebyUid(@Param("uid")Integer uid);

    List<RoleVo> selectAllRole();

}