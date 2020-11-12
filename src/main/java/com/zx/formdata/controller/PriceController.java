package com.zx.formdata.controller;

import com.zx.formdata.entity.CzVo;
import com.zx.formdata.entity.Result;
import com.zx.formdata.mapper.CzVoMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PriceController {
    @Resource
    private CzVoMapper czVoMapper;
    @GetMapping("findCzPriceList")
    public Result findCzPriceList(
            @RequestParam(required = false,name = "cz")String cz,
            @RequestParam(required = false,name = "thick")String thick,
            @RequestParam(required = false,name = "wide")String wide,
            @RequestParam("pageIndex")Integer pageIndex,
            @RequestParam("pageSize")Integer pageSize
    ){
        Integer start = (pageIndex-1)*pageSize;
        List<CzVo> czVoList = czVoMapper.selectCzVoList(cz,thick,wide,start,pageSize);
        return Result.ofSuccess(czVoList);
    }

    @PostMapping("addCzPrice")
    public Result addCzPrice(@RequestParam("cz")String cz,
                             @RequestParam("thick")String thick,
                             @RequestParam("wide")String wide,
                             @RequestParam("czPrice")String czPrice
                             ){
        CzVo czVo = new CzVo();
        czVo.setCz(cz);
        czVo.setThick(thick);
        czVo.setWide(wide);
        czVo.setCzPrice(czPrice);
        czVoMapper.insertSelective(czVo);
        return Result.ofSuccess(czVo);
    }

    @PutMapping("updateCzPrice")
    public Result updateCzPrice(
            @RequestParam("cid")Integer cid,
            @RequestParam("cz")String cz,
            @RequestParam("thick")String thick,
            @RequestParam("wide")String wide,
            @RequestParam("czPrice")String czPrice
    ){
        CzVo czVo = new CzVo();
        czVo.setCid(cid);
        czVo.setCz(cz);
        czVo.setThick(thick);
        czVo.setWide(wide);
        czVo.setCzPrice(czPrice);
        czVoMapper.updateByPrimaryKeySelective(czVo);
        return Result.ofSuccess(czVo);
    }

    @DeleteMapping("delCzPrice")
    public Result delCzPrice(@RequestParam("cid")Integer cid){
        czVoMapper.deleteByPrimaryKey(cid);
        return Result.ofSuccess();
    }

    @GetMapping("findByCzById")
    public Result findByCzById(@RequestParam("cid")Integer cid){
        return Result.ofSuccess(czVoMapper.selectByPrimaryKey(cid));
    }
}
