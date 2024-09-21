package org.example;

import org.example.strategy.RoundRobinSelectionStrategy;
import org.example.strategy.SelectionStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LoadBalancerTest {
    LoadBalancer loadBalancer;

    @BeforeEach
    void preProcess() {
        SelectionStrategy strategy=new RoundRobinSelectionStrategy();
        loadBalancer=new LoadBalancer(strategy);
        String backendInstanceIp = "255.4.34.89";
        loadBalancer.register(new BackendInstance(backendInstanceIp));
    }

    @Test
    void register() {
        String backendInstanceIp = "255.4.34.88";
        loadBalancer.register(new BackendInstance(backendInstanceIp));
        assertTrue(loadBalancer.instances.containsKey(backendInstanceIp));
    }

    @Test
    void getBackendInstance() {
        BackendInstance backendInstance = loadBalancer.getBackendInstance();
        assertEquals("255.4.34.89", backendInstance.getIpAddress());
    }
}