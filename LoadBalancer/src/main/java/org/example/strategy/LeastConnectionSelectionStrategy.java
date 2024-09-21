package org.example.strategy;

import org.example.BackendInstance;

import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;

public class LeastConnectionSelectionStrategy implements SelectionStrategy {
    static class Tuple implements Comparable<Tuple> {
        private int count;
        private String ipAddress;

        public Tuple(int count, String ipAddress) {
            this.count = count;
            this.ipAddress = ipAddress;
        }

        @Override
        public String toString() {
            return "[" + count + ", " + ipAddress + "]";
        }

        @Override
        public int compareTo(Tuple tuple) {
            return this.count - tuple.count;
        }
    }

    @Override
    public BackendInstance select(Map<String, BackendInstance> instances) {
        PriorityQueue<Tuple> queueOfAddresses = new PriorityQueue<>();
        instances.forEach((key, value) -> queueOfAddresses.add(new Tuple(value.getConnectionCount(), key)));
        Tuple tuple = queueOfAddresses.poll();
        if (tuple == null) return null;
        BackendInstance instance = instances.get(tuple.ipAddress);
        instance.incrementConnectionCount();
        queueOfAddresses.add(new Tuple( instance.getConnectionCount(), tuple.ipAddress));
        System.out.println(queueOfAddresses);
        return instances.get(tuple.ipAddress);
    }
}
