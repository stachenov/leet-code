/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author alqualos
 */
public class StringListCodec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        sb.append(strs.size()).append(':');
        for (String s : strs) {
            sb.append(s.length()).append(':');
        }
        for (String s : strs) {
            sb.append(s);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        java.util.StringTokenizer st = new java.util.StringTokenizer(s, ":", false);
        int dataStart = 0;
        String t = st.nextToken();
        dataStart += t.length() + 1;
        int count = Integer.parseInt(t);
        int[] lengths = new int[count];
        for (int i = 0; i < count; ++i) {
            t = st.nextToken();
            dataStart += t.length() + 1;
            lengths[i] = Integer.parseInt(t);
        }
        List<String> strings = new ArrayList<>(count);
        for (int pos = dataStart, i = 0; i < count; pos += lengths[i], ++i) {
            strings.add(s.substring(pos, pos + lengths[i]));
        }
        return strings;
    }

    public static void main(String[] args) {
        StringListCodec slc = new StringListCodec();
        slc.decode(slc.encode(Arrays.asList("")));
    }
}
