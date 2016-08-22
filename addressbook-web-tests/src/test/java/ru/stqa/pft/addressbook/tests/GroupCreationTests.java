package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    APP_MANAGER.goTo().groupPage();
    List<GroupData> before = APP_MANAGER.group().list();
    GroupData group = new GroupData().withName("test1");

    APP_MANAGER.group().create(group);
    APP_MANAGER.goTo().groupPage();

    List<GroupData> after = APP_MANAGER.group().list();

    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    after.sort(byId);
    before.sort(byId);

    Assert.assertEquals(before, after);
  }
}