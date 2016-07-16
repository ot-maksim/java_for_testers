package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    hello("world");
    hello("Maks");

    double length = 3;
    System.out.println("Square's square with length " + length + " = " + area(length));

    double a = 5;
    double b = 3;
    System.out.println("Rectangle's square with lengths " + a + " and " + b + " = " + area(a,b));
  }

  public static void hello(String name) {
    System.out.println("Hello " + name + "!");

  }

  public static double area(double len) {
    return len * len;
  }

  public static double area(double a, double b) {
    return a * b;
  }


}