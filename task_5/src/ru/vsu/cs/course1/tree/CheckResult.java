package ru.vsu.cs.course1.tree;

import java.util.List;

public class CheckResult<T> {
    private int size;
    private T max;
    private T min;
    private boolean isBST;
    private List<BinaryTree.TreeNode<T>> nodeList;
    public CheckResult(int size, T max, T min, boolean isBST, List<BinaryTree.TreeNode<T>> nodeList) {
        this.size = size;
        this.max = max;
        this.min = min;
        this.isBST = isBST;
        this.nodeList = nodeList;
    }
    public CheckResult(List<BinaryTree.TreeNode<T>> nodeList, boolean isBST) {
        this.nodeList = nodeList;
        this.isBST = isBST;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public boolean isBST() {
        return isBST;
    }

    public void setBST(boolean BST) {
        isBST = BST;
    }

    public List<BinaryTree.TreeNode<T>> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<BinaryTree.TreeNode<T>> nodeList) {
        this.nodeList = nodeList;
    }
}
