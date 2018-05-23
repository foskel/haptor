package com.github.foskel.haptor.registry.decorate;

import com.github.foskel.haptor.registry.DependencyRegistry;
import com.github.foskel.haptor.scan.UnsatisfiedDependencyScanner;

import java.util.Collections;
import java.util.Map;
import java.util.function.Predicate;

public final class ImmutableDependencyRegistryDecorator<I, D> implements DependencyRegistry<I, D> {
    private final DependencyRegistry<I, D> backingRegistry;

    public ImmutableDependencyRegistryDecorator(DependencyRegistry<I, D> backingRegistry) {
        this.backingRegistry = backingRegistry;
    }

    @Override
    public boolean register(Object source, UnsatisfiedDependencyScanner<I> scanningStrategy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean registerDirectly(I identifier, D dependency) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean unregister(Object source) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean unregisterDirectly(I identifier) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean unregisterIf(Predicate<I> condition) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean has(I identifier) {
        return this.backingRegistry.has(identifier);
    }

    @Override
    public Map<I, D> findAllDependencies() {
        return Collections.unmodifiableMap(this.backingRegistry.findAllDependencies());
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}