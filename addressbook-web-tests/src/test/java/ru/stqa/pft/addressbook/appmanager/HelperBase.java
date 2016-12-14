package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {
    findElement(locator).click();
  }

  private WebElement findElement(By locator) {
    return wd.findElement(locator);
  }

  protected void type(By locator, String text) {
    WebElement element = findElement(locator);
    element.clear();
    element.sendKeys(text);
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected void closeAlert() {
    if (isAlertPresent()==true) {
      wd.switchTo().alert().accept();
    }
  }

  protected boolean isCheckBoxSelected(By by) {
    return findElement(by).isSelected();
  }
}