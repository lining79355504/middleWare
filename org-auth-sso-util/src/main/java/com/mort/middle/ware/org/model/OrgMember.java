package com.mort.middle.ware.org.model;

import java.util.Date;

public class OrgMember {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 销售名称
     */
    private String name;

    /**
     * 公司邮箱
     */
    private String email;

    /**
     * 销售类型
     */
    private Byte type;

    /**
     * 销售小组ID
     */
    private Integer groupId;

    /**
     * 状态（1-有效，2-无效）
     */
    private Byte status;

    /**
     * 软删除,0是有效,1是删除
     */
    private Byte isDeleted;

    /**
     * 添加时间
     */
    private Date ctime;

    /**
     * 修改时间
     */
    private Date mtime;

    /**
     * 角色，0销售1组长2总监
     */
    private Byte level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }
}