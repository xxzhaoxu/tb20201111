package com.zx.formdata.mapper;

import com.zx.formdata.entity.JbOfferVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JbOfferVoMapper {
    int deleteByPrimaryKey(Integer joId);

    int deleteAll();

    int insertSelective(JbOfferVo record);

    JbOfferVo selectByPrimaryKey(Integer joId);

    int updateByPrimaryKeySelective(JbOfferVo record);

    List<String> selectJbofferName(@Param("name")String name,@Param("start")Integer start,@Param("end")Integer end);

    List<JbOfferVo> selectJbOfferVoList(
            @Param("name")String name,
            @Param("surface")String surface,
            @Param("place")String place,
            @Param("thick")String thick,
            @Param("jbType")String jbType,
            @Param("start")Integer start,
            @Param("end")Integer end);
    Integer selectJbOfferVoCount(
            @Param("name")String name,
            @Param("surface")String surface,
            @Param("place")String place,
            @Param("thick")String thick,
            @Param("jbType")String jbType
    );

    List<String> selectJbofferNameList();

    List<String> selectSurface();

    List<String> selectPlace();

    List<String> selectThick();

    List<String> selectJtype();

}