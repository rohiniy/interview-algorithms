/**
 * You are given an n x n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 *
 * Note:
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 * Example 1:
 *
 * Given input matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * Example 2:
 *
 * Given input matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 * Solution:
 *
 */
package com.basicAlgorithms.arrays;

public class RotateImage {

  public static void rotateImageWithTempArray(int matrix[][]) {
    int n = matrix.length;
    int m = matrix[0].length;
    int temp[] = new int[n*m];
    int k = 0;

    for (int i=0; i<n; i++) {
      for (int j=0; j<m;j++) {
        temp[k++] = matrix[i][j];
      }
    }

    k = 0;
    for (int i = n-1; i>=0; i--) {
      for (int j = 0; j<m;j++) {
        matrix[j][i] = temp[k++];
      }
    }
  }

  public static void main(String args[]) {
    int matrix[][] = new int[3][3];
    int val = 1;
    for (int i=0; i< matrix.length; i++) {
      for (int j = 0; j<matrix[0].length;j++) {
        matrix[i][j] = val++;
      }
    }

    System.out.println("Original Matrix::");
    printMatrix(matrix);
    rotateImageWithTempArray(matrix);
    System.out.println("Rotated Matrix::");
    printMatrix(matrix);

  }

  public static void printMatrix (int[][] matrix) {
    for (int i=0; i< matrix.length; i++) {
      System.out.println();
      for (int j = 0; j<matrix[0].length; j++) {
        System.out.print(matrix[i][j] + ", ");
      }
    }
  }
}