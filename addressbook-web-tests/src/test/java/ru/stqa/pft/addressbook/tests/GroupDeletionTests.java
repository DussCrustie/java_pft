package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHalper().isThereAGroup()){
      app.getGroupHalper().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.getGroupHalper().getGroupList();
    app.getGroupHalper().selectGroup(before.size()-1);
    app.getGroupHalper().deleteSelectedGroups();
    app.getGroupHalper().returnToGroupPage();
    List<GroupData> after = app.getGroupHalper().getGroupList();
    Assert.assertEquals(after.size(),before.size()-1);

    before.remove(before.size()-1);
    Assert.assertEquals(before, after);

  }


}