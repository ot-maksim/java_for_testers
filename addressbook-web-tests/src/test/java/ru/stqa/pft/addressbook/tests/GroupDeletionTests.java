package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    APP_MANAGER.goTo().groupPage();
    if(APP_MANAGER.group().list().size() == 0) {
      APP_MANAGER.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testGroupDeletion() {
    List<GroupData> before = APP_MANAGER.group().list();
    int index = before.size() - 1;

    APP_MANAGER.group().delete(index);

    List<GroupData> after = APP_MANAGER.group().list();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);

    Assert.assertEquals(before, after);
  }
}