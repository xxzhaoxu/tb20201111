package com.zx.formdata.mapper;

import com.zx.formdata.entity.MachiniVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MachiniVoMapper {
    int deleteByPrimaryKey(Integer mid);

    int insertSelective(MachiniVo record);

    int insertMachiniVoList(@Param("list")List<MachiniVo> list);

    int deleteAll();

    MachiniVo selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(MachiniVo record);

    List<MachiniVo> selectAllMachiniVoList(@Param("start")Integer start,@Param("end")Integer end,@Param("surface")String surface,@Param("film")String film);

    Integer selectCount(@Param("surface")String surface,@Param("film")String film);

    List<String> selectAllSurface();

    List<String> selectAllFilm();

    MachiniVo selectMachiniVo(@Param("surface")String surface,@Param("film")String film,@Param("level")String level, @Param("wide")String wide);
}