package ru.stqa.pft.addressbook;

public class GroupDelitionTests extends TestBase {

    @Test
    public void testGroupDelition() {
        gotoGroupPage();
        selectGroup();
        deleteSelectedGroups();
        returnToGroupPage();
    }


}
