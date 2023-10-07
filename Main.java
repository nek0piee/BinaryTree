package com.mycompany.treenode;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        BinaryTree binaryTree = new BinaryTree();
        Random random = new Random();

        int[] testSizes = {100, 500, 1000, 10000, 20000};

        for (int size : testSizes) {
            System.out.println("Testing with " + size + " random elements:");

            // Inserção
            long startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                int value = random.nextInt(100000); // Valores aleatórios de 0 a 99999
                avlTree.insert(value);
                binaryTree.insert(value);
            }
            long avlInsertTime = System.nanoTime() - startTime;

            // Busca
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                int value = random.nextInt(100000);
                avlTree.search(value);
            }
            long avlSearchTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                int value = random.nextInt(100000);
                binaryTree.search(value);
            }
            long binarySearchTime = System.nanoTime() - startTime;

            // Remoção
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                int value = random.nextInt(100000);
                avlTree.remove(value);
            }
            long avlRemoveTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                int value = random.nextInt(100000);
                binaryTree.remove(value);
            }
            long binaryRemoveTime = System.nanoTime() - startTime;

            System.out.println("AVL Tree - Insertion Time: " + avlInsertTime + " ns");
            System.out.println("Binary Search Tree - Insertion Time: " + avlInsertTime + " ns");
            System.out.println("AVL Tree - Search Time: " + avlSearchTime + " ns");
            System.out.println("Binary Search Tree - Search Time: " + binarySearchTime + " ns");
            System.out.println("AVL Tree - Removal Time: " + avlRemoveTime + " ns");
            System.out.println("Binary Search Tree - Removal Time: " + binaryRemoveTime + " ns");
            System.out.println();
        }
    }
}

