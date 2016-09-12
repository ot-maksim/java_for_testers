package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maksym on 9/8/16.
 */
public class MailHelper extends HelperBase {

  private final Wiser wiser;

  protected MailHelper(ApplicationManager appManager) {
    super(appManager);
    wiser = new Wiser();
  }

  public List<MailMessage> waitForMail(int count, long timeout) {
    long start = System.currentTimeMillis();
    while (System.currentTimeMillis() < start + timeout) {
      if (wiser.getMessages().size() >= count) {
        return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new Error("No mail :(");
  }

  private static MailMessage toModelMail(WiserMessage message) {
    try {
      MimeMessage mm = message.getMimeMessage();
      return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent());
    } catch (MessagingException ex) {
      ex.printStackTrace();
      return null;
    } catch (IOException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public void start() {
    wiser.setPort(1025);
    wiser.start();
  }

  public void stop() {
    wiser.stop();
  }
}
