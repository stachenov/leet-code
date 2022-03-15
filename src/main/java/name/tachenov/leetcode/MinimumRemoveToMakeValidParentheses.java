package name.tachenov.leetcode;

public class MinimumRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        var runningSum = 0;
        var min = 0;
        for (int i = 0; i < s.length(); ++i) {
            var c = s.charAt(i);
            if (c == '(') {
                ++runningSum;
            } else if (c == ')') {
                --runningSum;
            }
            min = Math.min(min, runningSum);
        }
        var closingToRemove = -min;
        var openingToRemove = runningSum + closingToRemove;
        final var buffer = new StringBuilder(s);
        removeClosing(buffer, closingToRemove);
        removeOpening(buffer, openingToRemove);
        return buffer.toString();
    }

    private void removeClosing(StringBuilder buffer, int closingToRemove) {
        for (int i = 0, removed = 0; removed < closingToRemove && i < buffer.length(); ) {
            var c = buffer.charAt(i);
            if (c == ')') {
                buffer.deleteCharAt(i);
                ++removed;
            } else {
                ++i;
            }
        }
    }

    private void removeOpening(StringBuilder buffer, int openingToRemove) {
        for (int i = buffer.length() - 1, removed = 0; removed < openingToRemove && i >= 0; --i) {
            var c = buffer.charAt(i);
            if (c == '(') {
                buffer.deleteCharAt(i);
                ++removed;
            }
        }
    }
}
