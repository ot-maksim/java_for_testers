package ru.stqa.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksym on 9/7/16.
 */
public class HttpSessionHelper {
  private CloseableHttpClient httpClient;
  private ApplicationManager appManager;

  public HttpSessionHelper(ApplicationManager appManager) {
    this.appManager = appManager;
    httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }

  public boolean login(String username, String password) throws IOException {
    HttpPost post = new HttpPost(appManager.getProperty("web.baseUrl") + "/login.php");
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));

    post.setEntity(new UrlEncodedFormEntity(params));
    CloseableHttpResponse response = httpClient.execute(post);
    String body = getTextFrom(response);
    return body.contains(String.format("<span id=\"logged-in-user\">%s</span>", username));
//    return body.contains("You should disable the default 'administrator' account or change its password.");
  }

  public boolean isLoggedInAs(String username) throws IOException {
    HttpGet get = new HttpGet(appManager.getProperty("web.baseUrl") + "/my_view_page.php");
    CloseableHttpResponse response = httpClient.execute(get);
    String body = getTextFrom(response);
    return body.contains(String.format("<span id=\"logged-in-user\">%s</span>", username));
  }

  private String getTextFrom(CloseableHttpResponse response) throws IOException {
    try {
      return EntityUtils.toString(response.getEntity());
    } finally {
      response.close();
    }
  }
}
