package com.company;

public class Node implements Cloneable{
    int data;
    int height = 0;
    Node left, right, parent;
    boolean rotate = true;
    boolean ignore = false;
    int binary = 0;
    int leftInt;
    int rightInt;

    Node(int data)
    {
        this.data = data;
        this.left = null;
        this.right = null;
    }
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}