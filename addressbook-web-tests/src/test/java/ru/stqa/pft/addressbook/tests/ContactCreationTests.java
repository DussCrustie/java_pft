package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    List<ContactData> before = app.getContactHelper().getContactList();

    ContactData contact = new ContactData("gsdg", "geega", "fff city, jjj street 44 - 14", "89005553322", "Ivanov@mail.ru", "test1");
    app.getContactHelper().createContact(contact);
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    //contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }
  //app.getContactHelper().fillContactForm(new ContactData("gsdg", "geega", "fff city, jjj street 44 - 14", "89005553322", "Ivanov@mail.ru", "test1"),true);
  //app.getContactHelper().submitContactCreation();
}