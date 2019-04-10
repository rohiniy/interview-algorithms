/**
 *    1   3   5   8
 *    4   2   1   7
 *    4   1   2   3
 *    10  1   1   4
 *
 *    1   4   9   17
 *    5   6   7   15
 *    9   7   9   12
 *    19  8   9   13
 *
 *    Min sum path = 13
 *    Path = 4, 1, 1, 1, 2, 3, 1
 *
 *    00, nn = Min(sum along the path)
 *    right, down
 */
package com.basicAlgorithms.dynamicPrograms;

public class MinimumPathSumInMatrix {
  public static int minPathSum(int[][] grid) {
    // Dynamic Programming
    //Prefix sum for 0th row and 0th col
    int m = grid.length-1;
    int n = grid[0].length-1;

    for (int i= 1; i<= n; i++) { // O(n)
      // 0 th row and all columns
      grid[0][i] += grid[0][i - 1];
    }

    for (int i= 1; i<= m; i++) { //O(n)
      // 0 th col and all rows
      grid[i][0] += grid[i-1][0];
    }

    for (int i=1; i<= m; i++) { // O(n2)
      for (int j=1; j<= n; j++) {
        grid[i][j] += Math.min(grid[i][j-1], grid[i-1][j]);
      }
    }


    return grid[m][n];
  }

  public static void main(String args[]) {
    int [][]grid = {{1,3,1},{1,5,1},{4,2,1}};
    int sum = minPathSum(grid);
    System.out.println(sum);
  }
}
