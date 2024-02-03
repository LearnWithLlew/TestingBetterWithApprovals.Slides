package org.learnwithllew.helper;

import org.learnwithllew.BotAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(List<String> words, BotAction response) {
        TrieNode current = root;
        for (String word : words) {
            current.children.putIfAbsent(word, new TrieNode());
            current = current.children.get(word);
        }
        current.response = response;
    }

    public BotAction search(List<String> messages) {
        TrieNode current = root;
        for (String word : messages) {
            if (!current.children.containsKey(word)) {
                return null;
            }
            current = current.children.get(word);
        }
        return current.response;
    }

    private static class TrieNode {
        Map<String, TrieNode> children;
        BotAction response;

        public TrieNode() {
            children = new HashMap<>();
            response = null;
        }
    }
}
