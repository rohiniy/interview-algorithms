/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d
 * in nums such that a + b + c + d = target? Find all unique quadruplets in the array which
 * gives the sum of target.
 *
 * Note:
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 *
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
package com.basicAlgorithms.medium;

import java.util.*;

public class Array4Sum {
  public static List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Arrays.sort(nums);

    if (nums.length == 4) {
      if (nums[0] + nums[1] + nums[2] + nums[3] == target) {

        result.add(Arrays.asList(nums[0], nums[1], nums[2], nums[3]));
        return result;
      }
    }

    for (int i =0; i< nums.length-3; i++) {
      if (nums[i] > 0 && nums[i] > target) {
        return result;
      }
      if (nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) {
        break;
      }
      if (nums[i] + nums[nums.length-1] + nums[nums.length-2] + nums[nums.length-3] < target) {
        // increment i as it is less
        continue;
      }
      if (i > 0 && nums[i] != nums[i-1]) {
        // if same then
        continue;
      }
        // x + y + z + k = target
        // x + y + z = target - k
        // fix k
        int threeSum = target - nums[i];

        // 3 sum
        for (int j = i+1; j<nums.length-2; j++) {
          // fix z
          // x + y = threeSum-z

          if (nums[i] + nums[j] + nums[j+1] + nums[j+2] > target) {
            break;
          }
          if (nums[i] + nums[j] + nums[nums.length-1] + nums[nums.length-2] < target) {
            // increment j as value is less as added to the end elements
            continue;
          }
          if (j == i+1 || (j > i+1 && nums[j] != nums[j-1])) {
            int p = j+1;
            int q = nums.length -1;
            // x + y = target - k - z
            int twoSum = threeSum - nums[j];

            while (p < q) {
              if (nums[p] + nums[q] == twoSum) {
                // got the numbers
                ArrayList<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                list.add(nums[j]);
                list.add(nums[p]);
                list.add(nums[q]);

                result.add(list);
                while (p < q && nums[p] == nums[p+1]) {
                  p++;
                }
                p++;
                while (p < q && nums[q] == nums[q-1]) {
                  q--;
                }
                q--;
              }
              else if (nums[p] + nums[q] < twoSum) {
                // do not do same check again
                while (p < q && nums[p] == nums[p+1]) {
                  p++;
                }
                p++;
              }
              else {
                // do not do same check again
                while (p < q && nums[q] == nums[q-1]) {
                  q--;
                }
                q--;
              }
            }
          }
        }
    }
    return result;
  }


//    public List<List<Integer>> fourSumEfficient(int[] nums, int target) {
//      Arrays.sort(nums);
//      List<List<Integer>> res = new ArrayList<>();
//      for (int i = 0; i < nums.length - 3; i++) {
//        if (i > 0 && nums[i] == nums[i - 1]) {
//          continue;
//        }
//        if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
//          break;
//        }
//        if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) {
//          continue;
//        }
//        for (int j = i + 1; j < nums.length - 2; j++) {
//          if (j > i + 1 && nums[j] == nums[j - 1]) {
//            continue;
//          }
//          if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
//            break;
//          }
//          if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) {
//            continue;
//          }
//          int k = j + 1;
//          int l = nums.length - 1;
//          while (k < l) {
//            int sum = nums[i] + nums[j] + nums[k] + nums[l];
//            if (sum == target) {
//              res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
//              k++;
//              l--;
//            } else if (sum < target) {
//              k++;
//            } else {
//              l--;
//            }
//            while (k < l && k > j + 1 && nums[k] == nums[k - 1]) {
//              k++;
//            }
//            while (l > k && l < nums.length - 2 && nums[l] == nums[l + 1]) {
//              l--;
//            }
//          }
//        }
//      }
//      return res;
//    }


  public static void main(String args[]) {
    int nums[] = {1,-2,-5,-4,-3,3,3,5};
//
//[1,-2,-5,-4,-3,3,3,5]
//    -11

    System.out.println(fourSum(nums, -11));

  }
}
