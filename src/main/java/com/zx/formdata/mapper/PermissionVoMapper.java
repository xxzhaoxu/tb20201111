package com.zx.formdata.mapper;

import com.zx.formdata.entity.PermissionVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface PermissionVoMapper {
    int deleteByPrimaryKey(Integer pid);

    int insertSelective(PermissionVo record);

    PermissionVo selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(PermissionVo record);

    List<PermissionVo> selectPermissionTreeList();

    List<PermissionVo> selectPermission(@Param("parentId")Integer parentId,@Param("pidList")List<Integer> pidList);

    List<Integer> selectPidListByUid(@Param("uid")Integer uid);

}