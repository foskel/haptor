package com.github.idkp.haptor.registry;

import com.github.idkp.haptor.DependencyRef;
import com.github.idkp.haptor.scan.UnsatisfiedDependencyScanner;

import java.util.*;
import java.util.function.Predicate;

/**
 * 5/28/2017
 */
public final class StandardDependencyRegistry<I, D> implements DependencyRegistry<I, D> {
    private final Map<Object, Set<DependencyRef<I, D>>> dependencyHolders = new HashMap<>();
    private final Map<I, DependencyRef<I, D>> dependencies = new HashMap<>();

    @Override
    public boolean register(Object source, UnsatisfiedDependencyScanner scanningStrategy) {
        //noinspection unchecked
        Collection<I> unsatisfiedIds = (Collection<I>) scanningStrategy.scan(source);

        if (unsatisfiedIds.isEmpty()) {
            return false;
        }

        Set<DependencyRef<I, D>> dependencyRefs = new HashSet<>();

        for (I identifier : unsatisfiedIds) {
            DependencyRef<I, D> ref = new DependencyRef<>(identifier);

            dependencyRefs.add(ref);
            this.dependencies.put(identifier, ref);
        }

        this.dependencyHolders.put(source, dependencyRefs);

        return true;
    }

    @Override
    public boolean registerDirectly(I identifier, D value) {
        DependencyRef<I, D> result = this.dependencies.get(identifier);

        if (result== null) {
            result = new DependencyRef<>(identifier);
        }

        result.setValue(value);
        this.dependencies.put(identifier, result);

        return true;
    }

    @Override
    public boolean unregister(Object source) {
        Set<DependencyRef<I, D>> identifier = this.dependencyHolders.get(source);

        if (identifier ==null) {
            return false;
        }

        this.dependencies.values().removeAll(identifier);
        this.dependencyHolders.remove(source);

        return true;
    }

    @Override
    public boolean unregisterDirectly(I identifier) {
        return this.dependencies.remove(identifier) != null;
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
    public Map<I, DependencyRef<I, D>> getAllDependencies() {
        return Collections.unmodifiableMap(this.dependencies);
    }

    @Override
    public void clear() {
        this.dependencies.clear();
        this.dependencyHolders.clear();
    }
}