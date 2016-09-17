package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by maksym on 7/24/16.
 */
public class SquareTests {

  @Test
  public void testArea(){
    Square s = new Square(5);
    Assert.assertEquals(s.area(), 20.0);
  }

  @Test
  public void testPoint1(){
    Point p1 = new Point(12,10);
    Point p2 = new Point(22,20);
    String distance = String.format("%.2f", p1.distance(p1,p2));
    Assert.assertEquals(distance, "14.14");
  }

  @Test
  public void testPoint2(){
    Point p1 = new Point(12,10);
    Point p2 = new Point(22,20);
    Assert.assertEquals(p1.distance(p1,p2), 14.142135623730951);
  }
}
