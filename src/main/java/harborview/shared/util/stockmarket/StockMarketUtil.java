package harborview.shared.util.stockmarket;

import harborview.shared.dto.OptionType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;

public class StockMarketUtil {

    public static LocalDate thirdFriday(int year, int month) {
        return LocalDate.of(year, month, 1).
                with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.FRIDAY));
    }

    public static LocalDate thirdFriday(String euronextMonthYear) {
        return null;
    }

    public static String stockMarketTickerFor(int oid, int year, int month, OptionType ot) {
        var company = mapOid(oid);
        var ms = mapMonth(month, ot);
        return "";
    }
    private static String mapYear(int year) {
        switch (year) {
            case 2026: return ""
        }
    }

    private static String mapMonth(int month, OptionType ot) {
        if (ot == OptionType.CALL) {
            switch (month) {
                case 1: return "A";
                case 2: return "B";
                case 3: return "C";
                case 4: return "D";
                case 5: return "E";
                case 6: return "F";
                case 7: return "G";
                case 8: return "H";
                case 9: return "I";
                case 10: return "J";
                case 11: return "K";
                case 12: return "L";
            }
        }
        else {
            switch (month) {
                case 1: return "M";
                case 2: return "N";
                case 3: return "O";
                case 4: return "P";
                case 5: return "Q";
                case 6: return "R";
                case 7: return "S";
                case 8: return "T";
                case 9: return "U";
                case 10: return "V";
                case 11: return "W";
                case 12: return "X";
            }
        }
        throw new RuntimeException("Month should be between 1 and 12");
    }


    private static final Map<Integer,String> oid2str = Map.ofEntries(
            Map.entry(18,"AKSO"),
            Map.entry(27,"BAKKA"),
            Map.entry(26, "BWLPG"),
            Map.entry(19, "DNB"),
            Map.entry(20, "DNO"),
            Map.entry(2, "EQNR"),
            Map.entry(21, "GJF"),
            Map.entry(28, "GOGL"),
            Map.entry(1,  "NHY"),
            Map.entry(29, "NAS"),
            Map.entry(7,  "OBX"),
            Map.entry(9,  "ORK"),
            //Map.entry(12, "PGS"),
            Map.entry(14, "STB"),
            Map.entry(23, "SUBC"),
            Map.entry(6,  "TEL"),
            Map.entry(16, "TGS"),
            Map.entry(17, "TOM"),
            Map.entry(3,  "YAR")
    );
    private static final Map<String, Integer> str2oid = Map.ofEntries(
            Map.entry("AKSO", 18),
            Map.entry("BAKKA", 27),
            Map.entry("BWLPG", 26),
            Map.entry("DNB", 19),
            Map.entry("DNO", 20),
            Map.entry("EQNR", 2),
            Map.entry("GJF", 21),
            Map.entry("GOGL", 28),
            Map.entry("NHY", 1),
            Map.entry("NAS", 29),
            Map.entry("OBX", 7),
            Map.entry("ORK", 9),
            //Map.entry("PGS", 12),
            Map.entry("STB", 14),
            Map.entry("SUBC", 23),
            Map.entry("TEL", 6),
            Map.entry("TGS", 16),
            Map.entry("TOM", 17),
            Map.entry( "YAR", 3)
    );
    public static String mapOid(int value) {
        return oid2str.get(value);
    }
    public static Integer mapString(String value) {
        return str2oid.get(value);
    }
}

