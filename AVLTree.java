package com.mycompany.treenode;

public class AVLTree {
    TreeNode root;

    public AVLTree() {
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
        } else {
            // Valor já existe na árvore, não faz nada
            return root;
        }

        // Atualizar a altura do nó atual
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Realizar o balanceamento
        int balance = getBalance(root);

        // Casos de rotação
        if (balance > 1 && value < root.left.val) {
            return rightRotate(root);
        }
        if (balance < -1 && value > root.right.val) {
            return leftRotate(root);
        }
        if (balance > 1 && value > root.left.val) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && value < root.right.val) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
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
            // Nó com apenas um filho ou nenhum filho
            if ((root.left == null) || (root.right == null)) {
                TreeNode temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                // Caso sem filho
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    // Caso de um filho
                    root = temp;
                }
            } else {
                // Nó com dois filhos: obter o sucessor in-order (menor na subárvore direita)
                TreeNode temp = minValueNode(root.right);

                // Copiar o conteúdo do sucessor para este nó
                root.val = temp.val;

                // Remover o sucessor
                root.right = removeRec(root.right, temp.val);
            }
        }

        if (root == null) {
            return root;
        }

        // Atualizar a altura do nó atual
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Realizar o balanceamento
        int balance = getBalance(root);

        // Casos de rotação
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        // Atualizar as alturas após a rotação
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        // Atualizar as alturas após a rotação
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }
}
