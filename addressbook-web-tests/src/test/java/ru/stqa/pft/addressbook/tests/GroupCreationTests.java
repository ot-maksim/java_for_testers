package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line;

    while((line = br.readLine()) != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
    }

    return list.iterator();
  }

  @Test (dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    APP_MANAGER.goTo().groupPage();
    Groups before = APP_MANAGER.group().all();
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