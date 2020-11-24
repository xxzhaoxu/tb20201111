package com.zx.formdata.mapper;

import com.zx.formdata.entity.CzVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CzVoMapper {
    int deleteByPrimaryKey(Integer cid);

    int deleteAll();

    int insertSelective(CzVo record);
    int insertSelectiveList(List<CzVo> list);

    CzVo selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(CzVo record);

    List<CzVo> selectCzVoList(@Param("cz")String cz,
                              @Param("thick")String thick,
                              @Param("wide")String wide,
                              @Param("len")String len,
                              @Param("start")Integer start,
                              @Param("end")Integer end);
    Integer selectCount(@Param("cz")String cz,
                        @Param("thick")String thick,
                        @Param("len")String len,
                        @Param("wide")String wide);
    List<String> selectAllCz();

    List<String> selectAllThick(@Param("cz")String cz);

    List<String> selectAllWide(@Param("cz")String cz,@Param("thick")String thick);
    List<String> selectAllLen();

    CzVo selectCzvo(@Param("cz")String cz,@Param("thick")String thick,@Param("wide")String wide);

    Long selectCreateTime();

}