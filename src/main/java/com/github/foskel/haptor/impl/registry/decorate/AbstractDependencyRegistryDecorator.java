package com.github.foskel.haptor.impl.registry.decorate;

import com.github.foskel.haptor.scan.DependencyScanningStrategy;
import com.github.foskel.haptor.registry.DependencyRegistry;

import java.util.Map;

public abstract class AbstractDependencyRegistryDecorator<S, I, D> implements DependencyRegistry<S, I, D> {
    final DependencyRegistry<S, I, D> backingRegistry;

    AbstractDependencyRegistryDecorator(DependencyRegistry<S, I, D> backingRegistry) {
        this.backingRegistry = backingRegistry;
    }

    @Override
    public boolean register(S source, DependencyScanningStrategy<I, D> scanningStrategy) {
        return this.backingRegistry.register(source, scanningStrategy);
    }

    @Override
    public boolean registerDirectly(I identifier, D dependency) {
        return this.backingRegistry.registerDirectly(identifier, dependency);
    }

    @Override
    public boolean unregister(S source) {
        return this.backingRegistry.unregister(source);
    }

    @Override
    public boolean unregisterDirectly(I identifier) {
        return this.backingRegistry.unregisterDirectly(identifier);
    }

    @Override
    public boolean has(I identifier) {
        return this.backingRegistry.has(identifier);
    }

    @Override
    public boolean hasDependencies() {
        return this.backingRegistry.hasDependencies();
    }

    @Override
    public Map<I, D> findAllDependencies() {
        return this.backingRegistry.findAllDependencies();
    }

    @Override
    public void clearDependencies() {
        this.backingRegistry.clearDependencies();
    }
}