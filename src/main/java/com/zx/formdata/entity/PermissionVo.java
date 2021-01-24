package com.zx.formdata.entity;

import java.util.List;

/**
 * @author zhaoxu
 */
public class PermissionVo {
    private Integer pid;

    private String permissionName;

    private String url;

    private Integer parantId;

    private String icon;

    private String text;

    private String expand;

    private String selected;

    private String disabled;

    private String checked;

    private String router;

    List<PermissionVo> permissionVoList;


    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public List<PermissionVo> getPermissionVoList() {
        return permissionVoList;
    }

    public void setPermissionVoList(List<PermissionVo> permissionVoList) {
        this.permissionVoList = permissionVoList;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getParantId() {
        return parantId;
    }

    public void setParantId(Integer parantId) {
        this.parantId = parantId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand == null ? null : expand.trim();
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected == null ? null : selected.trim();
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled == null ? null : disabled.trim();
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router == null ? null : router.trim();
    }
}