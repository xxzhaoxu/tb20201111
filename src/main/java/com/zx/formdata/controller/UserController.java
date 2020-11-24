package com.zx.formdata.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zx.formdata.entity.*;
import com.zx.formdata.mapper.*;
import com.zx.formdata.shiroconfig.JwtUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author create by zhaoxu
 * @create 2020/11/15
 */
@RestController
public class UserController {
    @Resource
    private UserVoMapper userVoMapper;
    @Resource
    private RoleVoMapper roleVoMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RolePermissionVoMapper rolePermissionVoMapper;
    @Resource
    private PermissionVoMapper permissionVoMapper;
    @Resource
    private MachiniVoMapper machiniVoMapper;
    @PostMapping("login")
    public Result login(@RequestParam("account")String account,@RequestParam("passWord")String passWord){
        UserVo userVo = userVoMapper.selectUserByAccount(account);
        if (userVo==null){
            return Result.ofFail(400,"账号不存在");
        }
        String md5Pswd = DigestUtil.md5Hex(passWord);
        if (!userVo.getPassWord().equals(md5Pswd)){
            return Result.ofFail(400,"密码不正确");
        }
        UserRole userRole =  userRoleMapper.selectbyUid(userVo.getUid());
        if (userRole==null){
            return Result.ofFail(400,"账号尚未分配角色，请联系管理员");
        }
        String token =  JwtUtil.sign(account,md5Pswd);
        return Result.ofSuccess(userVo,token);
    }

    @PostMapping("addUser")
    public Result addUser(
            @RequestParam("userName")String userName,
//            @RequestParam("passWord")String passWord,
            @RequestParam("account")String account,
            @RequestParam("phone")String phone
    ){
        UserVo userVo =userVoMapper.selectUserByAccount(account);
        if (userVo!=null){
            return Result.ofFail(400,"账号已存在");
        }
        String md5Pswd = DigestUtil.md5Hex("123456");
        userVo = new UserVo();
        userVo.setAccount(account);
        userVo.setPassWord(md5Pswd);
        userVo.setUserName(userName);
        userVo.setPhone(phone);

        userVoMapper.insertSelective(userVo);
        return Result.ofSuccess(userName);
    }

    @GetMapping("findUserList")
    public Result findUserList(
            @RequestParam("pageIndex")Integer pageIndex,
            @RequestParam("pageSize")Integer pageSize,
            @RequestParam(required = false,name = "userName")String userName,
            @RequestParam(required = false,name = "phone")String phone
    ){
        Integer start = (pageIndex-1)* pageSize;
        List<JSONObject> reList = new ArrayList<>();
        List<UserVo> userVoList = userVoMapper.selectUserList(start,pageSize,userName,phone);
        Integer count = userVoMapper.selectUserCount(userName, phone);
        for (UserVo userVo : userVoList) {
            JSONObject userJson = (JSONObject) JSON.toJSON(userVo);
            RoleVo roleVo =  roleVoMapper.selectRolebyUid(userVo.getUid());
            userJson.put("role",roleVo);
            reList.add(userJson);
        }
        JSONObject re = new JSONObject();
        re.put("list",reList);
        re.put("count",count);
        return Result.ofSuccess(re);
    }

    @DeleteMapping("delUser")
    public Result delUser(@RequestParam("uid")Integer uid){
        userVoMapper.deleteByPrimaryKey(uid);
        return Result.ofSuccess();
    }

