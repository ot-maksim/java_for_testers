package ru.stqa.pft.sandbox;

/**
 * Created by maksym on 7/17/16.
 */
public class PointTest {

  private static Point p1 = new Point(3,5);
  private static Point p2 = new Point(11.15,15);

  public static void main(String[] args){

    String distance = String.format("%.2f", distance(p1,p2));

    System.out.println("Distance between point one (with coordinates x = " + p1.getX() + " and y = " + p1.getY() + ")" +
            " and point two (with coordinates x = " + p2.getX() + " and y = " + p2.getY() + ")" + " = " + distance);
  }

  public static double distance(Point p1, Point p2){

    double x = p2.getX() - p1.getX();
    double y = p2.getY() - p1.getY();

    double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

    return distance;
  }
}
