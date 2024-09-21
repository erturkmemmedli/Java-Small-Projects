package org.example.strategy;

import org.example.BackendInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class RandomSelectionStrategyTest {
    SelectionStrategy strategy;

    @BeforeEach
    public void beforeEach() {
        strategy = new RandomSelectionStrategy();
    }

    @Test
    void select() {
        Map<String, BackendInstance> instances = new ConcurrentHashMap<>();
        instances.put("1.1.1.1", new BackendInstance("1.1.1.1", 0));

        assertEquals("1.1.1.1", strategy.select(instances).getIpAddress());
    }
}