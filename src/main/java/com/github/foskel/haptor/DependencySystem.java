package com.github.foskel.haptor;

import com.github.foskel.haptor.process.DependencySatisfyingProcessor;
import com.github.foskel.haptor.registry.DependencyRegistry;
import com.github.foskel.haptor.satisfy.DependencySatisfyingResult;

import java.util.List;
import java.util.Map;

public interface DependencySystem<S, I, D> {
    boolean registerDependencies(S source);

    boolean unregisterDependencies(S source);

    boolean registerProcessor(DependencySatisfyingProcessor processor);

    boolean unregisterProcessor(DependencySatisfyingProcessor processor);

    <T extends D> T find(I identifier);

    Map<I, D> findAllDependencies();

    void clearDependencies();

    List<DependencySatisfyingResult> satisfy(Map<I, D> dependencies);

    DependencyRegistry<S, I, D> getDependencyRegistry();
}