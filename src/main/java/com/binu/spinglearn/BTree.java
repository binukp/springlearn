package com.binu.spinglearn;

import java.util.Arrays;

public class BTree {

    //Represent a node of binary tree
    public static class Node{
        int data;
        Node left,right;


        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(int data){
            //Assign data to the new node, set left and right children to null
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    //Represent the root of binary tree
    public Node root;

    int[] treeArray;
    int index = 0;

    public BTree(){
        root = null;
    }

    //convertBTBST() will convert a binary tree to binary search tree
    public Node convertBTBST(Node node) {

        //Variable treeSize will hold size of tree
        int treeSize = calculateSize(node);
        treeArray = new int[treeSize];

        //Converts binary tree to array
        convertBTtoArray(node);

        //Sort treeArray
        Arrays.sort(treeArray);

        //Converts array to binary search tree
        Node d = createBST(0, treeArray.length -1);
        return d;
    }

    //calculateSize() will calculate size of tree
    public int calculateSize(Node node)
    {
        int size = 0;
        if (node == null)
            return 0;
        else {
            size = calculateSize (node.left) + calculateSize (node.right) + 1;
            return size;
        }
    }

    //convertBTtoArray() will convert the given binary tree to its corresponding array representation
    public void convertBTtoArray(Node node) {
        //Check whether tree is empty
        if(root == null){
            System.out.println("Tree is empty");
            return;
        }
        else {
            if(node.left != null)
                convertBTtoArray(node.left);
            //Adds nodes of binary tree to treeArray
            treeArray[index] = node.data;
            index++;
            if(node.right != null)
                convertBTtoArray(node.right);
        }
    }

    //createBST() will convert array to binary search tree
    public Node createBST(int start, int end) {

        //It will avoid overflow
        if (start > end) {
            return null;
        }

        //Variable will store middle element of array and make it root of binary search tree
        int mid = (start + end) / 2;
        Node node = new Node(treeArray[mid]);

        //Construct left subtree
        node.left = createBST(start, mid - 1);

        //Construct right subtree
        node.right = createBST(mid + 1, end);

        return node;
    }

    //inorder() will perform inorder traversal on binary search tree
    public void inorderTraversal(Node node) {

        //Check whether tree is empty
        if(root == null){
            System.out.println("Tree is empty");
            return;
        }
        else {
            if(node.left!= null)
                inorderTraversal(node.left);
            System.out.print(node.data + " ");
            if(node.right!= null)
                inorderTraversal(node.right);
        }
    }

    public static void main(String[] args) {

        BTree bt = new BTree();

        // let's create a binary tree
        Node n10 = new Node(10);
        Node n34 = new Node(34);
        Node n43 = new Node(43);
        Node n78 = new Node(78);

        Node n25 = new Node(25, n10, n34);
        Node n65 = new Node(65, n43, n78);
        Node root = new Node(50, n25, n65);

        //Add nodes to the binary tree
        bt.root = root;

        //Display given binary tree
        System.out.println("Inorder representation of binary tree: ");
        bt.inorderTraversal(bt.root);

        //Converts binary tree to corresponding binary search tree
        Node bst = bt.convertBTBST(bt.root);

        //Display corresponding binary search tree
        System.out.println("\nInorder representation of resulting binary search tree: ");
        bt.inorderTraversal(bst);
    }
}