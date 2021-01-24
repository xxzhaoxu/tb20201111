package com.zx.formdata.entity;

public class JbOfferVo {
    private Integer joId;

    private String thick;

    private String jType;

    private String price;

    private String taxPrice;

    private String weight;

    private Long createTime;

    private String name;

    private String surface;

    private String place;

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getJoId() {
        return joId;
    }

    public void setJoId(Integer joId) {
        this.joId = joId;
    }

    public String getThick() {
        return thick;
    }

    public void setThick(String thick) {
        this.thick = thick == null ? null : thick.trim();
    }

    public String getjType() {
        return jType;
    }

    public void setjType(String jType) {
        this.jType = jType == null ? null : jType.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(String taxPrice) {
        this.taxPrice = taxPrice == null ? null : taxPrice.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}