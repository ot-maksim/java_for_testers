package ru.stqa.pft.sandbox;

/**
 * Created by maksym on 7/17/16.
 */
public class Point {

  private double x;
  private double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double distance(Point p1, Point p2){

    double x = p2.getX() - p1.getX();
    double y = p2.getY() - p1.getY();

    double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

    return distance;
  }
}
