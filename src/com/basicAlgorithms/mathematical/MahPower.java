package com.basicAlgorithms.mathematical;

public class MahPower {
  // x^y : y can be negative
  // if y is negative: 2^-2 = 1/4
  public static float power(float x, int y) {
    float temp;
    if( y == 0)
      return 1;

    if (y == 1) {
      return x;
    }

    temp = power(x, y/2);

    if (y%2 == 0)
      return temp*temp;
    else
    {
      if(y > 0)
        return x * temp * temp;
      else
        return (temp * temp) / x;
    }
  }

  public static void main(String args[]) {
    System.out.println(power(2, -3));
  }
}
