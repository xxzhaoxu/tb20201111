package com.zx.formdata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回菜单列表
 * @author create by zhaoxu
 * @create 2020/11/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    private String name;
    private Integer size;
    private String type;
    private String text;
    private List<MenuItem> children;

    public MenuItem build(PermissionVo permissionVo){
        MenuItem menuItem = new MenuItem();
        menuItem.setName(permissionVo.getPermissionName());
        menuItem.setSize(12);
        menuItem.setType(permissionVo.getIcon());
        menuItem.setText(permissionVo.getText());
        if (permissionVo.getPermissionVoList()!=null&&permissionVo.getPermissionVoList().size()>0){
            List<MenuItem> childredList = new ArrayList<>();
            for (PermissionVo vo : permissionVo.getPermissionVoList()) {
                MenuItem childre = new MenuItem();
                childredList.add(childre.build(vo));
            }
            menuItem.setChildren(childredList);
        }
        return menuItem;
    }


}
