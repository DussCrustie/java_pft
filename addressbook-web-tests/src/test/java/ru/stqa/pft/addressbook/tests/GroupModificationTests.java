package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHalper().isThereAGroup()){
      app.getGroupHalper().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.getGroupHalper().getGroupList();
    app.getGroupHalper().selectGroup(before.size()-1);
    app.getGroupHalper().initGroupModification();
    GroupData group=new GroupData(before.get(before.size()-1).getId(),"test1", "test2", "test3");
    app.getGroupHalper().fillGroupForm(group);
    app.getGroupHalper().submitGroupModification();
    app.getGroupHalper().returnToGroupPage();
    List<GroupData> after = app.getGroupHalper().getGroupList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size()-1);
    before.add(group);
    Comparator<? super GroupData> byId=(g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
