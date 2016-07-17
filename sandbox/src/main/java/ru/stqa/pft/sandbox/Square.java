package ru.stqa.pft.sandbox;

/**
 * Created by maksym on 7/17/16.
 */
public class Square {

  private double length;

  public Square(double length) {
    this.length = length;
  }

  public double area() {
    return this.length * this.length;
  }

  public double getLength() {
    return length;
  }
}
