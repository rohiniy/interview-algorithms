/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 *
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 *
 *
 * SOLUTION:
 *                 1
 * DFS to check  1 1 1
 *                 1
 *
 * the center 1 need to check if it has 1 up, left, right or down and make it all 0 and make numOfIslands++
 * Go ahead and search for next 1 and increment numOfIslands
 */
package com.basicAlgorithms.medium;

public class MAtrixNoOfIslands {
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int numIslands = 0;
    int r = grid.length;
    int c = grid[0].length;

    for (int i =0; i<r; ++i) {
      for (int j=0; j< c; ++j) {
        if (grid[i][j] == '1') {
          ++numIslands;
          dfs(grid, i, j);
        }
      }
    }

    return numIslands;
  }

  private void dfs(char[][] grid, int r, int c) {
    if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
      // ignore
      return;
    }

    grid[r][c] = '0'; // mark it 0 as visited
    dfs(grid, r-1, c);
    dfs(grid, r+1, c);
    dfs(grid, r, c-1);
    dfs(grid, r, c+1);

  }
}
