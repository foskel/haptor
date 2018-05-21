package com.github.foskel.haptor.impl.registry;

import com.github.foskel.haptor.scan.DependencyScanResult;
import com.github.foskel.haptor.scan.DependencyScanningStrategy;
import com.github.foskel.haptor.registry.DependencyRegistry;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fred on 5/28/2017.
 */
public final class ClassDependencyRegistry<D> implements DependencyRegistry<Object, Class<? extends D>, D> {
    private final Map<Object, Class<? extends D>> dependencyHolders = new HashMap<>();
    private final Map<Class<? extends D>, D> dependencies = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public boolean register(Object source, DependencyScanningStrategy<Class<? extends D>, D> scanningStrategy) {
        List<DependencyScanResult<Class<? extends D>, D>> foundDependencies = scanningStrategy.scan(source);

        if (foundDependencies.isEmpty()) {
            return false;
        }

        this.registerAllDependencies(source, foundDependencies);

        return true;
    }

    @SuppressWarnings("unchecked")
    private void registerAllDependencies(Object source, List<DependencyScanResult<Class<? extends D>, D>> scanResults) {
        scanResults.forEach(scanResult -> {
            Class<? extends D> identifier = scanResult.getDependencyIdentifier();
            D dependency = scanResult.getDependency();

            this.dependencies.put(identifier, dependency);
            this.dependencyHolders.put(source, identifier);
        });
    }

    @Override
    public boolean registerDirectly(Class<? extends D> identifier, D value) {
        this.dependencies.put(identifier, value);

        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean unregister(Object source) {
        Class<? extends D> identifier = this.dependencyHolders.get(source);

        return this.dependencies.remove(identifier) != null;
    }

    @Override
    public boolean unregisterDirectly(Class<? extends D> identifier) {
        if (this.dependencies.containsKey(identifier)) {
            this.dependencies.remove(identifier);

            return true;
        }

        return false;
    }

    @Override
    public boolean has(Class<? extends D> identifier) {
        return this.dependencies.containsKey(identifier);
    }

    @Override
    public boolean hasDependencies() {
        return !this.dependencies.isEmpty();
    }

    @Override
    public Map<Class<? extends D>, D> findAllDependencies() {
        return Collections.unmodifiableMap(this.dependencies);
    }

    @Override
    public void clearDependencies() {
        this.dependencies.clear();
    }
}