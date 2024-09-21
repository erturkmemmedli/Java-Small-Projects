package org.example;

import org.example.exception.MaximumCapacityExceededException;
import org.example.strategy.SelectionStrategy;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LoadBalancer {
    public Map<String, BackendInstance> instances = new ConcurrentHashMap<>();
    private final int MAX_CAPACITY = 10;
    private SelectionStrategy selectionStrategy;

    public LoadBalancer(SelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public void register(BackendInstance instance) {
        if (instance == null) throw new IllegalArgumentException("Null instance");
        if (instance.getIpAddress() == null) throw new IllegalArgumentException("Null ip address");
        if (instance.getIpAddress().isEmpty()) throw new IllegalArgumentException("Empty ip address");
        if (instances.size() > MAX_CAPACITY) throw new MaximumCapacityExceededException("Max capacity exceeded");

        instances.put(instance.getIpAddress(), instance);
    }

    public BackendInstance getBackendInstance() {
        return selectionStrategy.select(instances);
    }
}
