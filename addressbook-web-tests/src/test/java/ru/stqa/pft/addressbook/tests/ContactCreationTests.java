package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("", "", "Novosibirsk city, Lenina street 5 - 14", "89135685945", "Ivanova@mail.ru"));
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().returnToHomePage();
    }


}