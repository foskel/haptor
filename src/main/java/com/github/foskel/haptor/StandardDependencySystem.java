package com.github.foskel.haptor;

import com.github.foskel.haptor.process.DependencyProcessor;
import com.github.foskel.haptor.registry.DependencyRegistry;
import com.github.foskel.haptor.satisfy.DependencySatisfyingResult;
import com.github.foskel.haptor.satisfy.DependencySatisfyingStrategy;
import com.github.foskel.haptor.scan.UnsatisfiedDependencyScanner;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class StandardDependencySystem<S, I, D> implements DependencySystem<S, I, D> {
    private final DependencyRegistry<I, D> registry;
    private final Set<DependencyProcessor> satisfyingProcessors;
    private final DependencySatisfyingStrategy satisfyingStrategy;
    private final UnsatisfiedDependencyScanner<I> unsatisfiedScanner;

    public StandardDependencySystem(DependencyRegistry<I, D> registry,
                                    DependencySatisfyingStrategy satisfyingStrategy,
                                    UnsatisfiedDependencyScanner<I> unsatisfiedScanner) {
        this.registry = registry;
        this.unsatisfiedScanner = unsatisfiedScanner;
        this.satisfyingProcessors = new HashSet<>();
        this.satisfyingStrategy = satisfyingStrategy;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean register(S source) {
        return this.registry.register(source, this.unsatisfiedScanner);
    }

    @Override
    public boolean unregister(S source) {
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
    @SuppressWarnings("unchecked")
    public <T extends D> T find(I identifier) {
        return (T) this.registry.findAllDependencies().get(identifier);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DependencySatisfyingResult<I, D>> satisfy(Map<I, D> dependencies) {
        return this.satisfyingStrategy.satisfy(this.registry,
                this.satisfyingProcessors,
                dependencies);
    }

    @Override
    public DependencyRegistry<I, D> getRegistry() {
        return this.registry;
    }
}