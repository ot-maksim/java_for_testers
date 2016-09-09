package ru.stqa.pft.mantis.model;

import ru.stqa.pft.mantis.appmanager.MailHelper;

/**
 * Created by maksym on 9/8/16.
 */
public class MailMessage {
  public String to;
  public String text;

  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;
  }
}
