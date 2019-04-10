/**
 * Simplify Path
 *
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
 *
 * In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level. For more information, see: Absolute path vs relative path in Linux/Unix
 *
 * Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.
 *
 *
 *
 * Example 1:
 *
 * Input: "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 *
 * Example 2:
 *
 * Input: "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 *
 * Example 3:
 *
 * Input: "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 *
 * Example 4:
 *
 * Input: "/a/./b/../../c/"
 * Output: "/c"
 *
 * Example 5:
 *
 * Input: "/a/../../b/../c//.//"
 * Output: "/c"
 *
 * Example 6:
 *
 * Input: "/a//b////c/d//././/.."
 * Output: "/a/b/c"
 *
 *
 * SOLUTION:
 * USING STACK
 * 1. Do not take the last slash
 * 2. If there are slash repeated then just take it once
 * 3. Use stack and push each char in it, if there is .. then remove from stack - 2 elements.
 * 4. Add to the string in reverse manner. So, from stack put in an array and then traverse array from
 * last to first and append to the string
 *
 *
 * USING QUEUE - space optimal
 * 1. Split on "/"
 * 2. For each splitted string in the array
 * 3. If it is "." OR "" (happen because of // or /) then ignore - continue to loop
 * 4. If it is ".." then need to remove from last of the queue
 * ((LinkedList<String>)q).pollLast()
 * 5. Otherwise, it means it is dir name - then jsut add to the queue: q.add()
 * 6. After the loop is over, loop  till !queue.isEmpty append to the result string
 * str += ((LinkedList<String>)q).popFirst()
 * 7. If it is empty then return "/"
 */

package com.basicAlgorithms.medium;

import java.util.*;

public class StackQueueUnixAbsToCannonicalPath {

  /**
   * 13 ms and  39 MB memory space
   * @param path
   * @return
   */
  public static String simplifyPathUsingStack(String path) {
    if (path == null || path.length() == 0) {
      return null;
    }

    Stack<String> st = new Stack<String>();

    String [] pathSplit = path.split("/");

    for(String str: pathSplit) {
      // for each string in between / we need to see if it is
      // file name - push in stack
      // .  - ignore
      // .. - pop from stack
      switch(str) {
        case ".":
          //ignore
          break;

        case "":
          //ignore
          break;

        case "..":
          if (!st.isEmpty()) {
            st.pop();
            st.pop();
          }
          break;

        default:
          // all the filenames
          st.push("/");
          st.push(str);
          break;
      }
    }

    // pop the stack and reverse it
    String result = new String();
    String [] arr = new String[st.size()];
    int i = 0;
    while (!st.isEmpty()) {
      arr[i++] = st.pop();
    }

    for (i= arr.length-1; i >= 0; i--){
      result = result + arr[i];
    }

    if (result.equals("")) {
      return "/";
    }
    return result;
  }


  public static String simplifyPathUsingQueue(String path) {
    Queue<String> q = new LinkedList<>();

    String strArr[] = path.split("/");

    for(String str: strArr) {
      if (str.equals(".") || str.equals("")) {
        // ignore them
        continue;
      }
      else if (str.equals("..")) {
        // remove the last element inserted
        ((LinkedList<String>) q).pollLast();
      }
      else {
        // dir name
        q.add(str);
      }
    }

    String result  = new String();
    while(!q.isEmpty()) {
      result += "/";
      result += ((LinkedList<String>)q).pollFirst();
    }

    if (result.equals("")) {
      return "/";
    }
    return result;
  }

  public static void main(String args[]) {
    //System.out.println(simplifyPath("//home/b/.././c/.."));
    System.out.println(simplifyPathUsingStack("/home/ab/c/../"));
    System.out.println(simplifyPathUsingQueue("/home/ab/c/../"));

  }
}
