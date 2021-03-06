package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactFromJson() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Devel\\java_pft\\addressbook-web-tests\\src\\test\\resources\\contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> groups = gson.fromJson(json, new TypeToken<List<ContactData>>(){
    }.getType());
    return  groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();

  }

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\Devel\\java_pft\\addressbook-web-tests\\src\\test\\resources\\contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>)xStream.fromXML(xml);
    return  contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "validContacts")
  public void ContactCreationTests(ContactData contact) {

    app.goTo().ContactPage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/stru.png");
    app.contact().create(contact);
    app.contact().returnToContactPage();
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUI();

  }

  @Test(enabled = false)
  public void  testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}