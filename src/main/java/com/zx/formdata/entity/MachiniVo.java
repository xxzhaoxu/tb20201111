package com.zx.formdata.entity;

public class MachiniVo {
    private Integer mid;

    private String level;

    private String wide;

    private String thick;

    private String surface;

    private String film;

    private String mPrice;


    public String getThick() {
        return thick;
    }

    public void setThick(String thick) {
        this.thick = thick;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface == null ? null : surface.trim();
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film == null ? null : film.trim();
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice == null ? null : mPrice.trim();
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getWide() {
        return wide;
    }

    public void setWide(String wide) {
        this.wide = wide;
    }
}