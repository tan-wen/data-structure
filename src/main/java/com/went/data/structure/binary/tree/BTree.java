package com.went.data.structure.binary.tree;

// 二叉树 (Binary Tree)
public interface BTree {

    // 获取根节点
    BinaryTreeNode getRoot();

    // 插入节点
    void insert(BinaryTreeNode node);

    // 删除节点
    default void deleteOnlyNode(BinaryTreeNode node) {
        // 找到指定节点及其父节点
        BinaryTreeNode parent = null;
        Boolean isLeft = null;
        BinaryTreeNode cur = getRoot();
        while (cur != null) {
            if (cur.getValue().compareTo(node.getValue()) == 0) {
                break;
            }
            parent = cur;
            if (cur.getValue().compareTo(node.getValue()) < 0) {
                cur = cur.getRight();
                isLeft = false;
            } else {
                cur = cur.getLeft();
                isLeft = true;
            }
        }
        // 如果该节点为叶子节点，则直接删除；
        if (parent != null && cur.isLeaf()) {
            if (isLeft) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (cur.getLeft() != null && cur.getRight() != null) {
            // 如果该节点有两个叶子节点，则删除该节点，并将该节点的右子树的最小节点替换该节点
            BinaryTreeNode minNode = getMinNodeFrom(cur);
            cur.setValue(minNode.getValue());
            if (cur.getRight() == minNode) {
                cur.setRight(minNode.getRight());
            } else {
                cur.setLeft(minNode.getRight());
            }
        } else {
            // 如果该节点有一个叶子节点，则将该叶子节点替换该节点；
            if (isLeft) {
                parent.setLeft(cur.getLeft() != null ? cur.getLeft() : cur.getRight());
            } else {
                parent.setRight(cur.getLeft() != null ? cur.getLeft() : cur.getRight());
            }
        }
    }

    default BinaryTreeNode getMinNodeFrom(BinaryTreeNode root) {
        BinaryTreeNode cur = root;
        BinaryTreeNode parent = null;
        while (cur.getLeft() != null) {
            parent = cur;
            cur = cur.getLeft();
        }
        cur.setParent(parent);
        return cur;
    }

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
