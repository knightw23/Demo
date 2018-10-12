package com.rock.model;

import java.util.List;

public class TreeNode {

    private long id;

    private String name;

    private long parentId;

    private List<TreeNode> childList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public List<TreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<TreeNode> childList) {
        this.childList = childList;
    }

    public TreeNode(long id, String name, long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public TreeNode() {
    }
}
