package com.parkson.assignment.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** The Logger utils is utility service for logging */
public class LoggerUtils {

  private static Logger LGR = LoggerFactory.getLogger(LoggerUtils.class);

  /**
   * Concat string.
   *
   * @param args the args
   * @return the string
   */
  public static String concat(String... args) {

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < args.length; i++) {
      builder.append(args[i]);
    }
    return builder.toString();
  }

  /**
   * Debug.
   *
   * @param log the log
   * @param messages the messages
   */
  public static void debug(Logger log, String... messages) {
    log.debug(log.isDebugEnabled() ? LoggerUtils.concat(messages) : null);
  }

  /**
   * Warn.
   *
   * @param log the log
   * @param messages the messages
   */
  public static void warn(Logger log, String... messages) {
    log.warn(log.isWarnEnabled() ? LoggerUtils.concat(messages) : null);
  }
}
