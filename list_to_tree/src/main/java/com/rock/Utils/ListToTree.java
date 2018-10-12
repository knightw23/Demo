package com.rock.Utils;

import com.rock.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ListToTree {

    public static void main(String[] args) {
        List<TreeNode> list = new ArrayList<>();
        TreeNode treeNode4 = new TreeNode(5L, "四级节点", 4);
        TreeNode treeNode5 = new TreeNode(6L, "五级节点", 5);
        TreeNode treeNode6 = new TreeNode(7L, "六级节点", 6);
        TreeNode treeNode = new TreeNode(1L, "根节点", 0);
        TreeNode treeNode1 = new TreeNode(2L, "一级节点", 1);
        TreeNode treeNode2 = new TreeNode(3L, "二级节点", 2);
        TreeNode treeNode3 = new TreeNode(4L, "三级节点", 2);
        TreeNode treeNode7 = new TreeNode(8L, "七级节点", 7);
        TreeNode treeNode8 = new TreeNode(91L, "一级节点", 1);
        TreeNode treeNode9 = new TreeNode(10L, "一级节点", 1);
        TreeNode treeNode10 = new TreeNode(11L, "根节点", 0);

        list.add(treeNode);
        list.add(treeNode1);
        list.add(treeNode2);
        list.add(treeNode10);
        list.add(treeNode9);

        List<TreeNode> tree = getTree(list);
    }

    public static List<TreeNode> getTree(List<TreeNode> list) {

        List<TreeNode> reult = new ArrayList<TreeNode>();
        for (TreeNode node1 : list) {
            boolean mark = false;
            for (TreeNode node2 : list) {
                if (node1.getParentId() != 0 && node1.getParentId() == node2.getId()) {
                    mark = true;
                    if (node2.getChildList() == null)
                        node2.setChildList(new ArrayList<TreeNode>());
                    node2.getChildList().add(node1);
                    break;
                }
            }
            if (!mark) {
                reult.add(node1);
            }
        }
        return reult;
    }

}
