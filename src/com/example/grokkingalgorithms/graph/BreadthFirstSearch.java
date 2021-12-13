package com.example.grokkingalgorithms.graph;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 广度优先搜索，在非加权图（unweighted graph）中查找最短路径
 */
public class BreadthFirstSearch {
    public static void main(String[] args) {
        Person you = new Person("Tom");
        buildGraph(you);
        breadthFirstSearch(you, p -> "Peggy".equals(p.name));
    }

    private static void buildGraph(Person you) {
        Person alice = new Person("Alice");
        Person bob = new Person("Bob");
        Person claire = new Person("Claire");

        Person anuj = new Person("Anuj");
        Person jonny = new Person("Jonny");
        Person peggy = new Person("Peggy");
        Person thom = new Person("Thom");

        you.friends(alice, bob, claire);

        alice.friends(you, peggy);
        bob.friends(you, anuj, peggy);
        claire.friends(you, jonny, thom);

        anuj.friends(bob);
        jonny.friends(claire);
        peggy.friends(alice, bob);
        thom.friends(claire);
    }

    private static void breadthFirstSearch(Person you, Predicate<Person> predicate) {
        GraphNode<Person> result = null;
        int maxSearchDeepth = 2;
        LinkedList<GraphNode<Person>> nodes = new LinkedList<>();
        Set<Person> searched = new HashSet<>();

        GraphNode<Person> root = new GraphNode<>(null, you);
        searched.add(you);

        for (Person f : you.friends) {
            nodes.add(new GraphNode<>(root, f));
        }

        while (!nodes.isEmpty()) {
            GraphNode<Person> node = nodes.pollFirst();

            if (searched.contains(node.value)) {
                continue;
            }
            searched.add(node.value);

            if (predicate.test(node.value)) {
                result = node;
                break;
            }

            if (node.level < maxSearchDeepth) {
                for (Person f : node.value.friends) {
                    if (!searched.contains(f)) {
                        nodes.add(new GraphNode<>(node, f));
                    }
                }
            }
        }

        if (result == null) {
            System.out.println("not found");
            return;
        }

        Stream.iterate(result, node -> node != null, node -> node.prev)
                .sorted(Comparator.comparingInt(node -> node.level))
                .forEach(node -> System.out.println(node.level + " " + node.value.name));
    }

    private static class GraphNode<T> {
        private final GraphNode<T> prev;
        private final int level;
        private final T value;

        public GraphNode(GraphNode<T> prev, T value) {
            this.prev = prev;
            this.level = prev != null ? prev.level + 1 : 0;
            this.value = value;
        }
    }

    private static class Person {
        private final String name;
        private List<Person> friends;

        public Person(String name) {
            this.name = name;
        }

        public void friends(Person... friends) {
            this.friends = new LinkedList<Person>();
            Collections.addAll(this.friends, friends);
        }
    }
}
