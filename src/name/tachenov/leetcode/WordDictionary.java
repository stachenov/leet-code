/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.tachenov.leetcode;

import java.util.Arrays;

/**
 *
 * @author alqualos
 */
public class WordDictionary {
    private static final int LETTERS = 26;
    private static final char A = 'a';
    
    private TrieNode[] roots = new TrieNode[32]; // longest "normal" English word is about 30 chars

    // Adds a word into the data structure.
    public void addWord(String word) {
        if (word.length() >= roots.length) {
            roots = Arrays.copyOf(roots, Math.max(roots.length * 2, word.length()));
        }
        TrieNode node = roots[word.length()];
        if (node == null) {
            node = roots[word.length()] = new TrieNode();
        }
        for (char c : word.toCharArray()) {
            TrieNode child = node.children[c - A];
            if (child == null) {
                child = node.children[c - A] = new TrieNode();
                node.chars[node.size++] = c;
            }
            node = child;
        }
        node.word = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        if (word.length() >= roots.length) {
            return false;
        }
        TrieNode root = roots[word.length()];
        return root == null ? false : search(root, word, 0);
    }
    
    private static boolean search(TrieNode root, String word, int pos) {
        if (pos == word.length()) {
            return root.word;
        } else {
            char c = word.charAt(pos);
            if (c == '.') {
                boolean found = false;
                for (int i = 0; i < root.size; ++i) {
                    if (search(root.children[root.chars[i] - A], word, pos + 1)) {
                        found = true;
                        break;
                    }
                }
                return found;
            } else {
                TrieNode child = root.children[c - A];
                return child == null ? false : search(child, word, pos + 1);
            }
        }
    }
    
    private static class TrieNode {
        final TrieNode[] children = new TrieNode[LETTERS];
        boolean word = false;
        final char[] chars = new char[LETTERS];
        int size = 0;
    }
    
    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("aa");
        System.out.println(wd.search("aa"));
        System.out.println(wd.search(".b"));
        System.out.println(wd.search("."));
    }
}
