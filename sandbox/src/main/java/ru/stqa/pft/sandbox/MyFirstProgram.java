package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {

    System.out.println("Class Point");
    Point p1 = new Point(3, 6);
    Point p2 = new Point(8, 1);

      /* реализация с помощью функции*/
    System.out.println("distance function (" + p1.x + "," + p1.y + "), (" + p2.x + "," + p2.y + ") = " + distance(p1, p2));

     /* метод в классе Point*/
    System.out.println("distance method (" + p1.x + "," + p1.y + "), (" + p2.x + "," + p2.y + ") = " + p1.distance(p2));
  }
  /* функция */
  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
  }

}


