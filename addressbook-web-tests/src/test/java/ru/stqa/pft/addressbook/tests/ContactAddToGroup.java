package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.junit.Assert.assertTrue;

public class ContactAddToGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){

    app.goTo().ContactPage();
    if (app.db().contacts().size()==0){
      app.contact().create(new ContactData().withFirstname("test_1").withLastname("test2"));
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
      app.goTo().groupPage();
    }
  }

  @Test
  public void testAddContactToGroup() {

    boolean success = false;

    for (ContactData contact : app.db().contacts()) {
      if (app.db().groups().size() != contact.getGroups().size()) {
        app.contact().selectContactById(contact.getId());
        for (GroupData group : app.db().groups()) {
          if (! group.getContacts().contains(contact)) {
            app.contact().selectGroupForAdd(group.getId());
            app.contact().addToSelectedGroup();
            app.db().refresh(group);
            assertTrue(group.getContacts().contains(contact));
            return;
          }
        }
      }
    }
    app.contact().create(new ContactData()
            .withFirstname("test_1").withLastname("test2"));
    int contactId = app.db().contacts().stream().mapToInt((c) -> c.getId()).max().getAsInt();
    app.contact().selectContactById(contactId);
    GroupData group = app.db().groups().iterator().next();
    app.contact().selectGroupForAdd(group.getId());
    app.contact().addToSelectedGroup();
    app.db().refresh(group);
    for (ContactData contact : group.getContacts()) {
      if (contact.getId() == contactId) {
        success = true;
      }
    }
    assertTrue(success);
  }

}