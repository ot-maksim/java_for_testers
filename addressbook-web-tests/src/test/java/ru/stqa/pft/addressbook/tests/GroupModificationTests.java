package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by maksym on 7/25/16.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    APP_MANAGER.goTo().groupPage();
    if (APP_MANAGER.group().list().size() == 0) {
      APP_MANAGER.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testGroupModification() {
    List<GroupData> before = APP_MANAGER.group().list();
    int index = before.size() - 1;
    int groupId = before.get(index).getId();
    GroupData group = new GroupData().withId(groupId).withName("test2").withFooter("test4");

    APP_MANAGER.group().modify(index, group);

    List<GroupData> after = APP_MANAGER.group().list();

    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());

    after.sort(byId);
    before.sort(byId);

    Assert.assertEquals(before, after);
  }
}