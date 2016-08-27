package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
  @Parameter(names = "-c", description = "Group count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<GroupData> groups = generateGroups(count);
    save(groups, new File(file));
  }

  private void save(List<GroupData> groups, File file) throws IOException {
//    System.out.println(new File(".").getAbsolutePath());
//    System.out.println(file.getAbsolutePath());
    Writer writer = new FileWriter(file);

    for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getFooter(), group.getHeader()));
    }
    writer.close();
  }

  private List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      GroupData group = new GroupData()
              .withName(String.format("test %d", i))
              .withHeader(String.format("header %d", i))
              .withFooter(String.format("footer %d", i));
      groups.add(group);
    }
    return groups;
  }
}
