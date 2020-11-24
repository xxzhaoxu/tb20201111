package com.zx.formdata.entity;

import lombok.AllArgsConstructor;



public class CzVo {
    private Integer cid;

    private String cz;

    private String thick;

    private String wide;

    private String czPrice;

    private String len;

    private Long createTime;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCz() {
        return cz;
    }

    public void setCz(String cz) {
        this.cz = cz == null ? null : cz.trim();
    }

    public String getThick() {
        return thick;
    }

    public void setThick(String thick) {
        this.thick = thick == null ? null : thick.trim();
    }

    public String getWide() {
        return wide;
    }

    public void setWide(String wide) {
        this.wide = wide == null ? null : wide.trim();
    }

    public String getCzPrice() {
        return czPrice;
    }

    public void setCzPrice(String czPrice) {
        this.czPrice = czPrice == null ? null : czPrice.trim();
    }
}