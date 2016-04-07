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
public class FindTheCelebrity {
    
    private boolean knows(int i, int j) {
        return (i == 0 && j == 1) || (i == 0 && j == 2) || (i == 1 && j == 0);
    }
    
    public int findCelebrity(int n) {
        boolean[] notCelebrity = new boolean[n];
        int notCelebrities = 0;
        OUTER:
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (notCelebrity[i] && notCelebrity[j]) {
                    continue;
                }
                if (knows(i, j)) {
                    if (!notCelebrity[i]) {
                        notCelebrity[i] = true;
                        if (++notCelebrities == n - 1) {
                            break OUTER;
                        }
                    }
                } else {
                    if (!notCelebrity[j]) {
                        notCelebrity[j] = true;
                        if (++notCelebrities == n - 1) {
                            break OUTER;
                        }
                    }
                }
            }
        }
        OUTER:
        if (notCelebrities < n - 1) {
            for (int i = 1; i < n; ++i) {
                for (int j = 0; j < i; ++j) {
                    if (notCelebrity[i] && notCelebrity[j]) {
                        continue;
                    }
                    if (knows(i, j)) {
                        if (!notCelebrity[i]) {
                            notCelebrity[i] = true;
                            if (++notCelebrities == n - 1) {
                                break OUTER;
                            }
                        }
                    } else {
                        if (!notCelebrity[j]) {
                            notCelebrity[j] = true;
                            if (++notCelebrities == n - 1) {
                                break OUTER;
                            }
                        }
                    }
                }
            }
        }
        if (notCelebrities == n - 1) {
            for (int i = 0; i < n; ++i) {
                if (!notCelebrity[i]) {
                    for (int j = 0; j < n; ++j) {
                        if (j == i) {
                            continue;
                        }
                        if (knows(i, j) || !knows(j, i)) {
                            return -1; // not the celebrity either
                        }
                    }
                    return i;
                }
            }
            throw new AssertionError();
        } else {
            return -1; // too many possible celebrities
        }
    }
    
    public static void main(String[] args) {
        FindTheCelebrity fc = new FindTheCelebrity();
        System.out.println(fc.findCelebrity(3));
    }
}
