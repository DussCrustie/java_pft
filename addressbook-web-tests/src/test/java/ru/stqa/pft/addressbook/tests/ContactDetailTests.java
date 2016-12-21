package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactDetailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();
    if (app.contact().all().size()==0){
      app.contact().create(new ContactData().withFirstname("test_1").withLastname("test2").withHome("836678")
              .withMobile("89501234448").withWork("+495144").withAddress("fsafs")
              .withEmail1("1@mail.ru").withEmail2("2@mail.ru").withEmail3("3@mail.ru"));
    }
  }
  @Test
  public void testPersonAddress() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactInfoDetails = app.contact().infoDetailForm(contact);

    MatcherAssert.assertThat((merge(contactInfoDetails)), CoreMatchers.equalTo(merge(contactInfoDetails)));

  }

  private String merge(ContactData contact) {

    return Arrays.asList(contact.getLastname(), contact.getFirstname(), contact.getAddress(),
            contact.getHome(), contact.getMobile(), contact.getWork(), contact.getEmail1(),
            contact.getEmail2(), contact.getEmail3()).stream()
            .filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
  }
}