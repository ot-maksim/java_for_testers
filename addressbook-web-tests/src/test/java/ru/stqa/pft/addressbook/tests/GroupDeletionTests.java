package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    APP_MANAGER.goTo().groupPage();
    if (APP_MANAGER.group().all().size() == 0) {
      APP_MANAGER.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testGroupDeletion() {
    Set<GroupData> before = APP_MANAGER.group().all();
    GroupData group = before.iterator().next();

    APP_MANAGER.group().delete(group);

    Set<GroupData> after = APP_MANAGER.group().all();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(group);

    Assert.assertEquals(before, after);
  }
}