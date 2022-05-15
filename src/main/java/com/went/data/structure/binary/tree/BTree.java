package com.went.data.structure.binary.tree;

// 二叉树 (Binary Tree)
public interface BTree {
    // 插入节点
    void insert(BinaryTreeNode node);

    // 删除节点
    void delete(BinaryTreeNode node);

    // 前序遍历
    default void preOrder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.getValue() + " ");
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    // 中序遍历
    default void infixOrder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        infixOrder(root.getLeft());
        System.out.print(root.getValue() + " ");
        infixOrder(root.getRight());
    }

    // 后序遍历
    default void postOrder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.getValue() + " ");
    }

}
