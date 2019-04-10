package com.datastructure.binarySearchTree;

public class TreeNode {
  private int data;
  private TreeNode leftChild;
  private TreeNode rightChild;

  public TreeNode(int data) {
    this.data = data;
  }

  public int getData() {
    return this.data;
  }

  public TreeNode getLeftChild() {
    return this.leftChild;
  }

  public TreeNode getRightChild() {
    return this.rightChild;
  }

  public void setLeftChild(TreeNode node) {
    this.leftChild = node;
  }

  public void setRightChild(TreeNode node) {
    this.rightChild = node;
  }

  public TreeNode search(int data) {
    if (data == this.data) {
      return this;
    }

    if (data < this.data && this.leftChild != null){
      return this.leftChild.search(data);
    }

    if (this.rightChild != null){
      return this.rightChild.search(data);
    }
    return null;
  }

  public void insert(int data) {
    if (data <= this.data) {
      // go to left child
      if (this.leftChild == null) {
        this.leftChild = new TreeNode(data);
      }
      else {
        this.leftChild.insert(data);
      }
    }
    else {
      if (this.rightChild == null) {
        this.rightChild = new TreeNode(data);
      }
      else {
        this.rightChild.insert(data);
      }
    }
  }
}
