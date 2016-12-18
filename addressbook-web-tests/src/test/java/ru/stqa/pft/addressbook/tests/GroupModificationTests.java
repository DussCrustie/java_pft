package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {
  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHalper().isThereAGroup()){
      app.getGroupHalper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHalper().selectGroup();
    app.getGroupHalper().initGroupModification();
    app.getGroupHalper().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHalper().submitGroupModification();
    app.getGroupHalper().returnToGroupPage();

  }
}
