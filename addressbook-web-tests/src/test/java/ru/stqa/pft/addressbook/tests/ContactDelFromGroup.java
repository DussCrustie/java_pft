package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDelFromGroup extends  TestBase{

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
  public void testDelContactFromGroup() {
    for (GroupData group : app.db().groups()) {
      if (! group.getContacts().isEmpty()) {
        Contacts before = (Contacts) group.getContacts();
        app.contact().selectGroup(group.getId());
        ContactData delContact = before.iterator().next();
        app.contact().selectContactById(delContact.getId());
        app.contact().delSelectedContactFromGroup();
        app.goTo().ContactPage();
        app.db().refresh(group);
        Contacts after = (Contacts) group.getContacts();
        assertThat(after, equalTo(before.without(delContact)));
        return;
      }
    }
    ContactData contact = app.db().contacts().iterator().next();
    app.contact().selectContactById(contact.getId());
    GroupData group = app.db().groups().iterator().next();
    app.contact().selectGroupForAdd(group.getId());
    app.contact().addToSelectedGroup();
    app.goTo().ContactPage();
    app.db().refresh(group);
    assertThat(group.getContacts().size(), equalTo(1));
    app.contact().selectGroup(group.getId());
    app.contact().selectContactById(contact.getId());
    app.contact().delSelectedContactFromGroup();
    app.goTo().ContactPage();
    app.db().refresh(group);
    assertThat(group.getContacts().size(), equalTo(0));
  }

}