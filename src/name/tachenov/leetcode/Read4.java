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
public class Read4 {
    private final char[] buffer = new char[4];
    private int bufferStart = 0;
    private int bufferSize = 0;
    
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int pos = 0;
        while (pos < n) {
            if (bufferSize == 0) {
                //bufferSize = read4(buffer);
                bufferStart = 0;
            }
            if (bufferSize == 0)
                return pos;
            int toRead = Math.min(n - pos, bufferSize);
            System.arraycopy(buffer, bufferStart, buf, pos, toRead);
            bufferStart += toRead;
            bufferSize -= toRead;
            pos += toRead;
        }
        return pos;
    }
}
