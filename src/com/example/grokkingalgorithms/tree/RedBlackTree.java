package com.example.grokkingalgorithms.tree;

public class RedBlackTree {

    private enum Color {
        RED,
        BLACK
    }

    private class Node {
        private final int value;
        private Color color;
        private Node parent;
        private Node left;
        private Node right;

        public Node(int value, Color color) {
            this.value = value;
            this.color = color;
        }

        public void turnLeft() {
            if (this.right == NIL) {
                return;
            }

            Node parent = this.parent;
            Node right = this.right;
            Node rightLeft = right.left;

            if (parent == null) {
                RedBlackTree.this.root = right;
            } else {
                if (this == parent.left) {
                    parent.left = right;
                } else {
                    parent.right = right;
                }
                right.parent = parent;
                this.parent = right;
            }
            right.left = this;
            this.right = rightLeft;
        }

        public void turnRight() {
            if (this.left == NIL) {
                return;
            }

            Node parent = this.parent;
            Node left = this.left;
            Node leftRight = left.right;

            if (parent == null) {
                RedBlackTree.this.root = left;
            } else {
                if (this == parent.left) {
                    parent.left = left;
                } else {
                    parent.right = left;
                }
                left.parent = parent;
                this.parent = left;
            }
            left.right = this;
            this.left = leftRight;
        }
    }

    private Node root;
    private final Node NIL = new Node(0, Color.BLACK);

    public void add(int value) {
        Node position = get(value, root);
        if (position != null && position != NIL) {
            return;
        }

        Node node = new Node(value, Color.RED);
        node.left = NIL;
        node.right = NIL;

        if (position == null) {
            root = node;
            return;
        }

        if (position == NIL) {
            if (position.parent.value > value) {
                position.parent.left = node;
            } else {
                position.parent.right = node;
            }
        }
    }

    public void remove(int value) {
        Node node = get(value, root);
        if (node == null || node == NIL) {
            return;
        }
    }

    public Node get(int value) {
        Node node = get(value, root);
        if (node == NIL) {
            return null;
        }
        return node;
    }

    public Node get(int value, Node node) {
        if (node == null || node == NIL) {
            return node;
        }
        if (value == node.value) {
            return node;
        }
        return get(value, value < node.value ? node.left : node.right);
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        print(sb, root);
        System.out.println(sb.toString());
    }

    private void print(StringBuilder sb, Node node) {
        if (node == null) {
            return;
        }

        sb.append(node == NIL ? "nil" : node.value).append(Color.RED == node.color ? "r" : "b");

        if (node == NIL) {
            return;
        }

        sb.append(":{");
        print(sb, node.left);
        sb.append(",");
        print(sb, node.right);
        sb.append("}");
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.print();

        tree.root.turnRight();
        tree.print();
    }

}
