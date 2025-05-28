package model;

import java.util.*;

public class Trie {
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }

    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        node.isEndOfWord = true;
    }

    public List<String> searchPrefix(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) return Collections.emptyList();
        }
        List<String> results = new ArrayList<>();
        collectAllWords(node, new StringBuilder(prefix), results);
        return results;
    }

    private void collectAllWords(TrieNode node, StringBuilder prefix, List<String> results) {
        if (node.isEndOfWord) results.add(prefix.toString());
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            prefix.append(entry.getKey());
            collectAllWords(entry.getValue(), prefix, results);
            prefix.setLength(prefix.length() - 1);
        }
    }
}