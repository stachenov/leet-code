/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import static java.lang.Math.*;

public class SortTransformedArray {

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] result = new int[nums.length];
        if (a == 0 && b >= 0) {
            justTransform(nums, b, c, result);
        } else if (a == 0 && b < 0) {
            transformInReverseOrder(nums, b, c, result);
        } else {
            quadraticTransform(nums, a, b, c, result);
        }
        return result;
    }

    private void justTransform(int[] nums, int b, int c, int[] result) {
        for (int i = 0; i < nums.length; ++i) {
            result[i] = b * nums[i] + c;
        }
    }

    private void transformInReverseOrder(int[] nums, int b, int c, int[] result) {
        for (int i = 0; i < nums.length; ++i) {
            result[result.length - 1 - i] = b * nums[i] + c;
        }
    }

    private void quadraticTransform(int[] nums, int a, int b, int c, int[] result) {
        boolean convex = a < 0;
        int iLeft = 0;
        int iRight = nums.length - 1;
        int iResult = convex ? 0 : nums.length - 1;
        int inc = iResult == 0 ? +1 : -1;
        while (iResult >= 0 && iResult < nums.length) {
            int yLeft = transform(nums[iLeft], a, b, c);
            int yRight = transform(nums[iRight], a, b, c);
            if (convex) {
                result[iResult] = min(yLeft, yRight);
            } else {
                result[iResult] = max(yLeft, yRight);
            }
            if (result[iResult] == yLeft) {
                ++iLeft;
            } else {
                --iRight;
            }
            iResult += inc;
        }
    }
    
    private static int transform(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
