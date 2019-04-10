package com.designPatterns.factory;

public class Driver {
  public static void main(String args[]) {
    CalculateFactory obj = new CalculateFactory();
    Calculate calObj = obj.getInstance("add");
    calObj.calculate(2, 3);

    Calculate calObj1 = obj.getInstance("subtract");
    calObj1.calculate(2, 3);
  }
}
