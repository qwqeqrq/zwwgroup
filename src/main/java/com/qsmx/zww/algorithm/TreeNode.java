package com.qsmx.zww.algorithm;


/**
 * 二叉树的数据结构 以及 前序遍历 中序遍历 后续遍历
 */
public class TreeNode {

    private int value;

    private TreeNode leftNode;

    private TreeNode rightNode;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(int value, TreeNode leftNode, TreeNode rightNode) {
        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }


    //先序遍历 （核心思想使用了递归的编程方式）
    private static void bianli(TreeNode root) {

        int value = root.getValue();
        System.out.println(value);
        if (root.getLeftNode() != null) {
            bianli(root.leftNode);
        }
        if (root.getRightNode() != null) {
            bianli(root.getRightNode());
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);//root节点
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(7);
        TreeNode treeNode6 = new TreeNode(8);
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode7 = new TreeNode(5);
        treeNode.setLeftNode(treeNode2);
        treeNode2.setLeftNode(treeNode3);
        treeNode3.setRightNode(treeNode4);
        treeNode4.setLeftNode(treeNode5);
        treeNode4.setRightNode(treeNode6);
        treeNode.setRightNode(treeNode1);
        treeNode1.setRightNode(treeNode7);

        bianli(treeNode);
    }

}
