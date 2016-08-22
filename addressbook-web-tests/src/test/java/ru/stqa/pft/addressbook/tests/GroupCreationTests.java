package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    APP_MANAGER.getNavigationHelper().goToGroupPage();
    List<GroupData> before = APP_MANAGER.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test1", null, null);
    APP_MANAGER.getGroupHelper().createGroup(group);
    APP_MANAGER.getNavigationHelper().goToGroupPage();
    List<GroupData> after = APP_MANAGER.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    after.sort(byId);
    before.sort(byId);

    Assert.assertEquals(before, after);
  }
}