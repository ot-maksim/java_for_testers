package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by maksym on 7/25/16.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    APP_MANAGER.getNavigationHelper().goToGroupPage();

    if(!APP_MANAGER.getGroupHelper().isThereAnyGroup()) {
      APP_MANAGER.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    List<GroupData> before = APP_MANAGER.getGroupHelper().getGroupList();
    APP_MANAGER.getGroupHelper().selectGroup(before.size() - 1);
    APP_MANAGER.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"test2", null, "test4");
    APP_MANAGER.getGroupHelper().fillGroupForm(group);
    APP_MANAGER.getGroupHelper().submitGroupModification();
    APP_MANAGER.getGroupHelper().returnToGroupPage();
    List<GroupData> after = APP_MANAGER.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());

    after.sort(byId);
    before.sort(byId);

    Assert.assertEquals(before, after);
  }
}