package com.mort.middle.ware.org.entity;

import java.util.List;



public class OrgTree {
    private String name;
    private Integer id;
    private List<OrgTree> children;

    public OrgTree() {

    }

    public OrgTree(Integer id, String name, List<OrgTree> children) {
        this.name = name;
        this.id = id;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrgTree> getChildren() {
        return children;
    }

    public void setChildren(List<OrgTree> children) {
        this.children = children;
    }
}
