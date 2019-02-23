package com.github.foskel.haptor.satisfy;

import com.github.foskel.haptor.process.DependencyProcessor;
import com.github.foskel.haptor.registry.DependencyRegistry;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Created by Fred on 5/28/2017.
 */
public interface DependencySatisfyingStrategy {
    <I, D> void satisfy(DependencyRegistry<I, D> registry, Function<I, D> depSupplier, Collection<DependencyProcessor> processors) throws UnsatisfiedDependencyException;
}