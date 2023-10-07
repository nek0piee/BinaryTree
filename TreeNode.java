package com.mycompany.treenode;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    int height;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}
