package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Groups before = APP_MANAGER.group().all();
    GroupData group = before.iterator().next();
    APP_MANAGER.group().delete(group);

    assertThat(APP_MANAGER.group().count(), equalTo(before.size() - 1));

    Groups after = APP_MANAGER.group().all();

    assertThat(before.without(group), equalTo(after));
  }
}