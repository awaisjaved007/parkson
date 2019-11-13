package com.parkson.assignment.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

  private static Logger LGR = LoggerFactory.getLogger(LoggerUtils.class);

  public static String concat(String... args) {

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < args.length; i++) {
      builder.append(args[i]);
    }
    return builder.toString();
  }

  public static void debug(Logger log, String ... messages) {
      log.debug(log.isDebugEnabled() ? LoggerUtils.concat(messages):null);

  }
  public static void warn(Logger log, String ... messages) {
    log.warn(log.isWarnEnabled() ? LoggerUtils.concat(messages):null);

  }
}
