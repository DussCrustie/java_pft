package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().createContact(new ContactData("gsdg", "geega", "fff city, jjj street 44 - 14", "89005553322", "Ivanov@mail.ru", "test1"));

    }
}