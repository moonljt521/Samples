package com.example.algorithm_print_bindarytree;

import java.util.Stack;

/**
 * author: moon
 * created on: 17/9/12 下午2:32
 * description:  二叉树
 */
public class BinaryTree {

    private static TreeNode rootTreeNode;

    public static void main(String[] args) {

        // 构造一颗树
        rootTreeNode = new TreeNode(1, "1");
        TreeNode treeNode2 = new TreeNode(1, "2");
        TreeNode treeNode3 = new TreeNode(1, "3");
        TreeNode treeNode4 = new TreeNode(1, "4");
        TreeNode treeNode5 = new TreeNode(1, "5");
        TreeNode treeNode6 = new TreeNode(1, "6");
        TreeNode treeNode7 = new TreeNode(1, "7");

        rootTreeNode.setLeftChild(treeNode2);
        rootTreeNode.setRightChild(treeNode3);

        treeNode2.setLeftChild(treeNode4);

        treeNode3.setLeftChild(treeNode5);
        treeNode3.setRightChild(treeNode6);

        treeNode6.setRightChild(treeNode7);


        BinaryTree tree = new BinaryTree();

        System.out.println("获取这棵树的高度：" + tree.getHeight());
        System.out.println("获取这棵树的节点数：" + tree.getSize());
        System.out.println("前序优先遍历：" );
        tree.beforeOrder();

        System.out.println("中序优先遍历：" );
        tree.middleOrder();

        System.out.println("后序优先遍历：" );
        tree.afterOrder();

        System.out.println("非递归 前序优先遍历：" );
        tree.beforeOrderNonRecursively();
    }


    private int getHeight() {
        return getHeight(rootTreeNode);
    }

    private int getSize() {
        return getSize(rootTreeNode);
    }

    private void beforeOrder(){
        beforeOrder(rootTreeNode);
    }

    private void middleOrder(){
        middleOrder(rootTreeNode);
    }

    private void afterOrder(){
        afterOrder(rootTreeNode);
    }

    private void beforeOrderNonRecursively(){
        beforeOrderNonRecursively(rootTreeNode);
    }

    // ----------------------------------------------------------



    /**
     * 递归计算高度，如果一个节点有左孩子或者右孩子，则它的高度就加1 ，最后再加上根节点
     *
     * @param node
     * @return
     */
    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftH = getHeight(node.getLeftChild());
        int rightH = getHeight(node.getRightChild());
        return leftH > rightH ? (leftH + 1) : (rightH + 1);
    }

    /**
     * 递归计算 树的节点个数 : 也是分别计算左右节点之和，最后加根节点 *******
     *
     * @param node
     * @return
     */
    private int getSize(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSize = getSize(node.getLeftChild());
        int rightSize = getSize(node.getRightChild());

        return leftSize + rightSize +1;
    }


    /**
     * 二叉树  前序优先遍历 【递归！！！】
     * @param node  根节点
     */
    private void beforeOrder(TreeNode node){
        if (node == null){
            return;
        }

        System.out.println(node.getData());
        beforeOrder(node.getLeftChild());
        beforeOrder(node.getRightChild());
    }

    /**
     * 二叉树  中序优先遍历 【递归！！！】
     * @param node  根节点
     */
    private void middleOrder(TreeNode node){
        if (node == null){
            return;
        }
        middleOrder(node.getLeftChild());
        System.out.println(node.getData());
        middleOrder(node.getRightChild());
    }


    /**
     * 二叉树  后序优先遍历 【递归！！！】
     * @param node  根节点
     */
    private void afterOrder(TreeNode node){
        if (node == null){
            return;
        }
        afterOrder(node.getLeftChild());
        afterOrder(node.getRightChild());
        System.out.println(node.getData());
    }


    /**
     * 前序优先遍历 （非递归 ！！！！）
     * @param node
     */
    private void beforeOrderNonRecursively(TreeNode node){
        if (node == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()){
            node = stack.pop();
            System.out.println(node.getData());
            if (node.getRightChild()!=null){
                stack.push(node.getRightChild());
            }

            if (node.getLeftChild()!=null){
                stack.push(node.getLeftChild());
            }
        }
    }
}

// 节点
class TreeNode {
    private int index;  // 角标
    private Object data;   // 数据
    private TreeNode leftChild;   // 左节点
    private TreeNode rightChild;   // 右节点

    public TreeNode() {}

    public TreeNode(int index, Object data) {
        this.index = index;
        this.data = data;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }
}


