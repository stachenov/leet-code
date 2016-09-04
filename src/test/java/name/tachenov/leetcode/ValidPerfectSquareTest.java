/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.*;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

/**
 *
 * @author Sergey A. Tachenov
 */
public class ValidPerfectSquareTest {
    
    @Test(dataProvider = "nums")
    public void isPerfectSquare(int num) {
        ValidPerfectSquare vps = new ValidPerfectSquare();
        assertThat(vps.isPerfectSquare(num))
                .isEqualTo((long) Math.pow(Math.round(Math.sqrt(num)), 2) == num);
    }
    
    @DataProvider
    public Iterator<Object[]> nums() {
        List<Integer> nums = new ArrayList<>();
        for (int i = -1; i <= 20; ++i)
            nums.add(i);
        nums.add(2147483647);
        return nums.stream().map(i -> new Object[] {i}).iterator();
    }
    
}
