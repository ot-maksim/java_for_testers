package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test1", null, null);
    app.getGroupHelper().createGroup(group);
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

//   Here are several options how to find max id using List

    // option 1 - using loop
//    int maxId = 0;
//    for (GroupData g : after) {
//      if (g.getId() > maxId) {
//        maxId = g.getId();
//      }
//    }

    // option 2 - using anonymous class
//    Comparator<? super GroupData> byId = new Comparator<GroupData>() {
//      @Override
//      public int compare(GroupData o1, GroupData o2) {
//        return Integer.compare(o1.getId(), o2.getId());
//      }
//    };
//    Collections.sort(after, byId);
//    int maxId = after.get(after.size() - 1).getId();

    // option 3 - using inner class
//    class GroupIdCompare implements Comparator<GroupData> {
//      @Override
//      public int compare(GroupData o1, GroupData o2) {
//        return Integer.compare(o1.getId(), o2.getId());
//      }
//    }
//    GroupIdCompare byId = new GroupIdCompare();
//    Collections.sort(after, byId);
//    int maxId = after.get(after.size() - 1).getId();

    // option 4 - using lamda/anonymous function - starting from java 8
//    Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
//    int maxId = after.stream().max(byId).get().getId();
//    OR
    int maxId = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

    group.setId(maxId);
    before.add(group);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }
}