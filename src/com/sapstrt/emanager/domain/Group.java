package com.sapstrt.emanager.domain;

import java.util.List;

/**
 * Created by pteltu on 11/28/13.
 */
public class Group {
    private Integer id;
    private Integer grpId;
    private String groupName;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGrpId() {
        return grpId;
    }

    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", grpId=" + grpId +
                ", groupName='" + groupName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
