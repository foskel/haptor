package com.github.foskel.haptor.impl;

import com.github.foskel.haptor.impl.registry.ClassDependencyRegistry;
import com.github.foskel.haptor.impl.registry.decorate.ImmutableDependencyRegistryDecorator;
import com.github.foskel.haptor.impl.satisfy.StandardDependencySatisfyingStrategy;
import com.github.foskel.haptor.impl.scan.AnnotationDependencyScanningStrategy;
import com.github.foskel.haptor.impl.validate.NullCheckingDependencyValidatorService;
import com.github.foskel.haptor.process.DependencySatisfyingProcessor;
import com.github.foskel.haptor.DependencySystem;
import com.github.foskel.haptor.registry.DependencyRegistry;
import com.github.foskel.haptor.satisfy.DependencySatisfyingResult;
import com.github.foskel.haptor.satisfy.DependencySatisfyingStrategy;

import java.util.*;

public final class ClassDependencySystem<D> implements DependencySystem<Object, Class<? extends D>, D> {
    private final DependencyRegistry<Object, Class<? extends D>, D> registry;
    private final Set<DependencySatisfyingProcessor> satisfyingProcessors;
    private final DependencySatisfyingStrategy satisfyingStrategy;

    public ClassDependencySystem() {
        this(new StandardDependencySatisfyingStrategy(NullCheckingDependencyValidatorService.INSTANCE));
    }

    public ClassDependencySystem(DependencySatisfyingStrategy satisfyingStrategy) {
        this.registry = new ClassDependencyRegistry<>();
        this.satisfyingProcessors = new HashSet<>();
        this.satisfyingStrategy = satisfyingStrategy;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean registerDependencies(Object source) {
        return this.registry.register(source, AnnotationDependencyScanningStrategy.INSTANCE);
    }

    @Override
    public boolean unregisterDependencies(Object source) {
        return this.registry.unregister(source);
    }

    @Override
    public boolean registerProcessor(DependencySatisfyingProcessor processor) {
        return this.satisfyingProcessors.add(processor);
    }

    @Override
    public boolean unregisterProcessor(DependencySatisfyingProcessor processor) {
        return this.satisfyingProcessors.remove(processor);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends D> T find(Class<? extends D> identifier) {
        return (T) this.registry.findAllDependencies().get(identifier);
    }

    @Override
    public Map<Class<? extends D>, D> findAllDependencies() {
        return Collections.unmodifiableMap(this.registry.findAllDependencies());
    }

    @Override
    public void clearDependencies() {
        this.registry.clearDependencies();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DependencySatisfyingResult> satisfy(Map<Class<? extends D>, D> dependencies) {
        return this.satisfyingStrategy.satisfy(this.registry,
                this.satisfyingProcessors,
                (Map<Object, Object>) (Map<?, ?>) dependencies);
    }

    @Override
    public DependencyRegistry<Object, Class<? extends D>, D> getDependencyRegistry() {
        return new ImmutableDependencyRegistryDecorator<>(this.registry);
    }
}