package com.example.grokkingalgorithms.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 狄克斯特拉算法，使用贪心算法基本思想，在加权图（weighted graph）中查找最短路径
 * 
 * 狄克斯特拉算法只适用于有向无环图（directed acyclic graph）
 * 
 * 狄克斯特拉算法也不能用于包含负权边的图（因为其认为已经处理过的节点就已经找到该节点最短路径，即便之后遇到负权边发现更短路径，也不会重复处理之前的节点）
 * 包含负权边的图可以使用贝尔曼-福德算法（Bellman-Ford）处理
 * 
 * 本实现进行了调整，对于已经处理过的节点，如果后续处理其他节点时发现了更优的路径，就会将之前处理过的节点再次放到任务队列中等待处理，因此可以处理负权边
 * 但是本实现同样不能处理有环图（主要是环中包含负权边的情况），如下例，最终会陷入A和B之间的无限循环：
 * start->A=1, A->end=1, A->B=0, B->A=-1
 */
public class Dijkstra {
    public static void main(String[] args) {
        Station start = new Station("start");
        Station end = new Station("end");
        buildGraph(start, end);
        testDijkstra(start, end);
    }

    private static void buildGraph(Station start, Station end) {
        Station a = new Station("A");
        Station b = new Station("B");

        start.neighbor(a, 6);
        start.neighbor(b, 2);
        a.neighbor(end, 1);
        b.neighbor(a, 3);
        b.neighbor(end, 5);
    }

    private static void testDijkstra(Station start, Station end) {
        Map<Station, BestPath> paths = new HashMap<>();
        paths.put(start, new BestPath(start, null, 0));
        paths.put(end, new BestPath(end, null, Integer.MAX_VALUE));

        LinkedList<Station> stations = new LinkedList<>();
        stations.add(start);

        while (!stations.isEmpty()) {
            Station station = stations.pollFirst();
            if (station == end) {
                continue;
            }

            BestPath startToStationPath = paths.get(station);
            int startToStationDistance = startToStationPath.distance;

            for (Entry<Station, Integer> entry : station.neighbors.entrySet()) {
                Station neighbor = entry.getKey();
                int stationToNeighborDistance = entry.getValue();
                int startToStationToNeighborDistance = startToStationDistance + stationToNeighborDistance;

                BestPath startToNeighborPath = paths.get(neighbor);
                if (startToNeighborPath == null) {
                    startToNeighborPath = new BestPath(neighbor, station, startToStationToNeighborDistance);
                    paths.put(neighbor, startToNeighborPath);
                    stations.add(neighbor);
                    continue;
                }

                if (startToStationToNeighborDistance >= startToNeighborPath.distance) {
                    continue;
                }

                startToNeighborPath.distance = startToStationToNeighborDistance;
                startToNeighborPath.prev = station;
                stations.add(neighbor);
            }

            stations.sort((s1, s2) -> Integer.compare(paths.get(s1).distance, paths.get(s2).distance));
        }

        BestPath bestPath = paths.get(end);
        List<BestPath> bestPathStations = Stream.iterate(bestPath, p -> p != null, p -> paths.get(p.prev))
                .collect(Collectors.toList());
        Collections.reverse(bestPathStations);
        System.out.println(bestPathStations.stream().map(p -> p.station.name).collect(Collectors.joining(" -> ")));
        System.out.println(bestPath.distance);
    }

    private static class Station {
        private final String name;
        private Map<Station, Integer> neighbors = new HashMap<>();

        public Station(String name) {
            this.name = name;
        }

        public void neighbor(Station neighbor, int distance) {
            neighbors.put(neighbor, distance);
        }
    }

    private static class BestPath {
        private Station prev;
        private Station station;
        private Integer distance;

        public BestPath(Station station, Station prev, int distance) {
            this.station = station;
            this.prev = prev;
            this.distance = distance;
        }
    }
}
