package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    hello("Maks");

    Square square = new Square(5);
    System.out.println("Square's square with length " + square.getLength() +
            " = " + square.area());

    Rectangle rectangle = new Rectangle(3,5);
    System.out.println("Rectangle's square with length " + rectangle.getLength() +
            " and width " + rectangle.getWidth() + " = " + rectangle.area());
  }

  public static void hello(String name) {
    System.out.println("Hello " + name + "!");

  }


}