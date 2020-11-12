package com.zx.formdata.mapper;

import com.zx.formdata.entity.CzVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CzVoMapper {
    int deleteByPrimaryKey(Integer cid);

    int insertSelective(CzVo record);

    CzVo selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(CzVo record);

    List<CzVo> selectCzVoList(@Param("cz")String cz,
                              @Param("thick")String thick,
                              @Param("wide")String wide,
                              @Param("start")Integer start,
                              @Param("end")Integer end);

}