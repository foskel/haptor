package com.github.idkp.haptor.registry;

import com.github.idkp.haptor.DependencyRef;
import com.github.idkp.haptor.scan.UnsatisfiedDependencyScanner;

import java.util.Map;
import java.util.function.Predicate;

/**
 * 5/28/2017
 */
public interface DependencyRegistry<I, D> {
    boolean register(Object source, UnsatisfiedDependencyScanner scanningStrategy);

    boolean registerDirectly(I identifier, D dependency);

    boolean unregister(Object source);

    boolean unregisterDirectly(I identifier);

    boolean unregisterIf(Predicate<I> condition);

    boolean has(I identifier);

    Map<I, DependencyRef<I, D>> getAllDependencies();

    void clear();
}