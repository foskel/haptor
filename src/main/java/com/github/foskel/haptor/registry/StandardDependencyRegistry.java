package com.github.foskel.haptor.registry;

import com.github.foskel.haptor.scan.UnsatisfiedDependencyScanner;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by Fred on 5/28/2017.
 */
public final class StandardDependencyRegistry<I, D> implements DependencyRegistry<I, D> {
    private final Map<Object, I> dependencyHolders = new HashMap<>();
    private final Map<I, D> dependencies = new HashMap<>();

    @Override
    public boolean register(Object source, UnsatisfiedDependencyScanner<I> scanningStrategy) {
        Collection<I> foundDependencies = scanningStrategy.scan(source);

        if (foundDependencies.isEmpty()) {
            return false;
        }

        this.registerAllDependencies(source, foundDependencies);

        return true;
    }

    private void registerAllDependencies(Object source, Collection<I> scanResults) {
        scanResults.forEach(identifier -> {
            this.dependencies.put(identifier, null);//The dependency is not satisfied yet, so it's null.
            this.dependencyHolders.put(source, identifier);
        });
    }

    @Override
    public boolean registerDirectly(I identifier, D value) {
        this.dependencies.put(identifier, value);

        return true;
    }

    @Override
    public boolean unregister(Object source) {
        I identifier = this.dependencyHolders.get(source);

        this.dependencies.remove(identifier);

        return true;
    }

    @Override
    public boolean unregisterDirectly(I identifier) {
        this.dependencies.remove(identifier);

        return true;
    }

    @Override
    public boolean unregisterIf(Predicate<I> condition) {
        return this.dependencies.keySet().removeIf(condition);
    }

    @Override
    public boolean has(I identifier) {
        return this.dependencies.containsKey(identifier);
    }

    @Override
    public Map<I, D> findAllDependencies() {
        return Collections.unmodifiableMap(this.dependencies);
    }

    @Override
    public void clear() {
        this.dependencies.clear();
    }
}