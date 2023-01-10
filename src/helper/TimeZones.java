package helper;

import java.time.*;

public abstract class TimeZones {

    public static LocalDateTime toUtc(LocalDateTime dateTime) {
        ZoneId zId = ZoneId.systemDefault();
        ZonedDateTime zLocal = ZonedDateTime.ofLocal(dateTime, zId, ZoneOffset.UTC);
        ZonedDateTime zUtc = zLocal.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime lUtc = zUtc.toLocalDateTime();

        return lUtc;
    }

    public static  LocalDateTime toLocal(LocalDateTime dateTime) {
        ZoneId zId = ZoneId.systemDefault();
        ZonedDateTime zOut = dateTime.atZone(ZoneId.of("UTC"));
        ZonedDateTime zLocal = zOut.withZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime local = zLocal.toLocalDateTime();

        return local;
    }
}
