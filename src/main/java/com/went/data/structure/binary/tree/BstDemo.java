package com.went.data.structure.binary.tree;

import java.util.Arrays;
import java.util.List;

public class BstDemo {

    public static void main(String[] args) {

        // 构建二叉搜索树
        Bst bst = new Bst(new BinaryTreeNode<>(8));
        List<Integer> list = Arrays.asList(7, 3, 10, 12, 5, 9);
        for (Integer i : list) {
            bst.insert(new BinaryTreeNode(i));
        }
        bst.deleteOnlyNode(new BinaryTreeNode(7));
        bst.print();
    }
}

// 二叉排序树是一种特殊的二叉树，它的每个节点都存储一个非负整数，并且每个节点的值都比它的左子树中所有节点的值大，
// 它的右子树中所有节点的值都比它的值大。
// 二叉排序树的特点：
// 1. 每个节点的值都比它的左子树中所有节点的值大，它的右子树中所有节点的值都比它的值大。
// 2. 每个节点的左子树和右子树都是二叉排序树。
class Bst implements BTree {
    private BinaryTreeNode root;

    public Bst(BinaryTreeNode root) {
        this.root = root;
    }

    @Override
    public BinaryTreeNode getRoot() {
        return root;
    }

    @Override
    public void insert(BinaryTreeNode node) {

        //insertByRecursive(root, node);

        insertByLoop(node);
    }

    public void print() {
        System.out.println("中序遍历：");
        infixOrder(root);
    }

    // 循环增加节点
    private void insertByLoop(BinaryTreeNode node) {
        if (root == null) {
            root = node;
            return;
        }
        BinaryTreeNode cur = root;
        BinaryTreeNode parent = null;
        while (cur != null) {
            parent = cur;
            if (node.getValue().compareTo(parent.getValue()) < 0) {
                cur = cur.getLeft();
            } else {
                cur = cur.getRight();
            }
        }
        if (node.getValue().compareTo(parent.getValue()) < 0) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
    }

    // 递归增加节点
    private void insertByRecursive(BinaryTreeNode parent, BinaryTreeNode node) {
        if (root == null) {
            root = node;
            return;
        }
        if (node.getValue().compareTo(parent.getValue()) > 0) {
            if (parent.getRight() != null) {
                parent = parent.getRight();
                insertByRecursive(parent, node);
            } else {
                parent.setRight(node);
            }
        } else {
            if (parent.getLeft() != null) {
                parent = parent.getLeft();
                insertByRecursive(parent, node);
            } else {
                parent.setLeft(node);
            }
        }
    }

}

