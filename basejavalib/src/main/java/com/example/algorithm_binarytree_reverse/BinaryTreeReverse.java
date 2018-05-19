package com.example.algorithm_binarytree_reverse;

import java.util.Stack;

/**
 * author: moon
 * created on: 18/2/24 下午5:21
 * description:  翻转一颗二叉树
 */
public class BinaryTreeReverse {

    public static void main(String[] args) {

        TreeNode root = new TreeNode();
        root.value = 1;

        TreeNode a = new TreeNode();
        a.value = 2;

        TreeNode b = new TreeNode();
        b.value = 3;

        root.left = a;
        root.right = b;

        TreeNode c = new TreeNode();
        c.value = 5;

        TreeNode d = new TreeNode();
        d.value = 4;


        b.left = c;
        b.right = d;

        printTree(root);


        System.out.println("--------------");

        printTree(reverseBinaryTree(root));
//        printTree(reverseBinaryTreeNotRecursion(root));
    }

    /**
     * 打印这颗二叉树
     *
     * @param node
     */
    public static void printTree(TreeNode node) {
        if (node != null) {
            System.out.println(node.value);

            printTree(node.left);
            printTree(node.right);
        }
    }

    private static class TreeNode {

        public TreeNode left;

        public TreeNode right;

        int value;
    }

    /**
     * 递归方式
     *
     * @param root
     * @return
     */
    public static TreeNode reverseBinaryTree(TreeNode root) {
        //先处理base case，当root ==null 时，什么都不需要做,返回空指针
        if (root == null) {
            return null;
        } else {
            //把左子树翻转
            TreeNode left = reverseBinaryTree(root.left);
            //把右子树翻转
            TreeNode right = reverseBinaryTree(root.right);
            //把左右子树分别赋值给root节点，但是是翻转过来的顺序
            root.left = right;
            root.right = left;
            //返回根节点
            return root;
        }
    }

    /**
     * 非递归方式
     *
     * @param root
     * @return
     */
    public static TreeNode reverseBinaryTreeNotRecursion(TreeNode root) {
        //先处理base case，当root ==null 时，什么都不需要做,返回空指针
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {

            TreeNode n = stack.pop();

            TreeNode left = n.left;
            TreeNode right = n.right;

            n.right = left;
            if (n.right !=null){
                stack.push(n.right);
            }

            n.left = right;
            if (n.left !=null){
                stack.push(n.left);
            }

        }

        return root;
    }
}
