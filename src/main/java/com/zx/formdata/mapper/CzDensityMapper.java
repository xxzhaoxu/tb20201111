package com.zx.formdata.mapper;

import com.zx.formdata.entity.CzDensity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CzDensityMapper {
    int deleteByPrimaryKey(Integer cdId);

    int insertSelective(CzDensity record);

    CzDensity selectByPrimaryKey(Integer cdId);

    int updateByPrimaryKeySelective(CzDensity record);

    List<CzDensity> selectCzDensityList();

    CzDensity selectByCz(@Param("cz")String cz);
}