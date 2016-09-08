package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by maksym on 9/8/16.
 */
public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() {
    APP_MANAGER.registration().start("user1", "user1@localhost.localdomain");
  }
}
