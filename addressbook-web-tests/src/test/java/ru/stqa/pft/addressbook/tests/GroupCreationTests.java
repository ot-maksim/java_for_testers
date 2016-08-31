package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsAsXml() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
      String line;
      String xml = "";
      while ((line = br.readLine()) != null) {
        xml += line;
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(GroupData.class);
      List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validGroupsAsJson() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
      String line;
      String json = "";
      while ((line = br.readLine()) != null) {
        json += line;
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
      }.getType()); // List<GroupData>.class

      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validGroupsAsJson")
  public void testGroupCreation(GroupData group) {
    Groups before = APP_MANAGER.db().groups();

    APP_MANAGER.goTo().groupPage();
    APP_MANAGER.group().create(group);

    assertThat(APP_MANAGER.group().count(), equalTo(before.size() + 1));

    Groups after = APP_MANAGER.db().groups();

    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testBadGroupCreation() {
    APP_MANAGER.goTo().groupPage();
    Groups before = APP_MANAGER.group().all();
    GroupData group = new GroupData().withName("badGroupName'");
    APP_MANAGER.group().create(group);

    assertThat(APP_MANAGER.group().count(), equalTo(before.size()));

    Groups after = APP_MANAGER.group().all();

    assertThat(after, equalTo(before));

    verifyGroupListInUI();
  }
}