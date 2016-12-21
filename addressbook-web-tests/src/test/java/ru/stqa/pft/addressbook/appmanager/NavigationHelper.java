package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("H1"))
            && wd.findElement(By.tagName("H1")).getText().equals("Groups")
            && isElementPresent(By.name("New"))){
      return;
    }
    click(By.linkText("groups"));
 }

  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home"));
  }

  public void ContactPage() {
  }
}