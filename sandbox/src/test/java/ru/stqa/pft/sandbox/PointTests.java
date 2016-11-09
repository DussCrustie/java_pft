package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * Created by AKuznetsov on 09.11.2016.
 */
public class PointTests {

  /*Positiv test initial value*/
  @Test
  public void testDistance(){

    Point p1 = new Point(2, 8);
    Point p2 = new Point(7, 1);
    Assert.assertEquals(p1.distance(p2),7.0710678118654755);
  }

  /*Positiv test correct value*/
  @Test
  public void testDistance1(){

    Point p1 = new Point(4, 7);
    Point p2 = new Point(8, 2);
    Assert.assertEquals(p1.distance(p2),6.708203932499369);
  }

  /*Positiv test zero value*/
  @Test
  public void testDistance2(){

    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2),0.0);
  }

  /*Negativ test short value*/
  @Test
  public void testDistance3() {

    Point p1 = new Point(-1, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distance(p2), 0.0);
  }

  /*Negativ test short value*/
  @Test
  public void testDistance4() {

    Point p1 = new Point(-3, -1);
    Point p2 = new Point(1, 2);
    Assert.assertEquals(p1.distance(p2), 6.403);
  }

  /*Negativ test string*/
  @Test
  public void testDistance5() {

    Point p1 = new Point(-5, -3);
    Point p2 = new Point(1, 2);
    Assert.assertEquals(p1.distance(p2), "7.4031242374783493");
  }

}