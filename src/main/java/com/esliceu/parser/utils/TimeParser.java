package com.esliceu.parser.utils;

import org.joda.time.DateTime;

public interface TimeParser {

    DateTime addTime (Integer amount,String startingHour);

    String formatTime(DateTime time);

}
