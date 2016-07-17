package ru.stqa.pft.sandbox;

/**
 * Created by maksym on 7/17/16.
 */
public class Rectangle {
  private double length;
  private double width;

  public Rectangle(double length, double width) {
    this.length = length;
    this.width = width;
  }

  public double area() {
    return this.length * this.width;
  }

  public double getLength() {
    return length;
  }

  public double getWidth() {
    return width;
  }
}
