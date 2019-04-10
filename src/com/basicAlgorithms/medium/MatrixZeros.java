/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 *
 * Example 1:
 *
 * Input:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * Output:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * Example 2:
 *
 * Input:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * Output:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * Follow up:
 *
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 *
 *
 * Solution:
 * 1. Boolean array for rows, and columns of respective size and then marking the ith row true if it is 0.
 * Then traverse the boolean array and then mark that complete row 0. Similarly traverse the column boolean
 * array then mark it 0
 * 2. If you get 0 then mark the 1st element in that row and 1st element of that column as 0
 * Then traverse the rows and see which is 1st element is 0 then mark the complete row = 0
 * Then traverse columns and mark 0 for the column which has 1st element 0
 */
package com.basicAlgorithms.medium;

public class MatrixZeros {

  public static void setZeros(int[][] matrix) {

    boolean isRow0 = false;
    boolean isCol0 = false;
    // set the 1st element in the row and col as 0 if there is 0
    for (int i=0; i< matrix.length; i++) {
      for (int j = 0; j< matrix[0].length;j++) {
          if (matrix[i][j] == 0) {
            matrix[0][j] = 0;
            matrix[i][0] = 0;
          }
      }
    }

    // check if the 0th row has any 0 elements then mark row0 = true
    for (int i=0; i<matrix.length; i++) {
      if(matrix[0][i] == 0) {
        isRow0 = true;
        break;
      }
    }

    for(int i=0; i<matrix[0].length;i++) {
      if(matrix[i][0] == 0) {
        isCol0 = true;
        break;
      }
    }

    // for each row if the 0th element = 0, then mark the row= 0
    for (int i=1; i< matrix.length; i++) {
      // for each row if the 0th col is 0 then mark all row 0
      if (matrix[i][0] == 0) {
        for (int j = 1; j<matrix[0].length;j++) {
          // mark the complete row as 0
          matrix[i][j] = 0; // i is same and j is incremented

        }
      }
    }

    // for each col if the 0th element
    for (int i=1; i< matrix[0].length; i++) {
      // for each column if the 0th row is 0 then
      if (matrix[0][i] == 0) {
        for (int j = 1; j<matrix.length;j++) {
          // mark the complete col as 0
          matrix[j][i] = 0; // i is same - same col and row is incrementing
        }
      }
    }

    if (isRow0) {
      for (int i=0;i<matrix[0].length;i++) {
        // mark the complete 0th row as 0
        matrix[0][i] = 0;
      }
    }

    if (isCol0) {
      for (int i=0;i<matrix.length;i++) {
        // mark the complete 0th row as 0
        matrix[i][0] = 0;
      }
    }
  }

//  public static void setZerosCorrect(int [][] matrix) {
//    if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
//      return;
//    }
//
//    // check if the first row/ column has a zero
//    boolean rowZeroHasZero = false;
//    boolean colZeroHasZero = false;
//
//    for (int i = 0; i < matrix.length; i++){
//      if (matrix[i][0] == 0){
//        colZeroHasZero = true;
//        break;
//      }
//    }
//    for (int i = 0; i < matrix[0].length; i++){
//      if (matrix[0][i] == 0){
//        rowZeroHasZero = true;
//        break;
//      }
//    }
//
//    // mark the first row/ column zero if the element is zero
//    for (int i = 0; i < matrix.length; i++){
//      for (int j = 1; j < matrix[0].length; j++){
//        if (matrix[i][j] == 0){
//          matrix[i][0] = matrix[0][j] = 0;
//        }
//      }
//    }
//
//    // zero the column/ rows if the first column/ row is zero
////    for (int i = 1; i < matrix.length; i++){
////      if (matrix[i][0] == 0){
////        for (int j = 1; j < matrix[0].length; j++){
////          matrix[i][j] = 0;
////        }
////      }
////    }
////    for (int i = 1; i < matrix[0].length; i++){
////      if (matrix[0][i] == 0){
////        for (int j = 1; j < matrix.length; j++){
////          matrix[j][i] = 0;
////        }
////      }
////    }
//
//    // zero the first column/ rows if they had zeros
//    if (rowZeroHasZero){
//      for (int i = 0; i < matrix[0].length; i++){
//        matrix[0][i] = 0;
//      }
//    }
//    if (colZeroHasZero){
//      for (int i = 0; i < matrix.length; i++){
//        matrix[i][0] = 0;
//      }
//    }
//  }

  public static void main(String args[]) {
    int row = 4;
    int col = 4;
    int count = 12;
    int matrix[][] = new int[row][col];

    for (int i = 0; i<row; i++) {
      for (int j=0; j<col; j++) {
        matrix[i][j] = count++;
      }
    }

    matrix[0][0] = 0;
    matrix[0][3] = 0;
    setZeros(matrix);
    printMatrix(matrix);
  }

  public static void printMatrix(int[][] matrix) {
    for (int i=0;i< matrix.length; i++) {
      System.out.println();
      for (int j =0; j< matrix[0].length; j++) {
        System.out.print(" " +matrix[i][j] +  " ");
      }
    }
  }


}
