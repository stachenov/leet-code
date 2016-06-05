/*
 * (C) Sergey A. Tachenov
 * This thing belongs to public domain. Really.
 */
package name.tachenov.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Android Unlock Patterns #351.
 * @author Sergey A. Tachenov
 */
public class AndroidUnlockPatterns {
    private int numberOfPatterns;

    public int numberOfPatterns(int m, int n) {
        numberOfPatterns = 0;
        for (int i = m; i <= n; ++i) {
            numberOfPatterns(i);
        }
        return numberOfPatterns;
    }
    
    private void numberOfPatterns(int n) {
        TouchableKeypad keypad = new TouchableKeypad();
        for (Iterator<KeypadKey> it = new AllKeysIterator(); it.hasNext(); ) {
            KeypadKey key = it.next();
            keypad.touch(key);
            numberOfPatterns(keypad, n - 1);
            keypad.untouch(key);
        }
    }
    
    private void numberOfPatterns(TouchableKeypad keypad, int touchesRemaining) {
        if (touchesRemaining == 0) {
            ++numberOfPatterns;
            return;
        }
        for (Iterator<KeypadKey> it = new AllKeysIterator(); it.hasNext(); ) {
            KeypadKey key = it.next();
            if (keypad.isValidMove(key)) {
                keypad.touch(key);
                numberOfPatterns(keypad, touchesRemaining - 1);
                keypad.untouch(key);
            }
        }
    }
    
}

class TouchableKeypad {
    static final int HEIGHT = 3;
    static final int WIDTH = 3;
    private final boolean[][] touched = new boolean[HEIGHT][WIDTH];
    private final Deque<KeypadKey> touchedKeys = new ArrayDeque<>(HEIGHT * WIDTH);

    void touch(KeypadKey key) {
        touched[key.getRow()][key.getColumn()] = true;
        touchedKeys.push(key);
    }

    void untouch(KeypadKey key) {
        KeypadKey popped = touchedKeys.pop();
        assert popped.equals(key);
        touched[key.getRow()][key.getColumn()] = false;
    }

    boolean isTouched(KeypadKey key) {
        return touched[key.getRow()][key.getColumn()];
    }

    private KeypadKey lastTouchedKey() {
        return touchedKeys.peek();
    }

    boolean isValidMove(KeypadKey key) {
        KeypadKey lastKey = lastTouchedKey();
        return !isTouched(key) && doesntCrossNotTouched(lastKey, key);
    }

    private boolean doesntCrossNotTouched(KeypadKey lastKey, KeypadKey key) {
        return !isStraightLine(lastKey, key) || allIntermediatePointsAreTouched(lastKey, key);
    }

    private static boolean isStraightLine(KeypadKey fromKey, KeypadKey toKey) {
        final int rowDistance = Math.abs(toKey.getRow() - fromKey.getRow());
        final int columnDistance = Math.abs(toKey.getColumn() - fromKey.getColumn());
        return rowDistance == 0 || columnDistance == 0 || rowDistance == columnDistance;
    }

    private boolean allIntermediatePointsAreTouched(KeypadKey fromKey, KeypadKey toKey) {
        for (Iterator<KeypadKey> it = new StraightLineExclusiveIterator(fromKey, toKey); it.hasNext(); ) {
            KeypadKey key = it.next();
            if (!isTouched(key)) {
                return false;
            }
        }
        return true;
    }

}

class KeypadKey {
    private final int row;
    private final int column;

    public KeypadKey(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.row;
        hash = 89 * hash + this.column;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KeypadKey other = (KeypadKey) obj;
        return this.row == other.row && this.column == other.column;
    }
}

class AllKeysIterator implements Iterator<KeypadKey> {
    
    private int row = 0;
    private int column = 0;

    @Override
    public boolean hasNext() {
        return row < TouchableKeypad.HEIGHT;
    }

    @Override
    public KeypadKey next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        KeypadKey key = new KeypadKey(row, column);
        if (++column == TouchableKeypad.WIDTH) {
            column = 0;
            ++row;
        }
        return key;
    }
}

class StraightLineExclusiveIterator implements Iterator<KeypadKey> {

    private final KeypadKey toKey;
    private int row;
    private int column;
    private final int rowStep;
    private final int columnStep;

    public StraightLineExclusiveIterator(KeypadKey fromKey, KeypadKey toKey) {
        this.row = fromKey.getRow();
        this.column = fromKey.getColumn();
        this.rowStep = step(fromKey.getRow(), toKey.getRow());
        this.columnStep = step(fromKey.getColumn(), toKey.getColumn());
        this.toKey = toKey;
        // Skip the first key, iterator is exclusive!
        row += rowStep;
        column += columnStep;
    }

    private static int step(int fromRow, int toRow) {
        return toRow == fromRow ? 0 : toRow > fromRow ? +1 : -1;
    }
    
    @Override
    public boolean hasNext() {
        return !(row == toKey.getRow() && column == toKey.getColumn());
    }

    @Override
    public KeypadKey next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        KeypadKey key = new KeypadKey(row, column);
        row += rowStep;
        column += columnStep;
        return key;
    }
}

