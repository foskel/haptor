package com.github.foskel.haptor.impl.registry.decorate;

import com.github.foskel.haptor.scan.DependencyScanningStrategy;
import com.github.foskel.haptor.registry.DependencyRegistry;

import java.util.Collections;
import java.util.Map;

public final class ImmutableDependencyRegistryDecorator<S, I, D> extends AbstractDependencyRegistryDecorator<S, I, D> {
    public ImmutableDependencyRegistryDecorator(DependencyRegistry<S, I, D> backingDecoratedRegistry) {
        super(backingDecoratedRegistry);
    }

    @Override
    public boolean register(S source, DependencyScanningStrategy<I> scanningStrategy) {
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
    public Map<I, D> findAllDependencies() {
        return Collections.unmodifiableMap(this.backingRegistry.findAllDependencies());
    }
}