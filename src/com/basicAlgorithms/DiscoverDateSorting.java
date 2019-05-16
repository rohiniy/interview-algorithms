/**
 * Given an array of strings dates[], the task is to sort these dates in ascending order.
 * Note: Each date is of the form dd mmm yyyy where:
 *
 * Domain of dd is [0-31].
 * Domain of mmm is [Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec].
 * And, yyyy is a four digit integer.
 * Examples:
 *
 * Input: dates[] = {“01 Mar 2015”, “11 Apr 1996”, “22 Oct 2007”}
 * Output:
 * 11 Apr 1996
 * 22 Oct 2007
 * 01 Mar 2015
 *
 * Input: dates[] = {“03 Jan 2018”, “02 Jan 2018”, “04 Jan 2017”}
 * Output:
 * 04 Jan 2017
 * 02 Jan 2018
 * 03 Jan 2018
 */
package com.basicAlgorithms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DiscoverDateSorting {

  public static List<String> getDatesSorted(List<String> dates) {
    // format in 01 Jan 2018
    // other formats of month: MM : 01, 02 or MMMMM: July
    SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
    List<Date> datesList = new ArrayList<>();
    List<String> result = new ArrayList<>();

    try {
      for (String date : dates) {
        // format each date
        datesList.add(formatter.parse(date));
      }

      // sort
      Collections.sort(datesList, new Comparator<Date>() {
        @Override
        public int compare(Date o1, Date o2) {
          // ascending order
          if (o1.getTime() > o2.getTime()) {
            return 1;
          }
          else if (o1.getTime() < o2.getTime()) {
            return -1;
          }
          else {
            // compare month
            return 0;
          }
        }
      });

      // add it in string list
      for (Date date: datesList) {
        result.add(formatter.format(date));
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return result;
  }

  public static void main(String args[]) {
    List<String> dates = new ArrayList<>();
    dates.add("11 Apr 2018");
    dates.add("11 Apr 2000");
    dates.add("11 Jan 1996");
    dates.add("11 Apr 1996");
    dates.add("12 Apr 1996");

    System.out.println(getDatesSorted(dates));
  }

}
