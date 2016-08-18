package ru.stqa.pft.sandbox;

import java.util.*;

/**
 * Created by maksym on 8/18/16.
 */
public class Collections {

  public static void main(String[] args) {

    //Arrays

//    String[] langs = new String[4];
//    langs[0] = "Java";
//    langs[1] = "C#";
//    langs[2] = "Python";
//    langs[3] = "PHP";

//    String[] langs = {"Java", "C#", "Python", "PHP"};

//    for (int i = 0; i < langs.length; i++){
//      System.out.println("I want to learn " + langs[i]);
//    }

//    for (String i : langs) {
//      System.out.println("I want to learn " + i);
//    }


    // Collections

    //List

//    List<String> languages = new ArrayList<>();
//    languages.add("Java");
//    languages.add("C#");
//    languages.add("Python");

//    List<String> languages = Arrays.asList(langs);
//    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
//    List languages = Arrays.asList("Java", "C#", "Python", "PHP");

//    Iterator iterator = languages.iterator();

//    for (String i : languages) {
//      System.out.println("I want to learn " + i);
//    }

//    for (int i = 0; i < languages.size(); i++) {
//      System.out.println("I want to learn " + languages.get(i));
//    }

//    while (iterator.hasNext()) {
//      String language = (String) iterator.next();
//      System.out.println("I want to learn " + language);
//      if(language.equals("Java")){
//        System.out.println(language + " is the best!");
//      }
//    }

//    for (Object i : languages) {
//      System.out.println("I want to learn " + i);
//    }

      //Map
    
//    Map <String, String> pets = new HashMap<>();
//    pets.put("Tom", "cat");
//    pets.put("Jerry", "mouse");
//
//    for (Map.Entry<String,String> entry : pets.entrySet()){
//      if (entry.getKey().equals("Tom")){
//        entry.setValue("Big Cat");
//      }
//      if (entry.getKey().equals("Jerry")){
//        entry.setValue("Small Mouse");
//      }
//      System.out.println(entry.getKey() + " is " + entry.getValue());
//    }

//    pets.forEach((k,v) -> System.out.println(k + " is " + v));

//    Iterator entries = pets.entrySet().iterator();
//    while (entries.hasNext()){
//      Map.Entry thisEntry = (Map.Entry) entries.next();
//      Object key = thisEntry.getKey();
//      Object value = thisEntry.getValue();
//      System.out.println(key + " is " + value);
//    }

//    for (Iterator entries = pets.entrySet().iterator(); entries.hasNext();){
//      Map.Entry thisEntry = (Map.Entry) entries.next();
//      Object key = thisEntry.getKey();
//      Object value = thisEntry.getValue();
//      System.out.println(key + " is " + value);
//    }

  }
}
