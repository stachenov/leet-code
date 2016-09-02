/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

public class LoggerRateLimiterTest {

    private LoggerRateLimiter limiter;
    
    @BeforeMethod
    public void setUp() {
        limiter = new LoggerRateLimiter();
    }
    
    @Test
    public void shouldPrintFirstMessage() {
        assertThat(limiter.shouldPrintMessage(0, "message")).isTrue();
    }
    
    @Test(dataProvider = "timeIntervalsLessThan10")
    public void shouldNotPrintSecondMessage_IfLessThan10SecondsPassed(int interval) {
        limiter.shouldPrintMessage(0, "message");
        assertThat(limiter.shouldPrintMessage(interval, "message")).isFalse();
    }
    
    @DataProvider
    public Iterator<Object[]> timeIntervalsLessThan10() {
        return Arrays.asList(0, 1, 9)
                .stream()
                .map(i -> new Object[] {i})
                .iterator();
    }
    
    @Test(dataProvider = "timeIntervals10SecondsAndMore")
    public void shouldPrintSecondMessage_If10SecondsPassed(int interval) {
        limiter.shouldPrintMessage(0, "message");
        assertThat(limiter.shouldPrintMessage(interval, "message")).isTrue();
    }
    
    @DataProvider
    public Iterator<Object[]> timeIntervals10SecondsAndMore() {
        return Arrays.asList(10, 11, 100)
                .stream()
                .map(i -> new Object[] {i})
                .iterator();
    }
    
    @Test
    public void shouldSkipManyMessagesUntil10SecondsPass() {
        limiter.shouldPrintMessage(0, "message");
        for (int i = 0; i < 10; ++i) {
            assertThat(limiter.shouldPrintMessage(i, "message")).isFalse();
        }
        assertThat(limiter.shouldPrintMessage(10, "message")).isTrue();
    }
    
    @Test
    public void generalCase() {
        assertThat(limiter.shouldPrintMessage(0, "foo")).isTrue();
        assertThat(limiter.shouldPrintMessage(1, "bar")).isTrue();
        assertThat(limiter.shouldPrintMessage(1, "bar")).isFalse();
        assertThat(limiter.shouldPrintMessage(9, "foo")).isFalse();
        assertThat(limiter.shouldPrintMessage(9, "bar")).isFalse();
        assertThat(limiter.shouldPrintMessage(11, "bar")).isTrue();
        assertThat(limiter.shouldPrintMessage(10, "foo")).isTrue();
    }
    
}
