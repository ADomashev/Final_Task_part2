package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.Clock.systemUTC;
import static java.time.format.DateTimeFormatter.ofPattern;

public class DateUtils {

    private DateUtils(){}

    public static final String DATE_PATTERN_TICKET = "yy-MM-dd";
    public static final String DATE_PATTERN_CALENDAR = "dd.MM.yyyy";

    public static String getCurrentDate(){
        return LocalDate.now(systemUTC()).format(ofPattern(DATE_PATTERN_CALENDAR));
    }

    public static LocalDate toLocalDate(String dateValue){
        return LocalDate.parse(dateValue, DateTimeFormatter.ofPattern(DATE_PATTERN_TICKET));
    }
}
