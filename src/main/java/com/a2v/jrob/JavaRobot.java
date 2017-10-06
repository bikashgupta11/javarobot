package com.a2v.jrob;

import java.net.URISyntaxException;
import java.util.Properties;

import org.python.core.Py;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

public class JavaRobot {
  
  private PythonInterpreter interp;
  
  public JavaRobot() {
    Properties properties = System.getProperties();
    properties.put("python.home", JavaRobot.class.getResource("/jython").getPath());
    PythonInterpreter.initialize(System.getProperties(), properties, new String[0]);
    interp = new PythonInterpreter();
    
    PySystemState sys = Py.getSystemState();
    System.out.println("Python Path - " + sys.path.toString());
  }
  
  public void runRobot(String robotPath) throws URISyntaxException {
    try {
      robotPath = robotPath.replaceAll("\\\\", "/");
      System.out.println(robotPath);
      String outputFileName = "output-file.txt";
      interp.exec("import robot");
      System.out.println("Robot imported");
      interp.exec("from robot import version");
      interp.exec("rf_version = version.VERSION");
      System.out.println(interp.get("rf_version").toString());
      interp.exec("from robot import run");
      interp.exec("run('" + robotPath + "', outputdir='" + JavaRobot.class.getResource("/jython").getPath()
          + "', listener='com.a2v.jrob.JavaListener:12:23', variable='__FILE_NAME__:" + outputFileName
          + "', report='NONE', log='NONE', output='NONE')");
    } finally {
      interp.cleanup();
    }
    
  }
  
}
