package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by maksym on 9/12/16.
 */
@Table(name = "bugtracker_user_table")
@Entity
public class UserData {
  @Id
  @Column(name = "id")
  private int id = 0;

  @Column(name = "username")
  private String username = "";

  @Column(name = "email")
  private String email = "";

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }
}
