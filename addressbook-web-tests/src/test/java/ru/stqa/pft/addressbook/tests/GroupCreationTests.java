package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    APP_MANAGER.goTo().groupPage();
    Set<GroupData> before = APP_MANAGER.group().all();
    GroupData group = new GroupData().withName("test1");

    APP_MANAGER.group().create(group);

    Set<GroupData> after = APP_MANAGER.group().all();

    Assert.assertEquals(after.size(), before.size() + 1);


    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);

    Assert.assertEquals(before, after);
  }
}