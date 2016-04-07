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
class TrieNode {
    final TrieNode[] children = new TrieNode[26];
    boolean wordEnd = false;
    // Initialize your data structure here.
    public TrieNode() {
    }
}

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            TrieNode child = current.children[c - 'a'];
            if (child == null) {
                child = current.children[c - 'a'] = new TrieNode();
            }
            current = child;
        }
        current.wordEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = locate(word);
        return node != null && node.wordEnd;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = locate(prefix);
        return node != null;
    }
    
    private TrieNode locate(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            TrieNode child = current.children[c - 'a'];
            if (child == null)
                return null;
            current = child;
        }
        return current;
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("a"));
        System.out.println(trie.search("ab"));
        trie.insert("ab");
        System.out.println(trie.search("a"));
        System.out.println(trie.search("ab"));
        System.out.println(trie.startsWith("a"));
        System.out.println(trie.startsWith("ab"));
    }
}
