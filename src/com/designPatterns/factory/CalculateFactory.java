package com.designPatterns.factory;

public class CalculateFactory {
  public Calculate getInstance(String typeOfObject) {
    Calculate calculate = null;

    if (typeOfObject.equals("add")) {
      calculate = new Add();
    } else if (typeOfObject.equals("subtract")) {
      calculate = new Subtract();
    }

    return calculate;
  }
}
