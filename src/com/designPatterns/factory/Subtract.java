package com.designPatterns.factory;

public class Subtract implements Calculate {
  @Override
  public void calculate(int num1, int num2) {
    System.out.println("Subtraction:: "+ (num1 - num2));
  }
}
