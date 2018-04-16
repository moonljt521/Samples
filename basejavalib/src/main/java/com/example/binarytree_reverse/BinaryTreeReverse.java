package com.example.binarytree_reverse;

import javax.swing.tree.TreeNode;

/**
 * author: moon
 * created on: 18/2/24 下午5:21
 * description:  翻转一颗二叉树
 */
public class BinaryTreeReverse {

    public static void main(String[] args) {


        TreeNode root = new TreeNode();
        root.value = 1;

        TreeNode left = new TreeNode();
        left.value = 2;

        TreeNode right = new TreeNode();
        right.value = 3;

        root.left = left;
        root.right = right;

    }



    public static class TreeNode {

        public TreeNode left;

        public TreeNode right;

        int value;

    }

    /**
     *
     * @param root
     * @return
     */
    public TreeNode reverseBinaryTree(TreeNode root){
        //先处理base case，当root ==null 时，什么都不需要做,返回空指针
        if(root == null){
            return null;
        }
        else{
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


}
