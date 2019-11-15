package com.github.idkp.haptor.satisfy;

import com.github.idkp.haptor.process.DependencyProcessor;
import com.github.idkp.haptor.registry.DependencyRegistry;

import java.util.Collection;
import java.util.function.Function;

/**
 * 5/28/2017
 */
public interface DependencySatisfyingStrategy {
    <I, D> void satisfy(DependencyRegistry<I, D> registry, Function<I, D> depSupplier, Collection<DependencyProcessor> processors) throws UnsatisfiedDependencyException;
}