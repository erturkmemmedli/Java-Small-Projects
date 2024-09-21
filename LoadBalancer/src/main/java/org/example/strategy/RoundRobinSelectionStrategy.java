package org.example.strategy;

import org.example.BackendInstance;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinSelectionStrategy implements SelectionStrategy {
    AtomicInteger counter = new AtomicInteger(0);
    @Override
    public BackendInstance select(Map<String, BackendInstance> instances) {
        List<String> listOfAddresses = instances.keySet().stream().toList();
        if (listOfAddresses.isEmpty()) return null;
        int index = counter.getAndIncrement() % listOfAddresses.size();
        String ipAddress = listOfAddresses.get(index);
        BackendInstance instance = instances.get(ipAddress);
        if (instance != null) {
            instance.incrementConnectionCount();
            System.out.println(instance);
        }
        return instance;
    }
}
