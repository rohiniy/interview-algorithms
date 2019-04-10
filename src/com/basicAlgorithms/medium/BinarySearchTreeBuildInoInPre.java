package com.basicAlgorithms.medium;

import java.util.HashMap;

public class BinarySearchTreeBuildInoInPre {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    HashMap<Integer, Integer> inorderMap = storeInorderInMap(inorder);
    // preorder starts with root it will have root in the 1st position
    return buildBST(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, inorderMap);
  }

  private TreeNode buildBST(int[] preorder, int preS, int preE,
                            int[] inOrder, int inS, int inE,
                            HashMap<Integer, Integer> inorderMap) {
    if (preS > preE) {return null;}
    if (inS > inE) {
      return null;
    }

    // root is 1st
    TreeNode root = new TreeNode(preorder[preS]);
    // search the root in inOrder
    int rootIndex = inorderMap.get(root.val);

    // left of root in inorder is left tree
    int leftSize = rootIndex - inS;

    root.left = buildBST(preorder, preS+1, preS + leftSize,
        inOrder, inS, rootIndex-1, inorderMap);

    root.right = buildBST(preorder, preS+leftSize+1, preE,
        inOrder, rootIndex+1, inE, inorderMap);

    return root;
  }
  private HashMap<Integer, Integer> storeInorderInMap(int[] inorder) {
    HashMap<Integer, Integer> inorderMap = new HashMap<>();
    for (int i=0; i< inorder.length;i++) {
      inorderMap.put(inorder[i], i);
    }
    return inorderMap;
  }



  public static void main(String args[]) {
    int inorder[] = {3,9,20,15,7};
    int preorder[] = {9,3,15,20,7};
    BinarySearchTreeBuildInoInPre obj = new BinarySearchTreeBuildInoInPre();

    obj.buildTree(inorder, preorder);
  }
}
