package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by maksym on 7/25/16.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (APP_MANAGER.db().groups().size() == 0) {
      APP_MANAGER.goTo().groupPage();
      APP_MANAGER.group().create(new GroupData()
              .withName("test1")
              .withHeader("test1")
              .withFooter("test1"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = APP_MANAGER.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId())
            .withName("test2")
            .withHeader("test2")
            .withFooter("test2");

    APP_MANAGER.goTo().groupPage();
    APP_MANAGER.group().modify(group);

    assertThat(APP_MANAGER.group().count(), equalTo(before.size()));

    Groups after = APP_MANAGER.db().groups();

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }
}