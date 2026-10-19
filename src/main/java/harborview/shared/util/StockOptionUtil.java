package harborview.shared.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class StockOptionUtil {
    public static LocalDate thirdFriday(int year, int month) {
        return LocalDate.of(year, month, 1).
                with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.FRIDAY));
    }

    public static LocalDate thirdFriday(String euronextMonthYear) {
        return null;
    }
}

