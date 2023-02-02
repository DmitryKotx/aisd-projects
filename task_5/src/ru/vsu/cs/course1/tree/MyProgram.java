package ru.vsu.cs.course1.tree;

import java.util.ArrayList;

public class MyProgram {
    //44 (17 (18 (10 (11 (, 12 (2))), 15), 26 (22, 29)), 58 (59 (, 60), 94 (89 (90 (, 81 (65, 84))))))
    //8 (6 (4 (3,6), 6(2,3)), 5 (2(1,3), 5 (2, 8)))
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }
    public static <T extends Comparable<T>> T min(T a, T b) {
        return a.compareTo(b) < 0 ? a : b;
    }
    public static <T extends Comparable<T>> CheckResult<T> MaxSubTreeSearch(BinaryTree.TreeNode<T> treeNode) {
        if (treeNode == null) {
            return new CheckResult<>(0, null, null, true, new ArrayList<>());
        }
        if (treeNode.getRight() == null && treeNode.getLeft() == null) {
            return new CheckResult<>(1, treeNode.getValue(), treeNode.getValue(), true, new ArrayList<>());
        }
        CheckResult<T> leftSubTree = MaxSubTreeSearch(treeNode.getLeft());
        CheckResult<T> rightSubTree = MaxSubTreeSearch(treeNode.getRight());
        CheckResult<T> answer = new CheckResult<>(new ArrayList<>(), true);
        answer.setSize(leftSubTree.getSize() + 1 + rightSubTree.getSize());
        if (treeNode.getRight() == null) {
            answer.setBST(leftSubTree.getMax().compareTo(treeNode.getValue()) < 0 && leftSubTree.isBST());
            if (answer.isBST())
                answer.getNodeList().add(treeNode);
            answer = answer.isBST() ? answer : leftSubTree;
            answer.setBST(leftSubTree.getMax().compareTo(treeNode.getValue()) < 0 && leftSubTree.isBST());
            answer.setMin(min(leftSubTree.getMin(), treeNode.getValue()));
            answer.setMax(max(leftSubTree.getMax(), treeNode.getValue()));
            return answer;
        } else if (treeNode.getLeft() == null) {
            answer.setBST(rightSubTree.getMin().compareTo(treeNode.getValue()) > 0 && rightSubTree.isBST());
            if (answer.isBST())
                answer.getNodeList().add(treeNode);
            answer = answer.isBST() ? answer : rightSubTree;
            answer.setBST(rightSubTree.getMin().compareTo(treeNode.getValue()) > 0 && rightSubTree.isBST());
            answer.setMin(min(rightSubTree.getMin(), treeNode.getValue()));
            answer.setMax(max(rightSubTree.getMax(), treeNode.getValue()));
            return answer;
        } else {
            if (leftSubTree.getMax().compareTo(treeNode.getValue()) < 0 &&
                    rightSubTree.getMin().compareTo(treeNode.getValue()) > 0 && rightSubTree.isBST() && leftSubTree.isBST()) {
                answer.getNodeList().add(treeNode);
            } else {
                answer = leftSubTree.getSize() > rightSubTree.getSize() ? leftSubTree : rightSubTree;
                if (leftSubTree.getSize() == rightSubTree.getSize())
                    answer.getNodeList().addAll(leftSubTree.getNodeList());
                answer.setBST(false);
            }
            answer.setMin(min(min(leftSubTree.getMin(), rightSubTree.getMin()), treeNode.getValue()));
            answer.setMax(max(max(leftSubTree.getMax(), rightSubTree.getMax()), treeNode.getValue()));
            return answer;
        }
    }
}