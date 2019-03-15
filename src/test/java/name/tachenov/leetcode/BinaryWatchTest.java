package name.tachenov.leetcode;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BinaryWatchTest {
    @DataProvider
    public static Object[][] binaryWatchData() {
        return new Object[][] {
            {0, Collections.singletonList("0:00")},
            {1, Arrays.asList("1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32")},
            {2, Arrays.asList(
                    "10:00", "9:00", "8:32", "8:16", "8:08", "8:04", "8:02", "8:01",
                    "6:00",  "5:00", "4:32", "4:16", "4:08", "4:04", "4:02", "4:01",
                    "3:00",  "2:32", "2:16", "2:08", "2:04", "2:02", "2:01",
                    "1:32",  "1:16", "1:08", "1:04", "1:02", "1:01",
                    "0:48",  "0:40", "0:36", "0:34", "0:33",
                    "0:24",  "0:20", "0:18", "0:17",
                    "0:12",  "0:10", "0:09",
                    "0:06",  "0:05",
                    "0:03"
            )},
        };
    }

    @Test(dataProvider = "binaryWatchData")
    public void binaryWatch(int num, List<String> expected) {
        assertThat(new BinaryWatch().readBinaryWatch(num)).containsOnlyElementsOf(expected);
        assertThat(new BinaryWatch().readBinaryWatch(num)).doesNotHaveDuplicates();
    }
}
