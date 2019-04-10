/**
 * Singleton class means only 1 object of the class is created
 */
package com.designPatterns.singleton;

public class Logger {
  static Logger logger;
  private Logger() {

  }

  public static Logger getInstance() {
    if (logger == null) {
      logger = new Logger();
    }
    return logger;
  }
}
