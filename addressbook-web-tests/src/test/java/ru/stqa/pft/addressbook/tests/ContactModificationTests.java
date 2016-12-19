package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @Test
  public void testPersonModification(){

    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("gsdg", "geega", "fff city, jjj street 44 - 14", "89005553322", "Ivanov@mail.ru", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData person = new ContactData(before.get(before.size()-1).getId(),"gsdg", "geega", "fff city, jjj street 44 - 14", "89005553322", "Ivanov@mail.ru", null);
    app.getContactHelper().fillContactForm(person, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size()-1);
    before.add(person);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}