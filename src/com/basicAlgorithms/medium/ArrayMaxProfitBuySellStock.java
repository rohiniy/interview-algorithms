/**
 Say you have an array for which the ith element is the price of a given stock on day i.

 If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the
 stock), design an algorithm to find the maximum profit.

 Note that you cannot sell a stock before you buy one.

 Example 1:

 Input: [7,1,5,3,6,4]
 Output: 5
 Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 Not 7-1 = 6, as selling price needs to be larger than buying price.
 Example 2:

 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
package com.basicAlgorithms.medium;

public class ArrayMaxProfitBuySellStock {
  public static int maxProfitOneTransaction(int[] prices) {
    int maxProfit = 0;
    int minPrice = Integer.MAX_VALUE;
    for (int i=0; i<prices.length; i++) {
      minPrice = Math.min(minPrice, prices[i]); // 1
      maxProfit = Math.max(maxProfit, prices[i] - minPrice); //5
    }
    return maxProfit;
  }

  /**
   *
   * @param prices : array of prices as per days
   * @return total profit by buying and selling
   */
  public static int maxProfitMultipleTransaction(int[] prices) {
    // [3, 2, 5, 8, 1, 9]
    // here doing: total = 5-2 + 8-5 = 8-2 = 6 which we get though we go sequentially
    int total = 0;
    for (int i = 0; i<prices.length-1; i++) {
      if (prices[i + 1] > prices[i]) {
        total += prices[i+1] - prices[i];
      }
    }
    return total;
  }

  public static void main(String args[]) {
//    int prices[] = {3, 2, 5, 8, 1, 9};
    int prices[] = {7, 3, 9, 10, 1, 5};
    System.out.println("Profit by 1 trasaction = "+ maxProfitOneTransaction(prices));
    System.out.println("Profit by multiple transaction = " + maxProfitMultipleTransaction(prices));


  }
}