    @PutMapping("updateUserInfo")
    public Result updateUserInfo(
            @RequestParam("uid")Integer uid,
            @RequestParam("userName")String userName,
            @RequestParam("account")String account,
            @RequestParam("phone")String phone,
            @RequestParam(required = false,name = "rid")Integer roleId
    ){
        UserVo userVo = userVoMapper.hasOtherAccount(uid,account);
        if (userVo!=null){
            return Result.ofFail(400,"账号不可用");
        }
        userVo = userVoMapper.selectByPrimaryKey(uid);
        if (userVo==null){
            return Result.ofFail(400,"用户不存在");
        }
        userVo.setPhone(phone);
        userVo.setUserName(account);
        userVo.setUserName(userName);
        userVoMapper.updateByPrimaryKeySelective(userVo);

        if (roleId!=null){
            UserRole userRole = userRoleMapper.selectbyUid(uid);
            if (userRole==null){
                userRole = new UserRole();
                userRole.setUserid(uid);
                userRole.setRoleid(roleId);
                userRole.setCreateTime(System.currentTimeMillis());
                userRoleMapper.insertSelective(userRole);
            }else {
                userRole.setRoleid(roleId);
                userRoleMapper.updateByPrimaryKeySelective(userRole);
            }
        }

        return Result.ofSuccess();
    }
    @PutMapping("updataUserPswd")
    public Result updataUserPswd(
            @RequestParam("account")String account,
            @RequestParam("oldpswd")String pswd,//旧密码
            @RequestParam("pswd")String passWord,
            @RequestParam("pswd2")String passWord2
    ){
        UserVo userVo = userVoMapper.selectUserByAccount(account);
        if (userVo==null){
            return Result.ofFail(400,"账号不存在");
        }
        String md5Pswd = DigestUtil.md5Hex(pswd);
        if (!md5Pswd.equals(userVo.getPassWord())){
            return Result.ofFail(400,"密码不正确");
        }

        if (!passWord.equals(passWord2)){
            return Result.ofFail(400,"两次密码不能一样");
        }
        String md5 = DigestUtil.md5Hex(passWord);
        userVo.setPassWord(md5);
        userVoMapper.updateByPrimaryKeySelective(userVo);
        return Result.ofSuccess();
    }

    @GetMapping("findAllRole")
    public Result findAllRole(@RequestParam("pageIndex")Integer pageIndex,
                              @RequestParam("pageSize")Integer pageSize,
                              @RequestParam(required = false,name = "roleName")String roleName
                              ){
        Integer start = (pageIndex-1)*pageSize;
        List<RoleVo> roleVoList = roleVoMapper.selectRoleList(start, pageSize, roleName);
        Integer count = roleVoMapper.selectRoleCount(roleName);
        JSONObject re = new JSONObject();
        re.put("list",roleVoList);
        re.put("count",count);
        return Result.ofSuccess(re);
    }

    @GetMapping("queryRoleList")
    public Result findRoleList(){
        return Result.ofSuccess(roleVoMapper.selectAllRole());
    }

    @PostMapping("saveRole")
    public Result saveRole(@RequestParam("roleName")String roleName,
                           @RequestParam(required = false,name = "remark")String remark
                           ){
        RoleVo roleVo = roleVoMapper.selectRoleByRoleName(roleName);
        if (roleVo!=null){
            return Result.ofFail(400,"角色已存在");
        }
        roleVo = new RoleVo();
        roleVo.setRemark(remark);
        roleVo.setRoleName(roleName);
        roleVo.setCreateTime(System.currentTimeMillis());
        roleVoMapper.insertSelective(roleVo);
        return Result.ofSuccess(roleName);
    }

    @PutMapping("updateRole")
    public Result updateRole(
            @RequestParam("rid")Integer rid,
            @RequestParam(required = false,name = "roleName")String roleName,
            @RequestParam(required = false,name = "remark")String remark
    ){
        RoleVo roleVo = roleVoMapper.selectByPrimaryKey(rid);
        if (roleVo==null){
            return Result.ofFail(400,"角色不存在");
        }
        roleVo.setRemark(remark);
        roleVo.setRoleName(roleName);
        roleVoMapper.updateByPrimaryKeySelective(roleVo);
        return Result.ofSuccess(roleVo);
    }

