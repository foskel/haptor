package us.subtle.haptor;

import us.subtle.haptor.process.DependencySatisfyingProcessor;
import us.subtle.haptor.registry.DependencyRegistry;
import us.subtle.haptor.satisfy.DependencySatisfyingResult;

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