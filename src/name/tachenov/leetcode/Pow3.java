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
public class Pow3 {
    public boolean isPowerOfThree(int n) {
        if (n <= 0)
            return false;
        double log = Math.log(n) / Math.log(3);
        //System.out.println(log - Math.round(log));
        return Math.abs(log - Math.round(log)) < 1E-11;
    }
    
    public static void main(String[] args) {
        Pow3 pow3 = new Pow3();
        for (int n = 1, np = 0; n > np; np = n, n = n * 3) {
            //System.out.println("Checking " + n);
            if (!pow3.isPowerOfThree(n)) {
                System.out.println("Failed for " + n);
            }
            if (pow3.isPowerOfThree(n - 1)) {
                System.out.println("Failed for " + (n - 1));
            }
            if (pow3.isPowerOfThree(n + 1)) {
                System.out.println("Failed for " + (n + 1));
            }
        }
    }
}
