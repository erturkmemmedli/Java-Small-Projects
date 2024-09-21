package org.example;

import org.example.strategy.LeastConnectionSelectionStrategy;
import org.example.strategy.RandomSelectionStrategy;
import org.example.strategy.RoundRobinSelectionStrategy;

import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args) {
        LoadBalancer loadBalancer = new LoadBalancer(new LeastConnectionSelectionStrategy());
        loadBalancer.register(new BackendInstance("123.12.45.10",1));
        loadBalancer.register(new BackendInstance("123.12.45.11",3));
        loadBalancer.register(new BackendInstance("123.12.45.12",5));
//        loadBalancer.register(new BackendInstance("123.12.45.13"));
//        loadBalancer.register(new BackendInstance("123.12.45.14"));

        IntStream.range(0,10).forEach(value -> System.out.println(loadBalancer.getBackendInstance().getIpAddress()));
    }
}