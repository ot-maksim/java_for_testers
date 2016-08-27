package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksym on 8/27/16.
 */
public class GroupDataGenerator {
  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<GroupData> groups = generateGroups(count);
    save(groups, file);
  }

  private static void save(List<GroupData> groups, File file) throws IOException {
//    System.out.println(new File(".").getAbsolutePath());
//    System.out.println(file.getAbsolutePath());
    Writer writer = new FileWriter(file);

    for(GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getFooter(), group.getHeader()));
    }
    writer.close();
  }

  private static List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<>();
    for(int i = 0; i < count; i++) {
      GroupData group = new GroupData()
              .withName(String.format("test %d", i))
              .withHeader(String.format("header %d", i))
              .withFooter(String.format("footer %d", i));
      groups.add(group);
    }
    return groups;
  }
}
