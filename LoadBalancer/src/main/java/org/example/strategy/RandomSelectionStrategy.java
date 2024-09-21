package org.example.strategy;

import org.example.BackendInstance;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSelectionStrategy implements SelectionStrategy {
    @Override
    public BackendInstance select(Map<String, BackendInstance> instances) {
        List<String> listOfInstances = instances.keySet().stream().toList();
        if (listOfInstances.isEmpty()) return null;
        int index = ThreadLocalRandom.current().nextInt(listOfInstances.size());
        String ipAddress = listOfInstances.get(index);
        BackendInstance instance = instances.get(ipAddress);
        if (instance != null) {
            instance.incrementConnectionCount();
            System.out.println(instance);
        }
        return instance;
    }
}
