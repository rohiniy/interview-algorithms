/**
 *  Equations are given in the format A / B = k, where A and B are variables represented as strings,
 *  and k is a real number (floating point number). Given some queries, return the answers.
 *  If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values,
 * vector<pair<string, string>> queries , where equations.size() == values.size(),
 * and the values are positive. This represents the equations. Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 *
 * The input is always valid. You may assume that evaluating the queries will result in
 * no division by zero and there is no contradiction.
 *
 *
 *------SOLUTION - UNION FIND PROBLEM
 *   1. Thoughts
 *         - check if we have enough info to get the result
 *         - if yes, calculate; if not, return -1.0
 *         - Method: union find
 *             - a/b = 2.0 --> b is the root of a; the distance from a to b is 1/2.0
 *             - if two nums have the same root, we can get the result; a/b=2.0, b/c=3.0
 *             index   a   b   c
 *             root    b   c   c
 *             dist    2   3   1
 *             - if we want to know a/c = ?: a = 2 * b = 2 * 3 * c => a/c = 6.0
 *     2. Corner case
 *         - if any input is null, return null
 *         - no enough info, return -1.0
 *     3. Steps
 *         - go through equations to union elements with the same root and update root map and distance map
 *         - go through each query: check if has the same root; find relative dist
 *
 *
 */
package com.basicAlgorithms.medium;

public class FindUnionEvaluateDivision {
  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    // Find Union problem
    // We can represent a/b =2 and b/c = 3
    //Map<String, String> rootMap = new HashMap<>();
    //Map<>
    return null;
  }
}
