package org.example.strategy;

import org.example.BackendInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RoundRobinSelectionStrategyTest {
    SelectionStrategy strategy;

    @BeforeEach
    public void beforeEach() {
        strategy = new RoundRobinSelectionStrategy();
    }

    @Test
    void select() {
        Map<String, BackendInstance> instances = new ConcurrentHashMap<>();
        instances.put("1.1.1.1", new BackendInstance("1.1.1.1", 0));
        instances.put("1.1.1.2", new BackendInstance("1.1.1.2", 0));
        instances.put("1.1.1.3", new BackendInstance("1.1.1.3", 0));

        List<String> selections = new ArrayList<>();
        IntStream.range(0, 9).forEach(val -> selections.add(strategy.select(instances).getIpAddress()));

        HashMap<String, Integer> counterMap = new HashMap<>();
        selections.forEach(ip -> counterMap.put(ip, counterMap.getOrDefault(ip, 0) + 1));
        assertEquals(1, new HashSet<>(counterMap.values()).size());
    }
}