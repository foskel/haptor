package com.github.foskel.haptor;

import com.github.foskel.haptor.process.DependencyProcessor;
import com.github.foskel.haptor.registry.DependencyRegistry;
import com.github.foskel.haptor.satisfy.DependencySatisfyingStrategy;
import com.github.foskel.haptor.satisfy.UnsatisfiedDependencyException;
import com.github.foskel.haptor.scan.UnsatisfiedDependencyScanner;

import java.util.*;
import java.util.function.Function;

public final class StandardDependencySystem<I, D> implements DependencySystem<I, D> {
    private final DependencyRegistry<I, D> registry;
    private final Set<DependencyProcessor> satisfyingProcessors;
    private final DependencySatisfyingStrategy satisfyingStrategy;
    private Function<Object[], ? extends D> customLocator;

    public StandardDependencySystem(DependencyRegistry<I, D> registry, DependencySatisfyingStrategy satisfyingStrategy) {
        this.registry = registry;
        this.satisfyingProcessors = new HashSet<>();
        this.satisfyingStrategy = satisfyingStrategy;
    }

    @Override
    public boolean register(Object source, UnsatisfiedDependencyScanner scanner) {
        return this.registry.register(source, scanner);
    }

    @Override
    public boolean unregister(Object source) {
        return this.registry.unregister(source);
    }

    @Override
    public boolean registerProcessor(DependencyProcessor processor) {
        return this.satisfyingProcessors.add(processor);
    }

    @Override
    public boolean unregisterProcessor(DependencyProcessor processor) {
        return this.satisfyingProcessors.remove(processor);
    }

    @Override
    public void satisfy(Function<I, D> depSupplier) throws UnsatisfiedDependencyException {
        this.satisfyingStrategy.satisfy(this.registry, depSupplier, this.satisfyingProcessors);
    }

    @Override
    public DependencyRegistry<I, D> getRegistry() {
        return this.registry;
    }

    @Override
    public <T extends D> T find(Object... args) {
        if (customLocator == null) {
            return null;
        }
        
        //noinspection unchecked
        return (T) customLocator.apply(args);
    }

    @Override
    public <T extends D> T find(I identifier) {
        DependencyRef<I, D> ref = this.registry.getAllDependencies().get(identifier);

        if (ref == null) {
            return null;
        }

        //noinspection unchecked
        return (T) ref.getValue();
    }

    @Override
    public void setCustomLocator(Function<Object[], ? extends D> customLocator) {
        this.customLocator = customLocator;
    }
}