/**
 * Check if a given sequence of moves for a robot is circular or not
 * Given a sequence of moves for a robot, check if the sequence is circular or not. A sequence of moves is circular if first and last positions of robot are same. A move can be on of the following.
 *
 *   G - Go one unit
 *   L - Turn left
 *   R - Turn right
 * Examples:
 *
 * Input: path[] = "GLGLGLG"
 * Output: Given sequence of moves is circular
 *
 * Input: path[] = "GLLG"
 * Output: Given sequence of moves is circular
 */


/**
 * Solution:
 *        N
 *        |
 *        |
 *  W------------ E
 *        |
 *        |
 *        S
 *
 * Possible movements of the robot
 * N=0, E=1, S=2, W=3
 * If robot is facing
 * 1. N, L -> W(0->3) and R->E (0->1) , G->change y
 * 2. E, L ->N (1->0), R->S (1->2), G-> change x
 * 3. S, L->E (2->1), R-> W (2->3), G-> change y
 * 4. W, L->S (4->3), R->N(3->0), G-> change x
 *
 * Changing to R is (Dir. No. +1)%4
 * Changing to L is (Dir no. + 4 -1)%4
 *
 */

package com.basicAlgorithms;

public class RobotMotions {
  public static boolean isCircularMotion(char path[]) {
    // lets start with facing N (0, 0)
    // N=0, E=1, S=2, W=3
    int currentDirection = 0; // N
    int x = 0;
    int y = 0;
    for (int i = 0; i<path.length; i++) {
      switch (path[i]) {
        case 'G':
          if (currentDirection == 0) {
            // change y
            y += 1;
          }
          else if (currentDirection == 2) {
            y -= 1;
          }
          else if (currentDirection == 1){
            // change x
            x += 1;
          }
          else {
            x -= 1;
          }
          break;

        case 'R':
//          if (currentDirection == 'N') {
//            // change y
//            currentDirection = 'E';
//          }
//          else if (currentDirection == 'S'){
//            currentDirection = 'W';
//          }
//          else if (currentDirection == 'E') {
//            currentDirection = 'S';
//          }else {
//            currentDirection = 'N';
//          }
            currentDirection = (currentDirection + 1)%4;
          break;

        case 'L':
//          if (currentDirection == 'N') {
//            // change y
//            currentDirection = 'W';
//          }
//          else if (currentDirection == 'S'){
//            currentDirection = 'E';
//          }
//          else if (currentDirection == 'E') {
//            currentDirection = 'N';
//          }else {
//            currentDirection = 'S';
//          }
            currentDirection = (currentDirection +4 -1)%4;
          break;
      }
    }

    return (x ==0 && y ==0);
  }

  public static void main(String args[]) {
    String str = "GLGLGLG";
    char[] path = str.toCharArray();
    System.out.println(isCircularMotion(path));
  }
}
