package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;


public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    //click(By.name("company"));
    type(By.name("address"), contactData.getAddress());
    //click(By.name("theform"));
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());

    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());

  }
  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
  }

  public void CloseWindow() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification() {

    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    //String xpath = "//table[@id='maintable']/tbody/tr[" + Integer.toString(id + 2) + "]/td[8]/a/img";
    //click(By.xpath(xpath));
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void returnToContactPage() {
    click(By.linkText("home"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact,true);
    submitContactCreation();
    returnToContactPage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModification();
    fillContactForm(contact, false);
    submitContactModification();
    returnToContactPage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    CloseWindow();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows){
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));

    }
    return  contacts;
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHome(home).withMobile(mobile).withWork(work).withAddress(address)
            .withEmail1(email1).withEmail2(email2).withEmail3(email3);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    //wd.findElement(By.xpath(String.format("a[href='wdit.php&id=%s']", id))).click();
  }
  public ContactData infoDetailForm(ContactData contact){
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", contact.getId()))).click();
    String info[] = wd.findElement(By.id("content")).getText().replaceAll("[HMW:]", "")
            .replaceAll("\\n+\\s*", "\n").replaceFirst(" ", "\n").split("\n");

    wd.navigate().back();

    return new ContactData().withId(contact.getId()).withFirstname(info [0]).withLastname(info [1])
            .withAddress(info [2]).withHome(info [3]).withMobile(info [4]).withWork(info [5])
            .withEmail1(info [6]).withEmail2(info [7]).withEmail3(info [8]);
  }


  public void selectGroupForAdd(int id) {
    new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(id));
  }


  public void addToSelectedGroup() {
    click(By.xpath("//*[@id=\"content\"]/form[2]/div[4]/input"));
  }

  public void selectGroup(int id) {
    new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(id));
  }

  public void delSelectedContactFromGroup() {
    click(By.xpath("//*[@id=\"content\"]/form[2]/div[3]/input"));
  }
}