/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

public class UTF8Validation {
    public boolean validUtf8(int[] data) {
        int len = 0;
        for (int b : data) {
            if ((b & 0x80) == 0) {
                if (len != 0)
                    return false;
            } else {
                if (len == 0) {
                    len = len(b);
                    if (len == 8 || len == 1)
                        return false;
                    --len;
                } else {
                    if ((b & 0b1100_0000) != 0b1000_0000)
                        return false;
                    --len;
                }
            }
        }
        return len == 0;
    }

    private int len(int b) {
        int len = 0;
        for (int i = 7; i >= 0; --i) {
            if ((b & (1 << i)) == 0)
                break;
            ++len;
        }
        return len;
    }
}
