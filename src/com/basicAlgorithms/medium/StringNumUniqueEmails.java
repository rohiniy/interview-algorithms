/**
 * Every email consists of a local name and a domain name, separated by the @ sign.
 *
 * For example, in alice@leetcode.com, alice is the local name, and
 * leetcode.com is the domain name.
 *
 * Besides lowercase letters, these emails may contain '.'s or '+'s.
 *
 * If you add periods ('.') between some characters in the local name part of an email address,
 * mail sent there will be forwarded to the same address without dots in the local name.
 * For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
 * (Note that this rule does not apply for domain names.)
 *
 * If you add a plus ('+') in the local name, everything after the first plus sign will be ignored.
 * This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to
 * my@email.com.  (Again, this rule does not apply for domain names.)
 *
 * It is possible to use both of these rules at the same time.
 *
 * Given a list of emails, we send one email to each address in the list.
 * How many different addresses actually receive mails?
 *
 *
 *
 * Example 1:
 *
 * Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * Output: 2
 * Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
 *
 *
 *
 * Note:
 *
 *     1 <= emails[i].length <= 100
 *     1 <= emails.length <= 100
 *     Each emails[i] contains exactly one '@' character.
 */
package com.basicAlgorithms.medium;

import java.util.*;

public class StringNumUniqueEmails {
  public static int numUniqueEmails(String[] emails) {
    if (emails == null) {
      return 0;
    }

    HashSet<String> emailSet = new HashSet<>();

    for (String email: emails) {
      String[] splitAtRate = email.split("@");
      String[] splitAtPlus = splitAtRate[0].split("\\+");
      // replace all '.' in the local name
      if (splitAtPlus[0].contains(".")) {
        splitAtPlus[0] = splitAtPlus[0].replaceAll(".", "");
      }

      String resultEmail = splitAtPlus[0] + '@' + splitAtRate[1];
      emailSet.add(resultEmail);
    }

    return emailSet.size();
  }

  public static int numsUniqueEmailsBySubstring(String[] emails) {
    if (emails == null) {
      return 0;
    }

    if (emails.length == 0) {
      return 0;
    }

    Set<String> emailsSet = new HashSet<String>();
    for (String email: emails) {
      int indexRate = email.indexOf('@');
      // domain name
      String domain = email.substring(indexRate);
      String localName = email.substring(0, indexRate);
      int indexPlus = email.indexOf('+');
      if (indexPlus != -1) {
        localName = localName.substring(0, indexPlus);
      }
      if (localName.contains(".")) {
        localName = localName.replaceAll(".", "");
      }

      emailsSet.add(localName + domain);
    }
    return emailsSet.size();
  }

  public static void main(String args[]) {
    String emails[] = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        //{"testemail@leetcode.com","testemail1@leetcode.com","testemail+david@lee.tcode.com"};
        //{"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
    System.out.println(numUniqueEmails(emails));
    System.out.println(numsUniqueEmailsBySubstring(emails));
  }
}
