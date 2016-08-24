package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    APP_MANAGER.goTo().groupPage();
    Groups before = APP_MANAGER.group().all();
    GroupData group = new GroupData().withName("test1");
    APP_MANAGER.group().create(group);

    assertThat(APP_MANAGER.group().count(), equalTo(before.size() + 1));

    Groups after = APP_MANAGER.group().all();

    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadGroupCreation() {
    APP_MANAGER.goTo().groupPage();
    Groups before = APP_MANAGER.group().all();
    GroupData group = new GroupData().withName("badGroupName'");
    APP_MANAGER.group().create(group);

    assertThat(APP_MANAGER.group().count(), equalTo(before.size()));

    Groups after = APP_MANAGER.group().all();

    assertThat(after, equalTo(before));
  }
}