
package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

  @Test

  public void testPersonAddress(){
    app.goTo().ContactPage();
    ContactData person = app.contact().all().iterator().next();
    ContactData personInfoFromEditForm = app.contact().infoFromEditForm(person);

    assertThat(person.getAddress(), equalTo(personInfoFromEditForm.getAddress()));
  }
}