    @DeleteMapping("delRole")
    public Result delRole(@RequestParam("rid")Integer rid){
         roleVoMapper.deleteByPrimaryKey(rid);
         return Result.ofSuccess();
    }
//    private  List<PermissionVo> getPermissionList( List<PermissionVo>  list,Integer rid){
//        for (PermissionVo permissionVo : list) {
//            Integer pid = permissionVo.getPid();
//             RolePermissionVo rolePermissionVo  = rolePermissionVoMapper.selectRolePermissionVoByRidAndPid(rid,pid);
//            if (rolePermissionVo==null){
//                permissionVo.setChecked("false");
//            }else {
//                permissionVo.setChecked("true");
//            }
//            if (permissionVo.getPermissionVoList()!=null&&permissionVo.getPermissionVoList().size()>0){
//                for (PermissionVo vo : permissionVo.getPermissionVoList()) {
//                    RolePermissionVo rp = rolePermissionVoMapper.selectRolePermissionVoByRidAndPid(rid,vo.getPid());
//                    if (rp==null){
//                        vo.setChecked("false");
//                    }else {
//                        vo.setChecked("true");
//                    }
//                }
//            }
//        }
//        return list;
//    }
    @GetMapping("findRolePermissionTreeList")
    public Result findRolePermissionTreeList(@RequestParam("rid")Integer rid){
        List<PermissionVo> permissionVoList = permissionVoMapper.selectPermissionTreeList();

        for (PermissionVo permissionVo : permissionVoList) {
            JSONObject jsonObject = new JSONObject();
            Integer pid = permissionVo.getPid();
            RolePermissionVo rolePermissionVo  = rolePermissionVoMapper.selectRolePermissionVoByRidAndPid(rid,pid);
            if (rolePermissionVo==null){
                permissionVo.setChecked("0");
                jsonObject.put("selected",false);
            }else {
                permissionVo.setChecked("1");
                jsonObject.put("selected",true);
            }

            if (permissionVo.getPermissionVoList()!=null&&permissionVo.getPermissionVoList().size()>0){
                for (PermissionVo vo : permissionVo.getPermissionVoList()) {
                    RolePermissionVo rp = rolePermissionVoMapper.selectRolePermissionVoByRidAndPid(rid,vo.getPid());
                    if (rp==null){
                        vo.setChecked("0");
                    }else {
                        vo.setChecked("1");
                    }
                }
            }
        }
        return Result.ofSuccess(permissionVoList);
    }
    @GetMapping("findPermissionTreeList")
    public Result findPermissionTreeList(){
        List<PermissionVo> permissionVoList = permissionVoMapper.selectPermissionTreeList();
        return Result.ofSuccess(permissionVoList);
    }

    @PostMapping("saveRolePermission")
    public Result saveRolePermission(@RequestParam("rid")Integer rid,@RequestParam("pids")String pids){
        rolePermissionVoMapper.delectByRid(rid);
        String[] pidArr = pids.split(",");
        for (String s : pidArr) {
            RolePermissionVo rolePermissionVo = new RolePermissionVo();
            rolePermissionVo.setPermissionid(Integer.parseInt(s));
            rolePermissionVo.setRoleid(rid);
            rolePermissionVoMapper.insertSelective(rolePermissionVo);
        }
        return Result.ofSuccess();
    }

    @GetMapping("findUserPermission")
    public Result findUserPermission(@RequestParam("uid")Integer uid){
        List<Integer> pidList = permissionVoMapper.selectPidListByUid(uid);
        List<PermissionVo> permissionVoList = permissionVoMapper.selectPermission(0,pidList);
        for (PermissionVo permissionVo : permissionVoList) {
           Integer pid = permissionVo.getPid();
           List childrenList = permissionVoMapper.selectPermission(pid,pidList);
           permissionVo.setPermissionVoList(childrenList);
        }

        List<MenuItem> menuItemList = new ArrayList<>();
        for (PermissionVo permissionVo : permissionVoList) {
            MenuItem menuItem = new MenuItem();
            menuItemList.add( menuItem.build(permissionVo)) ;
        }
        return Result.ofSuccess(menuItemList);
    }
}
