package com.a2v.jrob;

import java.io.IOException;
import java.util.Map;

public class JavaListener {
  public static final int ROBOT_LISTENER_API_VERSION = 2;
  public static final String DEFAULT_FILENAME = "listen_java.txt";
  private DBOutputStream outfile = null;
  
  public JavaListener() {}
  
  public JavaListener(String id1, String id2) throws IOException {
    System.out.println(id1 + " - " + id2);
    outfile = new DBOutputStream();
  }
  
  public void startSuite(String name, Map attrs) throws IOException {
    // outfile.write(name + " '" + attrs.get("doc") + "'\n");
  }
  
  public void startTest(String name, Map attrs) throws IOException {
    // outfile.write("- " + name + " '" + attrs.get("doc") + "' [ ");
    // List tags = (List) attrs.get("tags");
    // for (int i = 0; i < tags.size(); i++) {
    // outfile.write(tags.get(i) + " ");
    // }
    // outfile.write(" ] :: ");
    outfile.write("Name - " + name);
    outfile.write("Id - " + attrs.get("id"));
    outfile.write("Longname - " + attrs.get("longname"));
    outfile.write("Doc - " + attrs.get("doc"));
    outfile.write("Critical - " + attrs.get("critical"));
    outfile.write("Template - " + attrs.get("template"));
    outfile.write("Starttime - " + attrs.get("starttime"));
  }
  
  public void endTest(String name, Map attrs) throws IOException {
    // String status = attrs.get("status").toString();
    // if (status.equals("PASS")) {
    // outfile.write("PASS\n");
    // } else {
    // outfile.write("FAIL: " + attrs.get("message") + "\n");
    // }
    outfile.write("Name - " + name);
    outfile.write("Id - " + attrs.get("id"));
    outfile.write("Longname - " + attrs.get("longname"));
    outfile.write("Doc - " + attrs.get("doc"));
    outfile.write("Critical - " + attrs.get("critical"));
    outfile.write("Template - " + attrs.get("template"));
    outfile.write("Endtime - " + attrs.get("endtime"));
    outfile.write("Elapsedtime - " + attrs.get("elapsedtime"));
    outfile.write("Status - " + attrs.get("status"));
    outfile.write("Message - " + attrs.get("message"));
  }
  
  public void endSuite(String name, Map attrs) throws IOException {
    // outfile.write(attrs.get("status") + "\n" + attrs.get("message") + "\n");
  }
  
  public void logMessage(Map message) {
    // outfile.write("BAARLOG - " + message.get("message") + "\n");
  }
  
  public void close() {
    outfile.write("EXECUTION FINISHED\n");
  }
}
