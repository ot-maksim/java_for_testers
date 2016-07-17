package ru.stqa.pft.sandbox;

/**
 * Created by maksym on 7/17/16.
 */
public class PointTest {
  
  public static void main(String[] args){
    Point p1 = new Point(3,5);
    Point p2 = new Point(11.15,15);
    p1.distance(p1,p2);

    String distance = String.format("%.2f", p1.distance(p1,p2));

    System.out.println("Distance between point one (with coordinates x = " + p1.getX() + " and y = " + p1.getY() + ")" +
            " and point two (with coordinates x = " + p2.getX() + " and y = " + p2.getY() + ")" + " = " + distance);
  }

}
