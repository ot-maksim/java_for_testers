package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by maksym on 9/16/16.
 */
public class GitHubTests {
  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("0f1ee585c63512cd57e24d4b192b6cb7306b5e37");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("ot-maksim", "myFirstGitRepo")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
