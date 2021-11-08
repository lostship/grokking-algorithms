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
 * �ҿ�˹�����㷨��ʹ��̰���㷨����˼�룬�ڼ�Ȩͼ��weighted graph���в������·��
 * 
 * �ҿ�˹�����㷨ֻ�����������޻�ͼ��directed acyclic graph��
 * 
 * �ҿ�˹�����㷨Ҳ�������ڰ�����Ȩ�ߵ�ͼ����Ϊ����Ϊ�Ѿ�������Ľڵ���Ѿ��ҵ��ýڵ����·��������֮��������Ȩ�߷��ָ���·����Ҳ�����ظ�����֮ǰ�Ľڵ㣩
 * ������Ȩ�ߵ�ͼ����ʹ�ñ�����-�����㷨��Bellman-Ford������
 * 
 * ��ʵ�ֽ����˵����������Ѿ�������Ľڵ㣬����������������ڵ�ʱ�����˸��ŵ�·�����ͻὫ֮ǰ������Ľڵ��ٴηŵ���������еȴ�������˿��Դ���Ȩ��
 * ���Ǳ�ʵ��ͬ�����ܴ����л�ͼ����Ҫ�ǻ��а�����Ȩ�ߵ�������������������ջ�����A��B֮�������ѭ����
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
