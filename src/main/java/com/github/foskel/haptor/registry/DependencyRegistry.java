package com.github.foskel.haptor.registry;

import com.github.foskel.haptor.DependencyRef;
import com.github.foskel.haptor.scan.UnsatisfiedDependencyScanner;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by Fred on 5/28/2017.
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