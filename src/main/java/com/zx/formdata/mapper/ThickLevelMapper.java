package com.zx.formdata.mapper;

import com.zx.formdata.entity.ThickLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThickLevelMapper {
    int deleteByPrimaryKey(Integer tlId);

    int deleteAllThickLevel();

    int insertSelective(ThickLevel record);

    int insertThickLevelList(List<ThickLevel> list);

    ThickLevel selectByPrimaryKey(Integer tlId);

    ThickLevel selectByThickAndLevel(@Param("thick")String thick);

    int updateByPrimaryKeySelective(ThickLevel record);

    ThickLevel selectByThick(@Param("thick")Double thick);

    ThickLevel selectByLevel(@Param("level")String level);

    List<ThickLevel> selectThickLevelList();

}