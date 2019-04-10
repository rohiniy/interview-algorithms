/**
 * Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
 * IPv4 addresses are canonically represented in dot-decimal notation, which consists of
 * four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
 * Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.
 *
 * IPv6 addresses are represented as eight groups of four hexadecimal digits,
 * each group representing 16 bits. The groups are separated by colons (":").
 * For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one.
 * Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters
 * in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6
 * address(Omit leading zeros and using upper cases).
 *
 * However, we don't replace a consecutive group of zero value with a single empty group using two
 * consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an
 * invalid IPv6 address.
 *
 * Besides, extra leading zeros in the IPv6 is also invalid. For example,
 * the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
 *
 * Note: You may assume there is no extra space or special characters in the input string.
 *
 * Example 1:
 * Input: "172.16.254.1"
 *
 * Output: "IPv4"
 *
 * Explanation: This is a valid IPv4 address, return "IPv4".
 * Example 2:
 * Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
 *
 * Output: "IPv6"
 *
 * Explanation: This is a valid IPv6 address, return "IPv6".
 * Example 3:
 * Input: "256.256.256.256"
 *
 * Output: "Neither"
 *
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 */
package com.basicAlgorithms.medium;

public class StringValidateIPAddress {
  public static String validIPAddress(String IP) {
    // least 0.0.0.0
    if (IP == null || IP.length() < 7) {
      return "Neither";
    }

    // IP4 can be 0-255.0-255.0-255.0-255
    // leading 0 is invalid
    // strictly digits

    // IP6
    // 16 bits each section - 8 sections

    String[] splitOnDot = null;
    if (IP.contains(".")) {
      if (IP.charAt(IP.length()-1) == '.') {
        return "Neither";
      }
      splitOnDot = IP.split("\\.");
    }
    else if (IP.contains(":")) {
      if (IP.charAt(IP.length()-1) ==  ':') {
        return "Neither";
      }
      splitOnDot = IP.split(":");

    }
    else {
      return "Neither";
    }


    if ( splitOnDot.length == 4) {
      // check for IP4
      for (String str: splitOnDot) {

        if (str.length() == 0 || str.length() > 3) {
          return "Neither";
        }
        // for IP4 need to check if it is 0-255 and does not have a leading 0
        for (Character c: str.toCharArray()) {
          if (!Character.isDigit(c)) {
            return "Neither";
          }
        }

        if (Integer.valueOf(str) < 0 || Integer.valueOf(str) > 255) {
          return "Neither";
        }
        if (str.length() > 1 && str.charAt(0) == '0') {
          // leading 0
          return "Neither";
        }
      }
      return "IPv4";
    }
    else if (splitOnDot.length == 8) {
      // check for IP6
      // each section should be length 1 - 4
      for (String str: splitOnDot) {
        try {
          if (str.length() < 1 || str.length() > 4 || Integer.parseInt(str, 16) > 65535 || str.charAt(0) == '-') {
            return "Neither";
          }
        }
        catch (NumberFormatException e) {
          return "Neither";
        }

      }
      return "IPv6";
    }
    else {
      return "Neither";
    }
  }

  public static void main(String args[]) {
     String IP = "172.0.0.1";
     //String IP = "20EE:0db8:85a3:0:0:8A2E:0370:7334:";
     //String IP = "20EE:FGb8:85a3:0:0:8A2E:0370:7334";

    System.out.println(validIPAddress(IP));
  }
}
