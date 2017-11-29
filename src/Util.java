import java.time.LocalDate;

public class Util {
    public static int numberDayOfWeekInDays(int dayOfWeek, int startDayOfWeek, int days) {
        int count = 0;
        int star = startDayOfWeek;
        for (int i = 0; i < days; i++) {
            if (star == dayOfWeek) {
                count += 1;
            }
            star = (star + 1) % 7;
        }
        return count;
    }

    public static boolean inPeriod(LocalDate day, LocalDate start, LocalDate end) {
        return day.equals(start) || day.equals(end)
                || (day.isAfter(start) && day.isBefore(end));
    }
}
