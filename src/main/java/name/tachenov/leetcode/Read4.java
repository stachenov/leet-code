package name.tachenov.leetcode;

public abstract class Read4 {
    private final char[] buffer = new char[4];
    private int lenOfDataInBuffer = 0;
    private int posInBuffer = 0;
    
    public int read(char[] buf, int n) {
        int read = 0;
        while (read < n) {
            fillBufferIfNeeded();
            if (remainingInBuffer() == 0)
                break;
            read += readFromBuffer(buf, read, n - read);
        }
        return read;
    }

    private void fillBufferIfNeeded() {
        if (remainingInBuffer() == 0) {
            lenOfDataInBuffer = read4(buffer);
            posInBuffer = 0;
        }
    }

    private int remainingInBuffer() {
        return lenOfDataInBuffer - posInBuffer;
    }
    
    private int readFromBuffer(char[] buf, int offset, int n) {
        int toRead = Math.min(n, remainingInBuffer());
        System.arraycopy(buffer, posInBuffer, buf, offset, toRead);
        posInBuffer += toRead;
        return toRead;
    }
    
    abstract int read4(char[] buf);
}