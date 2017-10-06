package com.a2v.jrob;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class TestRobot {
  
  public static void main(String... args) {
    JavaRobot robot = new JavaRobot();
    try {
      URL url = TestRobot.class.getResource("/testcase");
      if (url == null) {
        System.out.println("Test Case is not present!!!");
      } else {
        File dir = new File(url.toURI());
        for (File nextFile : dir.listFiles()) {
          System.out.println(nextFile.getPath());
          robot.runRobot(nextFile.getPath());
        }
      }
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }
}
