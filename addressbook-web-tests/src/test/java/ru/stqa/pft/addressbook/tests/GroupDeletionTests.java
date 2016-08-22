package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    APP_MANAGER.getNavigationHelper().goToGroupPage();

    if(!APP_MANAGER.getGroupHelper().isThereAnyGroup()) {
      APP_MANAGER.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    List<GroupData> before = APP_MANAGER.getGroupHelper().getGroupList();
    APP_MANAGER.getGroupHelper().selectGroup(before.size() - 1);
    APP_MANAGER.getGroupHelper().deleteSelectedGroups();
    APP_MANAGER.getGroupHelper().returnToGroupPage();
    List<GroupData> after = APP_MANAGER.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);

    Assert.assertEquals(before, after);
  }
}