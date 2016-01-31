/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alqualos
 */
public class DiffWaysToCompute {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> results = new ArrayList<>();
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("\\b");
        diffWaysToCompute(scanner, 0, 1, results);
        return results;
    }
    
    private static void diffWaysToCompute(Scanner in, int sum, int prod, List<Integer> results) {
        int s = sum, p = prod;
        while (in.hasNext()) {
            if (in.hasNextInt()) {
                int num = in.nextInt();
                p *= num;
            } else {
                char op = in.next().charAt(0);
                switch (op) {
                    case '+':
                    case '-':
                        s += p;
                        p = op == '+' ? +1 : -1;
                        break;
                    case '*':
                        break; // just delimits numbers
                }
            }
        }
        results.add(s + p);
    }
    
    public static void main(String[] args) {
        DiffWaysToCompute dfc = new DiffWaysToCompute();
        System.out.println(dfc.diffWaysToCompute("1+2*3"));
    }
}
