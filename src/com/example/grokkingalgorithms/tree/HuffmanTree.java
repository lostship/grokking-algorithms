package com.example.grokkingalgorithms.tree;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.example.grokkingalgorithms.util.ArrayUtils;

public class HuffmanTree {

    private Node root;
    private Map<Character, Node> leafMap;

    public static void main(String[] args) throws IOException {
        String text = "aaabbcdef";
        HuffmanTree huffman = HuffmanTree.instanceOf(text);
        byte[] bytes = huffman.encode(text);
        String decode = huffman.decode(bytes);

        // 原本如果等长编码，6个码元编码长度至少需要4bits，整个字符串长36bits
        // 使用哈夫曼编码后整个字符串长只有22bits
        ArrayUtils.print(bytes);
        System.out.println(bytes.length);
        System.out.println(decode);
        System.out.println(decode.equals(text));

        System.out.println(huffman.leafMap.get('a').height);
        System.out.println(huffman.leafMap.get('b').height);
        System.out.println(huffman.leafMap.get('c').height);
    }

    private HuffmanTree() {}

    private HuffmanTree(String text) {
        init(text);
    };

    public static HuffmanTree instanceOf(String text) {
        return new HuffmanTree(text);
    }

    public byte[] encode(String text) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0, len = text.length(); i < len; i++) {
            out.write(getHuffmanCoding(text.charAt(i)));
        }
        return out.toByteArray();
    }

    public String decode(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        Node node = root;
        Node child;
        for (byte b : bytes) {
            if (b == 0) {
                child = node.left;
            } else {
                child = node.right;
            }

            if (child != null) {
                node = child;
            }

            if (node.left == null && node.right == null) {
                sb.append(node.value);
                node = root;
                child = null;
            }
        }

        return sb.toString();
    }

    private void init(String text) {
        if (text == null || text.length() < 1) {
            throw new IllegalArgumentException("The text should not be empty.");
        }

        Map<Character, Node> leafMap = new HashMap<>();
        LinkedList<Node> leafList = new LinkedList<>();

        for (int i = 0, len = text.length(); i < len; i++) {
            char c = text.charAt(i);

            Node leaf = leafMap.get(c);
            if (leaf == null) {
                leaf = new Node();
                leaf.value = c;
                leaf.weight = 0;
                leaf.height = 0;
                leafList.add(leaf);
                leafMap.put(c, leaf);
            }
            leaf.weight += 1;
        }

        Node root = createHuffmanTree(leafList);

        this.root = root;
        this.leafMap = leafMap;
    }

    private Node createHuffmanTree(LinkedList<Node> leafList) {
        if (leafList.size() == 1) {
            return leafList.getFirst();
        }

        mergeTwoSmallests(leafList);
        return createHuffmanTree(leafList);
    }

    private void mergeTwoSmallests(LinkedList<Node> leafList) {
        Node small1 = null;
        Node small2 = null;
        for (Node node : leafList) {
            if (small1 == null) {
                small1 = node;
                continue;
            }
            if (small2 == null) {
                small2 = node;
                continue;
            }

            if (small1.weight > node.weight || small2.weight > node.weight) {
                if (small1.weight > small2.weight) {
                    small1 = node;
                } else {
                    small2 = node;
                }
            }
        }

        leafList.remove(small1);
        leafList.remove(small2);

        Node parent = new Node();
        parent.left = small1;
        small1.parent = parent;
        parent.right = small2;
        small2.parent = parent;
        parent.weight = small1.weight + small2.weight;
        leafList.add(parent);
    }

    private byte[] getHuffmanCoding(char c) {
        Node node = leafMap.get(c);
        if (node.parent == null) {
            return new byte[] { 0 };
        }

        if (node.height == 0) {
            Node temp = node;
            while (temp.parent != null) {
                temp = temp.parent;
                node.height += 1;
            }
        }

        byte[] bytes = new byte[node.height];
        int i = node.height - 1;
        while (node.parent != null) {
            if (node == node.parent.left) {
                bytes[i] = (byte) 0;
            } else {
                bytes[i] = (byte) 1;
            }

            node = node.parent;
            i--;
        }

        return bytes;
    }

    private static class Node {

        private char value;
        private int weight;
        private int height;
        private Node parent;
        private Node left;
        private Node right;

    }

}
