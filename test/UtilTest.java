import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;

public class UtilTest {
    @Test
    public void should_zero_count() {
        Assert.assertEquals(0, Util.numberDayOfWeekInDays(1, 2, 6));
    }

    @Test
    public void should_one_count() {
        Assert.assertEquals(1, Util.numberDayOfWeekInDays(1, 1, 7));
        Assert.assertEquals(1, Util.numberDayOfWeekInDays(1, 2, 13));
    }

    @Test
    public void should_two_count() {
        Assert.assertEquals(2, Util.numberDayOfWeekInDays(1, 1, 8));
        Assert.assertEquals(2, Util.numberDayOfWeekInDays(1, 3, 13));
    }

    @Test
    public void should_two_count_friday() {
        Assert.assertEquals(2, Util.numberDayOfWeekInDays(5, 1, 12));
        Assert.assertEquals(2, Util.numberDayOfWeekInDays(5, 6, 14));
    }

    @Test
    public void should_period_days() {
        Assert.assertEquals(1, Period.between(LocalDate.of(2017, 11, 1), LocalDate.of(2017, 11, 1)).getDays() + 1);
    }
}