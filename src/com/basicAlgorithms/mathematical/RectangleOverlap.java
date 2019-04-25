package com.basicAlgorithms.mathematical;

public class RectangleOverlap {
  public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
    // 0  1  2  3
    // bx by tx ty
    // cal x distance
    int xDistance = distance(rec1[0], rec1[2],rec2[0], rec2[2] );
    if (xDistance <= 0) {
      return false;
    }

    // cal y distance
    int yDistance = distance(rec1[1], rec1[3],rec2[1], rec2[3] );
    if (yDistance <= 0) {
      return false;
    }

    // to calculate the area we can simply multiply the xDistance and yDistance

    return true;
  }

  public int distance(int R1bxy, int R1txy, int R2bxy, int R2txy) {
    return Math.min(R1txy, R2txy) - Math.max(R1bxy, R2bxy);
  }
}
