package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class ApplicationManager {

  WebDriver wd;

  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHalper;
  private SessionHelper SessionHelper;



  public void init() {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    groupHalper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    SessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    SessionHelper.login("admin", "secret");
  }



  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHalper() {
    return groupHalper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}