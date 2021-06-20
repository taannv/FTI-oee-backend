package com.dds.oee.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Author : duybv
 * Mar 28, 2019
 */

public class DateUtils {

  private static final ZoneId ZONE_ID_HCM = ZoneId.of("Asia/Ho_Chi_Minh");

  public static String formatDate(LocalDateTime time) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formatDateTime = time.format(formatter);
    return formatDateTime;
  }

  public static LocalDateTime atStartOfDay(Long time) {
    return at(time).with(LocalTime.MIN);
  }

  public static LocalDateTime atEndOfDay(Long time) {
    return at(time).with(LocalTime.MAX);
  }

  public static LocalDateTime at(Long time) {
    return Instant.ofEpochMilli(time)
            .atZone(ZONE_ID_HCM)
            .toLocalDateTime();
  }

  public static LocalDateTime now() {
    return LocalDateTime.now(ZONE_ID_HCM);
  }

  public static Date currentTime() {
    return Date.from(LocalDateTime.now().atZone(ZONE_ID_HCM).toInstant());
  }

  public static Date endOfCurrentDay() {
    return Date.from(LocalDateTime.now().with(LocalTime.MAX).atZone(ZONE_ID_HCM).toInstant());
  }

  public static long toMillis(LocalDateTime localDateTime) {
    return localDateTime.atZone(ZONE_ID_HCM).toEpochSecond();
  }

  public static void main(String[] args) {
    System.out.println(atStartOfDay(1578121675049l));
  }
  
}
