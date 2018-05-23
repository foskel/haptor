package com.github.foskel.haptor.registry;

import com.github.foskel.haptor.scan.UnsatisfiedDependencyScanner;

import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by Fred on 5/28/2017.
 */
public interface DependencyRegistry<I, D> {
    boolean register(Object source, UnsatisfiedDependencyScanner<I> scanningStrategy);

    boolean registerDirectly(I identifier, D dependency);

    boolean unregister(Object source);

    boolean unregisterDirectly(I identifier);

    boolean unregisterIf(Predicate<I> condition);

    boolean has(I identifier);

    Map<I, D> findAllDependencies();

    void clear();
}