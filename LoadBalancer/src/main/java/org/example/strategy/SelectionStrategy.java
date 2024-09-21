package org.example.strategy;

import org.example.BackendInstance;

import java.util.Map;
import java.util.Optional;

public interface SelectionStrategy {
    BackendInstance select(Map<String, BackendInstance> instances);
}
