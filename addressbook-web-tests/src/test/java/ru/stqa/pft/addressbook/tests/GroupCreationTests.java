package ru.stqa.pft.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    //List<Object[]> list = new ArrayList<Object[]>();
    //BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\groups.csv")));
    BufferedReader reader = new BufferedReader(new FileReader(new File("L:\\Devel\\java_pft\\addressbook-web-tests\\src\\test\\resources\\groups.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      //String[] split = line.split(";");
      // list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();

    }
    Gson gson = new Gson();
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
    return  groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    //list.add((new Object[]{new GroupData().withName("test1").withHeader("test1").withFooter("test1")}));
    //list.add((new Object[]{new GroupData().withName("test2").withHeader("test2").withFooter("test2")}));
    //list.add((new Object[]{new GroupData().withName("test3").withHeader("test3").withFooter("test3")}));
    //return list.listIterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    //List<Object[]> list = new ArrayList<Object[]>();
    //BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\groups.csv")));
    BufferedReader reader = new BufferedReader(new FileReader(new File("L:\\Devel\\java_pft\\addressbook-web-tests\\src\\test\\resources\\groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      //String[] split = line.split(";");
      // list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();

    }
    XStream xStream = new XStream();
    xStream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>)xStream.fromXML(xml);
    return  groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    //list.add((new Object[]{new GroupData().withName("test1").withHeader("test1").withFooter("test1")}));
    //list.add((new Object[]{new GroupData().withName("test2").withHeader("test2").withFooter("test2")}));
    //list.add((new Object[]{new GroupData().withName("test3").withHeader("test3").withFooter("test3")}));
    //return list.listIterator();
  }

  @Test(dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) {
    //String[] names = new String[] {"test1", "test2", "test3"};
    //GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test(enabled = false)
  public void testBadGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test4'");
    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}