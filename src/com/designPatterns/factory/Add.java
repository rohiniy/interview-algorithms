package com.designPatterns.factory;

import java.util.HashMap;
import java.util.*;

public class Add implements Calculate{
  @Override
  public void calculate(int num1, int num2) {
    System.out.println("Addition is:: " + (num1 + num2));
    String str;
  }
}
