package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

  @Test
  public void testPersonAddress(){
    app.goTo().HomePage();
    ContactData person = app.contact().all().iterator().next();
    ContactData personInfoFromEditForm = app.contact().infoFromEditForm(person);

    assertThat(person.getAllEmails(), equalTo(mergeEmail(personInfoFromEditForm)));

  }

  private String mergeEmail(ContactData person) {

    return Arrays.asList(person.getEmail1(), person.getEmail2(), person.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private static String cleaned(String email){
    return email.replaceAll("\\s", "");
  }
}