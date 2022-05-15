package com.went.data.structure.binary.tree;

public class BinaryTreeNode<T extends Comparable> {
    private T value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(T data) {
        this.value = data;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}
