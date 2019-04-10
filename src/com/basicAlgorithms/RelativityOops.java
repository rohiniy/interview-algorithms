package com.basicAlgorithms;

import java.util.*;

interface IShape<T> {
  T draw();
}

class Line implements IShape<String> {
  public String draw() {
    return "l";
  }
}

class Circle implements IShape<String> {
  public String draw() {
    return "o";
  }
}

public class RelativityOops {
  public static void main(String args[] ) throws Exception {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    IShape circle = new Circle();
    IShape line = new Line();

    List<IShape> listOfShapes = new ArrayList<>();
    listOfShapes.add(circle);
    listOfShapes.add(line);

    // excute draw method of all shapes in the list
    for (IShape shape: listOfShapes) {
      System.out.print(shape.draw());
    }
  }
}


