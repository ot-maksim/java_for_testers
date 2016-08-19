package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by maksym on 7/25/16.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().goToGroupPage();

    if(!app.getGroupHelper().isThereAnyGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"test2", null, "test4");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);


    // Here are several options how to sort list

    // option 1 - using Collections.sort(List, Comparator)

//    by anonymous class
//       Comparator<? super GroupData> byId = new Comparator<GroupData>() {
//      @Override
//      public int compare(GroupData o1, GroupData o2) {
//        return Integer.compare(o1.getId(), o2.getId());
//      }
//    };
// OR
//    by inner class
//    class GroupIdCompare implements Comparator<GroupData> {
//      @Override
//      public int compare(GroupData o1, GroupData o2) {
//        return Integer.compare(o1.getId(), o2.getId());
//      }
//    }
//    GroupIdCompare byId = new GroupIdCompare();

//    Collections.sort(after, byId);

//    option 2 - using lamda/anonymous function - starting from java 8

//    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
//    after.sort(byId);

    before.add(group);
    Assert.assertEquals(new HashSet<>(before),new HashSet<>(after));
  }
}
