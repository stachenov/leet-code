/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

/**
 *
 * @author alqualos
 */
public class NumWays {
    public int numWays(int n, int k) {
        if (n <= 0)
            return 0;
        else if (n == 1)
            return k;
        else if (n == 2)
            return k * k;
        int num1 = k * (k - 1), num2 = k - 1;
        for (int i = 3; i < n; ++i) {
            int num = (num1 + num2) * (k - 1);
            num2 = num1;
            num1 = num;
        }
        return (num1 + num2) * k;
    }
    
    public static void main(String[] args) {
        System.out.println(new NumWays().numWays(9, 2));
    }
}
