package helper;

import javafx.scene.input.DataFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public abstract class TimeZones {


    public static LocalDateTime toUtc(LocalDateTime dateTime) {
        ZoneId localId = ZoneId.systemDefault();
        ZoneId utcId = ZoneId.of("UTC");

        ZonedDateTime zLocal = dateTime.atZone(localId);
        ZonedDateTime zUtc = zLocal.withZoneSameInstant(utcId);
        LocalDateTime lUtc = zUtc.toLocalDateTime();


        return lUtc;
    }

    public static  LocalDateTime toLocal(LocalDateTime dateTime) {
        ZonedDateTime zOut = dateTime.atZone(ZoneId.of("UTC"));
        LocalDateTime zLocal = zOut.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

        return zLocal;
    }

    public static LocalDateTime toEST(LocalDateTime dateTime) {
        ZonedDateTime zUtc = dateTime.atZone(ZoneId.of("UTC"));
        ZonedDateTime zEst = zUtc.withZoneSameInstant(ZoneId.of("US/Eastern"));
        LocalDateTime lEst = zEst.toLocalDateTime();
        System.out.println(zUtc + "\n" + zEst + "\n" + lEst);

        return lEst;
    }

    public static LocalDateTime localToEst(LocalDateTime dateTime) {
        ZonedDateTime zLoc = dateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime zEst = zLoc.withZoneSameInstant(ZoneId.of("US/Eastern"));
        LocalDateTime lEst = zEst.toLocalDateTime();
        System.out.println(zLoc + "\n" + zEst + "\n" + lEst);

        return lEst;
    }
}
