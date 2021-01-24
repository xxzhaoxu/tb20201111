package com.zx.formdata.entity;

public class ThickLevel {
    private Integer tlId;

    private String level;

    private String thick;

    private Double thickMin;

    private Double thickMax;

    public Double getThickMax() {
        return thickMax;
    }

    public void setThickMax(Double thickMax) {
        this.thickMax = thickMax;
    }

    public Double getThickMin() {
        return thickMin;
    }

    public void setThickMin(Double thickMin) {
        this.thickMin = thickMin;
    }

    public Integer getTlId() {
        return tlId;
    }

    public void setTlId(Integer tlId) {
        this.tlId = tlId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getThick() {
        return thick;
    }

    public void setThick(String thick) {
        this.thick = thick == null ? null : thick.trim();
    }
}