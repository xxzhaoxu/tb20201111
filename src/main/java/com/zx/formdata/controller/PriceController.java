package com.zx.formdata.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONObject;
import com.zx.formdata.entity.*;
import com.zx.formdata.mapper.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class PriceController {
    @Resource
    private CzVoMapper czVoMapper;
    @Resource
    private MachiniVoMapper machiniVoMapper;
    @Resource
    private ThickLevelMapper thickLevelMapper;
    @Resource
    private CzDensityMapper czDensityMapper;

    @Resource
    private JbOfferVoMapper jbOfferVoMapper;
    @GetMapping("findCzPriceList")
    public Result findCzPriceList(
            @RequestParam(required = false,name = "cz")String cz,
            @RequestParam(required = false,name = "thick")String thick,
            @RequestParam(required = false,name = "wide")String wide,
            @RequestParam(required = false,name = "len")String len,
            @RequestParam("pageIndex")Integer pageIndex,
            @RequestParam("pageSize")Integer pageSize
    ){
        Integer start = (pageIndex-1)*pageSize;
        List<CzVo> czVoList = czVoMapper.selectCzVoList(cz,thick,wide,len,start,pageSize);
        Integer count = czVoMapper.selectCount(cz, thick, wide,len);
        JSONObject reJSON = new JSONObject();
        reJSON.put("count",count);
        reJSON.put("list",czVoList);
        return Result.ofSuccess(reJSON);
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

    @GetMapping("findUpdateTime")
    public Result findUpdateTime(){
        Long time = czVoMapper.selectCreateTime();
        if (time==null){
            return Result.ofSuccess();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tm = sdf.format(new Date(time));
        return Result.ofSuccess(tm,null);
    }
    @GetMapping("findAllCz")
    public Result findAllCz(){
        return Result.ofSuccess(czVoMapper.selectAllCz());
    }

    @GetMapping("findAllThick")
    public Result findAllThick(@RequestParam(required = false,name = "cz")String cz){
        return Result.ofSuccess(czVoMapper.selectAllThick(cz));
    }
    @GetMapping("findAllWide")
    public Result findAllWide(@RequestParam(required = false,name = "cz")String cz,
                              @RequestParam(required = false,name = "thick")String thick
                              ){
        return Result.ofSuccess(czVoMapper.selectAllWide(cz, thick));
    }
    @GetMapping("findAllLen")
    public Result findAllLen(){
        return Result.ofSuccess(czVoMapper.selectAllLen());
    }

    @GetMapping("findAllMachiniList")
    public Result findAllMachiniList(
            @RequestParam("pageIndex")Integer pageIndex,
            @RequestParam("pageSize")Integer pageSize,
            @RequestParam(required = false)String surface,
            @RequestParam(required = false)String film
    ){
        JSONObject reJson = new JSONObject();
        Integer start = (pageIndex-1)*pageSize;
        List<MachiniVo> machiniVoList = machiniVoMapper.selectAllMachiniVoList(start, pageSize, surface, film);
        Integer count = machiniVoMapper.selectCount(surface, film);
        reJson.put("list",machiniVoList);
        reJson.put("count",count);
        return Result.ofSuccess(reJson);
    }
    @PostMapping("saveMachini")
    public Result saveMachini(@RequestParam("surface")String surface,
                              @RequestParam("film")String film,
                              @RequestParam("mPrice")String mPrice
                              ){
        MachiniVo machiniVo = new MachiniVo();
        machiniVo.setSurface(surface);
        machiniVo.setFilm(film);
        machiniVo.setmPrice(mPrice);
        machiniVoMapper.insertSelective(machiniVo);
        return Result.ofSuccess(machiniVo);
    }
    @PutMapping("updateMachini")
    public Result updateMachini(
            @RequestParam("mid")Integer mid,
            @RequestParam(required = false,name = "surface")String surface,
            @RequestParam(required = false,name = "film")String film,
            @RequestParam(required = false,name = "mPrice")String mPrice
    ){
        MachiniVo machiniVo = new MachiniVo();
        machiniVo.setMid(mid);
        machiniVo.setSurface(surface);
        machiniVo.setFilm(film);
        machiniVo.setmPrice(mPrice);
        machiniVoMapper.updateByPrimaryKeySelective(machiniVo);
        return Result.ofSuccess(machiniVo);
    }

    @DeleteMapping("delMachini")
    public Result delMachini(@RequestParam("mid")Integer mid){
        machiniVoMapper.deleteByPrimaryKey(mid);
        return Result.ofSuccess();
    }


    @GetMapping("findSurface")
    public Result findSurface(){
        return Result.ofSuccess(machiniVoMapper.selectAllSurface());
    }

    @GetMapping("findFilm")
    public Result findFilm(){
        return Result.ofSuccess(machiniVoMapper.selectAllFilm());
    }

    @PostMapping("uploadExcel")
    @Transactional
    public Result updateExcel(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return Result.ofFail(400,"上传失败");
        }
        String suffix =   FileUtil.getSuffix(file.getOriginalFilename());
        System.out.println(suffix.equals("xlsx"));
        if (!(suffix.equals("xlsx")||suffix.equals("xls"))){
            return Result.ofFail(400,"只能上传excel文件");
        }
        ExcelReader  excelReader = null;
        try {
            excelReader = ExcelUtil.getReader(file.getInputStream(),0);
            List<List<Object>> lists =  excelReader.read();
            List<MachiniVo> machiniVoList = new ArrayList<>();
//            List<ThickLevel> thickLevelList = new ArrayList<>();
            MachiniVo machiniVo;
//            ThickLevel thickLevel;
            for (int i = 1; i < lists.size(); i++) {
                List<Object> list = lists.get(i);
                String level = String.valueOf(list.get(0)).trim();
                String wide = String.valueOf(list.get(1)).trim();
                String surfice = String.valueOf(list.get(2)).trim();
                String film = String.valueOf(list.get(3)).trim();
                String mPrice = String.valueOf(list.get(4)).trim();
                machiniVo = new MachiniVo();

                machiniVo.setSurface(surfice);
                machiniVo.setFilm(film);
                machiniVo.setmPrice(mPrice);
                machiniVo.setWide(wide);
//                machiniVo.setThick(thick);
                machiniVo.setLevel(level);
                machiniVoList.add(machiniVo);


//                thickLevel = new ThickLevel();
//                thickLevel.setLevel(level);
////                thickLevel.setThick(thick);
//                thickLevelList.add(thickLevel);


            }
            machiniVoMapper.deleteAll();
            machiniVoMapper.insertMachiniVoList(machiniVoList);
//            thickLevelMapper.deleteAllThickLevel();
//            thickLevelMapper.insertThickLevelList(thickLevelList);

            ExcelReader czReader = ExcelUtil.getReader(file.getInputStream(),1);
            List<List<Object>> czObjList = czReader.read();
            List<CzVo> czVoList = new ArrayList<>();
            CzVo czVo;
            for (int i = 1; i < czObjList.size(); i++) {
               List<Object> list = czObjList.get(i);
               String cz = String.valueOf(list.get(0));
               String thick = String.valueOf(list.get(1));
               String wide = String.valueOf(list.get(2));
               String len = String.valueOf(list.get(3));
               String czPrice = String.valueOf(list.get(4));
               czVo = new CzVo();
               czVo.setCz(cz);
               czVo.setThick(thick);
               czVo.setWide(wide);
               czVo.setLen(len);
               czVo.setCzPrice(czPrice);
               czVo.setCreateTime(System.currentTimeMillis());
               czVoList.add(czVo);
            }

            czVoMapper.deleteAll();
            czVoMapper.insertSelectiveList(czVoList);



            ExcelReader jbExcelReader = ExcelUtil.getReader(file.getInputStream(),2);
            int index = 0;
            List<List<Object>> jbLists =  jbExcelReader.read();
            String name="";
            jbOfferVoMapper.deleteAll();
            for(int j=1;j<jbLists.size();j++) {
                List<Object> list = jbLists.get(j);
//                index++;
//                for (Object o : list) {
//                    if (o != null) {
//                        if (o.equals("<开始>")) {
//                            index = 0;
//                            continue loop1;
//                        }
//                        break;
//                    }
//                }
//                if (index == 1) {
//                    for (Object o : list) {
//                        if (o != null) {
//                            name = String.valueOf(o);
//                            continue loop1;
//                        }
//                    }
//                }
                //跳过表头
//                if (index == 2) {
//                    continue;
//                }
//                if ("".equals(name)) {
//                    continue;
//                }
                int len = list.size();
                JbOfferVo jbOfferVo = new JbOfferVo();
                loop2:
                for (int i = 0; i < len; i++) {
                    System.out.print(list.get(i));
                    String value = list.get(i) == null ? "" : String.valueOf(list.get(i));
                    loop3:
                    switch (i) {
                        case 0:
                            jbOfferVo.setName(value);
                            break loop3;
                        case 1:
                            jbOfferVo.setSurface(value);
                            break loop3;
                        case 2:
                            jbOfferVo.setPlace(value);
                            break loop3;
                        case 3:
                            jbOfferVo.setThick(value);
                            break loop3;
                        case 4:
                            jbOfferVo.setjType(value);
                            break loop3;
                        case 5:
                            jbOfferVo.setTaxPrice(value);
                            break loop3;
                        case 6:
                            jbOfferVo.setPrice(value);
                            break loop3;
                        case 7:
                            jbOfferVo.setWeight(value);
                            break loop3;
                        case 8:
                            jbOfferVo.setRemark(value);
                            break loop3;
                        default:
                            break loop3;
                    }
                }
                jbOfferVo.setCreateTime(System.currentTimeMillis());
                jbOfferVoMapper.insertSelective(jbOfferVo);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.ofSuccess();
    }
    @PostMapping("calcPrice")
    public Result calcPrice(@RequestParam("cz")String cz,
                            @RequestParam("thick")String thick,
                            @RequestParam("wide")String wide,
                            @RequestParam("len")String len,
                            @RequestParam("surface")String surface,
                            @RequestParam("film")String film,
                            @RequestParam(required = false,name = "reSurface")String reSurface,
                            @RequestParam(required = false,name = "reFilm")String reFilm,
                            @RequestParam("num")Integer num
                            ){

        BigDecimal price1;
        BigDecimal price2;
        BigDecimal price3 = new BigDecimal(0);

        CzVo czVo = czVoMapper.selectCzvo(cz, thick, wide);
        String length = czVo.getLen();
        if (StrUtil.isEmpty(length)){
            return Result.ofFail(400,"材质长度未设置");
        }
        if (czVo==null){
            return Result.ofFail(400,"未设定材质价格");
        }

        ThickLevel thickLevel = thickLevelMapper.selectByThick(Double.valueOf(thick));

        if (thickLevel==null){
            return Result.ofFail(400,"厚度等级未设定");
        }
        if (thickLevel.getLevel()==null){
            return Result.ofFail(400,"厚度等级未设定");
        }
        String level = thickLevel.getLevel();
        MachiniVo machiniVo = machiniVoMapper.selectMachiniVo(surface, film,level,wide);
        if (machiniVo==null){
            return Result.ofFail(400,"未设定加工价格");
        }
        MachiniVo reMachiniVo=null;
        if (reFilm!=null&&reSurface!=null){
            reMachiniVo = machiniVoMapper.selectMachiniVo(reSurface,reFilm,level,wide);
        }
        try {
            price1 =  new  BigDecimal(czVo.getCzPrice());
        }catch (Exception e){
            return Result.ofFail(400,"材质价格设置错误。材质价格: "+ czVo.getCzPrice());
        }
        try {
            price2 = new BigDecimal(machiniVo.getmPrice());
        }catch (Exception e){
            return Result.ofFail(400,"加工费设置错误。加工费: "+ machiniVo.getmPrice());
        }
        if (reMachiniVo!=null){
            try {
                price3 = new BigDecimal(reMachiniVo.getmPrice());
            }catch (Exception e){
                return Result.ofFail(400,"反面加工费设置错误,反面加工费: "+reMachiniVo.getmPrice());
            }
        }
        BigDecimal sum1 =  price1.add(price2).add(price3);



        CzDensity czDensity  = czDensityMapper.selectByCz(cz);
        if (czDensity==null||StrUtil.isEmpty(czDensity.getDensity())){
            return Result.ofFail(400,"没有材质对应的密度");
        }
        String density = czDensity.getDensity();
        BigDecimal singlePrice = sum1.divide(new BigDecimal(length),6,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(len)).setScale(0,BigDecimal.ROUND_UP);
        BigDecimal taxSinglePrice = singlePrice.divide(new BigDecimal(0.91),0,BigDecimal.ROUND_UP);
        BigDecimal kgPrice = singlePrice.divide(
                        new BigDecimal(thick)//厚度
                        .multiply(new BigDecimal(wide)) //宽度
                        .multiply(new BigDecimal(len))//长度
                        .multiply(new BigDecimal(density))//密度
                ,2,BigDecimal.ROUND_UP);
        BigDecimal taxKgPrice = kgPrice.divide(new BigDecimal(0.91),2,BigDecimal.ROUND_UP);
        JSONObject reJson = new JSONObject();
        reJson.put("singlePrice",singlePrice);
        reJson.put("taxSinglePrice",taxSinglePrice);
        reJson.put("kgPrice",kgPrice);
        reJson.put("taxKgPrice",taxKgPrice);
        return Result.ofSuccess(reJson);
    }


    @PostMapping("saveThickLevel")
    public Result saveThickLevel(@RequestParam("level")String level,@RequestParam("thickMin")Double min,@RequestParam("thickMax")Double max){
        ThickLevel thickLevel = thickLevelMapper.selectByLevel(level);
        if (thickLevel!=null){
            return Result.ofFail(400,"等级重复");
        }
         ThickLevel minThick = thickLevelMapper.selectByThick(min);
         if (minThick!=null){
            return   Result.ofFail(400,"最小值范围冲突");
         }
         ThickLevel maxThick = thickLevelMapper.selectByThick(max);
         if (maxThick!=null){
              return Result.ofFail(400,"最大值冲突");
         }
        ThickLevel tl  =new ThickLevel();
         tl.setLevel(level);
         tl.setThickMax(max);
         tl.setThickMin(min);
         thickLevelMapper.insertSelective(tl);
        return Result.ofSuccess();
    }

    @DeleteMapping("deleteThickLevel")
    public Result deleteThickLevel(@RequestParam("tlId")Integer tlId){
        thickLevelMapper.deleteByPrimaryKey(tlId);
        return Result.ofSuccess();
    }

    @GetMapping("findThickLevelList")
    public Result findThickLevelList(){
        return Result.ofSuccess(thickLevelMapper.selectThickLevelList());
    }
    @PutMapping("updateThickLevel")
    public Result updateThickLevel(@RequestParam("tlId")Integer tlId,
                                   @RequestParam("level")String level,
                                   @RequestParam("min")Double min,
                                   @RequestParam("max")Double max
                                   ){
        return Result.ofSuccess();
    }


    @GetMapping("findCzDensityList")
    public Result findCzDensityList(){
        List<CzDensity> czDensityList = czDensityMapper.selectCzDensityList();
        return Result.ofSuccess(czDensityList);
    }
    @PostMapping("saveCzDensity")
    public Result saveCzDensity(@RequestParam("cz")String cz,
                                @RequestParam("density")String density
                                ){
        CzDensity czDensity =  czDensityMapper.selectByCz(cz);
        if (czDensity!=null){
            return Result.ofFail(400,"材质重复");
        }
        czDensity = new CzDensity();
        czDensity.setCz(cz);
        czDensity.setDensity(density);
        czDensity.setCreateTime(System.currentTimeMillis());
        czDensityMapper.insertSelective(czDensity);
        return Result.ofSuccess();
    }

    @PutMapping("updateCzDensity")
    public Result updateCzDensity(@RequestParam("cdId")Integer cdId,
                                  @RequestParam(required = false,name = "cz")String cz,
                                  @RequestParam(required = false,name = "dendity")String dendity
                                  ){

        if (cz==null&&dendity==null){
            return Result.ofSuccess();
        }
        CzDensity czDensity = new CzDensity();
        czDensity.setCz(cz);
        czDensity.setCdId(cdId);
        czDensity.setDensity(dendity);
        czDensityMapper.updateByPrimaryKeySelective(czDensity);
        return Result.ofSuccess();

    }

    @DeleteMapping("deleteCzDensity")
    public Result deleteCzDensity(@RequestParam("cdId")Integer cdId){
        czDensityMapper.deleteByPrimaryKey(cdId);
        return Result.ofSuccess();
    }

    @PostMapping("uploadJbFile")
    public Result  uploadJbFile(MultipartFile file){
        if (file.isEmpty()) {
            return Result.ofFail(400,"上传失败");
        }
        String suffix =   FileUtil.getSuffix(file.getOriginalFilename());
        if (!(suffix.equals("xlsx")||suffix.equals("xls"))){
            return Result.ofFail(400,"只能上传excel文件");
        }

        jbOfferVoMapper.deleteAll();
        ExcelReader  excelReader = null;
        try {
            excelReader = ExcelUtil.getReader(file.getInputStream(),0);
            int index = 0;
            List<List<Object>> lists =  excelReader.read();
            String name="";
            loop1:for(List<Object> list:lists){
                index++;
                for (Object o : list) {
                    if (o!=null){
                        if (o.equals("<开始>")){
                            index = 0;
                            continue loop1;
                        }
                        break;
                    }
                }
                if (index==1){
                    for (Object o : list) {
                        if (o!=null){
                            name = String.valueOf(o);
                            continue loop1;
                        }
                    }
                }
                //跳过表头
                if (index==2){
                    continue ;
                }
                if ("".equals(name)){
                    continue;
                }
                 int len = list.size();
                 JbOfferVo jbOfferVo = new JbOfferVo();
               loop2: for (int i = 0; i < len; i++) {
                    System.out.print(list.get(i));
                    String value = list.get(i)==null?"":String.valueOf(list.get(i));
                    loop3:   switch (i){
                        case 0:jbOfferVo.setThick(value);
                        break loop3;
                        case 1: jbOfferVo.setjType(value);
                        break loop3;
                        case 2:jbOfferVo.setTaxPrice(value);
                        break loop3;
                        case 3:jbOfferVo.setPrice(value);
                        break loop3;
                        case 4:jbOfferVo.setWeight(value);
                        break loop3;
                        default:break loop3 ;
                    }
                }
                jbOfferVo.setName(name);
                jbOfferVo.setCreateTime(System.currentTimeMillis());
                jbOfferVoMapper.insertSelective(jbOfferVo);
            }
        }catch (Exception e){
               e.printStackTrace();
            return Result.ofFail(500,"系统错误");
        }finally {
            if (excelReader!=null){
                excelReader.close();
            }

        }
        return Result.ofSuccess();
    }

    @GetMapping("findBjofferList")
    public Result findBjofferList(@RequestParam(required = false,name = "name")String name,
                                  @RequestParam(required = false,name = "surface")String surface,
                                  @RequestParam(required = false,name = "place")String place,
                                  @RequestParam(required = false,name = "thick")String thick,
                                  @RequestParam(required = false,name = "jbType")String jbType,
                                  @RequestParam("pageIndex")Integer pageIndex,
                                  @RequestParam("pageSize")Integer pageSize
                                  ){
        JSONObject jsonObject = new JSONObject();
        Integer start = (pageIndex-1)*pageSize;
        List<JbOfferVo> jbOfferVoList = jbOfferVoMapper.selectJbOfferVoList(name,surface,place,thick,jbType,start,pageSize);
        Integer count = jbOfferVoMapper.selectJbOfferVoCount(name,surface,place,thick,jbType);
        jsonObject.put("count",count);
        jsonObject.put("list",jbOfferVoList);
        return Result.ofSuccess(jsonObject);
    }

    @GetMapping("selectAllJbofferCz")
    public Result selectAllJbofferCz(){
        return Result.ofSuccess(jbOfferVoMapper.selectJbofferNameList());
    }

    @GetMapping("findJbSurface")
    public Result findJbSurface(){
        return Result.ofSuccess(jbOfferVoMapper.selectSurface());
    }

    @GetMapping("findJbPlace")
    public Result findJbPlace(){
        return Result.ofSuccess(jbOfferVoMapper.selectPlace());
    }

    @GetMapping("findJbThick")
    public Result findJbThick(){
        return Result.ofSuccess(jbOfferVoMapper.selectThick());
    }
    @GetMapping("findJbType")
    public Result findJbType(){
        return Result.ofSuccess(jbOfferVoMapper.selectJtype());
    }
}
