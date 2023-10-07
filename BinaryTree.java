package com.mycompany.treenode;

public class BinaryTree {
    TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }

        if (value < root.val) {
            root.left = insertRec(root.left, value);
        } else if (value > root.val) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    public boolean search(int value) {
        return searchRec(root, value);
    }

    private boolean searchRec(TreeNode root, int value) {
        if (root == null) {
            return false;
        }

        if (value == root.val) {
            return true;
        } else if (value < root.val) {
            return searchRec(root.left, value);
        } else {
            return searchRec(root.right, value);
        }
    }

    public void remove(int value) {
        root = removeRec(root, value);
    }

    private TreeNode removeRec(TreeNode root, int value) {
        if (root == null) {
            return root;
        }

        if (value < root.val) {
            root.left = removeRec(root.left, value);
        } else if (value > root.val) {
            root.right = removeRec(root.right, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.val = minValue(root.right);
            root.right = removeRec(root.right, root.val);
        }

        return root;
    }

    private int minValue(TreeNode node) {
        int minValue = node.val;
        while (node.left != null) {
            minValue = node.left.val;
            node = node.left;
        }
        return minValue;
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }
}

