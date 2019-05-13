package com.youthchina.courier.dto;

import java.util.List;

/**
 * @program: courier
 * @description: 公司DTO
 * @author: Qinghong Wang
 * @create: 2019-05-12 15:30
 **/
public class OrganizationDTO {
    private int id;
    private String name;
    private String avatarUrl;
    private String location;
    private String website;
    private String note;
    private String nation;

    private List<String> photoUrlList;
    private Integer jobCount;
    private Boolean isCollected = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public List<String> getPhotoUrlList() {
        return photoUrlList;
    }

    public void setPhotoUrlList(List<String> photoUrlList) {
        this.photoUrlList = photoUrlList;
    }

    public Integer getJobCount() {
        return jobCount;
    }

    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }

    public Boolean getCollected() {
        return isCollected;
    }

    public void setCollected(Boolean collected) {
        isCollected = collected;
    }
}
