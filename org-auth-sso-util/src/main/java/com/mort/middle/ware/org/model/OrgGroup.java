package com.mort.middle.ware.org.model;

import java.util.Date;

public class OrgGroup {
    /**
    * 自增ID
    */
    private Integer id;

    /**
    * 销售小组名称
    */
    private String name;

    /**
    * 销售类型
    */
    private Byte saleType;

    /**
    * 小组领导
    */
    private Integer leader;

    /**
    * 状态:1-正常,2-封停
    */
    private Byte status;

    /**
    * 软删除,0是有效,1是删除
    */
    private Byte isDeleted;

    /**
    * 创建时间
    */
    private Date ctime;

    /**
    * 最后修改时间
    */
    private Date mtime;

    /**
    * 销售总监ID
    */
    private Integer saleDirectorId;

    /**
    * 上层id
    */
    private Integer parentId;

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

    public Byte getSaleType() {
        return saleType;
    }

    public void setSaleType(Byte saleType) {
        this.saleType = saleType;
    }

    public Integer getLeader() {
        return leader;
    }

    public void setLeader(Integer leader) {
        this.leader = leader;
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

    public Integer getSaleDirectorId() {
        return saleDirectorId;
    }

    public void setSaleDirectorId(Integer saleDirectorId) {
        this.saleDirectorId = saleDirectorId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}