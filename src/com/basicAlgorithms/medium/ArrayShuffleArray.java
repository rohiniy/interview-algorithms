/**
 * Shuffle a set of numbers without duplicates.
 *
 * Example:
 *
 * // Init an array with set 1, 2, and 3.
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally
 * likely to be returned.
 * solution.shuffle();
 *
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 *
 * // Returns the random shuffling of array [1,2,3].
 * solution.shuffle();
 *
 * SOLUTION
 * reset() strore the original array
 *
 * shuffle()
 */
package com.basicAlgorithms.medium;

import java.util.Random;

public class ArrayShuffleArray {
  int originalArray[];

  public ArrayShuffleArray(int[] nums) {
    originalArray = new int[nums.length];
    originalArray = nums.clone();
  }

  /** Resets the array to its original configuration and return it. */
  public int[] reset() {
    return originalArray;
  }

  /** Returns a random shuffling of the array. */
  public int[] shuffle() {
    int[] shuffledArray = originalArray.clone();
    Random random = new Random();
    // use Fisher yates algorithm
    for (int i=shuffledArray.length-1; i > 0; i--) {
      // generate a random index from the array len that you are considering
      int randIndex = random.nextInt(i+1);
      // now swap the current index with this randomly generated index
      if (i!=randIndex) {
        swap(shuffledArray, i, randIndex);
      }
    }
    return shuffledArray;
  }

  private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String args[]) {
    ArrayShuffleArray obj = new ArrayShuffleArray(new int[]{1, 2, 3});
    obj.shuffle();
  }
}
