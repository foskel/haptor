package com.github.foskel.haptor.registry;

import com.github.foskel.haptor.scan.UnsatisfiedDependencyScanner;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by Fred on 5/28/2017.
 */
public final class ClassDependencyRegistry<D> implements DependencyRegistry<Class<? extends D>, D> {
    private final Map<Object, Class<? extends D>> dependencyHolders = new HashMap<>();
    private final Map<Class<? extends D>, D> dependencies = new HashMap<>();

    @Override
    public boolean register(Object source, UnsatisfiedDependencyScanner<Class<? extends D>> scanningStrategy) {
        Collection<Class<? extends D>> foundDependencies = scanningStrategy.scan(source);

        if (foundDependencies.isEmpty()) {
            return false;
        }

        this.registerAllDependencies(source, foundDependencies);

        return true;
    }

    private void registerAllDependencies(Object source, Collection<Class<? extends D>> scanResults) {
        scanResults.forEach(identifier -> {
            this.dependencies.put(identifier, null);//The dependency is not satisfied yet, so it's null.
            this.dependencyHolders.put(source, identifier);
        });
    }

    @Override
    public boolean registerDirectly(Class<? extends D> identifier, D value) {
        this.dependencies.put(identifier, value);

        return true;
    }

    @Override
    public boolean unregister(Object source) {
        Class<? extends D> identifier = this.dependencyHolders.get(source);

        this.dependencies.remove(identifier);

        return true;
    }

    @Override
    public boolean unregisterDirectly(Class<? extends D> identifier) {
        this.dependencies.remove(identifier);

        return true;
    }

    @Override
    public boolean unregisterIf(Predicate<Class<? extends D>> condition) {
        return this.dependencies.keySet().removeIf(condition);
    }

    @Override
    public boolean has(Class<? extends D> identifier) {
        return this.dependencies.containsKey(identifier);
    }

    @Override
    public Map<Class<? extends D>, D> findAllDependencies() {
        return Collections.unmodifiableMap(this.dependencies);
    }

    @Override
    public void clear() {
        this.dependencies.clear();
    }
}