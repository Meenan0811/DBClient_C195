package helper;

import javafx.scene.input.DataFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Contains method to convert local time to EST
 * @author Matthew Meenan
 */
public abstract class TimeZones {


    /**
     * Converts localDateTime object to EST
     * @param dateTime
     * @return
     */
    public static LocalDateTime localToEst(LocalDateTime dateTime) {
        ZonedDateTime zLoc = dateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime zEst = zLoc.withZoneSameInstant(ZoneId.of("US/Eastern"));
        LocalDateTime lEst = zEst.toLocalDateTime();
        System.out.println(zLoc + "\n" + zEst + "\n" + lEst);

        return lEst;
    }
}
